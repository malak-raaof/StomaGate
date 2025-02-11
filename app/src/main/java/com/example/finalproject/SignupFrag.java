package com.example.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class SignupFrag extends Fragment {

    TextView txtLoginClick;
    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);
        txtLoginClick = view.findViewById(R.id.txtLoginClick);

        txtLoginClick.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_signupFrag_to_loginFrag);

        });
        return view;
    }
}