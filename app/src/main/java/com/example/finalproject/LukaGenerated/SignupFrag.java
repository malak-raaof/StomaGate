package com.example.finalproject.LukaGenerated;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalproject.R;
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
    TextInputEditText txtPasswordSignup,txtConfirmPassword, txtEmailSignup;
    TextInputLayout textFieldPasswordSignUp,textFieldConfirmPassword, textFieldEmailSignup;
    private FirebaseAuth myauth;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_signup, container, false);
        txtLoginClick = view.findViewById(R.id.txtLoginClick);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        txtPasswordSignup = view.findViewById(R.id.txtPasswordSignup);
        txtConfirmPassword = view.findViewById(R.id.txtConfirmPassword);
        textFieldConfirmPassword = view.findViewById(R.id.textFieldConfirmPassword);
        textFieldPasswordSignUp = view.findViewById(R.id.textFieldPasswordSignUp);
        txtEmailSignup = view.findViewById(R.id.txtEmailSignup);
        textFieldEmailSignup = view.findViewById(R.id.textFieldEmailSignup);

        myauth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = myauth.getCurrentUser();



        txtLoginClick.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_signupFrag_to_loginFrag);

        });

        btnSignUp.setOnClickListener(v -> {

            String password = txtPasswordSignup.getText().toString();
            String confirmPassword = txtConfirmPassword.getText().toString();
            String email = txtEmailSignup.getText().toString();

            if (email.isEmpty()) {
                textFieldEmailSignup.setError("Field cannot be empty!");
            } else {
                textFieldEmailSignup.setError(null);

                boolean isPasswordEmpty = password.isEmpty();
                boolean isConfirmPasswordEmpty = confirmPassword.isEmpty();

                if (isPasswordEmpty || isConfirmPasswordEmpty) {
                    textFieldPasswordSignUp.setError(isPasswordEmpty ? "Field cannot be empty!" : null);
                    textFieldConfirmPassword.setError(isConfirmPasswordEmpty ? "Field cannot be empty!" : null);
                } else if (!password.equals(confirmPassword)) {
                    textFieldConfirmPassword.setError("Passwords do not match!");
                    textFieldPasswordSignUp.setError(null);
                } else {
                    textFieldPasswordSignUp.setError(null);
                    textFieldConfirmPassword.setError(null);
                    signUp();
                }
            }




        });



        return view;
    }

    private void signUp(){

        myauth.createUserWithEmailAndPassword(txtEmailSignup.getText().toString(), txtPasswordSignup.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getActivity(),"Signed Up successfully", Toast.LENGTH_LONG).show();
                        Navigation.findNavController(view).navigate(R.id.action_signupFrag_to_homeFragment);
                    }
                })       .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(),"Failed" + e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                }) ;

    }

    private void logOut(){
        myauth.signOut();
    }


}