package com.teknokrait.bogortourismguide.http;

import android.content.Context;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.RequestBody;

/**
 * Created by sirius on 4/28/2017.
 */

public class BTGWisataRatingRequest extends BTGBasicRequest {

    private Context context;

    public BTGWisataRatingRequest(Context context, String apiPath) {
        super(context, apiPath);

        this.context = context;

        RequestBody formBody = new FormBody.Builder()
                        .add("", "")
                        .build();

        addQuery(formBody);

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

    }

}
