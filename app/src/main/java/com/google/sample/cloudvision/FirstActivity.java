package com.google.sample.cloudvision;

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
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.jaeger.ninegridimageview.NineGridImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private SharedPreferences mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        gridView = (GridView) findViewById(R.id.gridView);
        mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        String size = mSettings.getString("size","empty");
        Log.d("firstactsize",size);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, new ArrayList(Integer.parseInt(size)));
        gridView.setAdapter(gridAdapter);

        ImageView cam = (ImageView)findViewById(R.id.cam);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(FirstActivity.this,MainActivity.class);
                startActivity(inten);
            }
        });
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
    public void onResume()
    {
        super.onResume();
        String size = mSettings.getString("size","empty");
        Log.d("firstactsize",size);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item_layout, new ArrayList(Integer.parseInt(size)));
        gridView.setAdapter(gridAdapter);
    }


}
