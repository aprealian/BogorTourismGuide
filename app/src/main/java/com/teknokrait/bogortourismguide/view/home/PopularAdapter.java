package com.teknokrait.bogortourismguide.view.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.Wisata;
import com.teknokrait.bogortourismguide.view.dev.RecyclerViewOnItemClickListener;
import com.teknokrait.bogortourismguide.helper.Helper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirius on 3/12/2017.
 */

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private Context context;
    private List<Wisata> wisataList;
    private int selectedPosition;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public PopularAdapter(Context context, @NonNull RecyclerViewOnItemClickListener
                                          recyclerViewOnItemClickListener) {
        this.context = context;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @Override
    public PopularAdapter.PopularViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_popular, parent, false);
        return new PopularAdapter.PopularViewHolder(row);
    }

    @Override
    public void onBindViewHolder(PopularAdapter.PopularViewHolder holder, int position) {
        Wisata wisata = wisataList.get(position);
        holder.getNamaTextView().setText(wisata.getNama());
        holder.getKategoriTextView().setText(wisata.getKategori());
        holder.getAlamatTextView().setText(wisata.getAlamat());
        holder.getRateRatingBar().setNumStars((int)wisata.getRating());
        holder.getRateTextView().setText(String.valueOf(wisata.getRating()));
        loadImage(holder.getWisataImageView() ,position);

    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public PopularAdapter(Context context) {
        this.context = context;
    }


    @Override
    public int getItemCount() {
        if (this.wisataList == null) {
            this.wisataList= new ArrayList<>();
        }
        return wisataList.size();
    }

    public Wisata getItem(int i) {
        setSelectedPosition(i);
        return wisataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void addWisata(Wisata wisata) {
        if (this.wisataList == null) {
            this.wisataList = new ArrayList<>();
        }
        this.wisataList.add(wisata);
    }

    public void addWisataList(List<Wisata> wisatas) {
        if (this.wisataList == null) {
            this.wisataList= new ArrayList<>();
        }
        this.wisataList.addAll(wisatas);
    }

    public void clear() {
        this.wisataList = new ArrayList<>();
    }

    class PopularViewHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {
        private ImageView wisataImageView;
        private TextView namaTextView;
        private TextView kategoriTextView;
        private TextView alamatTextView;
        private RatingBar rateRatingBar;
        private TextView rateTextView;


        public PopularViewHolder(View itemView) {
            super(itemView);
            wisataImageView = (ImageView) itemView.findViewById(R.id.wisataImageView);
            namaTextView = (TextView) itemView.findViewById(R.id.namaTextView);
            kategoriTextView = (TextView) itemView.findViewById(R.id.kategoriTextView);
            alamatTextView = (TextView) itemView.findViewById(R.id.alamatTextView);
            rateRatingBar = (RatingBar) itemView.findViewById(R.id.rateRatingBar);
            rateTextView = (TextView) itemView.findViewById(R.id.rateTextView);
            itemView.setOnClickListener(this);
        }

        public TextView getNamaTextView() {
            return namaTextView;
        }
        public TextView getKategoriTextView() {
            return kategoriTextView;
        }
        public TextView getAlamatTextView() {
            return alamatTextView;
        }
        public ImageView getWisataImageView() {
            return wisataImageView;
        }
        public TextView getRateTextView() {
            return rateTextView;
        }
        public RatingBar getRateRatingBar() {
            return rateRatingBar;
        }


        @Override
        public void onClick(View v) {
            recyclerViewOnItemClickListener.onClick(v, getAdapterPosition());
        }

    }


    public void loadImage(final ImageView wisataImageView, int position) {
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));

        if (wisataList != null && wisataList.get(position).getPhotos() != null && !wisataList.get(position).getPhotos().isEmpty() && wisataList.get(position).getPhotos().size() > 0) {

            if (TextUtils.isEmpty(wisataList.get(position).getPhotos().get(0))) {
                wisataImageView.setImageResource(R.mipmap.ic_launcher);
            } else {
                ImageLoader.getInstance().loadImage("http://mydummyproject.hol.es/get/photo/"+wisataList.get(position).getPhotos().get(0)+"/200", Helper.getOption(), new ImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {

                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        wisataImageView.setImageBitmap(loadedImage);
                    }

                    @Override
                    public void onLoadingCancelled(String imageUri, View view) {

                    }
                });

                ImageLoader.getInstance().displayImage("http://mydummyproject.hol.es/get/photo/"+wisataList.get(position).getPhotos().get(0)+"/200", wisataImageView, Helper.getOption());
            }

        }


    }


}
