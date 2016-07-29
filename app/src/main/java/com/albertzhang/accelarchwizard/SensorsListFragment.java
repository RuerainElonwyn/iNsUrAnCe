package com.albertzhang.accelarchwizard;


import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SensorsListFragment extends Fragment {

    private SensorManager sensorManager;
    private ListView listView;

    public SensorsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get ListView object from xml
        View x =  inflater.inflate(R.layout.fragment_sensors_list, null);
        listView = (ListView) x.findViewById(R.id.list);

        /**
         * instantiate the object of SensorManager class
         */
        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> mList= sensorManager.getSensorList(Sensor.TYPE_ALL);
        //TODO FORMAT THE SENSOR LIST

        ArrayAdapter<Sensor> adapter = new ArrayAdapter<Sensor>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, mList);
        listView.setAdapter(adapter);

        x.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        // Inflate the layout for this fragment
        return x;
    }

}
