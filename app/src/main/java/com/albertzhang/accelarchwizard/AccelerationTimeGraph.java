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
public class AccelerationTimeGraph extends Fragment{

    public String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AccelArchwizard_trackerData";


    public static ArrayList<File> fileList = new ArrayList<>();//This has to stay in memory throughout the course of the app

    public static ArrayList<Double> dataIn = new ArrayList<>();

    private RecyclerView recyclerView;
    private StaggeredGridLayoutManager gridLayoutManager;
    private FileListAdapter adapter;


    public AccelerationTimeGraph() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View x = inflater.inflate(R.layout.fragment_acceleration_time_graph, container, false);

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

        adapter = new FileListAdapter(x.getContext());

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new FileListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AccelArchwizard_trackerData";
                File file = new File(filePath + File.separator + fileList.get(position).getName());
                try {
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String s;

                    Scanner scanner = new Scanner(file);

                    /*while ((s = bufferedReader.readLine()) != null) {
                        dataIn.add(Double.parseDouble(s));
                        Log.d("Data", s);
                    }*/
                    while (scanner.hasNext()) {
                        scanner.useDelimiter(",");
                        dataIn.add(Double.parseDouble(scanner.next()));
                    }

                    if (dataIn.size() > 0) {

                        try {
                            Intent intent = new Intent(AccelerationTimeGraph.this.getActivity(), PlotterClass.class);
                            startActivity(intent);
                        } catch (RuntimeException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(AccelerationTimeGraph.this.getActivity());
                            builder.setIcon(R.drawable.acceleration_vector);
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


}
