package com.teknokrait.bogortourismguide.data;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by sirius on 4/30/2017.
 */

public class Category {

    private static String KEY_TITLE = "";
    private static String KEY_IMAGE = "";
    private static String KEY_TAG = "";
    private static String KEY_BANNER = "";

    private String title;
    private String image;
    private String tag;
    private String banner;

    public Category(String title, String tag, String image, String banner){
        this.title = title;
        this.tag = tag;
        this.image = image;
        this.banner = banner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public void parse(JSONObject obj){

        if (obj == null) return;

        try {
            if (!obj.isNull(KEY_TITLE)) {
                setTitle(obj.getString(KEY_TITLE));
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!obj.isNull(KEY_TAG)) {
                setTag(obj.getString(KEY_TAG));
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!obj.isNull(KEY_IMAGE)) {
                setImage(obj.getString(KEY_IMAGE));
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!obj.isNull(KEY_BANNER)) {
                setBanner(obj.getString(KEY_BANNER));
            }
        } catch (JSONException e){e.printStackTrace();}



    }

    @Override
    public String toString() {

        JSONObject obj = new JSONObject();

        try {
            if (!TextUtils.isEmpty(getTitle())) {
                obj.put(KEY_TITLE, getTitle());
            } else  {
                obj.put(KEY_TITLE, JSONObject.NULL);
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!TextUtils.isEmpty(getTag())) {
                obj.put(KEY_TAG, getTag());
            } else  {
                obj.put(KEY_TAG, JSONObject.NULL);
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!TextUtils.isEmpty(getImage())) {
                obj.put(KEY_IMAGE, getImage());
            } else  {
                obj.put(KEY_IMAGE, JSONObject.NULL);
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!TextUtils.isEmpty(getBanner())) {
                obj.put(KEY_BANNER, getBanner());
            } else  {
                obj.put(KEY_BANNER, JSONObject.NULL);
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            return obj.toString(3);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return super.toString();
    }
}
