package com.example.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Details extends AppCompatActivity {

    private static int One = 1;
    private Toolbar toolbarDetails;
    private EditText title, details;
    Calendar calendar;
    public static String todayDate;
    public static String todayTime;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        toolbarDetails = (Toolbar) findViewById(R.id.toolbarDetails);
        setSupportActionBar(toolbarDetails);
        getSupportActionBar().setTitle("Note Pad");
        //this code is used to add back button is tool bar
        //after "setDisplayHomeAsUpEnabled" go to manifest inside details activity set
//       ----------  <meta-data---- This need to be done in manifest
//        android:name="android.support.PARENT_ACTIVITY"
//        android:value=".MainActivity"/>
        //Manifest ma gayera garney
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarDetails.setTitleTextColor(getResources().getColor(R.color.white));

        title = (EditText) findViewById(R.id.title);
        details = (EditText) findViewById(R.id.details);

        title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        calendar = Calendar.getInstance();
        todayDate = calendar.get(Calendar.YEAR) + "/" + calendar.get((Calendar.MONTH) + One) + "/" + calendar.get(Calendar.DAY_OF_MONTH);
        todayTime = pad(calendar.get(Calendar.HOUR)) + ":" + pad(calendar.get(Calendar.MINUTE));
        Log.d("Message: ", todayDate + " and " + todayTime);
    }

    private String pad(int i) {
        if (i < 10)
            return "0" + i;
        return String.valueOf(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.details_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete) {
            Toast.makeText(this, "Delete clicked", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.check)
        {
            String messageOne = title.getText().toString();
            if(messageOne.length() !=0)
            {
                Data data = new Data(todayDate, todayTime, title.getText().toString(), details.getText().toString());
                //Toast.makeText(this, "Message " + messageOne, Toast.LENGTH_SHORT).show();
                NotePadDataBase notePadDataBase = new NotePadDataBase(this);
                long id = notePadDataBase.addData(data);
                Data save = notePadDataBase.getData(id);
                Log.d("inserted", "Note: "+ id + " -> Title:" + save.getTitle()+" Date: "+ save.getDate());
                onBackPressed();//This helps us to go in main activity when check icon is clicked
                Toast.makeText(this, "Note saved.", Toast.LENGTH_SHORT).show();
                gotoMain();
            }
            else
                {
                    title.setError("Title cannot be blanked");
                }
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}