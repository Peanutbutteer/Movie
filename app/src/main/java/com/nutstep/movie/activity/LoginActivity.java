package com.nutstep.movie.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nutstep.movie.R;
import com.nutstep.movie.fragment.LoginFragment;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.content_containner,LoginFragment.newInstance()).commit();
        }
    }
}
