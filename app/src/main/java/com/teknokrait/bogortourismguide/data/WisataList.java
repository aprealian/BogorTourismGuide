package com.teknokrait.bogortourismguide.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dantech on 3/31/17.
 */

public class WisataList extends AbstractList<Wisata>{
    @Override
    public Class<Wisata> getHandledClass() {
        return Wisata.class;
    }
}
