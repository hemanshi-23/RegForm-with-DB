package com.example.regformwithdb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.regformwithdb.Database.DBHandler;
import com.example.regformwithdb.ModelCLass.ModelClass;
import com.example.regformwithdb.R;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    EditText Email, PassWord;
    Button LoginBtn;
    TextView Register_now;
    private ArrayList<ModelClass> modelClassArrayList;
    private DBHandler dbHandler;
    ModelClass modelClass;
    String email, password;
    Boolean isRegistered = false;
    boolean isLogin = false;
    public static final String SHARED_PREFS = "shared_perf";
    public static SharedPreferences sharedpreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Email = findViewById(R.id.email_EdtTxt_Log);
        PassWord = findViewById(R.id.pass_EdtTxt_Log);
        LoginBtn = findViewById(R.id.login_Btn_Log);

        Register_now = findViewById(R.id.RegisterNowTxt);

        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        modelClassArrayList = new ArrayList<>();
        dbHandler = new DBHandler(LoginActivity.this);

        Register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
            }
        });

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                modelClassArrayList = dbHandler.readRegData();

                email = Email.getText().toString();
                password = PassWord.getText().toString();

                Email.setText("");
                PassWord.setText("");

                for (int i = 0; i < modelClassArrayList.size(); i++) {

                    modelClass = modelClassArrayList.get(i);

                    Log.e("TAG", "onClick:database email " + modelClass.getStudentEmail());
                    Log.e("TAG", "onClick:editText email " + email);

                    Log.e("TAG", "onClick:database pass " + modelClass.getStudentPass());
                    Log.e("TAG", "onClick:editText pass " + password);

                    if (modelClass.getStudentEmail().equals(email) && modelClass.getStudentPass().equals(password)) {

                        isRegistered = true;
                        sharedpreferences.edit().putBoolean("isLogin",true).apply();
                       
                    }
                }

                if (isRegistered) {

                    Toast.makeText(LoginActivity.this, "Login Succesfull", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(LoginActivity.this, LoginSucessActivity.class);
                    startActivity(intent);
                    isLogin = true;
                    isRegistered = false;
                    finish();

                }
                else {

                    Toast.makeText(LoginActivity.this, "Enter Correct Details", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("TAG", "onStart: " + sharedpreferences.getBoolean("isLogin",isLogin) );
        if (sharedpreferences.getBoolean("isLogin",isLogin)) {
            Log.e("TAG", "onStart: " + sharedpreferences.getBoolean("isLogin",isLogin) );
            Intent i = new Intent(LoginActivity.this, LoginSucessActivity.class);
            startActivity(i);
            finish();
        }
    }
}