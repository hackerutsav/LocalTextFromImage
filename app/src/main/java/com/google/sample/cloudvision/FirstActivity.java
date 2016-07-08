package com.google.sample.cloudvision;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jaeger.ninegridimageview.NineGridImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private SharedPreferences mSettings;
    private ArrayList arrayList= new ArrayList<>();
    private int i;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

//        gridView = (GridView) findViewById(R.id.gridView);
        mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mSettings.edit();
        if (mSettings.getString("size","empty").equals("empty")) {
            editor.putString("size", "0");
            editor.commit();
        }
//        String size = mSettings.getString("size","0");
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.imageLayout);
        layout.removeAllViews();
        String size = mSettings.getString("size","0");

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) FirstActivity.this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);



        for (i = 0; i < Integer.parseInt(size); i++) {
            ImageView image = new ImageView(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.height = Math.round(150 * displayMetrics.density);
            params.width = Math.round(80 * displayMetrics.density);
            params.leftMargin = Math.round(40 * displayMetrics.density);
            image.setId(i);
            image.setTag(String.valueOf(i));

            arrayList.add(i,image.getId());


            Log.d("arrayids",String.valueOf(arrayList.get(i)));

            if (i%2==0 && i!=0) {
                Log.d("insideeven","true");
                params.addRule(RelativeLayout.BELOW, (int) arrayList.get(i - 2));
            }

            else if (i!=0 ){
                Log.d("insideodd","true");
                params.addRule(RelativeLayout.RIGHT_OF, (int)arrayList.get(i-1));
                params.addRule(RelativeLayout.ALIGN_BOTTOM, (int)arrayList.get(i-1));
            }


            Log.d("addviewcalled",String.valueOf(i));
            if (i!=0 && i!=1 && i!=2) {
                layout.addView(image, params);
            }
            else if (i==0) {
                layout.addView(image,params);
            }
            else if (i==1)
            {   params.leftMargin = Math.round(160 * displayMetrics.density);
                layout.addView(image,params);
            }

            else if (i==2)
            {
                params.topMargin = Math.round(160 * displayMetrics.density);
                layout.addView(image,params);
            }

            loadImageFromStorage(i,image);
            Log.d("iamgeview",image.toString());

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("insideonclick","true");
                    String val = (String)view.getTag();
                    Log.d("tagofview",val);
                    Intent inten = new Intent(FirstActivity.this,ImageActivity.class);
                    inten.putExtra("pos",val);
                    startActivity(inten);
                }
            });
        }
        ImageView cam = (ImageView)findViewById(R.id.cam);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(FirstActivity.this,MainActivity.class);
                startActivity(inten);
            }
        });
        cam.bringToFront();
    }

    // Prepare some dummy data for gridview
//    private ArrayList<ImageItem> getData() {a
//        final ArrayList<ImageItem> imageItems = new ArrayList<>();
//        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
//        for (int i = 0; i < imgs.length(); i++) {
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
//            imageItems.add(new ImageItem(bitmap, "Image#" + i));
//        }
//        return imageItems;
//    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d("onresumecalled","true");
//        String size = mSettings.getString("size","0");
//        Log.d("firstactsize",size);
//        arrayList = new ArrayList<String>();
//        for (int i=0;i<Integer.parseInt(size);i++)
//        {
//            arrayList.add(i,"abcd");
//        }
//        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout,arrayList);
//        gridView.setAdapter(gridAdapter);
//    }
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.imageLayout);
        layout.removeAllViews();
        String size = mSettings.getString("size","0");

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) FirstActivity.this.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);



        for (i = 0; i < Integer.parseInt(size); i++) {
            ImageView image = new ImageView(this);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.height = Math.round(150 * displayMetrics.density);
            params.width = Math.round(80 * displayMetrics.density);
            params.leftMargin = Math.round(40 * displayMetrics.density);
            image.setId(i);
            image.setTag(String.valueOf(i));

            arrayList.add(i,image.getId());


            Log.d("arrayids",String.valueOf(arrayList.get(i)));

            if (i%2==0 && i!=0) {
                Log.d("insideeven","true");
                params.addRule(RelativeLayout.BELOW, (int) arrayList.get(i - 2));
            }

            else if (i!=0 ){
                Log.d("insideodd","true");
                params.addRule(RelativeLayout.RIGHT_OF, (int)arrayList.get(i-1));
                params.addRule(RelativeLayout.ALIGN_BOTTOM, (int)arrayList.get(i-1));
            }


            Log.d("addviewcalled",String.valueOf(i));
            if (i!=0 && i!=1 && i!=2) {
                layout.addView(image, params);
            }
            else if (i==0) {
                layout.addView(image,params);
            }
            else if (i==1)
            {   params.leftMargin = Math.round(160 * displayMetrics.density);
                layout.addView(image,params);
            }

            else if (i==2)
            {
                params.topMargin = Math.round(160 * displayMetrics.density);
                layout.addView(image,params);
            }

            loadImageFromStorage(i,image);
            Log.d("iamgeview",image.toString());

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("insideonclick","true");
                    String val = (String)view.getTag();
                    Log.d("tagofview",val);
                    Intent inten = new Intent(FirstActivity.this,ImageActivity.class);
                    inten.putExtra("pos",val);
                    startActivity(inten);
                }
            });
        }
    }


        private void loadImageFromStorage(int val, ImageView img) {

        Log.d("val", String.valueOf(val));

        try {
            String path = mSettings.getString("path", "empty");
            Log.d("path",path);

            File f = new File(path, String.valueOf(val)+".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

            img.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
