package com.fmpdroid.moneybox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateMoneyBox extends AppCompatActivity {

    private static String PREFERENCE = "moneybox_preference"; //temp variable
    private static String TEMP_CHECK = "moneybox_isEmpty"; //temp variable
    private Context context = CreateMoneyBox.this;
    private TextInputEditText edtTime;
    private TextInputEditText edtDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create_money_box);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_activity_create_money_box);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        edtTime = findViewById(R.id.edtTime);
        edtDate = findViewById(R.id.edtDate);
        edtTime.setText(String.format("%02d:%02d", getCurrentHour(), getCurrentMinute()));
        edtDate.setText(getCurrentMonthName() + " " + getCurrentDay() + ", " + getCurrentYear());
    }

    public void createMoneyBox(View v){

        Toast.makeText(context, "No no no", Toast.LENGTH_SHORT).show(); //temp
        /*
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(PREFERENCE, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(TEMP_CHECK, false);
        editor.commit();

        Intent intent = new Intent(CreateMoneyBox.this, MainActivity.class);
        startActivity(intent);
        */
    }

    public void pickTime(View v){
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                edtTime.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        }, getCurrentHour(), getCurrentMinute(), android.text.format.DateFormat.is24HourFormat(context));
        timePickerDialog.show();
    }

    public void pickDate(View v){
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, month); //sets the month of the calendar instance in order to retrieve its month name
                edtDate.setText(getMonthName(calendar) + " " + dayOfMonth + ", " + year);
            }
        }, getCurrentYear(), getCurrentMonth(), getCurrentDay());
        datePickerDialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private int getCurrentHour(){
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
    private int getCurrentMinute(){
        return Calendar.getInstance().get(Calendar.MINUTE);
    }
    private int getCurrentDay(){
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    private int getCurrentMonth(){
        return Calendar.getInstance().get(Calendar.MONTH);
    }
    private String getCurrentMonthName(){
        return Calendar.getInstance().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }
    private String getMonthName(Calendar calendar){
        return calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
    }
    private int getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }


}