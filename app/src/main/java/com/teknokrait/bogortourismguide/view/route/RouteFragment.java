package com.teknokrait.bogortourismguide.view.route;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.Route;

import butterknife.ButterKnife;

/**
 * Created by Lian on 2/18/2017.
 */

public class RouteFragment extends Fragment {

    private Route route;
    private EditText departEditText, destinationEditText;

    public RouteFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_route, container, false);

        ButterKnife.inject(getActivity());

        route = new Route();

        departEditText = (EditText) v.findViewById(R.id.departEditText);
        departEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Intent intent = new Intent(getActivity(), SelectRouteActivity.class);
                intent.putExtra("tag","depart");
                startActivity(intent);

            }
        });

        destinationEditText = (EditText) v.findViewById(R.id.destinationEditText);
        destinationEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Intent intent = new Intent(getActivity(), SelectRouteActivity.class);
                intent.putExtra("tag","destination");
                startActivity(intent);

            }
        });


        //final EditText et_search = (EditText) v.findViewById(R.id.et_search);*/



        // Inflate the layout for this fragment
        return v;
    }


    @Override
    public void onResume() {
        super.onResume();

        departEditText.setText(route.getDepart());
        destinationEditText.setText(route.getDestination());

    }

    @Override
    public void onStart() {
        super.onStart();
        //route.setDepart("");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        route.setDepart("");
        route.setDestination("");

    }
}
