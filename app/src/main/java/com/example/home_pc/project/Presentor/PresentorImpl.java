package com.example.home_pc.project.Presentor;

import android.content.Context;

import com.example.home_pc.project.Model.Model;
import com.example.home_pc.project.Model.NavigationRouter;
import com.example.home_pc.project.View.MainView;


public class PresentorImpl implements Presentor {

    private MainView mainView;
    private Model modelObject;

    public PresentorImpl(MainView mainView) {
        this.mainView = mainView;
        this.modelObject = new NavigationRouter();
    }

    @Override
    public void sentDataToModel(int id, Context context) {
        if(mainView != null){
            modelObject = NavigationRouter.getInstance(context);
            modelObject.openFragment(id);
        }
    }
}
