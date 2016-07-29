package com.albertzhang.accelarchwizard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ImpulseInfoFragment extends Fragment {


    public ImpulseInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x = inflater.inflate(R.layout.fragment_impulse_info, container, false);
        x.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        // Inflate the layout for this fragment
        return x;
    }

}
