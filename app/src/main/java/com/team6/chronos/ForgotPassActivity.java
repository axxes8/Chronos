package com.team6.chronos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity {

    //User Inputs
    EditText inputEmail;
    Button btnResetPass;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        //User inputs
        inputEmail = findViewById(R.id.inputEmail);
        btnResetPass = findViewById(R.id.btnResetPass);

        //firebase stuff
        firebaseAuth = FirebaseAuth.getInstance();

        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = inputEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    //Error if email is empty
                    Toast.makeText(ForgotPassActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    //Send Password Reset Email
                    firebaseAuth.sendPasswordResetEmail(inputEmail.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        //If Successful, display message and take user back to login screen
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(ForgotPassActivity.this, "Password Reset Email Sent", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
