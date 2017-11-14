package com.example.home_pc.project;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static int identifier;
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final int MAIN_PAGE = 0;
    private static final int IMAGE_PAGE = 1;
    private static final int FAVORITE_PAGE = 2;
    NavigationRouter navigationRouter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationRouter = new NavigationRouter(this, navigationView, drawer);
        identifier = navigationRouter.selectItem(identifier);
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
                identifier = navigationRouter.switchFragment(identifier);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ID, identifier);
        Log.e("TAG", "ID после вызова сейв инстанс = " + identifier);
        outState.putString(TITLE, getTitle().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        identifier = savedInstanceState.getInt(ID);
        Log.e("TAG", "ID после вызова рестор инстанс = " + identifier);
        String title = savedInstanceState.getString(TITLE);
        setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_main:
                item.setChecked(true);
                identifier = navigationRouter.selectItem(MAIN_PAGE);
                break;
            case R.id.nav_pictures:
                item.setChecked(true);
                identifier = navigationRouter.selectItem(IMAGE_PAGE);
                break;
            case R.id.nav_favorites:
                item.setChecked(true);
                identifier = navigationRouter.selectItem(FAVORITE_PAGE);
                break;
        }
        return true;
    }
}
