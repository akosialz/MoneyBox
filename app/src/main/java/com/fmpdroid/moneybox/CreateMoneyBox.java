package com.fmpdroid.moneybox;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fmpdroid.moneybox.dto.MoneyBoxDto;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Locale;

public class CreateMoneyBox extends AppCompatActivity {

    private Context mContext = CreateMoneyBox.this;
    private Calendar mCalendar = Calendar.getInstance();
    private TextInputEditText edtTime;
    private TextInputEditText edtTargetDate;
    private TextInputEditText edtName;
    private TextInputEditText edtDescription;
    private TextInputEditText edtTargetAmount;
    private Switch mSwitch;

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
        edtTargetDate = findViewById(R.id.edtDate);
        edtName = findViewById(R.id.edtMoneyBoxName);
        edtDescription = findViewById(R.id.edtDescription);
        edtTargetAmount = findViewById(R.id.edtTargetAmount);
        mSwitch = findViewById(R.id.switchAllowReminder);


        edtTime.setText(String.format("%02d:%02d", mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE)));
        edtTargetDate.setText(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + mCalendar.get(Calendar.DAY_OF_MONTH) + ", " + mCalendar.get(Calendar.YEAR));

    }

    public void createMoneyBox(View v) {

        MoneyBoxDto moneyBox = new MoneyBoxDto();
        moneyBox.setAllowReminder(mSwitch.isChecked());
        moneyBox.setDateCreated(mCalendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + mCalendar.get(Calendar.DAY_OF_MONTH) + ", " + mCalendar.get(Calendar.YEAR));
        moneyBox.setDescription(edtDescription.getText().toString());
        moneyBox.setTargetAmount(Float.parseFloat(edtTargetAmount.getText().toString()));
        moneyBox.setRemainingAmount(Float.parseFloat(edtTargetAmount.getText().toString()));
        moneyBox.setTargetDate(edtTargetDate.getText().toString());
        moneyBox.setName(edtName.getText().toString());
        Intent resultIntent = new Intent();
        resultIntent.putExtra(getString(R.string.key_moneybox), moneyBox);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    public void pickTime(View v) {

        new TimePickerDialog(mContext, (timePicker, hourOfDay, minute) ->
                edtTime.setText(String.format("%02d:%02d", hourOfDay, minute)), mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE), android.text.format.DateFormat.is24HourFormat(mContext))
                .show();
    }

    public void pickDate(View v) {

        new DatePickerDialog(mContext, (datePicker, year, month, dayOfMonth) -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, month); //sets the month of the calendar instance in order to retrieve its month name
            edtTargetDate.setText(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()) + " " + dayOfMonth + ", " + year);
        }, mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH))
                .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}