package com.teknokrait.bogortourismguide.view.wisata;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.teknokrait.bogortourismguide.R;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirius on 5/14/2017.
 */

public class ImageAdapter extends PagerAdapter {

    Context context;
    List<Bitmap> images = new ArrayList<Bitmap>();
    List<String> url = new ArrayList<String>();

    ImageAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return url.size();
    }


    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }


    public void setImages(List<Bitmap> images) {
        this.images = images;
    }

    public Bitmap getImage(int position) {
        return images.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.general_padding);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        //imageView.setImageResource(GalImages[position]);

        new LoadImage(imageView).execute(url.get(position));

        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        ImageView img=null;
        int position;

        public LoadImage(ImageView img){
            this.img=img;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }
        protected Bitmap doInBackground(String... args) {
            Bitmap bitmap=null;
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

                position = Integer.valueOf(args[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                img.setImageBitmap(image);
            }

            images.add(position, image);
        }
    }
}