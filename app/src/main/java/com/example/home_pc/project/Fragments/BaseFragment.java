package com.example.home_pc.project.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.home_pc.project.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    private static final String TOOLBAR_TITLE_FIRST = "Project";
    private static final String TOOLBAR_TITLE_SECOND = "Картинки";
    private static final String TOOLBAR_TITLE_THIRD = "Избранное";
    private static final int  MAIN_PAGE = 0;
    private static final int  IMAGE_PAGE = 1;
    private static final int  FAVORITE_PAGE = 2;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        return textView;
    }

    public abstract void getScreenName();

    }

