package com.teknokrait.bogortourismguide.tourism;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import com.teknokrait.bogortourismguide.R;

/**
 * Created by Lian on 2/18/2017.
 */

public class TourismFragment extends Fragment {


    public TourismFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_tourism, container, false);

        ButterKnife.inject(getActivity());

        /*final LinearLayout detail = (LinearLayout) v.findViewById(R.id.detail);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(getActivity(),DetailBelumAccActivity.class);
                startActivity(intent);
            }
        });

        final EditText et_search = (EditText) v.findViewById(R.id.et_search);*/


        // Inflate the layout for this fragment
        return v;
    }



}
