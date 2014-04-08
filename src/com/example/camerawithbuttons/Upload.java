package com.example.camerawithbuttons;

import android.app.Activity;
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

public class Upload extends Activity {
	private static int RESULT_LOAD_CONTENT = 1;

@Override
protected void onCreate(Bundle savedInstanceState) {
	
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.second_layout);
	
	 final Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
	 galleryIntent.setType("*/*");
     startActivityForResult(galleryIntent, RESULT_LOAD_CONTENT);
	
}


protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
     
    if (requestCode == RESULT_LOAD_CONTENT && resultCode == RESULT_OK && null != data) {
        Uri selectedImage = data.getData();
//        String[] filePathColumn = { MediaStore.Video.Media.DATA};
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        Cursor cursor = getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
         
        //VideoView videoView1 = (VideoView) findViewById(R.id.videoView1);

       // videoView1.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        
        ImageView imageView = (ImageView) findViewById(R.id.imgView);
        imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
     
    }
}

}
