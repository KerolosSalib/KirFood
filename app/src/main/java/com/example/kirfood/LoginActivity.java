package com.example.kirfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

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

        if (!hasInvitationCode())
            registerBtn.setVisibility(View.VISIBLE);

        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        colorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(colorSwitch.isChecked())
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);

//                    parentLayout.setBackgroundColor(getResources().getColor(R.color.background_night_mood));
                else
//                    parentLayout.setBackgroundColor(getResources().getColor(R.color.background_light_mood));
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);


            }
        });


    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","Activity Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity","Activity Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity","Activity Paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity","Activity Stoped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity","Activity Destroied");
    }

    private boolean hasInvitationCode(){
        return false;
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

