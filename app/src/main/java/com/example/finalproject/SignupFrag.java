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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupFrag extends Fragment {

    TextView txtLoginClick;
    Button btnSignUp;
    TextInputEditText txtPassword,txtConfirmPassword,txtEmail;
    TextInputLayout textFieldPassword,textFieldConfirmPassword,textFieldEmail;
    private FirebaseAuth myauth;

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
        btnSignUp = view.findViewById(R.id.btnSignUp);
        txtPassword = view.findViewById(R.id.txtPassword);
        txtConfirmPassword = view.findViewById(R.id.txtConfirmPassword);
        textFieldConfirmPassword = view.findViewById(R.id.textFieldConfirmPassword);
        textFieldPassword = view.findViewById(R.id.textFieldPassword);
        txtEmail = view.findViewById(R.id.txtEmail);
        textFieldEmail = view.findViewById(R.id.textFieldEmail);

        myauth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = myauth.getCurrentUser();

        String password = txtPassword.getText().toString();
        String confirmPassword = txtConfirmPassword.getText().toString();
        String email = txtEmail.getText().toString();

        txtLoginClick.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_signupFrag_to_loginFrag);

        });

        btnSignUp.setOnClickListener(v -> {

            if (email.isEmpty()) {
                textFieldEmail.setError("Field cannot be empty!");
            } else {
                textFieldEmail.setError(null);

                boolean isPasswordEmpty = password.isEmpty();
                boolean isConfirmPasswordEmpty = confirmPassword.isEmpty();

                if (isPasswordEmpty || isConfirmPasswordEmpty) {
                    textFieldPassword.setError(isPasswordEmpty ? "Field cannot be empty!" : null);
                    textFieldConfirmPassword.setError(isConfirmPasswordEmpty ? "Field cannot be empty!" : null);
                } else if (!password.equals(confirmPassword)) {
                    textFieldConfirmPassword.setError("Passwords do not match!");
                    textFieldPassword.setError(null);
                } else {
                    textFieldPassword.setError(null);
                    textFieldConfirmPassword.setError(null);
                    signUp();
                }
            }




        });



        return view;
    }

    private void signUp(){

        myauth.createUserWithEmailAndPassword(txtEmail.getText().toString(),txtPassword.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getActivity(),"Signed Up successfully", Toast.LENGTH_LONG).show();
                    }
                })       .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),"Failed" + e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }) ;

    }


}