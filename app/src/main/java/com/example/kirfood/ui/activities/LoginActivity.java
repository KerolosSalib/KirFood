package com.example.kirfood.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.kirfood.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout parentLayout;
    Button loginBtn;
    Button registerBtn;

    EditText emailEt;
    EditText passwordEt;
    Switch colorSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Attache this to the activity_layout.xml file
        setContentView(R.layout.activity_login);
        Log.i("MainActivity","Activity Created");
        parentLayout = findViewById(R.id.parent_layout);
        loginBtn = findViewById(R.id.login_btn);
        registerBtn = findViewById(R.id.register_btn);
        emailEt = findViewById(R.id.email_edit_text);
        passwordEt = findViewById(R.id.password_edit_text);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_btn){
            //To do login
            if (controlEmail(emailEt) && controlPassword(passwordEt)) {
                // TODO: 1/31/2019
            }
            else if(controlEmail(emailEt) && !controlPassword(passwordEt))
                showToast("Email valid but too short pass");
            else if (!controlEmail(emailEt) && controlPassword(passwordEt))
                showToast("email not valid but good pass");
            else
                showToast("email and password not valid");


        }else if (v.getId() == R.id.register_btn){
            // To do Register
            Intent iRegister = new Intent(this, RegisterActivity.class);
            startActivity(iRegister);
        }

    }

    private void showToast(String t){
        Toast.makeText(this, t, Toast.LENGTH_LONG).show();
    }

    private String getEmail(EditText eT){
        return eT.getText().toString().trim().toLowerCase();
    }
    private boolean controlEmail(EditText eT){
        String email = eT.getText().toString().trim();
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean controlPassword(EditText eT){
        String password = eT.getText().toString().trim();
        if (password.length() > 6)
            return true;
        else
            return false;
    }

}

