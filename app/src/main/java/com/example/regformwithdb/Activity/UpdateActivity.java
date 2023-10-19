package com.example.regformwithdb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.regformwithdb.Database.DBHandler;
import com.example.regformwithdb.R;

public class UpdateActivity extends AppCompatActivity {

    EditText name_edt,email_edt,pass_edt;
    Button update_btn;
    DBHandler dbHandler;
    String name,email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name_edt = findViewById(R.id.name_EdtTxt_Update);
        email_edt = findViewById(R.id.email_EdtTxt_Update);
        pass_edt = findViewById(R.id.pass_EdtTxt_Update);
        update_btn = findViewById(R.id.Update_Btn);

        dbHandler = new DBHandler(UpdateActivity.this);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        pass = getIntent().getStringExtra("pass");

        name_edt.setText(name);
        email_edt.setText(email);
        pass_edt.setText(pass);

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHandler.updateCourse(name,name_edt.getText().toString(),email_edt.getText().toString(),pass_edt.getText().toString());

                Toast.makeText(UpdateActivity.this, "Updated..", Toast.LENGTH_SHORT).show();

                finish();

            }
        });



    }
}