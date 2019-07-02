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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class SignupActivity extends AppCompatActivity{

    //Firebase stuff
    private FirebaseAuth firebaseAuth;


    EditText editEmail, editPassword, confPassword;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        confPassword = findViewById(R.id.confPassword);
        btnSignup = findViewById(R.id.btnSignup);

        firebaseAuth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();
                String confirmPassword = confPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignupActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(SignupActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length()<6){
                    Toast.makeText(SignupActivity.this, "Password Must be at Least 6 Characters in Length", Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(confirmPassword)){
                    Toast.makeText(SignupActivity.this, "Please Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.equals(confirmPassword)){
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                            Toast.makeText(SignupActivity.this, "User With This Email Already Exists",Toast.LENGTH_SHORT).show();
                                        }
                                        // Sign in success, update UI with the signed-in user's information
                                        startActivity(new Intent(getApplicationContext(),QuickView.class));
                                        Toast.makeText(SignupActivity.this, "Registration Complete", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(SignupActivity.this, "Passwords do not Match", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

}
