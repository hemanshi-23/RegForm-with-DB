package com.example.regformwithdb.Activity;

import static com.example.regformwithdb.Activity.LoginActivity.SHARED_PREFS;

import static com.example.regformwithdb.Activity.LoginActivity.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.regformwithdb.R;

public class LoginSucessActivity extends AppCompatActivity {

    Button LogoutBtn,ShowDataBtn;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sucess);

        LogoutBtn = findViewById(R.id.Logout_Btn);
        ShowDataBtn = findViewById(R.id.ShowData_Btn);
        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("TAG", "onClick: login sucess "+ sharedpreferences.getBoolean("isLogin",false) );
                sharedpreferences.edit().putBoolean("isLogin",false).apply();

                Intent intent = new Intent(LoginSucessActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });

        ShowDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginSucessActivity.this, ShowDataActivity.class);
                startActivity(intent);
               // finish();
            }
        });
    }
}