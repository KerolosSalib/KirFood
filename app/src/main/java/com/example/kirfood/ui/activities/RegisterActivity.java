package com.example.kirfood.ui.activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.example.kirfood.Utilities;
import com.example.kirfood.R;

public class RegisterActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText phoneNumber;
    Button registerButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.email_edit_text);
        password = findViewById(R.id.password_edit_text);
        phoneNumber = findViewById(R.id.phone_number);
        registerButton = findViewById(R.id.register_btn);


        email.addTextChangedListener(new TextWatcher() {
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

        password.addTextChangedListener(new TextWatcher() {
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
        phoneNumber.addTextChangedListener(new TextWatcher() {
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


}
