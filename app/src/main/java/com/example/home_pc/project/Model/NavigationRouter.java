package com.example.home_pc.project.Model;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.example.home_pc.project.Fragments.FragmentForFavoritesPage;
import com.example.home_pc.project.Fragments.FragmentForMainPage;
import com.example.home_pc.project.Fragments.FragmentForPicturesPage;
import com.example.home_pc.project.R;

public class NavigationRouter implements Model  {
    private static final int MAIN_PAGE = 0;
    private static final int IMAGE_PAGE = 1;
    private static final int FAVORITE_PAGE = 2;
    private static final String TAG_VISIBLE = "visible";
    private static Context mContext;
    private static NavigationRouter instance;

    public static NavigationRouter getInstance(Context context){
        mContext = context;
        if (instance == null){
            instance = new NavigationRouter();
        }
        return instance;
    }

    public void openFragment(int id){
       Fragment show_fragment = new FragmentForMainPage();
        switch (id) {
            case MAIN_PAGE:
                show_fragment = new FragmentForMainPage();
                break;
            case IMAGE_PAGE:
                show_fragment = new FragmentForPicturesPage();
                break;
            case FAVORITE_PAGE:
                show_fragment = new FragmentForFavoritesPage();
                break;
        }
        FragmentActivity fragAct = (FragmentActivity) mContext;
        FragmentTransaction fragmentTransaction = fragAct.getSupportFragmentManager().beginTransaction().replace(R.id.container, show_fragment, TAG_VISIBLE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
