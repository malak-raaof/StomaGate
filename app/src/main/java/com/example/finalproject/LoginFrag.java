package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;


public class LoginFrag extends Fragment {

    TextInputEditText txtPasswordLogin, txtEmailLogin;

    TextInputLayout textFieldEmailLogin, textFieldPasswordLogin;

    TextView txtSignupClick;
    Button btnLogin, btnGoogle;

    private FirebaseAuth myauth;
    private GoogleSignInClient mygoogle;
    private View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login_me, container, false);
        txtPasswordLogin = view.findViewById(R.id.txtPasswordLogin);
        txtEmailLogin = view.findViewById(R.id.txtEmailLogin);
        textFieldPasswordLogin = view.findViewById(R.id.textFieldPasswordLogin);
        textFieldEmailLogin = view.findViewById(R.id.textFieldEmailLogin);
        txtSignupClick = view.findViewById(R.id.txtSignupClick);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnGoogle = view.findViewById(R.id.btnGoogle);

        myauth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = myauth.getCurrentUser();


        GoogleSignInOptions gsio = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)) //gpt instead : .requestIdToken(getString(GoogleSignInOptions.DEFAULT_WEB_CLIENT_ID))
                .requestEmail()
                .build();

        mygoogle = GoogleSignIn.getClient(requireActivity(), gsio);


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

        btnGoogle.setOnClickListener(v -> {
            googleLogIn();
        });


        return view;
    }

    private void logIn() {
        myauth.signInWithEmailAndPassword(txtEmailLogin.getText().toString(), txtPasswordLogin.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(), "Logged In successfully", Toast.LENGTH_LONG).show();
                            Navigation.findNavController(view).navigate(R.id.action_loginFrag_to_homeFragment);
                        } else {
                            Toast.makeText(getActivity(), "Failed" + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void googleLogIn() {

//        requireActivity().getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN
//        );

        Intent signInIntent = mygoogle.getSignInIntent();
        googleSignInLauncher.launch(signInIntent);
    }

    private final ActivityResultLauncher<Intent> googleSignInLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                    try {
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        signInWithGoogle(account.getIdToken());
                    } catch (ApiException e) {
                        Toast.makeText(getActivity(), "Google Sign-In failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });

    private void signInWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        myauth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getActivity(), "Logged In With Google successfully", Toast.LENGTH_LONG).show();
                        Navigation.findNavController(view).navigate(R.id.action_loginFrag_to_homeFragment);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Failed" + e.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
    }


    private void logOutFromGoogle(){
        myauth.signOut();
        mygoogle.signOut();
        Toast.makeText(getActivity(), "Logged Out From Google successfully", Toast.LENGTH_LONG).show();
    }
}