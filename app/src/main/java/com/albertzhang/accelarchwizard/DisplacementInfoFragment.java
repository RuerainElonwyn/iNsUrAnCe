package com.albertzhang.accelarchwizard;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplacementInfoFragment extends Fragment {


    public DisplacementInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Set the stylized text into this tab
        /*String sourceString1 = getString(R.string.displacement);
        View x =  inflater.inflate(R.layout.fragment_notes, null);
        TextView set1 = (TextView) x.findViewById(R.id.about_subtextset);
        set1.setText(Html.fromHtml(sourceString1));*/
        //no need, xml uses html strings
        View x =  inflater.inflate(R.layout.fragment_displacement_info, null);
        x.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        // Inflate the layout for this fragment
        return x;
    }

}
