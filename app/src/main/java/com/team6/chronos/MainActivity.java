package com.team6.chronos;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;



public class MainActivity extends AppCompatActivity {

    private Button SignupNow;

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
    }

    public void openSignupActivity(){
        Intent intent = new Intent(this, SignupActivity.class);
        startActivity(intent);
    }
}
