package com.work.vigilantes.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.ByteBuffer;
import java.util.List;

public class GridAdapter extends BaseAdapter{

    private final Context mContext;
    private final List<Uri> imageList;

    public GridAdapter(Context context, List<Uri> imageList){
        this.mContext = context;
        this.imageList = imageList;
    }// End GridAdaper()

    @Override
    public int getCount(){
        return imageList.size();
    }// End getCount()

    @Override
    public long getItemId(int position){
        return 0;
    }// End getItemId()

    @Override
    public Object getItem(int position){
        return null;
    }// End getItem()

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ImageView dummyTextView = new ImageView(mContext);
        Bitmap bitmap = null;
        String[] filePath = {MediaStore.Images.Media.DATA};
        Cursor c = mContext.getContentResolver().query(imageList.get(position),filePath,null,null,null);
        c.moveToFirst();
        int colum = c.getColumnIndex(filePath[0]);
        String picture = c.getString(colum);
        c.close();
        bitmap = BitmapFactory.decodeFile(picture);
        dummyTextView.setImageBitmap(bitmap);
        dummyTextView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        dummyTextView.setLayoutParams(new GridView.LayoutParams(130, 130));
        return dummyTextView;
    }// End getView
}// End class GridAdapter
