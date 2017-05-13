package com.teknokrait.bogortourismguide.view.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.Promo;
import com.teknokrait.bogortourismguide.data.Wisata;
import com.teknokrait.bogortourismguide.data.WisataList;
import com.teknokrait.bogortourismguide.http.BTGWisataRatingRequest;
import com.teknokrait.bogortourismguide.view.dev.RecyclerViewOnItemClickListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.OkHttpClient;

/**
 * Created by Lian on 2/18/2017.
 */

public class HomeFragment extends Fragment {

    private PopularAdapter popularAdapter;
    private WisataList wisataList;
    private CarouselView carouselView;
    private OnFragmentInteractionListener mListener;
    int[] sampleImages = {R.drawable.promo1, R.drawable.promo2, R.drawable.promo3};

    @InjectView(R.id.progressBar)
    ProgressBar progressBar;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, v);

        Toolbar toolbar = (Toolbar) v.findViewById(R.id.toolbar);
        toolbar.setTitle("Favorit");
        toolbar.setTitleTextColor(Color.WHITE);

        wisataList = new WisataList();

        popularAdapter = new PopularAdapter(getContext(), new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                mListener.onWisataSelected(popularAdapter.getItem(position).toString());
                //Toast.makeText(getActivity(), String.valueOf(position)+" "+popularAdapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_list);
        recyclerView.setAdapter(popularAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 1));

        //image slider
        carouselView = (CarouselView) v.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        //getting data
        new showListData(getContext(), getString(R.string.api_path_favorit)).execute();

        return v;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private class showListData extends BTGWisataRatingRequest{

        public showListData(Context context, String apiPath) {
            super(context, apiPath);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            progressBar.setVisibility(View.GONE);

            if (!getJson().isEmpty()) {

                JSONArray array = null;
                try {
                    array = new JSONArray(getJson());
                    wisataList.parse(array);

                    if(wisataList.getList() != null && !wisataList.getList().isEmpty()) {
                        if (popularAdapter != null) {
                            popularAdapter.clear();
                            popularAdapter.addWisataList(wisataList.getList());
                            popularAdapter.notifyDataSetChanged();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onWisataSelected(String wisata);
        void onPromoSelected(Promo promo);
    }



}