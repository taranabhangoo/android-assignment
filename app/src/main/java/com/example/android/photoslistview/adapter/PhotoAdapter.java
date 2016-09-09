package com.example.android.photoslistview.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.photoslistview.R;
import com.example.android.photoslistview.models.PhotoBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Dell I7 on 9/8/2016.
 */
/**
 * This is a custom adapter to display items from url
 * to the listview
 */
public class PhotoAdapter extends BaseAdapter {

    Context mContext = null;
    ArrayList<PhotoBean> mPhotoList = null;
    LayoutInflater mLayoutInflater = null;
    ImageLoader mImageLoader = ImageLoader.getInstance();

    public static class ViewHolder{
        ImageView imageView;
        TextView textView;
    }

    public PhotoAdapter(Context context, ArrayList<PhotoBean> photoList){
        mContext = context;
        mPhotoList = photoList;
        mLayoutInflater = LayoutInflater.from(context);
        mImageLoader = ImageLoader.getInstance();
    }
    @Override
    public int getCount() {
        return mPhotoList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.image_view);
            holder.textView = (TextView) view.findViewById(R.id.text_view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        PhotoBean photoItem = mPhotoList.get(i);
        holder.textView.setText(i + " - " + photoItem.getTitle());
        if (holder.imageView != null) {
            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                    .cacheOnDisc(true).resetViewBeforeLoading(true)
                    .build();

            mImageLoader.displayImage(photoItem.getThumbnailUrl(), holder.imageView, options);
        }
        return view;
    }
}
