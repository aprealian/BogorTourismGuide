package com.teknokrait.bogortourismguide.view.wisata;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.Category;
import com.teknokrait.bogortourismguide.data.Wisata;
import com.teknokrait.bogortourismguide.data.WisataList;
import com.teknokrait.bogortourismguide.helper.BTGConstant;
import com.teknokrait.bogortourismguide.helper.Helper;
import com.teknokrait.bogortourismguide.http.BTGWisataRatingRequest;
import com.teknokrait.bogortourismguide.view.dev.RecyclerViewOnItemClickListener;
import com.teknokrait.bogortourismguide.view.home.HomeFragment;
import com.teknokrait.bogortourismguide.view.home.PopularAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.OkHttpClient;

public class CategoryActivity extends AppCompatActivity {

    private WisataByCategoryAdapter wisataByCategoryAdapter;
    private WisataList wisataList;
    private String keyword;
    private Category category;

    /*@InjectView(R.id.progressBar)
    ProgressBar progressBar;*/

    @InjectView(R.id.categoryRecyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.inject(this);
        Intent intent = getIntent();
        if (intent != null) {
            //Toast.makeText(this, intent.getStringExtra(BTGConstant.CATEGORY), Toast.LENGTH_SHORT).show();
            try {
                JSONObject obj = new JSONObject( intent.getStringExtra(BTGConstant.CATEGORY));
                category = new Category();
                category.parse(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        wisataList = new WisataList();

        wisataByCategoryAdapter = new WisataByCategoryAdapter(getApplicationContext(), new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(CategoryActivity.this, DetailWisataActivity.class);
                intent.putExtra(BTGConstant.WISATA, wisataByCategoryAdapter.getItem(position).toString());
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(wisataByCategoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));

        if (Helper.checkConnection(getApplicationContext())) {
            // Its Available...
            //getting data
            new showListData(getApplicationContext(), getString(R.string.api_path_search)+category.getTag()).execute();
            Log.e("URL ", getString(R.string.api_path_search)+category.getTag());
        } else {
            // Not Available...
            Toast.makeText(this, "Please, check your internet connection", Toast.LENGTH_SHORT).show();
        }

    }

    private class showListData extends BTGWisataRatingRequest {

        public showListData(Context context, String apiPath) {
            super(context, apiPath);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //progressBar.setVisibility(View.VISIBLE);
            //recyclerView.setVisibility(View.GONE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.e("JSON ", getJson());

            //progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            if (!getJson().isEmpty()) {

                JSONArray array = null;
                try {
                    array = new JSONArray(getJson());
                    wisataList.parse(array);

                    if(wisataList.getList() != null && !wisataList.getList().isEmpty()) {
                        if (wisataByCategoryAdapter != null) {
                            wisataByCategoryAdapter.clear();
                            wisataByCategoryAdapter.addWisataList(wisataList.getList());
                            wisataByCategoryAdapter.notifyDataSetChanged();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }

    }


}
