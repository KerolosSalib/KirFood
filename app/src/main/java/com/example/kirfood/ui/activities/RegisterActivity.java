package com.example.kirfood.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kirfood.Utilities;
import com.example.kirfood.R;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    TextInputLayout emailTextInput;
    TextInputLayout passwordTextInput;
    TextInputLayout repeatPasswordTextInput;
    TextInputLayout phoneTextInput;
    EditText emailEditText;
    EditText passwordEditText;
    EditText repetePasswordEditText;
    EditText phoneEditText;
    Button registerButton;
    Button cancelButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailTextInput = findViewById(R.id.email_text_input);
        emailEditText = findViewById(R.id.email_edit_text);
        passwordTextInput = findViewById(R.id.password_text_input);
        repeatPasswordTextInput = findViewById(R.id.repeat_password_text_input);
        passwordEditText = findViewById(R.id.password_edit_text);
        phoneTextInput = findViewById(R.id.phone_text_input);
        phoneEditText = findViewById(R.id.phone_edit_text);

        //Buttons
        registerButton = findViewById(R.id.register_button);
        cancelButton = findViewById(R.id.cancel_button);

        //Set On Click listener
        registerButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);




        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Utilities.validateEmail(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {
                enableRegisterButton();
            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Utilities.validatePassword(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {
                enableRegisterButton();
            }
        });
        phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Utilities.validatePhoneNumber(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {
                enableRegisterButton();
            }
        });


    }





    private void enableRegisterButton(){
        if(Utilities.canRegister())
            registerButton.setEnabled(true);
        else {
            registerButton.setEnabled(false);

        }

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.register_button){

        }else if (v.getId() == R.id.cancel_button){
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}
