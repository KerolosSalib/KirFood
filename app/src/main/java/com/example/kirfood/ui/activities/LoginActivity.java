package com.example.kirfood.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kirfood.R;
import com.example.kirfood.Utilities;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button loginButton;
    Button cancelButton;
    TextView registerTextView;

    TextInputLayout emailTextInput;
    EditText emailEt;

    TextInputLayout passwordTextInput;
    EditText passwordEt;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_no_Actionbar); // set a no actionbar theme

        //Set a full window activity to hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);


        loginButton = findViewById(R.id.login_button);
        cancelButton = findViewById(R.id.cancel_button);
        registerTextView = findViewById(R.id.register_text_view);

        emailTextInput = findViewById(R.id.email_text_input);
        emailEt = findViewById(R.id.email_edit_text);

        passwordTextInput = findViewById(R.id.password_text_input);
        passwordEt = findViewById(R.id.password_edit_text);

        loginButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        registerTextView.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button){
            //To do login
            if (Utilities.validateEmail(emailEt.getText().toString()) && Utilities.validatePassword(passwordEt.getText().toString())) {
                // TODO: 1/31/2019
                emailTextInput.setError(null);
                passwordTextInput.setError(null);

                startActivity(new Intent(this,MainActivity.class));
            }else if (!Utilities.validateEmail(emailEt.getText().toString()))
                emailTextInput.setError(getString(R.string.emailError));
            else if (!Utilities.validatePassword(passwordEt.getText().toString()))
                passwordTextInput.setError(getString((R.string.passwordError)));

        }else if (v.getId() == R.id.cancel_button){
            startActivity(new Intent(this, MainActivity.class));
        }
        else if (v.getId() == R.id.register_text_view){
            // To do Register
            Intent iRegister = new Intent(this, RegisterActivity.class);
            startActivity(iRegister);
        }

    }



}

