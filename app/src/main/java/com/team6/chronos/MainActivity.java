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

    private Button SignupNow;
    private Button Login;

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

        relLay1=(RelativeLayout) findViewById(R.id.relLay1);
        relLay2=(RelativeLayout) findViewById(R.id.relLay2);

        handler.postDelayed(runnable, 2000); //timer for splash screen

        SignupNow = findViewById(R.id.btnSignupNow);
        SignupNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignupActivity();
            }
        });

        emailInput = findViewById(R.id.inputEmail);
        passwordInput = findViewById(R.id.inputPassword);

        Login = findViewById(R.id.btnLogin);


        firebaseAuth = FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    //
                    Toast.makeText(MainActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6){
                    Toast.makeText(MainActivity.this, "Password is too Short", Toast.LENGTH_SHORT).show();
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    startActivity(new Intent(getApplicationContext(), QuickView.class));

                                } else {

                                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
            }
        });
    }

    public void openSignupActivity(){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }

    /*public void loginToApp(){
        Intent intent = new Intent(this, QuickView.class);
        startActivity(intent);
    }*/
}
