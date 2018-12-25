package com.example.user.gmailappclone;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private FrameLayout layoutContainer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeWidgets();
        setDefaultFragment();
    }

    private void initializeWidgets(){
        drawerLayout = findViewById(R.id.main_drawerlayout);
        layoutContainer = findViewById(R.id.main_layout_container);
        navigationView = findViewById(R.id.main_navigationview);
        toolbar = findViewById(R.id.main_toolbar);

        toolbar.setTitle("Primary");
    }

    private void setDefaultFragment(){
        getSupportFragmentManager().beginTransaction().replace(R.id.main_layout_container,new PrimaryFragment())
                .commit();
    }
}
