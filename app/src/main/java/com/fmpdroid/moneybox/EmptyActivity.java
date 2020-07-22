package com.fmpdroid.moneybox;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class EmptyActivity extends AppCompatActivity {

    private FloatingActionButton fabAddMoneyBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        fabAddMoneyBox = findViewById(R.id.fabAddMoneyBox);
    }

    public void addMoneyBox(View v){
        Intent myIntent = new Intent(EmptyActivity.this, CreateMoneyBox.class);
        EmptyActivity.this.startActivity(myIntent);
    }
}