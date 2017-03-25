package com.teknokrait.bogortourismguide.data;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirius on 3/12/2017.
 */

public class Tourism {

    private static String KEY_ID = "id";
    private static String KEY_NAME = "name";
    private static String KEY_DESCRIPTION = "description";
    private static String KEY_LATITUDE = "latitude";
    private static String KEY_LONGITUDE = "longitude";
    private static String KEY_PICTURE = "picture";
    private static String KEY_RATING = "rating";
    private static String KEY_TYPES = "types";
    private static String KEY_VICINITY = "vicinity";

    private String id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private String picture;
    private String rating;
    private List<String> types;
    private String vicinity;

    public Tourism(String id, String name, double latitude, double longitude, String picture, String rating, List<String> types, String vicinity) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.picture = picture;
        this.rating = rating;
        this.types = types;
        this.vicinity = vicinity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }




    public void parse (JSONObject obj){

        if (obj == null) return;

        try {
            if (!obj.isNull(KEY_ID)) {
                setId(obj.getString(KEY_ID));
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!obj.isNull(KEY_NAME)) {
                setName(obj.getString(KEY_NAME));
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!obj.isNull(KEY_DESCRIPTION)) {
                setDescription(obj.getString(KEY_DESCRIPTION));
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!obj.isNull(KEY_LATITUDE)) {
                setLatitude(obj.getDouble(KEY_LATITUDE));
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!obj.isNull(KEY_LONGITUDE)) {
                setLongitude(obj.getDouble(KEY_LONGITUDE));
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!obj.isNull(KEY_PICTURE)) {
                setPicture(obj.getString(KEY_PICTURE));
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!obj.isNull(KEY_VICINITY)) {
                setVicinity(obj.getString(KEY_VICINITY));
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!obj.isNull(KEY_TYPES)) {

                JSONArray array = obj.getJSONArray(KEY_TYPES);
                if (array != null && array.length() > 0) {
                   types = new ArrayList<>();
                    for (int i=0; i< array.length(); i++){
                        JSONObject o = array.getJSONObject(i);
                        types.add(o.toString());
                    }

                }
            }
        } catch (JSONException e){e.printStackTrace();}

    }


    @Override
    public String toString() {

        JSONObject obj = new JSONObject();

        try {
            if (!TextUtils.isEmpty(getId())) {
                obj.put(KEY_ID, getId());
            } else  {
                obj.put(KEY_ID, JSONObject.NULL);
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!TextUtils.isEmpty(getName())) {
                obj.put(KEY_NAME, getName());
            } else  {
                obj.put(KEY_NAME, JSONObject.NULL);
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!TextUtils.isEmpty(getDescription())) {
                obj.put(KEY_DESCRIPTION, getDescription());
            } else  {
                obj.put(KEY_DESCRIPTION, JSONObject.NULL);
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            obj.put(KEY_LATITUDE, getLatitude());
        } catch (JSONException e){e.printStackTrace();}

        try {
            obj.put(KEY_LONGITUDE, getLongitude());
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!TextUtils.isEmpty(getPicture())) {
                obj.put(KEY_PICTURE, getPicture());
            } else  {
                obj.put(KEY_PICTURE, JSONObject.NULL);
            }
        } catch (JSONException e){e.printStackTrace();}


        try {
            if (!TextUtils.isEmpty(getVicinity())) {
                obj.put(KEY_VICINITY, getVicinity());
            } else  {
                obj.put(KEY_VICINITY, JSONObject.NULL);
            }
        } catch (JSONException e){e.printStackTrace();}


        try {
            if (!TextUtils.isEmpty(getName())) {
                obj.put(KEY_NAME, getName());
            } else  {
                obj.put(KEY_NAME, JSONObject.NULL);
            }
        } catch (JSONException e){e.printStackTrace();}


        return null;
    }


}


