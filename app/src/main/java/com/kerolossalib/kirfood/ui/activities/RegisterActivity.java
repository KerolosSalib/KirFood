package com.kerolossalib.kirfood.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.kerolossalib.kirfood.R;
import com.kerolossalib.kirfood.SharedPreferencesSettings;
import com.kerolossalib.kirfood.Utilities;
import com.kerolossalib.kirfood.services.RESTController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, Response.Listener<String>, Response.ErrorListener {
    TextInputLayout emailTextInput;
    TextInputLayout passwordTextInput;
    TextInputLayout repeatPasswordTextInput;
    TextInputLayout phoneTextInput;
    EditText emailEditText;
    EditText passwordEditText;
    EditText repeatPasswordEditText;
    EditText phoneEditText;
    Button registerButton;
    Button cancelButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initialize Email
        emailTextInput = findViewById(R.id.email_text_input);
        emailEditText = findViewById(R.id.email_edit_text);

        //Initialize password
        passwordTextInput = findViewById(R.id.password_text_input);
        passwordEditText = findViewById(R.id.password_edit_text);

        //Initialize repeat password
        repeatPasswordTextInput = findViewById(R.id.repeat_password_text_input);
        repeatPasswordEditText = findViewById(R.id.repeat_password_edit_text);

        //Initialize Phone
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
                if (!Utilities.EMAIL_VALID)
                    emailTextInput.setError("Not a Valid Email");
                else
                    emailTextInput.setError(null);
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
                if (!Utilities.PASSWORD_VALID)
                    passwordTextInput.setError("Not a Valid Password");
                else
                    passwordTextInput.setError(null);


                enableRegisterButton();
            }
        });

        repeatPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Utilities.validatePassword(s.toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

                if (repeatPasswordEditText.getText().toString().equals(passwordEditText.getText().toString()))
                    repeatPasswordTextInput.setError(null);
                else
                    repeatPasswordTextInput.setError("Password is not the same");

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
                if (Utilities.PHONE_NUMBER_VALID)
                    passwordTextInput.setError(null);
                else
                    passwordTextInput.setError("Not a Valid Phone number");
                enableRegisterButton();
            }
        });


    }


    private void enableRegisterButton() {
        if (Utilities.canRegister() && passwordEditText.getText().toString().equals(repeatPasswordEditText.getText().toString()))
            registerButton.setEnabled(true);
        else {
            registerButton.setEnabled(false);

        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.register_button) {
            RESTController restController = new RESTController(this);
            restController.postRequest(RESTController.REGISTER_ENDPOINT, this, this, getRegisterParams());
        } else if (v.getId() == R.id.cancel_button) {
            startActivity(new Intent(this, MainActivity.class));
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
            SharedPreferencesSettings.setSharedPreferences(this, "user_token", jsonObject.getString("jwt"));
        } catch (JSONException e) {
            Toast.makeText(this, "can't make a registration", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(this, "Thank you for Registration", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));

    }

    protected Map<String, String> getRegisterParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", phoneEditText.getText().toString().trim());
        params.put("email", emailEditText.getText().toString().trim());
        params.put("password", passwordEditText.getText().toString());

        return params;
    }
}
