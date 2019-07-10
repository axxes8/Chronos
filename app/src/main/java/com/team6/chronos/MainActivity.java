package com.team6.chronos;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

   EditText emailInput, passwordInput;

    RelativeLayout relLay1, relLay2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            relLay1.setVisibility(View.VISIBLE);
            relLay2.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relative layouts
        relLay1= findViewById(R.id.relLay1);
        relLay2= findViewById(R.id.relLay2);

        //timer for splash screen
        handler.postDelayed(runnable, 2000);

        //Text inputs
        emailInput = findViewById(R.id.inputEmail);
        passwordInput = findViewById(R.id.inputPassword);

        //Button for openSignupActivity
        Button signupNow = findViewById(R.id.btnSignupNow);
        signupNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();
            }
        });

        //Button for login
        Button login = findViewById(R.id.btnLogin);

        //Button for openForgotActivity
        Button forgotPass = findViewById(R.id.btnForgot);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgotActivity();
            }
        });

        //Firebase authorization
        firebaseAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    //Toast popup if email field is empty
                    Toast.makeText(MainActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    //Toast popup if password field is empty
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //If login details are valid, open QuickView activity
                                    startActivity(new Intent(getApplicationContext(), QuickView.class));

                                } else {
                                    //Toast popup if login details are incorrect
                                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
            }
        });


    }

    //opens signup activity
    public void openSignupActivity(){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    //opens forgot password activity
    public void openForgotActivity(){
        Intent intent = new Intent(this, ForgotPassActivity.class);
        startActivity(intent);
    }
}
