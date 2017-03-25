package com.teknokrait.bogortourismguide.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.Tourism;
import com.teknokrait.bogortourismguide.dev.RecyclerViewOnItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Lian on 2/18/2017.
 */

public class HomeFragment extends Fragment {

    private List<Tourism> popular;
    private CarouselView carouselView;
    int[] sampleImages = {R.drawable.promo1, R.drawable.promo2, R.drawable.promo3};


    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(getActivity());

        initColors();

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_list);
        recyclerView.setAdapter(new PopularAdapter(popular, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
            }
        }));
        //VERTICAL
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //HORIZONTAL
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 1));



        //image slider
        carouselView = (CarouselView) v.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

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



}
