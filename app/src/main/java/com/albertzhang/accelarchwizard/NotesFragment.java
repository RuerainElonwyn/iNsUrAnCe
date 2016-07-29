package com.albertzhang.accelarchwizard;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotesFragment extends Fragment {


    public NotesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Set the stylized text into this tab


        View x = inflater.inflate(R.layout.fragment_notes, container, false);
        x.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        // Inflate the layout for this fragment
        return x;
    }

}
