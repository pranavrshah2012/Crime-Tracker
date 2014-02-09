package com.example.camerawithbuttons;

import android.os.*;
import android.view.*;
//import android.media.MediaPlayer;
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
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	protected void onResume(){
		super.onResume();
	}
	
	
	protected void onPause(){
	  super.onPause();
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
