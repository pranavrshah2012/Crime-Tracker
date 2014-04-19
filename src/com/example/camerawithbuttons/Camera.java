package com.example.camerawithbuttons;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.app.Activity;

public class Camera extends Activity {
private int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 200;
private int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200; 
Calendar rightNow;
Date d;


	public Camera() {
		// TODO Auto-generated constructor stub
		rightNow = Calendar.getInstance();		
		d = rightNow.getTime(); //only used for checking
	}
	
	
	
	

}
