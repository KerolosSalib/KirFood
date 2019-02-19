package com.kerolossalib.kirfood.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kerolossalib.kirfood.R;
import com.kerolossalib.kirfood.Utilities;
import com.kerolossalib.kirfood.datamodels.User;
import com.kerolossalib.kirfood.services.RESTController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener<String>, Response.ErrorListener {
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
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d(TAG, "User loged in: " + user.getUid());
                } else {
                    Log.d(TAG, "User loged out: ");

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
                if (Utilities.validateEmail(emailEt.getText().toString()))
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
                if (Utilities.validatePassword(passwordEt.getText().toString()))
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
        if (mAuthListener != null)
            mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.login_button) {
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


            RESTController restController = new RESTController(this);
            restController.postRequest(RESTController.LOGIN_ENDPOINT, this, this, getLoginParams());

        } else if (v.getId() == R.id.cancel_button) {
            startActivity(new Intent(this, MainActivity.class));
        } else if (v.getId() == R.id.register_layout) {
            // To do Register
            Intent iRegister = new Intent(this, RegisterActivity.class);
            startActivity(iRegister);
        }

    }


    @Override
    public void onErrorResponse(VolleyError error) {
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            //This indicates that the request has either time out or there is no connection
            Toast.makeText(this, "Internet Connection error", Toast.LENGTH_LONG).show();
        } else if (error instanceof AuthFailureError) {
            //Error indicating that there was an Authentication Failure while performing the request
            Toast.makeText(this, "AuthFailureError", Toast.LENGTH_LONG).show();
        } else if (error instanceof ServerError) {
            //Indicates that the server responded with a error response
            Toast.makeText(this, "ServerError", Toast.LENGTH_LONG).show();
        } else if (error instanceof NetworkError) {
            //Indicates that there was network error while performing the request
            Toast.makeText(this, "NetworkError", Toast.LENGTH_LONG).show();
        } else if (error instanceof ParseError) {
            // Indicates that the server response could not be parsed
            Toast.makeText(this, "ParseError", Toast.LENGTH_LONG).show();
        }

        Log.e("RequestError", error.toString());
    }

    @Override
    public void onResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            User.login(this, jsonObject.getString("jwt"));
            Intent intent = new Intent();
            setResult(RESULT_OK);
        } catch (JSONException e) {
            Toast.makeText(this, "can't make a registration", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(this, "Thank you for Login", Toast.LENGTH_LONG).show();
        finish();
    }

    protected Map<String, String> getLoginParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("identifier", emailEt.getText().toString().trim());
        params.put("password", passwordEt.getText().toString());

        return params;
    }
}

