package com.nutstep.movie.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.nutstep.movie.R;
import com.nutstep.movie.activity.MainActivity;
import com.nutstep.movie.dao.User;
import com.nutstep.movie.manager.LocalStoreageManager;
import com.nutstep.movie.utils.Utils;

import java.util.Map;


public class SignUpFragment extends Fragment {
    EditText editTextEmail,editTextPassword,editTextName;
    Button btnSignUp;
    Firebase ref = new Firebase(Utils.getInstance().getBaseUrl());
    public SignUpFragment() {
        super();
    }

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
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
        editTextName = (EditText) rootView.findViewById(R.id.edit_text_full_name);
        btnSignUp = (Button) rootView.findViewById(R.id.btn_sign_up);

        btnSignUp.setOnClickListener(onClickListener);
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
            if(v==btnSignUp)
            {
                final String textEmail = editTextEmail.getText().toString();
                String textPassword = editTextPassword.getText().toString();
                final String textName = editTextName.getText().toString();
                ref.createUser(textEmail, textPassword, new Firebase.ValueResultHandler<Map<String, Object>>() {
                    @Override
                    public void onSuccess(Map<String, Object> stringObjectMap) {
                        Toast.makeText(getContext(),"Sign Up Sucess",Toast.LENGTH_SHORT).show();
                        Firebase refUser = ref.child("users").child(stringObjectMap.get("uid").toString());
                        refUser.setValue(new User(textName,"BKK"));
                        Intent intent = new Intent(getContext(),MainActivity.class);
                        LocalStoreageManager.getInstance().getEditor().putString("uid",stringObjectMap.get("uid").toString()).putString("email",textEmail).apply();
                        startActivity(intent);
                        if(getActivity()!=null)
                        {
                            getActivity().finish();
                        }
                    }

                    @Override
                    public void onError(FirebaseError firebaseError) {
                        Toast.makeText(getContext(),firebaseError.getMessage(),Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }
    };

}
