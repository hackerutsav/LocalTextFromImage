package com.google.sample.cloudvision;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jaeger.ninegridimageview.NineGridImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    private RecyclerView mRvPostLister;
    private PostAdapter mNineImageAdapter;
    private List<Post> mPostList;
    private String[] IMG_URL_LIST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        mRvPostLister = (RecyclerView) findViewById(R.id.rv_post_list);
        mRvPostLister.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("size","0");
        IMG_URL_LIST= new String[0];
        editor.commit();


        mPostList = new ArrayList<>();
//        for (int i = 0; i < 18; i++) {
            List<String> imgUrls = new ArrayList<>();
            imgUrls.addAll(Arrays.asList(IMG_URL_LIST));
            Post post = new Post("", imgUrls);
            mPostList.add(post);
//        }

        mNineImageAdapter = new PostAdapter(this, mPostList, NineGridImageView.STYLE_GRID);
        mRvPostLister.setAdapter(mNineImageAdapter);
        ImageView cam = (ImageView)findViewById(R.id.cam);
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstActivity.this,MainActivity.class);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        String size = mSettings.getString("size","empty");
        IMG_URL_LIST= new String[Integer.parseInt(size)];

        mPostList = new ArrayList<>();
//        for (int i = 0; i < 18; i++) {
        List<String> imgUrls = new ArrayList<>();
        imgUrls.addAll(Arrays.asList(IMG_URL_LIST));
        Post post = new Post("", imgUrls);
        mPostList.add(post);
//        }

        mNineImageAdapter = new PostAdapter(this, mPostList, NineGridImageView.STYLE_GRID);
        mRvPostLister.setAdapter(mNineImageAdapter);
    }



}
