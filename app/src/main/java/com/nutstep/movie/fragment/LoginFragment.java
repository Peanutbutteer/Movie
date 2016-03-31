package com.nutstep.movie.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nutstep.movie.R;
import com.nutstep.movie.activity.MainActivity;
import com.nutstep.movie.manager.LocalStoreageManager;
import com.nutstep.movie.utils.Utils;


public class LoginFragment extends Fragment {
    EditText editTextEmail,editTextPassword;
    Button btnLogin;
    TextView textSignUp;
    public LoginFragment() {
        super();
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);
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

        editTextEmail = (EditText) rootView.findViewById(R.id.edit_text_emil);
        editTextPassword = (EditText) rootView.findViewById(R.id.edit_text_password);
        btnLogin   = (Button) rootView.findViewById(R.id.btn_login);
        textSignUp = (TextView) rootView.findViewById(R.id.text_sign_up);
        btnLogin.setOnClickListener(onClickListener);
        textSignUp.setOnClickListener(onClickListener);
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


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v==btnLogin)
            {
                String passwordText = editTextPassword.getText().toString();
                final String emailText = editTextEmail.getText().toString();

//                ref.authWithPassword(emailText, passwordText, new Firebase.AuthResultHandler() {
//                    @Override
//                    public void onAuthenticated(AuthData authData) {
//                        Log.d("Login","User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
//                        Intent intent = new Intent(getContext(),MainActivity.class);
//                        LocalStoreageManager.getInstance().getEditor().putString("uid",authData.getUid()).putString("email",emailText).apply();
//                        startActivity(intent);
//                        if(getActivity()!=null)
//                        {
//                            getActivity().finish();
//                        }
//                    }
//
//                    @Override
//                    public void onAuthenticationError(FirebaseError firebaseError) {
//                        Toast.makeText(getContext(),firebaseError.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
            if(v==textSignUp)
            {
                getFragmentManager().beginTransaction().replace(R.id.content_containner,SignUpFragment.newInstance()).addToBackStack(null).commit();
            }
        }
    };

}
