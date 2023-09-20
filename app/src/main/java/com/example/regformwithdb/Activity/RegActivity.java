package com.example.regformwithdb.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.regformwithdb.R;
import com.example.regformwithdb.Database.DBHandler;

public class RegActivity extends AppCompatActivity {

    EditText name,mail,pass;
    Button submitBtn;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name_EdtTxt_Reg);
        mail = (EditText) findViewById(R.id.email_EdtTxt_Reg);
        pass = (EditText) findViewById(R.id.pass_EdtTxt_Reg);
        submitBtn = (Button) findViewById(R.id.submit_Btn_Reg);

        dbHandler = new DBHandler(RegActivity.this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String nameStr = name.getText().toString();
               String mailStr = mail.getText().toString();
               String passStr = pass.getText().toString();

               dbHandler.addRegData(nameStr,mailStr,passStr);

                Toast.makeText(RegActivity.this, "Registration done!", Toast.LENGTH_SHORT).show();
                name.setText("");
                mail.setText("");
                pass.setText("");

                finish();
             }
        });
    }
}