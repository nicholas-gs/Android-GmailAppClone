package com.example.user.gmailappclone;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.gmailappclone.Fragments.PrimaryFragment;
import com.example.user.gmailappclone.Fragments.PromotionsFragment;
import com.example.user.gmailappclone.Fragments.SocialFragment;
import com.example.user.gmailappclone.Helper.NetworkChecker;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private FrameLayout layoutContainer;
    private NavigationView navigationView;

    private ImageView toggleArrow;
    private int currentMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeWidgets();

        toggleNavigationDrawer();
        handleDrawerMenuToggle();
        setDefaultFragment(savedInstanceState);
    }

    private void initializeWidgets() {
        drawerLayout = findViewById(R.id.main_drawerlayout);
        layoutContainer = findViewById(R.id.main_layout_container);

        toolbar = findViewById(R.id.main_toolbar);
        toolbar.setTitle("Primary");
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.main_navigationview);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Sets the default fragment when the app cold starts
     */
    private void setDefaultFragment(Bundle bundle) {
        if (!NetworkChecker.isOnline(this)) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
        if (bundle == null) {
            navigationView.setCheckedItem(R.id.nav_menu_closed_primary);
            currentMenuItem = R.id.nav_menu_closed_primary;
            getSupportFragmentManager().beginTransaction().replace(R.id.main_layout_container,
                    new PrimaryFragment()).commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == navigationView.getCheckedItem().getItemId()) {
            // Do nothing so that the fragment is not re-initialised
        } else {
            switch (menuItem.getItemId()) {
                case R.id.nav_menu_closed_primary:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_layout_container, new PrimaryFragment())
                            .commit();
                    break;
                case R.id.nav_menu_closed_social:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_layout_container,
                            new SocialFragment()).commit();
                    break;
                case R.id.nav_menu_closed_promotions:
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_layout_container,
                            new PromotionsFragment()).commit();
                    break;
                case R.id.nav_menu_open_addaccount:
                    Toast.makeText(this, "Add account pressed", Toast.LENGTH_SHORT).show();
                    break;
            }
            currentMenuItem = navigationView.getCheckedItem().getItemId();
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return true;
    }

    private void toggleNavigationDrawer() {
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
    }

    private void handleDrawerMenuToggle() {
        View header = navigationView.getHeaderView(0);
        toggleArrow = header.findViewById(R.id.nav_header_selector);

        toggleArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.isSelected()) {
                    v.setSelected(false);
                    navigationView.getMenu().clear();
                    navigationView.inflateMenu(R.menu.nav_menu_closed);
                    navigationView.setCheckedItem(currentMenuItem);
                } else {
                    v.setSelected(true);
                    currentMenuItem = navigationView.getCheckedItem().getItemId();
                    navigationView.getMenu().clear();
                    navigationView.inflateMenu(R.menu.nav_menu_open);
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
