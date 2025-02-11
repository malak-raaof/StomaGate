package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginFrag extends Fragment {

    TextInputEditText txtPassword;

    TextInputLayout textField2;

    TextView txtSignupClick;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_me, container, false);
        txtPassword = view.findViewById(R.id.txtPassword);
        textField2 = view.findViewById(R.id.textField2);
        txtSignupClick = view.findViewById(R.id.txtSignupClick);

        txtSignupClick.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFrag_to_signupFrag);

        });


        return view;
    }
}