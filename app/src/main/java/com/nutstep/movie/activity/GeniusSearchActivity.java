package com.nutstep.movie.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.nutstep.movie.R;
import com.nutstep.movie.fragment.MainFragment;
import com.nutstep.movie.fragment.SearchMoiveListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GeniusSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genius_search);
        ButterKnife.bind(this);

        initiInstance();

        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.content_containner, SearchMoiveListFragment.newInstance()).commit();
        }
    }

    private void initiInstance() {

    }
}
