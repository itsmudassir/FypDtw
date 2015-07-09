package com.example.zone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;
import android.content.Intent;
 
import android.view.View;

import net.sf.javaml.distance.fastdtw.dtw.DTW;
import net.sf.javaml.distance.fastdtw.timeseries.TimeSeries;
import net.sf.javaml.distance.fastdtw.timeseries.TimeSeriesPoint;

import android.R.layout;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class Triangle extends Activity implements SensorEventListener ,
OnClickListener {
    protected static final String TAG = null;

	ArrayList<TimeDataContainer> list = new ArrayList<TimeDataContainer>();

	Sensor Accelerometer;
	SensorManager sensorManager;
	TextView txt,txt2,txt3;
    TimeSeries series1 = new TimeSeries(3);
    TimeSeries series2 = new TimeSeries(3);

	 
 ArrayList<AccelData> sensorData;
Button btnStart, btnStop,btn,btnSeries,btnSeries2,dtw,btnCircle,btnClearSeries,
btnTriangle;
	private boolean started = false;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.triangle);
        btnStart= (Button) findViewById(R.id.btnStart);
       // btnStop= (Button) findViewById(R.id.btnStop);
        btn= (Button) findViewById(R.id.button1);
        
        btnSeries= (Button) findViewById(R.id.btnSeries);
        btnSeries2= (Button) findViewById(R.id.btnSeries2);
        dtw= (Button) findViewById(R.id.dtw);
        btnClearSeries= (Button) findViewById(R.id.btnClearSeries);

        btnCircle= (Button) findViewById(R.id.btnCircle);
        btnTriangle= (Button) findViewById(R.id.btnTriangle);





        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        Accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //sensorManager.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        txt= (TextView) findViewById(R.id.text);
        txt2= (TextView) findViewById(R.id.textView);
        txt3= (TextView) findViewById(R.id.textView2);
      
        
        
        btnClearSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(v.getId() == R.id.btnClearSeries){
            		series1.clear();
            		series2.clear();
            		txt2.setText("...");
            		txt3.setText("Answer:?");
            		
            		
            		
            		
            	}
              
                    

            	
            }
        });
        
        
        
        
        btnCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	
               Intent myIntent = new Intent(view.getContext(), MainActivity.class);
                        startActivityForResult(myIntent, 0);
                    

            	
            }
        });
        btnTriangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	
               Intent myIntent = new Intent(view.getContext(), Triangle.class);
                        startActivityForResult(myIntent, 0);
                    

            	
            }
        });
        
        
        
        
        
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(v.getId() == R.id.button1){
            		started = false;
            	//sensorManager.unregisterListener(this);
            		txt2.setText("i am clicked!");
        

            	}
            }
        });
        
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(v.getId() == R.id.btnStart){
            		list.clear();
            		started = true;
            	//sensorManager.unregisterListener(this);
            		txt2.setText("i am clicked, Start!");
        

            	}
            }
        });
        
        
        btnSeries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(v.getId() == R.id.btnSeries){
            		
                    list = new ArrayList<TimeDataContainer>(new TreeSet<TimeDataContainer>(list));
                    Collections.sort(list);
                    for (TimeDataContainer container : list) {
                        Log.d(TAG, "Adding " + container + " to TimeSeries");
                        series1.addLast(container.getTime(), new TimeSeriesPoint(container.getData()));


            		txt2.setText("i am Button Series1!");
        

            	}
            }}
        });
        
        btnSeries2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(v.getId() == R.id.btnSeries2){
            		
                    list = new ArrayList<TimeDataContainer>(new TreeSet<TimeDataContainer>(list));
                    Collections.sort(list);
                 
                    for (TimeDataContainer container : list) {
                        Log.d(TAG, "Adding " + container + " to TimeSeries");
                        series2.addLast(container.getTime(), new TimeSeriesPoint(container.getData()));


            		txt2.setText("i am Button Series2!");
        

            	}
            }}
        });
        
        
        
        dtw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(v.getId() == R.id.dtw){
            		
                   
            		 String answer = "Answer: " + DTW.getWarpDistBetween(series1, series2);
            	        txt3.setText(answer);

            		txt2.setText("i am Button DTW!");
        

            	}
            }
        });
        
        
        sensorManager.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        
        
 
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        
        return true;
    }


	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}


	

protected void onResume() {
    super.onResume();
   // sensorManager.registerListener(this, Accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    //sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    //sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
}
protected void onPause() {
    super.onPause();
  //  if (started == true) {
	//	sensorManager.unregisterListener(this);
	//}
}




@Override
public void onClick(DialogInterface dialog, int which) {
	// TODO Auto-generated method stub
	
}

@Override
public void onSensorChanged(SensorEvent event) {
	// TODO Auto-generated method stub
	

	 txt.setText("acc X:not MOVED");
   if(started){
 		double x = event.values[0];
		double y = event.values[1];
		double z = event.values[2];
		if(x!=0){
		  txt.setText("acc X:MOVED");}
		long time = System.currentTimeMillis();
		
		TimeDataContainer container = new TimeDataContainer(time,x,y,z);
        list.add(container);
	
   } 
 /*  elseif(){
	   
	   sensorManager.unregisterListener(this);
		txt3.setText("i am reacher");  
   }
	*/
}







}