package com.example.camerawithbuttons;



import android.app.Activity;
import android.util.*;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.maps.*;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;

//to get content back
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;


public class MapPage extends Activity implements LocationListener {
private GoogleMap googleMap;
public  double lat = 0.0;
public  double lng = 0.0;

MarkerOptions marker;
private LocationManager locationManager;
private String provider;
Criteria criteria;
Location location;
LatLng coordinates;
CameraPosition cameraPosition;

@Override
protected void onCreate(Bundle savedInstanceState) {
	//MainActivity instance = new MainActivity();

	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.map);
	
	 try {
         // Loading map
         initilizeMap();

     } catch (Exception e) {
         e.printStackTrace();
     }

	// Get the location manager
    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    // Define the criteria how to select the locatioin provider -> use
    // default
    Criteria criteria = new Criteria();
    provider = locationManager.getBestProvider(criteria, false);
    Location location = locationManager.getLastKnownLocation(provider);

    // Initialize the location fields
    if (location != null) {
      Log.v("location alert", "Provider " + provider + " has been selected.");
      onLocationChanged(location);
    } else {
      Log.v("Location alert","Location not available");          
    }
    //end of location code

    Log.v("datetag", "abc" + " "+ lat + " " +lng);

	
	
}

protected void onPause(){
	super.onPause();
	Log.v("Function", "in onPause()");
    locationManager.removeUpdates(this);
    
}

public void onLocationChanged(Location location) {
   lat = location.getLatitude();
   lng = location.getLongitude();
   Log.v("Location alert", " "+lat);
   Log.v("Location alert", " "+lng);
   coordinates = new LatLng(lat, lng);
   cameraPosition = new CameraPosition.Builder().target(coordinates).zoom(12).build();
	googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

  }

public void onStatusChanged(String provider, int status, Bundle extras) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onProviderEnabled(String provider) {
    Toast.makeText(this, "Enabled new provider " + provider,
        Toast.LENGTH_SHORT).show();

  }

  @Override
  public void onProviderDisabled(String provider) {
    Toast.makeText(this, "Disabled provider " + provider,
        Toast.LENGTH_SHORT).show();
  }

  private void initilizeMap() {
      if (googleMap == null) {
          googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                  R.id.map)).getMap();

          // check if map is created successfully or not
          if (googleMap == null) {
              Toast.makeText(getApplicationContext(),
                      "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                      .show();
          }
      }
      //disable rotation of map
      googleMap.getUiSettings().setRotateGesturesEnabled(false);
  }

  @Override
  protected void onResume() {
      super.onResume();
      initilizeMap();
      reportLocation();
      
  }

  public void reportLocation (){
//create marker
 marker = new MarkerOptions().position(new LatLng(lat, lng)).title("Hello Maps ");

//adding marker
googleMap.addMarker(marker);
  }


//@Override
//protected boolean isRouteDisplayed() {
//	// TODO Auto-generated method stub
//	return false;
//}

}

