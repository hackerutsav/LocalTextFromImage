package com.google.sample.cloudvision;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Jaeger on 16/2/24.
 *
 * Email: chjie.jaeger@gamil.com
 * GitHub: https://github.com/laobie
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private LayoutInflater mInflater;
    private static int i =0;
    private List<Post> mPostList;
    private int mShowStyle;
    private Context context;

    public PostAdapter(Context context, List<Post> postList, int showStyle) {
        super();
        mPostList = postList;
        mInflater = LayoutInflater.from(context);
        mShowStyle = showStyle;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.bind(mPostList.get(position));
    }

    @Override
    public int getItemCount() {
        SharedPreferences mSettings = PreferenceManager.getDefaultSharedPreferences(context);
        String size = mSettings.getString("size", "empty");
        Log.d("size",size);
        return Integer.parseInt(size)+1;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return new PostViewHolder(mInflater.inflate(R.layout.item_post_grid_style, parent, false));

    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private NineGridImageView mNglContent;
        private TextView mTvContent;

        private NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, String s) {
//                Picasso.with(context)
//                        .load(s)
//                        .placeholder(R.mipmap.ic_launcher)
//                        .into(imageView);
                loadImageFromStorage(i,imageView);
                i++;
            }

            @Override
            protected ImageView generateImageView(Context context) {
                return super.generateImageView(context);
            }

            @Override
            protected void onItemImageClick(Context context, int index, List<String> list) {
                Intent intent = new Intent(context,ImageActivity.class);
                intent.putExtra("pos", String.valueOf(index));
                context.startActivity(intent);
                Toast.makeText(context, "image position is " + index, Toast.LENGTH_SHORT).show();
            }
        };

        public PostViewHolder(View itemView) {
            super(itemView);
            mTvContent = (TextView) itemView.findViewById(R.id.tv_content);
            mNglContent = (NineGridImageView) itemView.findViewById(R.id.ngl_images);
            mNglContent.setAdapter(mAdapter);
        }

        public void bind(Post post) {
            mNglContent.setImagesData(post.getImgUrlList());
            mTvContent.setText(post.getContent());
        }
    }

    private void loadImageFromStorage(int val,ImageView img)
    {

        try {
            ContextWrapper cw = new ContextWrapper(context);
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File f=new File(directory,String.valueOf(i)+".jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
//            ImageView img=(ImageView)findViewById(R.id.imgPicker);
            img.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}

