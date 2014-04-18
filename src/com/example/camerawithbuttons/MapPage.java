package com.example.camerawithbuttons;

import android.app.Activity;
import com.google.android.maps.MapActivity;
import android.os.Bundle;
import android.content.Intent;

//to get content back
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class MapPage extends Activity {
	

@Override
protected void onCreate(Bundle savedInstanceState) {
	
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.map);
	
//	 final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
//	 galleryIntent.setType("*/*");
//     startActivityForResult(galleryIntent, RESULT_LOAD_CONTENT);
	
}

//@Override
//protected boolean isRouteDisplayed() {
//	// TODO Auto-generated method stub
//	return false;
//}

}

