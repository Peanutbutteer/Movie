package com.nutstep.movie.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appyvet.rangebar.RangeBar;
import com.bumptech.glide.Glide;
import com.greenfrvr.hashtagview.HashtagView;
import com.nutstep.movie.R;
import com.nutstep.movie.dao.Intheater;
import com.nutstep.movie.dao.MovieIntheaterResult;
import com.nutstep.movie.manager.HttpManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RandomMovieFragment extends Fragment {

    private BottomSheetBehavior bottomSheetBehavior;
    private HashtagView genreTagView;
    private ImageView imagePoster;
    private TextView textBtnCancel, textBtnApply, textYearBe, textYearAf,textTitle,textYear;
    private RangeBar rangeBar;
    private ImageButton btnRefresh;
    private int yearGte=1990,yearLte=2016,page=1,maxPage=1;
    private String genre="";
    public RandomMovieFragment() {
        super();
    }

    public static RandomMovieFragment newInstance() {
        RandomMovieFragment fragment = new RandomMovieFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_random_movie, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        View view = rootView.findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(view);


        genreTagView = (HashtagView) rootView.findViewById(R.id.tag_movie_genre);
        textBtnApply = (TextView) rootView.findViewById(R.id.text_btn_apply);
        textBtnCancel = (TextView) rootView.findViewById(R.id.text_btn_cancel);
        rangeBar = (RangeBar) rootView.findViewById(R.id.rangebar);
        textYearBe = (TextView) rootView.findViewById(R.id.text_year_be);
        textYearAf = (TextView) rootView.findViewById(R.id.text_year_af);
        textTitle = (TextView) rootView.findViewById(R.id.text_title);
        textYear = (TextView) rootView.findViewById(R.id.text_year);
        imagePoster = (ImageView) rootView.findViewById(R.id.image_poster);
        btnRefresh = (ImageButton)rootView.findViewById(R.id.btn_refresh);


        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("max",maxPage+"");
                if(maxPage>1000) maxPage=1000;
                page = new Random().nextInt(maxPage);
                if(page<=0) page =1;
                renewRandom();
            }
        });
        textBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideBottomSheet();
            }
        });

        textBtnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideBottomSheet();
                page=1;
                renewRandom();
            }
        });

        rangeBar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                textYearAf.setText(rightPinValue);
                textYearBe.setText(leftPinValue);
                yearGte = Integer.parseInt(leftPinValue);
                yearLte = Integer.parseInt(rightPinValue);
            }
        });

        List<Genre> lists = new ArrayList<Genre>();
        lists.add(new Genre(28, "action"));
        lists.add(new Genre(878, "sci-fi"));
        lists.add(new Genre(12, "adventure"));
        lists.add(new Genre(16, "animation"));
        lists.add(new Genre(35, "comedy"));
        lists.add(new Genre(80, "crime"));
        lists.add(new Genre(99, "documentary"));
        lists.add(new Genre(18, "drama"));
        lists.add(new Genre(10751, "family"));
        lists.add(new Genre(14, "fantasy"));
        lists.add(new Genre(10769, "foreign"));
        lists.add(new Genre(36, "history"));
        lists.add(new Genre(27, "horror"));
        lists.add(new Genre(10402, "music"));
        lists.add(new Genre(9648, "mystery"));
        lists.add(new Genre(10749, "romance"));
        lists.add(new Genre(10770, "tv-movie"));
        lists.add(new Genre(53, "thriller"));
        lists.add(new Genre(10752, "war"));
        lists.add(new Genre(37, "western"));
        genreTagView.setData(lists, new HashtagView.DataStateTransform<Genre>() {
            @Override
            public CharSequence prepareSelected(Genre item) {
                return item.getName();
            }

            @Override
            public CharSequence prepare(Genre item) {
                String label = item.getName();
                SpannableString spannableString = new SpannableString(label);
                //spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#85F5F5F5")), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                //spannableString.setSpan(new SuperscriptSpan(), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return spannableString;
            }


        }, new HashtagView.DataSelector<Genre>() {
            @Override
            public boolean preselect(Genre item) {
                return false;
            }
        });

        genreTagView.addOnTagSelectListener(new HashtagView.TagsSelectListener() {
            @Override
            public void onItemSelected(Object item, boolean selected) {
                if (selected) {
                    Toast.makeText(getContext(), ((Genre) item).getName(), Toast.LENGTH_LONG).show();
                }
            }
        });

        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED)
                    bottomSheetBehavior.setPeekHeight(0);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
       // renewRandom();

    }

    private void hideBottomSheet() {
        bottomSheetBehavior.setPeekHeight(1);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
    }


    public void openBottomSheet() {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public void renewRandom()
    {
        addLoadingScreen();
        List<Object> list = genreTagView.getSelectedItems();
        genre = "";
        for(Object item : list)
        {
            if(item instanceof Genre)
            {
                Genre castItem = (Genre) item;
                genre+=castItem.getId()+"|";
            }
        }
        HttpManager.getInstance().getRandomMovie(yearGte+"-01-01",yearLte+"-12-31",page,genre).enqueue(new Callback<Intheater>() {
            @Override
            public void onResponse(Call<Intheater> call, Response<Intheater> response) {
                if(response.isSuccess())
                {
                    Random random = new Random();
                    if(response.body().getResults()!=null&&response.body().getResults().size()>0)
                    {
                        MovieIntheaterResult result = response.body().getResults().get(random.nextInt(response.body().getResults().size()));
                        textTitle.setText(result.getTitle());
                        textYear.setText(result.getReleaseDate().split("-")[0]);
                        Glide.with(getContext()).load("http://image.tmdb.org/t/p/w500/"+result.getPosterPath()).into(imagePoster);
                        maxPage = response.body().getTotalPages();
                    }
                    else {
                        Toast.makeText(getContext(),"Not Found",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    try {
                        Toast.makeText(getContext(),response.errorBody().string(),Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                removeLoadingScreen();

            }

            @Override
            public void onFailure(Call<Intheater> call, Throwable t) {
                Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
                removeLoadingScreen();
            }
        });
    }


    private class Genre {
        private int id;
        private String name;

        public Genre(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    LoadingScreenFragment loadingScreenFragment = LoadingScreenFragment.newInstance();

    public void removeLoadingScreen() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(loadingScreenFragment).commit();
    }

    public void addLoadingScreen() {
        getFragmentManager().beginTransaction().add(R.id.content_containner, loadingScreenFragment, "Loading").commit();
    }

}
