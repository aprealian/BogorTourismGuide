package com.teknokrait.bogortourismguide.view.wisata;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.WisataList;
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

    private PopularAdapter popularAdapter;
    private WisataList wisataList;

    @InjectView(R.id.categoryRecyclerView)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.inject(this);

        wisataList = new WisataList();

        popularAdapter = new PopularAdapter(getApplicationContext(), new RecyclerViewOnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(getApplicationContext(), "hello", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(popularAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, 1));

        if (Helper.checkConnection(getApplicationContext())) {
            // Its Available...
            //getting data
            new showListData(getApplicationContext(), getString(R.string.api_path_favorit)).execute();
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
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

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


}
