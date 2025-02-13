package com.example.finalproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginFrag extends Fragment {

    TextInputEditText txtPasswordLogin,txtEmailLogin;

    TextInputLayout textFieldEmailLogin,textFieldPasswordLogin;

    TextView txtSignupClick;
    Button btnLogin;

    private FirebaseAuth myauth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_me, container, false);
        txtPasswordLogin = view.findViewById(R.id.txtPasswordLogin);
        txtEmailLogin = view.findViewById(R.id.txtEmailLogin);
        textFieldPasswordLogin = view.findViewById(R.id.textFieldPasswordLogin);
        textFieldEmailLogin = view.findViewById(R.id.textFieldEmailLogin);
        txtSignupClick = view.findViewById(R.id.txtSignupClick);
        btnLogin = view.findViewById(R.id.btnLogin);

        myauth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = myauth.getCurrentUser();



        txtSignupClick.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_loginFrag_to_signupFrag);

        });

        btnLogin.setOnClickListener(v -> {

            String password = txtPasswordLogin.getText().toString();
            String email = txtEmailLogin.getText().toString();

                boolean isPasswordEmpty = password.isEmpty();
                boolean isEmailEmpty = email.isEmpty();

                if (isPasswordEmpty || isEmailEmpty) {
                    textFieldPasswordLogin.setError(isPasswordEmpty ? "Field cannot be empty!" : null);
                    textFieldEmailLogin.setError(isEmailEmpty ? "Field cannot be empty!" : null);
                } else {
                    textFieldPasswordLogin.setError(null);
                    textFieldEmailLogin.setError(null);
                    logIn();
                }

        });


        return view;
    }

    private void logIn(){
        myauth.signInWithEmailAndPassword(txtEmailLogin.getText().toString(),txtPasswordLogin.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(),"Logged In successfully", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getActivity(),"Failed" + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}