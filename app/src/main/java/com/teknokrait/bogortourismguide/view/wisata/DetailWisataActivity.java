package com.teknokrait.bogortourismguide.view.wisata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.Wisata;
import com.teknokrait.bogortourismguide.helper.BTGConstant;

import org.json.JSONException;
import org.json.JSONObject;

public class DetailWisataActivity extends AppCompatActivity {

    private Wisata wisata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            wisata = new Wisata();
            try {
                JSONObject obj = new JSONObject(bundle.getString(BTGConstant.SERVICE));
                wisata.parse(obj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }









}
