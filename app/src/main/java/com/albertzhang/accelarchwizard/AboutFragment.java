package com.albertzhang.accelarchwizard;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x =  inflater.inflate(R.layout.fragment_about, null);
        x.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        // Inflate the layout for this fragment
        return x;
    }

}
