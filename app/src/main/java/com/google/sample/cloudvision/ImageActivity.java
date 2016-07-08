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
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageActivity extends AppCompatActivity {
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        setContentView(R.layout.activity_image);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("pos");
            Log.d("posactima",j);
            int pos = Integer.parseInt(j);
            loadImageFromStorage(String.valueOf(pos));
        }
    }

    private void loadImageFromStorage(String val)
    {

        try {
            String path = mSettings.getString("path", "empty");
            File f=new File(path,val+".jpg");
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
