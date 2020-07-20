package com.example.notepad;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    NotePadDataBase dataBase;
    List<Data> data;
    Adapter adapter;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataBase = new NotePadDataBase(this);
        data = dataBase.getDatas();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String time= Details.todayTime;
        String date= Details.todayDate;
//        List<Data> data1 = new ArrayList<>();
//        data1.add(new Data(date,time,"Heading","content is lit!!!"));
//        data1.add(new Data("Heading","Date","time","content"));
//        data1.add(new Data("Heading","Date","Time","content"));
//        data1.add(new Data("Heading","Date","Time","content"));
//        data1.add(new Data("Heading","Date","Time","content"));
//        data1.add(new Data("Heading","Date","Time","content"));
//        data1.add(new Data("Heading","Date","Time","content"));
//        data1.add(new Data("Heading","Date","Time","content"));
//        data1.add(new Data("Heading","Date","Time","content"));
//        adapter = new Adapter(this,data1);
        adapter = new Adapter(this, data);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(this, Details.class);
            startActivity(intent);
            Toast.makeText(this, "Add clicked", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.save) {
            Toast.makeText(this, "Save clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}