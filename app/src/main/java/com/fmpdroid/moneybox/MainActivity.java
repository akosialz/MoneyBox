package com.fmpdroid.moneybox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.fmpdroid.moneybox.adapter.ViewPagerAdapter;
import com.fmpdroid.moneybox.dto.MoneyBoxDto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    private Toolbar toolbar;
    private ViewPagerAdapter adapter;
    private ViewPager2 viewPager;
    private FloatingActionButton fabAdd;
    private FloatingActionButton fabDelete;
    private DrawerLayout drawer;
    private NavigationView navigationView;

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
        fabDelete = findViewById(R.id.fabDelete);
        adapter = new ViewPagerAdapter(this);
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
                        fabDelete.show();
                        break;
                    case ViewPager2.SCROLL_STATE_DRAGGING:
                    case ViewPager2.SCROLL_STATE_SETTLING:
                        fabAdd.hide();
                        fabDelete.hide();
                        break;
                }
            }
        });

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.nav_personalize:
                case R.id.nav_currency:
                case R.id.nav_graph:
                case R.id.nav_archives:
                case R.id.nav_support:
                    Toast.makeText(MainActivity.this, menuItem.getTitle().toString() + " is selected", Toast.LENGTH_SHORT).show();
            }
            return false;
        });
    }

    public void addMoneyBox(View v) {
        Intent myIntent = new Intent(this, CreateMoneyBoxActivity.class);
        MainActivity.this.startActivityForResult(myIntent, REQUEST_CODE);
    }

    public void deleteMoneyBox(View v) {
        if (adapter.getItemCount() > 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Confirm Delete");
            alert.setMessage("Are you sure to delete this Money Box?");
            alert.setPositiveButton("Yes", (dialogInterface, i) -> {
                adapter.delete(viewPager.getCurrentItem());
                dialogInterface.dismiss();
                Toast.makeText(this, "Money Box successfully deleted", Toast.LENGTH_SHORT).show();
            });
            alert.setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss());
            alert.show();
        } else {
            Toast.makeText(this, "Nothing to delete", Toast.LENGTH_SHORT).show();
        }
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

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finishAffinity();
        }
    }

}