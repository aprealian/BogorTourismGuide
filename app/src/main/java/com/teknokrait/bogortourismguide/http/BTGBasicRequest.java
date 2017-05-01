package com.teknokrait.bogortourismguide.http;

import android.app.ProgressDialog;
import android.content.Context;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.teknokrait.bogortourismguide.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by sirius on 4/28/2017.
 */

public class BTGBasicRequest extends AsyncTask<Void, Void, Void> {

    private RequestBody formBody;
    private Context context;
    private String apiPath;
    private String json;
    private String type;

    public BTGBasicRequest (Context context) {
        this.context = context;
    }


    public BTGBasicRequest (Context context, String apiPath) {
        this.context = context;
        this.apiPath = apiPath;
    }

    public BTGBasicRequest (Context context, String apiPath, RequestBody formBody) {
        this.context = context;
        this.apiPath = apiPath;
        this.formBody = formBody;
    }

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

        try {

            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url(context.getString(R.string.url_domain)+apiPath)
                    .post(formBody)
                    .get()
                    .build();

            Response response = client.newCall(request).execute();
            json = response.body().string();

        } catch (@NonNull IOException e) {
            Log.e("JSON Parser", "Error parsing data " + e.getLocalizedMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //dialog.dismiss();

        String json = this.json;

        Log.d("ini json", json);

        if (TextUtils.isEmpty(json)) {

            Object jsonObj = null;
            try {
                jsonObj = new JSONTokener(json).nextValue();
                if (jsonObj instanceof JSONObject){
                    //you have an object
                    type = "object";
                } else if (jsonObj instanceof JSONArray){
                    //you have an array
                    type = "array";
                } else {
                    type = "error";
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
           type = "error";
        }

    }

    public void addQuery(RequestBody formBody) {
        this.formBody = formBody;
    }

    public String error() {
        return "Error, please check your connection";
    }

    public JSONObject successObject() throws JSONException {

        JSONObject obj = new JSONObject(json);
        return obj;
    }

    public JSONArray successArray() throws JSONException {

        JSONArray array = new JSONArray(json);
        return array;
    }

    public String getJson() {
        return json;
    }

    public String getType() {
        return type;
    }


}