package com.fmpdroid.moneybox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.fmpdroid.moneybox.adapter.ViewPagerAdapter;
import com.fmpdroid.moneybox.dto.MoneyBoxDto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static int REQUEST_CODE = 1;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ViewPagerAdapter adapter;
    private ViewPager2 viewPager;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_viewpager);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(R.string.title_main_page);

        viewPager = findViewById(R.id.viewPager);
        fabAdd = findViewById(R.id.fabAdd);
        adapter = new ViewPagerAdapter(MainActivity.this);
        viewPager.setAdapter(adapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager2.SCROLL_STATE_IDLE:
                        fabAdd.show();
                        break;
                    case ViewPager2.SCROLL_STATE_DRAGGING:
                    case ViewPager2.SCROLL_STATE_SETTLING:
                        fabAdd.hide();
                        break;
                }
            }
        });
    }

    private void goToDrawerScreen() {

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new HomeFragment()).commit();

    }

    public void addMoneyBox(View v) {
        Intent myIntent = new Intent(MainActivity.this, CreateMoneyBox.class);
        MainActivity.this.startActivityForResult(myIntent, REQUEST_CODE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            MoneyBoxDto moneyBoxDto = data.getParcelableExtra(getResources().getString(R.string.key_moneybox));
            adapter.add(moneyBoxDto);
            viewPager.setCurrentItem(adapter.getItemCount());
        }
    }

    /*
    /*Will be used later
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finishAffinity();
        }
    }*/

}