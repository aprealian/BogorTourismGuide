package com.teknokrait.bogortourismguide.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.Color;
import com.teknokrait.bogortourismguide.dev.RecyclerViewOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Lian on 2/18/2017.
 */

public class TestFragment extends Fragment {

    private List<Color> colors;

    public TestFragment() {
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

        /*final LinearLayout detail = (LinearLayout) v.findViewById(R.id.detail);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Intent intent = new Intent(getActivity(),DetailBelumAccActivity.class);
                startActivity(intent);
            }
        });

        final EditText et_search = (EditText) v.findViewById(R.id.et_search);*/

        initColors();

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_list);
        recyclerView.setAdapter(new MaterialPaletteAdapter(colors, new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast toast = Toast.makeText(getActivity(), String.valueOf(position), Toast.LENGTH_SHORT);
                int color = android.graphics.Color.parseColor(colors.get(position).getHex());
                toast.getView().setBackgroundColor(color);
                toast.show();
            }
        }));
        //VERTICAL
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        //HORIZONTAL
        //recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 1));


        return v;
    }

    private void initColors() {
        colors = new ArrayList<Color>();

        colors.add(new Color(getString(R.string.blue), getResources().getString(R.color.blue)));
        colors.add(new Color(getString(R.string.indigo), getResources().getString(R.color.indigo)));
        colors.add(new Color(getString(R.string.red), getResources().getString(R.color.red)));
        colors.add(new Color(getString(R.string.green), getResources().getString(R.color.green)));
        colors.add(new Color(getString(R.string.orange), getResources().getString(R.color.orange)));
        colors.add(new Color(getString(R.string.grey), getResources().getString(R.color.bluegrey)));
        colors.add(new Color(getString(R.string.amber), getResources().getString(R.color.teal)));
        colors.add(new Color(getString(R.string.deeppurple), getResources().getString(R.color.deeppurple)));
        colors.add(new Color(getString(R.string.bluegrey), getResources().getString(R.color.bluegrey)));
        colors.add(new Color(getString(R.string.yellow), getResources().getString(R.color.yellow)));
        colors.add(new Color(getString(R.string.cyan), getResources().getString(R.color.cyan)));
        colors.add(new Color(getString(R.string.brown), getResources().getString(R.color.brown)));
        colors.add(new Color(getString(R.string.teal), getResources().getString(R.color.teal)));
    }



}
