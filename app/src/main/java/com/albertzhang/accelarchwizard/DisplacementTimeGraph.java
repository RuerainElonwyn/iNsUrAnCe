package com.albertzhang.accelarchwizard;


import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import jp.wasabeef.recyclerview.animators.FadeInRightAnimator;
import jp.wasabeef.recyclerview.animators.FlipInRightYAnimator;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplacementTimeGraph extends Fragment{

    public String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AccelArchwizard_trackerData";


    public static ArrayList<File> fileList = new ArrayList<>();//This has to stay in memory throughout the course of the app

    public static ArrayList<Double> dataIn = new ArrayList<>();

    public static ArrayList<Double> dataIntegrated = new ArrayList<>();
    public static ArrayList<Double> dataX = new ArrayList<>();
    public static ArrayList<Double> dataY = new ArrayList<>();
    //public static int clickcount = 0;

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager gridLayoutManager;
    private FileListAdapterDisplacement adapter;


    public DisplacementTimeGraph() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View x = inflater.inflate(R.layout.fragment_displacement_time_graph, container, false);

        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AccelArchwizard_trackerData";
        File dir = new File(filePath);
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                fileList.add(files[i]);
            }
        }


        recyclerView = (RecyclerView) x.findViewById(R.id.list);
        gridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new FadeInRightAnimator());//TODO MAKE NICER ANIMATION
        recyclerView.getItemAnimator().setRemoveDuration(500);
        recyclerView.getItemAnimator().setMoveDuration(500);
        recyclerView.getItemAnimator().setChangeDuration(500);

        adapter = new FileListAdapterDisplacement(x.getContext());

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new FileListAdapterDisplacement.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AccelArchwizard_trackerData";
                File file = new File(filePath + File.separator + fileList.get(position).getName());
                try {
                    //FileReader fileReader = new FileReader(file);
                    //BufferedReader bufferedReader = new BufferedReader(fileReader);
                    //String s;

                    Scanner scanner = new Scanner(file);

                    /*while ((s = bufferedReader.readLine()) != null) {
                        dataIn.add(Double.parseDouble(s));
                        Log.d("Data", s);
                    }*/
                    while (scanner.hasNext()) {
                        scanner.useDelimiter(",");
                        dataIn.add(Double.parseDouble(scanner.next()));
                    }

                    for(int i = 0; i < dataIn.size(); i = i + 2)
                    {
                        dataX.add(dataIn.get(i));
                        dataY.add(dataIn.get(i+1));
                    }

                    ArrayList<Double> dataIntegratedstageone = IntegrateTrapeziumAlg(dataIn);
                    dataIntegrated = IntegrateTrapeziumAlgYargument(dataIntegratedstageone);

                    if (dataIntegrated.size() > 0) {

                        try {
                            Intent intent = new Intent(DisplacementTimeGraph.this.getActivity(), PlotterClassDisplacement.class);
                            startActivity(intent);
                        } catch (RuntimeException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(DisplacementTimeGraph.this.getActivity());
                            builder.setIcon(R.drawable.acceleration_vector_white);
                            builder.setTitle("File is corrupted!");
                            builder.setMessage("Please delete this file and use a new one");
                        }


                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });


        return x;
    }

    private ArrayList<Double> IntegrateTrapeziumAlg(ArrayList<Double> data) {

        //ArrayList<Double> dataX = new ArrayList<Double>();
        //ArrayList<Double> dataY = new ArrayList<Double>();

        /*for(int i = 0; i < data.size(); i = i + 2)
        {
            dataX.add(data.get(i));
            dataY.add(data.get(i+1));
        }*/

        ArrayList<Double> areaTrapeziumBits = new ArrayList<Double>();

        for(int j = 0; j < dataX.size(); j++) // note that if(dataX.size() == dataY.size()) returns true
        {
            if(j == 0)
            {
                areaTrapeziumBits.add((dataY.get(j)/2) * dataX.get(j));
            }
            else
            {
                areaTrapeziumBits.add((dataY.get(j) + dataY.get(j-1)/2) * (dataX.get(j) - dataX.get(j-1)));
            }
        }

        ArrayList<Double> integratedSeries = new ArrayList<Double>();
        Double adder = 0.0;

        for(int k = 1; k < areaTrapeziumBits.size(); k++)
        {
            for(int l = 0; l < k; l++)
            {
                adder = adder + areaTrapeziumBits.get(l);
            }
            integratedSeries.add(adder);
            adder = 0.0;
        }

        return integratedSeries;

    }

    private ArrayList<Double> IntegrateTrapeziumAlgYargument(ArrayList<Double> data) {


        ArrayList<Double> areaTrapeziumBits = new ArrayList<Double>();

        for(int j = 0; j < dataX.size()-1; j++) // note that dataX is the time X axis
        {
            if(j == 0)
            {
                areaTrapeziumBits.add((data.get(j)/2) * dataX.get(j));
            }
            else
            {
                areaTrapeziumBits.add((data.get(j) + data.get(j-1)/2) * (data.get(j) - data.get(j-1)));
            }
        }

        ArrayList<Double> integratedSeries = new ArrayList<Double>();
        Double adder = 0.0;

        for(int k = 1; k < areaTrapeziumBits.size(); k++)
        {
            for(int l = 0; l < k; l++)
            {
                adder = adder + areaTrapeziumBits.get(l);
            }
            integratedSeries.add(adder);
            adder = 0.0;
        }

        return integratedSeries;

    }


}
