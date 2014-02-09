package com.example.camerawithbuttons;

import android.os.*;
import android.view.*;
import android.text.format.Time;
import java.text.*;
import android.util.*;

import java.util.*;
import java.io.*;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Button;

public class MainActivity extends Activity {
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 200;
	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	private static final String myCamera = "Camera";
	private static final String DateTag = "Date Activity";
	String date, time ="";
	Calendar rightNow;
	Date d;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	protected void onResume(){
		super.onResume();
		Log.v("Function", "in onResume()");
		
		rightNow = Calendar.getInstance();		
		d = rightNow.getTime(); //only used for checking
		Time now = new Time();
		now.setToNow();		
//w		Log.v(DateTag, "now from time = "+now); //checking only
		
		//full time with date month year, time min seconds..
		DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		date = df.format(Calendar.getInstance().getTime());
		Log.v("datetag", " date please ="+date);
		DateFormat tf = new SimpleDateFormat("hh:mm:ss");
		time = tf.format(Calendar.getInstance().getTime()); 
		Log.v("datetag", "time please =" +time);
//		Log.v(DateTag, "rightnow=" +d);

	}
	
	protected void onStart(){
		super.onStart();
		Log.v("Function", "in onStart()");
		  
		}
	
	protected void onPause(){
		super.onPause();
		Log.v("Function", "in onPause()");
	  	}
	
	protected void onStop(){
		  super.onStop();
		  Log.v("Function", "in onStop()");
		}
		
	protected void onDestroy(){
		  super.onDestroy();
		  Log.v("Function", "in onDestroy()");
		}
		
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void captureImage(View v){
        Log.v("my camera", "a new image can be captured from this function");
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		File imagesFolder = new File(Environment.getExternalStorageDirectory(), "MyImages1");
        imagesFolder.mkdirs(); 
        File image = new File(imagesFolder, "IMAGE_001.JPG");
        Uri uriSavedVideo = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedVideo);// set the image file name
        startActivityForResult(intent,CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
	public void captureVideo(View v){
		Log.v("my camera", "a new video can be captured from this function");
		Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
	        File videosFolder = new File(Environment.getExternalStorageDirectory(), "MyVideos1");
	       videosFolder.mkdirs(); 
	        File video = new File(videosFolder, "video_001.mp4");
	        Uri uriSavedVideo = Uri.fromFile(video);
	        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedVideo);// set the video file name
	        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); // set the video quality to high
	        startActivityForResult(intent,CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE);	
	}

}
