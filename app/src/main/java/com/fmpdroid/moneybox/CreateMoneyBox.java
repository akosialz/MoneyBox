package com.fmpdroid.moneybox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Locale;

public class CreateMoneyBox extends AppCompatActivity {

    private Context mContext = CreateMoneyBox.this;
    private Calendar mCalendar = Calendar.getInstance();
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
        edtTime.setText(String.format("%02d:%02d", mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE)));
        edtDate.setText(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + mCalendar.get(Calendar.DAY_OF_MONTH) + ", " + mCalendar.get(Calendar.YEAR));
    }

    public void createMoneyBox(View v) {

    }

    public void pickTime(View v) {

        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, (timePicker, hourOfDay, minute) ->
                edtTime.setText(String.format("%02d:%02d", hourOfDay, minute)), mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(mContext));
        timePickerDialog.show();
    }

    public void pickDate(View v) {

        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, (datePicker, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, month); //sets the month of the calendar instance in order to retrieve its month name
            edtDate.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + dayOfMonth + ", " + year);
        }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}