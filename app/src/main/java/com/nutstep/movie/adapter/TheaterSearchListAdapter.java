package com.nutstep.movie.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nutstep.movie.R;
import com.nutstep.movie.dao.Theater;
import com.nutstep.movie.dao.v2.Collection;
import com.nutstep.movie.manager.HttpManager;
import com.nutstep.movie.viewholder.TheaterSearchViewHolder;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Date;
import static android.R.id.list;

/**
 * Created by peanutbutteer on 5/20/2016 AD.
 */

public class TheaterSearchListAdapter extends RecyclerView.Adapter<TheaterSearchViewHolder> {
    private List<Theater> theaterList;
    Calendar now = Calendar.getInstance();

    @Override
    public TheaterSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutInflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theater_list,parent,false);
        return new TheaterSearchViewHolder(layoutInflater);
    }

    public void addTheater(List<Theater> list){
        theaterList = list;
    }


    @Override
    public void onBindViewHolder(TheaterSearchViewHolder holder, int position) {
        Theater theater = theaterList.get(position);
        holder.setTextTheaterName(theater.getThai());
        if(theater.getDistance()!=null) holder.setTextDistance(theater.getDistance());
        if(theater.getShowTime()!=null) {
            Collections.sort(theater.getShowTime().getShowtimes(), new Comparator<String>() {
                @Override
                public int compare(String lhs, String rhs) {
                    String[] sp1 = lhs.split(":");
                    String[] sp2 = rhs.split(":");
                    Calendar timeOne = Calendar.getInstance(),timeTwo = Calendar.getInstance();
                    timeOne.set(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DATE),Integer.parseInt(sp1[0]),Integer.parseInt(sp1[1]));
                    timeTwo.set(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DATE),Integer.parseInt(sp2[0]),Integer.parseInt(sp2[0]));
                    long t1 = Math.abs(now.getTimeInMillis() - timeOne.getTimeInMillis()),t2 = Math.abs(now.getTimeInMillis() - timeTwo.getTimeInMillis());
                    if(t1<t2) {
                        return -1;
                    }else if (t1==t2) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
            List<String> showtime = theater.getShowTime().getShowtimes();
            String show = "ไม่มีรอบในวันนี้แล้ว";
            for(int i=0;i<showtime.size();i++)
            {
                String[] sp = showtime.get(i).split(":");
                Calendar time = Calendar.getInstance();
                time.set(now.get(Calendar.YEAR),now.get(Calendar.MONTH),now.get(Calendar.DATE),Integer.parseInt(sp[0]),Integer.parseInt(sp[1]));
                if(time.getTimeInMillis()<now.getTimeInMillis()){
                    continue;
                }
                show = showtime.get(i);
                if(theater.getCode()=="9") Log.d("showTime",show);
                break;
            }
            holder.setTextShowtime(show);
        }
    }

    @Override
    public int getItemCount() {
        return (theaterList!=null)?theaterList.size():0;
    }
}
