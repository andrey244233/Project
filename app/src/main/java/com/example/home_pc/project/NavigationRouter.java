package com.example.home_pc.project;

import android.content.Context;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import com.example.home_pc.project.Fragments.BaseFragment;
import com.example.home_pc.project.Fragments.FavoritesFragment;
import com.example.home_pc.project.Fragments.MainFragment;
import com.example.home_pc.project.Fragments.PicturesFragment;

public class NavigationRouter {
    private static final int MAIN_PAGE = 0;
    private static final int IMAGE_PAGE = 1;
    private static final int FAVORITE_PAGE = 2;
    private static final String TAG_VISIBLE = "visible";
    private Context context;
    private NavigationView navigationView;
    private DrawerLayout drawer;

    public NavigationRouter(Context context, NavigationView navigationView, DrawerLayout drawer) {
        this.context = context;
        this.navigationView = navigationView;
        this.drawer = drawer;
    }

    public int selectItem(int identifier) {
        View view = navigationView;
        BaseFragment container = new MainFragment();
       NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        switch (identifier) {
            case MAIN_PAGE:
                container = new MainFragment();
                break;
            case IMAGE_PAGE:
                container = new PicturesFragment();
                break;
            case FAVORITE_PAGE:
                container = new FavoritesFragment();
                break;
        }
        navigationView.getMenu().getItem(identifier).setChecked(true);
        FragmentActivity fragAct = (FragmentActivity) context;
        FragmentTransaction fragmentTransaction = fragAct.getSupportFragmentManager().beginTransaction().replace(R.id.container, container, TAG_VISIBLE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        View viewDrawer = drawer;
        DrawerLayout drawer = viewDrawer.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        String title = container.setTitles();
        ((FragmentActivity) context).setTitle(title);
        return identifier;
    }

    public int switchFragment(int identifire) {
        View view = drawer;
        FragmentActivity fragAct = (FragmentActivity) context;
        BaseFragment container = (BaseFragment) fragAct.getSupportFragmentManager().findFragmentByTag(TAG_VISIBLE);
        if (container instanceof MainFragment) {
            identifire = MAIN_PAGE;
        } else if (container instanceof PicturesFragment) {
            identifire = IMAGE_PAGE;
        } else {
            identifire = FAVORITE_PAGE;
        }

        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);
        navigationView.getMenu().getItem(identifire).setChecked(true);
        String title = container.setTitles();
        ((FragmentActivity) context).setTitle(title);
        return identifire;
    }
}
