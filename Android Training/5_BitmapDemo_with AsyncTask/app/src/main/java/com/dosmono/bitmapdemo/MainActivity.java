package com.dosmono.bitmapdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imagevieView);
        int reqWidth = imageView.getLayoutParams().width;
        int reqHight = imageView.getLayoutParams().height;
        Log.d("~~", " getLayoutParams W:H"+imageView.getLayoutParams().width+":"+imageView.getLayoutParams().height);

        loadBitmap(R.drawable.test,reqWidth,reqHight,imageView);

    }
    public void loadBitmap(int resId, int reqWidth, int reqHeight, ImageView imageView) {
        BitmapWorkerTask task = new BitmapWorkerTask(imageView);
        task.execute(resId,reqWidth,reqHeight);
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
    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        private WeakReference<ImageView> imageViewWeakReference;
        private int dataID = 0;
        public BitmapWorkerTask(ImageView imageView){
            super();
            imageViewWeakReference = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(Integer...params){
            dataID = params[0];
            int reqWidth = params[1];
            int reqHeight = params[2];
            Log.d("~~", "reqW:H"+reqWidth+":"+reqHeight);
            return decodeSampledBitmapFromResource(getResources(), dataID, reqWidth, reqHeight);
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            if (imageViewWeakReference != null && bitmap != null){
                final ImageView imageView = imageViewWeakReference.get();
                if (imageView != null){
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }
}


