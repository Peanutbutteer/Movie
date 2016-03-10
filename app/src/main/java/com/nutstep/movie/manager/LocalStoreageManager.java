package com.nutstep.movie.manager;
import android.content.Context;
import android.content.SharedPreferences;


public class LocalStoreageManager {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static LocalStoreageManager instance;

    public static LocalStoreageManager getInstance() {
        if (instance == null)
            instance = new LocalStoreageManager();
        return instance;
    }

    private Context mContext;

    private LocalStoreageManager() {
        mContext = Contextor.getInstance().getContext();
        sharedPreferences = mContext.getSharedPreferences("Data",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public SharedPreferences getSharedPreferences(){
        return this.sharedPreferences;
    }

    public SharedPreferences.Editor getEditor()
    {
        return this.editor;
    }

    public String getUid()
    {
        return this.sharedPreferences.getString("uid","");
    }
}
