package com.dosmono.bitmapdemo;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    //when the real image is not completely loaded. This image will be displayed in the ImageView.
    private Bitmap placeHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imagevieView);
        int reqWidth = imageView.getLayoutParams().width;
        int reqHight = imageView.getLayoutParams().height;
        Log.d("~~", " getLayoutParams W:H"+imageView.getLayoutParams().width+":"+imageView.getLayoutParams().height);
        placeHolder = BitmapFactory.decodeResource(getResources(),R.drawable.placeholder);

        loadBitmap(R.drawable.test,reqWidth,reqHight,imageView);

    }
    public void loadBitmap(int resId, int reqWidth, int reqHeight, ImageView imageView) {
        if(cancelPotentialWork(resId, imageView)) {
            final BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            final AsynDrawable asynDrawable = new AsynDrawable(getResources(),placeHolder,task);
            imageView.setImageDrawable(asynDrawable);
            task.execute(resId, reqWidth, reqHeight);
        }
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
        private final WeakReference<ImageView> imageViewWeakReference;
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
            try{
                Thread.sleep(5000);//1s
            }catch(Exception e){
                e.printStackTrace();
            }
            return decodeSampledBitmapFromResource(getResources(), dataID, reqWidth, reqHeight);
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            if(isCancelled()){
                bitmap = null;
            }
            if (imageViewWeakReference != null && bitmap != null){
                final ImageView imageView = imageViewWeakReference.get();
                final BitmapWorkerTask bitmapWorkerTask = getBigmapWorkerTask(imageView);

                if (this == bitmapWorkerTask && imageView != null){
                    imageView.setImageBitmap(bitmap);
                }
            }
        }
    }


    //check if another running task is already associated with ImageView.
    //if so, it attemps to cancel the previous task by calling cancel()
    public static boolean cancelPotentialWork(int data, ImageView imageView){
        final BitmapWorkerTask bitmapWorkerTask = getBigmapWorkerTask(imageView);
        if(bitmapWorkerTask != null){
            final int bitmapData =bitmapWorkerTask.dataID;
            if (bitmapData == 0 || bitmapData != data){
                bitmapWorkerTask.cancel(true);
            }else{
                return false;
            }
        }
        return true;
    }

    private static BitmapWorkerTask getBigmapWorkerTask(ImageView imageView){
        if(imageView != null){
            final Drawable drawable = imageView.getDrawable();
            if(drawable instanceof AsynDrawable){
                final AsynDrawable asynDrawable = (AsynDrawable)drawable;
                return asynDrawable.getBigmapWorkerTask();
            }
        }
        return null;
    }

    //create a dedicated Drawable subclass to store a reference back to the worker task.
    //in this case, a BitmapDrawable is used so that a placeholder image can be displayed
    //in the ImageView while the task completes.
    static class AsynDrawable extends BitmapDrawable{
        private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskWeakReference;

        public AsynDrawable(Resources resources, Bitmap bitmap,
                            BitmapWorkerTask bitmapWorkerTask){
            super(resources,bitmap);
            bitmapWorkerTaskWeakReference = new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
        }

        public BitmapWorkerTask getBigmapWorkerTask(){
            return bitmapWorkerTaskWeakReference.get();
        }
    }
}


