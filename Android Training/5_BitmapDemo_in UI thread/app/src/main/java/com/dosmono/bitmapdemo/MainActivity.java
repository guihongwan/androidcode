package com.dosmono.bitmapdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imagevieView);
        Log.d("~~", "reqW:H"+imageView.getLayoutParams().width+":"+imageView.getLayoutParams().height);

        imageView.setImageBitmap(decodeSampledBitmapFromResource(
                getResources(),R.drawable.test,imageView.getLayoutParams().width,imageView.getLayoutParams().height));
    }
    private static Bitmap decodeSampledBitmapFromResource(Resources resources, int resId,
                                                          int reqWidth, int reqHeight){

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources,resId,options);
        int width = options.outWidth;
        int height = options.outHeight;
        String type = options.outMimeType;
        Log.d("~~~","w:h:type"+width+":"+height+":"+type);

        options.inSampleSize = calculateInSampleSize(options, reqWidth,reqHeight);
        Log.d("~~~","inSampleSize:"+ options.inSampleSize);

        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources,resId,options);
    }
    private static int calculateInSampleSize(
        BitmapFactory.Options options, int reqWidth, int reqHight
    ){
        final int height = options.outHeight;
        final int width = options.outWidth;
        int insampleSize = 1;
        if(height>reqHight || width>reqWidth){
            final int halfHeight = height/2;
            final int halfwidth = width/2;

            //Calculate the largest inSampleSize vaule that is a power of 2 and keeps
            //both height and width larger than the requested height and width.
            while ((halfHeight/insampleSize) >= reqHight &&
                    (halfwidth/insampleSize) >= reqWidth ){
                insampleSize *= 2;
            }
        }
        return insampleSize;
    }
}
