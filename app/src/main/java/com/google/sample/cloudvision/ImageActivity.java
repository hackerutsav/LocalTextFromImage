package com.google.sample.cloudvision;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("pos");
            loadImageFromStorage(j);
        }
    }

    private void loadImageFromStorage(String val)
    {

        try {
            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File f=new File(directory,val+".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            ImageView img=(ImageView)findViewById(R.id.image);
            img.setImageBitmap(b);
            TextView textview = (TextView)findViewById(R.id.text);
            SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
            String text = mSettings.getString(val, "empty");
            textview.setText(text);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
