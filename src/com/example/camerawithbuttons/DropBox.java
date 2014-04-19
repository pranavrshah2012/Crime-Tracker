package com.example.camerawithbuttons;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.exception.DropboxUnlinkedException;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.TokenPair;

public class DropBox extends Activity implements OnClickListener {

	private DropboxAPI<AndroidAuthSession> dropbox;
	boolean flag=false;
	String imagepath = null;
	Bitmap yourSelectedImage;
	private final static String FILE_DIR = "/DropboxSample/";
	private final static String DROPBOX_NAME = "dropbox_prefs";
	private final static String ACCESS_KEY = "oco1e9ge1k2dw2n";
	private final static String ACCESS_SECRET = "k5vp6voggyzniis";
	private boolean isLoggedIn;
	private Button logIn;
	private Button uploadFile;
	//private Button listFiles;
	private LinearLayout container;
	File imageFile = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dropbox);
		logIn = (Button) findViewById(R.id.dropbox_login);
		logIn.setOnClickListener(this);
		uploadFile = (Button) findViewById(R.id.upload_file);
		uploadFile.setOnClickListener(this);
//		listFiles = (Button) findViewById(R.id.list_files);
//		listFiles.setOnClickListener(this);
		container = (LinearLayout) findViewById(R.id.container_files);

		loggedIn(false);

		AndroidAuthSession session;
		AppKeyPair pair = new AppKeyPair(ACCESS_KEY, ACCESS_SECRET);

		SharedPreferences prefs = getSharedPreferences(DROPBOX_NAME, 0);
		String key = prefs.getString(ACCESS_KEY, null);
		String secret = prefs.getString(ACCESS_SECRET, null);

		if (key != null && secret != null) {
			AccessTokenPair token = new AccessTokenPair(key, secret);
			session = new AndroidAuthSession(pair, AccessType.APP_FOLDER, token);
		} else {
			session = new AndroidAuthSession(pair, AccessType.APP_FOLDER);
		}
		dropbox = new DropboxAPI<AndroidAuthSession>(session);
	}

	@Override
protected void onResume() {
super.onResume();

AndroidAuthSession session = dropbox.getSession();
	if (session.authenticationSuccessful()) {
		try {
session.finishAuthentication();

TokenPair tokens = session.getAccessTokenPair();
SharedPreferences prefs = getSharedPreferences(DROPBOX_NAME, 0);
Editor editor = prefs.edit();
editor.putString(ACCESS_KEY, tokens.key);
editor.putString(ACCESS_SECRET, tokens.secret);
editor.commit();

loggedIn(true);
		} catch (IllegalStateException e) {
Toast.makeText(this, "Error during Dropbox authentication", Toast.LENGTH_SHORT).show();
	  }
	}
}

	public void loggedIn(boolean isLogged) {
		isLoggedIn = isLogged;
		
		//uploadFile and listfiles are buttons, who are set to true and false accordingly.
		uploadFile.setEnabled(isLogged);
//		listFiles.setEnabled(isLogged);
		logIn.setText(isLogged ? "Log out" : "Log in");
	}

	private final Handler handler = new Handler() {
		public void handleMessage(Message msg) {

			ArrayList<String> result = msg.getData().getStringArrayList("data");

			for (String fileName : result) {
				Log.i("ListFiles", fileName);
				TextView tv = new TextView(DropBox.this);
				tv.setText(fileName);

				container.addView(tv);
			}
		}
	};
	
	//just added
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {       
		
		if (requestCode == 1 && resultCode == RESULT_OK) {
            //Bitmap photo = (Bitmap) data.getData().getPath(); 
//	        Bundle bundle = data.getExtras();

            Uri selectedImageUri = data.getData();
            imagepath = getPath(selectedImageUri);
            imageFile = new File(imagepath);
            if(imageFile !=null)
            	flag=true;
             
        }
    }
         public String getPath(Uri uri) {
                String[] projection = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                
                int columnIndex = cursor.getColumnIndex(projection[0]);
                String filePath = cursor.getString(columnIndex);
//                cursor.close();


                yourSelectedImage = BitmapFactory.decodeFile(filePath);
                return filePath;
                //return cursor.getString(column_index);
            }
         //

	@Override
public void onClick(View v) {
switch (v.getId()) {
case R.id.dropbox_login:

if (isLoggedIn) {
dropbox.getSession().unlink();
loggedIn(false);
} else {
dropbox.getSession().startAuthentication(DropBox.this);
}

break;

//case R.id.list_files:
//ListFile list = new ListFile(dropbox, FILE_DIR,handler);
//list.execute();
//break;

case R.id.upload_file:

// doesnt work
	if(flag==false)
	{
	 final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
	 galleryIntent.setType("*/*");	
    startActivityForResult(galleryIntent, 1);
	}

    
//    ===================
    
    
//    
//    
//	File tmpFile = new File(imagepath);
//	Log.e("path", imagepath);
//	
//
//
//    try {
//		FileInputStream fis = new FileInputStream(tmpFile);
//		
//         dropbox.putFileOverwrite(FILE_DIR, fis, tmpFile.length(), null);
//
//    } catch (DropboxUnlinkedException e) {
//        Log.e("DbExampleLog", "User has unlinked.");
//    } catch (DropboxException e) {
//        Log.e("DbExampleLog", "Something went wrong while uploading.");
//    }
//
// catch (FileNotFoundException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}break;
//
if(flag){
uploadExample uploadSample = new uploadExample(this, dropbox, FILE_DIR, imageFile);
uploadSample.execute();
flag=false;
break;
}
//UploadFile upload = new UploadFile(this, dropbox,FILE_DIR);
//upload.execute();


default:break;
	}
	}
}