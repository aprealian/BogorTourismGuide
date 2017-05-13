package com.teknokrait.bogortourismguide.view.home;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.Category;
import com.teknokrait.bogortourismguide.view.dev.RecyclerViewOnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lian on 2/18/2017.
 */

public class CategoryFragment extends Fragment {

    private List<Category> menuList;
    private CategoryAdapter categoryAdapter;
    private OnFragmentInteractionListener mListener;



    public CategoryFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initMenuData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.inject(getActivity());

        categoryAdapter = new CategoryAdapter(getContext(), menuList,  new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                //Toast.makeText(getContext(), categoryAdapter.getItem(position).toString(), Toast.LENGTH_SHORT).show();
                mListener.onClickCategory(categoryAdapter.getItem(position).toString());
            }
        });

        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_list);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), 1));

        return v;
    }


    private void initMenuData() {
        menuList = new ArrayList<Category>();
        menuList.add(new Category("Food", "food", "ic_menu_food", "tag"));
        menuList.add(new Category("Cafe", "cafe", "ic_menu_cafe", "tag"));
        menuList.add(new Category("Market", "market", "ic_menu_market", ""));
        menuList.add(new Category("Museum", "museum", "ic_menu_museum", ""));
        menuList.add(new Category("Hotel", "hotel", "ic_menu_hotel", ""));
        menuList.add(new Category("Mosque", "mosque", "ic_menu_mosque", ""));

    }


    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnServiceFragmentInteractionListener");
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onClickCategory(String string);
    }



}
