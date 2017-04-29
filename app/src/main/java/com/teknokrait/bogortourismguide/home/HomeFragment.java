package com.teknokrait.bogortourismguide.home;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.Tourism;
import com.teknokrait.bogortourismguide.data.WisataList;
import com.teknokrait.bogortourismguide.dev.RecyclerViewOnItemClickListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by Lian on 2/18/2017.
 */

public class HomeFragment extends Fragment {

    private PopularAdapter popularAdapter;
    private OkHttpClient mClient;
    private List<Tourism> popular;
    private WisataList wisataList;
    private CarouselView carouselView;
    int[] sampleImages = {R.drawable.promo1, R.drawable.promo2, R.drawable.promo3};

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mClient = new OkHttpClient();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(getActivity());

        wisataList = new WisataList();
        initColors();

        popularAdapter = new PopularAdapter(getContext(), new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_list);
        recyclerView.setAdapter(popularAdapter);
        //VERTICAL
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        //HORIZONTAL
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 1));

        //image slider
        carouselView = (CarouselView) v.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        //testAsyncCreate();
        new showListData().execute();

        return v;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    private void initColors() {
        popular = new ArrayList<Tourism>();
        //String id, String name, double latitude, double longitude, String picture, String rating, List<String> types, String vicinity
        popular.add(new Tourism("1", "Mie Jogja Pak Karso", -6.61522, 106.8140317, "http://4.bp.blogspot.com/-J4M8_l7REhU/VhvPSJt7ldI/AAAAAAAAAGk/qP6KiYwy9kA/s1600/mie-jogja-pak-karso.jpg", "4.7", Arrays.asList("Restaurant", "Food"), "Jalan Raya Pajajaran No.28B, Baranangsiang"));
        popular.add(new Tourism("2", "Bogor Botanical Garden", -6.61522, 106.8140317, "http://4.bp.blogspot.com/-J4M8_l7REhU/VhvPSJt7ldI/AAAAAAAAAGk/qP6KiYwy9kA/s1600/mie-jogja-pak-karso.jpg", "4.7", Arrays.asList("Restaurant", "Food"), "Jalan Raya Pajajaran No.28B, Baranangsiang"));

    }


    /**
     * Creating Get Data Task for Getting Data From Web
     */
    private class showListData extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {

            /**
             * Getting JSON Object from Web Using okHttp
             */

            try {

                OkHttpClient client = new OkHttpClient();

                /*RequestBody formBody = new FormBody.Builder()
                        .add("token", token)
                        .build();*/

                Request request = new Request.Builder()
                        //.url(AppConfig.URL_PROFILE+"?type=2&status=1&token="+token)
                        .url(getString(R.string.api_path_favorit))
                        .get()
                        .build();

                Response response = client.newCall(request).execute();
                //jsonArray = new JSONArray(response.body().string());
                //jsonString = "array";

                String json = response.body().string();

                Log.d("ini json", json);

                if (!json.isEmpty()) {

                    JSONArray array = new JSONArray(json);
                    wisataList.parse(array);
                    Log.d("array ", array.toString());

                }

            } catch (@NonNull IOException | JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.getLocalizedMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //dialog.dismiss();

            if(wisataList.getList() != null && !wisataList.getList().isEmpty()) {
                if (popularAdapter != null) {
                    popularAdapter.clear();
                    popularAdapter.addBrands(wisataList.getList());
                    popularAdapter.notifyDataSetChanged();
                }
            }

        }
    }





}
