package com.fmpdroid.moneybox;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

public class EmptyActivity extends AppCompatActivity {

    private FloatingActionButton fabAddMoneyBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        fabAddMoneyBox = findViewById(R.id.fabAddMoneyBox);
    }

    public void addMoneyBox(View v) {
        Intent myIntent = new Intent(EmptyActivity.this, CreateMoneyBoxActivity.class);
        EmptyActivity.this.startActivity(myIntent);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}