package com.example.regformwithdb.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.regformwithdb.Adapter.RecycleviewAdapter;
import com.example.regformwithdb.Database.DBHandler;
import com.example.regformwithdb.ModelCLass.ModelClass;
import com.example.regformwithdb.R;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {

    private ArrayList<ModelClass> modelClassArrayList;
    private DBHandler dbHandler;
    private RecycleviewAdapter recycleviewAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        recyclerView = findViewById(R.id.recycleView);
        modelClassArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ShowDataActivity.this);

        modelClassArrayList = dbHandler.readRegData();

        recycleviewAdapter = new RecycleviewAdapter(modelClassArrayList,ShowDataActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowDataActivity.this,recyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(recycleviewAdapter);
        recycleviewAdapter.notifyDataSetChanged();
    }

    public void onResume() {
        super.onResume();
        modelClassArrayList = dbHandler.readRegData();
        recycleviewAdapter.notifyList(modelClassArrayList);

    }
}