package com.example.home_pc.project.MainActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.home_pc.project.Fragments.BaseFragment;
import com.example.home_pc.project.Fragments.FragmentForMainPage.FragmentForMainPage;
import com.example.home_pc.project.Fragments.FragmentForPicturesPage.FragmentForPicturesPage;
import com.example.home_pc.project.Model.Model;
import com.example.home_pc.project.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainActivityView {

    private int identifier;
    private static final String ID = "id";
    private static final int MAIN_PAGE = 0;
    private static final int IMAGE_PAGE = 1;
    private static final int FAVORITE_PAGE = 2;
    private static final String TAG_VISIBLE = "visible";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    PresentorForMainActivity presentorForMainActivity;
   // MainActivityPresentor presentor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if (ID == null) {
            identifier = MAIN_PAGE;
        } else {
            SharedPreferences sharedPref = this.getPreferences(this.MODE_PRIVATE);
            identifier = sharedPref.getInt(ID, MAIN_PAGE);
        }


       // presentorForMainActivity
        openScreen(identifier);
        navigationView.getMenu().getItem(identifier).setChecked(true);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_main:
                item.setChecked(true);
                identifier = (MAIN_PAGE);
                openScreen(identifier);
                break;
            case R.id.nav_pictures:
                item.setChecked(true);
                identifier = (IMAGE_PAGE);
                openScreen(identifier);
                break;
            case R.id.nav_favorites:
                item.setChecked(true);
                identifier = (FAVORITE_PAGE);
                openScreen(identifier);
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (identifier == MAIN_PAGE) {
                finish();
            } else {
                super.onBackPressed();
                switchFragment();
            }
        }
    }

    public void switchFragment() {
        Fragment show_fragment = getSupportFragmentManager().findFragmentByTag(TAG_VISIBLE);
        if (show_fragment instanceof FragmentForMainPage) {
            identifier = MAIN_PAGE;
        } else if (show_fragment instanceof FragmentForPicturesPage) {
            identifier = IMAGE_PAGE;
        } else {
            identifier = FAVORITE_PAGE;
        }
        navigationView.getMenu().getItem(identifier).setChecked(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = this.getPreferences(this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(ID, identifier);
        editor.commit();
    }


    public void openScreen(int id) {
        BaseFragment showFragment = null;
        switch (id) {
            case MAIN_PAGE:
                showFragment = requestToPresentor();
                break;
            case IMAGE_PAGE:
                showFragment = requestToPresentor();
                break;
            case FAVORITE_PAGE:
                showFragment = requestToPresentor();
                break;
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.container, showFragment, TAG_VISIBLE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    @Override
    public BaseFragment requestToPresentor() {
        PresentorForMainActivity presentorForMainActivity = new PresentorForMainActivity();
        BaseFragment fragment = presentorForMainActivity.requestToModel(identifier);
        return fragment;
    }
}
