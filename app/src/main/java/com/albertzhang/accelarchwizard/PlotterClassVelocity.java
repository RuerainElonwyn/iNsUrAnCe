package com.albertzhang.accelarchwizard;

import android.app.Activity;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


/**
 * Created by Albert Zhang on 18/4/2016.
 */
public class PlotterClassVelocity extends Activity {

    private static final Random RANDOM = new Random();
    private LineGraphSeries<DataPoint> series;
    private int lastX = 0;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plotgraphvelocity);
        // we get graph view instance
        GraphView graph = (GraphView) findViewById(R.id.graphPlot);
        graph.setTitle("Velocity/Time graph");
        // data
        series = new LineGraphSeries<DataPoint>();
        for(int i = 0; i < VelocityTimeGraph.dataX.size() - 2; i++)
        {
            series.appendData(new DataPoint(VelocityTimeGraph.dataX.get(i), VelocityTimeGraph.dataIntegrated.get(i)), true, 10000); // plot the accel graph, will need to redo this class for the other parameters
        }
        VelocityTimeGraph.dataIn.clear();
        VelocityTimeGraph.dataX.clear();
        VelocityTimeGraph.dataY.clear();
        VelocityTimeGraph.dataIntegrated.clear();
        graph.addSeries(series);
        // customize a little bit viewport
        Viewport viewport = graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(-45);
        viewport.setMaxY(45);
        viewport.setScrollable(true);
        viewport.setScalable(true);

        GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
        gridLabel.setHorizontalAxisTitle("Time/s");
        gridLabel.setVerticalAxisTitle("Velocity/ms^-1");

        counter = 0;
    }

    /*@Override
    protected void onResume() {
        super.onResume();
        // we're going to simulate real time with thread that append data to the graph
        new Thread(new Runnable() {

            @Override
            public void run() {
                // we add 100 new entries
                for (int i = 0; i < 100; i++) {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            //addEntry();

                        }
                    });

                    // sleep to slow down the add of entries
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        // manage error ...
                    }
                }
            }
        }).start();
    }*/


    public void showDataPoints(View v){
        Button button = (Button) findViewById(R.id.showpointsbutton2);

        if(this.counter == 0) {
            series.setDrawDataPoints(true);
            series.setDataPointsRadius(3);
            series.setThickness(2);
            series.setColor(Color.parseColor("#E91E63"));
            this.counter++;

            button.setText("Hide Data Points");
        }
        else{
            series.setDrawDataPoints(false);
            series.setThickness(5);
            series.setColor(Color.parseColor("#0077CC"));
            this.counter--;

            button.setText("Show Data Points");
        }

    }

}
