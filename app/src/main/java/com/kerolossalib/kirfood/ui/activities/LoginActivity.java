package com.kerolossalib.kirfood.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kerolossalib.kirfood.R;
import com.kerolossalib.kirfood.Utilities;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LOGIN ACTIVITY";
    Button loginButton;
    Button cancelButton;
    LinearLayout registerTextView;

    TextInputLayout emailTextInput;
    EditText emailEt;

    TextInputLayout passwordTextInput;
    EditText passwordEt;


    // TODO: Firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.AppTheme_no_Actionbar); // set a no actionbar theme

        //Set a full window activity to hide status bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);


        loginButton = findViewById(R.id.login_button);
        cancelButton = findViewById(R.id.cancel_button);
        registerTextView = findViewById(R.id.register_layout);

        emailTextInput = findViewById(R.id.email_text_input);
        emailEt = findViewById(R.id.email_edit_text);



        passwordTextInput = findViewById(R.id.password_text_input);
        passwordEt = findViewById(R.id.password_edit_text);


        loginButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        registerTextView.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAG,"User logedin: " + user.getUid());
                }else{
                    Log.d(TAG,"User loged out: " );

                }
            }
        };


        emailEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Utilities.validateEmail(emailEt.getText().toString()))
                    emailTextInput.setError(null);
                else
                    emailTextInput.setError(getString(R.string.emailError));
            }
        });

        passwordEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(Utilities.validatePassword(passwordEt.getText().toString()))
                    passwordEt.setError(null);
                else
                    passwordTextInput.setError(getString(R.string.passwordError));
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mAuthListener != null)
            mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button){
            //To do login
//            if (Utilities.validateEmail(emailEt.getText().toString()) && Utilities.validatePassword(passwordEt.getText().toString())) {
//                // TODO: 1/31/2019
//                emailTextInput.setError(null);
//                passwordTextInput.setError(null);
//
//                startActivity(new Intent(this,MainActivity.class));
//            }else if (!Utilities.validateEmail(emailEt.getText().toString()))
//                emailTextInput.setError(getString(R.string.emailError));
//            else if (!Utilities.validatePassword(passwordEt.getText().toString()))
//                passwordTextInput.setError(getString((R.string.passwordError)));

        }else if (v.getId() == R.id.cancel_button){
            startActivity(new Intent(this, MainActivity.class));
        }
        else if (v.getId() == R.id.register_layout){
            // To do Register
            Intent iRegister = new Intent(this, RegisterActivity.class);
            startActivity(iRegister);
        }

    }



}

