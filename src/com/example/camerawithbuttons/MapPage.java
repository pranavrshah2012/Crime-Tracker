package com.example.camerawithbuttons;

import android.app.Activity;
import android.util.*;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.maps.*;
import android.os.Bundle;
import android.content.Intent;

//to get content back
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;




public class MapPage extends Activity {
private GoogleMap googleMap;


@Override
protected void onCreate(Bundle savedInstanceState) {
	MainActivity instance = new MainActivity();
	LatLng coordinates = new LatLng(instance.lat, instance.lng);
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.map);

    Log.v("datetag", " " +instance.lat + instance.lng);

	
//	CameraPosition cameraPosition = new CameraPosition.Builder().target(coordinates).zoom(12).build();
//			googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	
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

