package com.example.home_pc.project.Presentor;

import android.content.Context;

import com.example.home_pc.project.Model.NavigationRouter;

/**
 * Created by HOME_PC on 16.11.2017.
 */

public class PresentorImpl implements Presentor {

    @Override
    public void openFragment(int id, Context context) {
        NavigationRouter navigationRouterInstance = NavigationRouter.getInstance(context);
        navigationRouterInstance.openFragment(id);
    }

    @Override
    public void handleTapInMainActivity(int id, Context context) {
        openFragment(id, context);
    }
}
