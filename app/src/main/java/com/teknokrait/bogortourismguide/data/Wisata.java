package com.teknokrait.bogortourismguide.data;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sirius on 4/28/2017.
 */

public class Wisata implements Parseable {

    private static String KEY_ID = "id";
    private static String KEY_NAMA = "nama";
    private static String KEY_KATEGORI = "kategori";
    private static String KEY_ALAMAT = "alamat";
    private static String KEY_HARGA = "harga";
    private static String KEY_RATING = "rating";
    private static String KEY_LAT = "lat";
    private static String KEY_LNG = "lng";
    private static String KEY_PHOTOS = "photos";

    private String id;
    private String nama;
    private String kategori;
    private String alamat;
    private long harga;
    private double rating;
    private double lat;
    private double lng;
    private List<String> photos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public long getHarga() {
        return harga;
    }

    public void setHarga(long harga) {
        this.harga = harga;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }


    public void parse(JSONObject obj) {

        if (obj == null) return;

        try {
            if (!obj.isNull(KEY_ID)) {
                setId(obj.getString(KEY_ID));
            }
        } catch (JSONException e){e.printStackTrace();}


        try {
            if (!obj.isNull(KEY_NAMA)) {
                setNama(obj.getString(KEY_NAMA));
            }
        } catch (JSONException e){e.printStackTrace();}


        try {
            if (!obj.isNull(KEY_KATEGORI)) {
                setKategori(obj.getString(KEY_KATEGORI));
            }
        } catch (JSONException e){e.printStackTrace();}


        try {
            if (!obj.isNull(KEY_ALAMAT)) {
                setAlamat(obj.getString(KEY_ALAMAT));
            }
        } catch (JSONException e){e.printStackTrace();}


        try {
            if (!obj.isNull(KEY_HARGA)) {
                setHarga(obj.getLong(KEY_HARGA));
            }
        } catch (JSONException e){e.printStackTrace();}


        try {
            if (!obj.isNull(KEY_RATING)) {
                setRating(obj.getDouble(KEY_RATING));
            }
        } catch (JSONException e){e.printStackTrace();}


        try {
            if (!obj.isNull(KEY_LAT)) {
                setLat(obj.getDouble(KEY_LAT));
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!obj.isNull(KEY_LNG)) {
                setLng(obj.getDouble(KEY_LNG));
            }
        } catch (JSONException e){e.printStackTrace();}


        try {
            if (!obj.isNull(KEY_PHOTOS)) {

                JSONArray array = obj.getJSONArray(KEY_PHOTOS);
                if (array != null && array.length() > 0) {
                    photos = new ArrayList<>();
                    for (int i=0; i< array.length(); i++){
                        photos.add(array.getString(i));
                    }
                    setPhotos(photos);
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
            if (!TextUtils.isEmpty(getNama())) {
                obj.put(KEY_NAMA, getNama());
            } else  {
                obj.put(KEY_NAMA, JSONObject.NULL);
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!TextUtils.isEmpty(getKategori())) {
                obj.put(KEY_KATEGORI, getKategori());
            } else  {
                obj.put(KEY_KATEGORI, JSONObject.NULL);
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            if (!TextUtils.isEmpty(getAlamat())) {
                obj.put(KEY_ALAMAT, getAlamat());
            } else  {
                obj.put(KEY_ALAMAT, JSONObject.NULL);
            }
        } catch (JSONException e){e.printStackTrace();}

        try {
            obj.put(KEY_HARGA, getHarga());
        } catch (JSONException e){e.printStackTrace();}

        try {
            obj.put(KEY_RATING, getRating());
        } catch (JSONException e){e.printStackTrace();}

        try {
            obj.put(KEY_LAT, getLat());
        } catch (JSONException e){e.printStackTrace();}

        try {
            obj.put(KEY_LNG, getLng());
        } catch (JSONException e){e.printStackTrace();}


        try {
            if (getPhotos() != null && !getPhotos().isEmpty()) {
                JSONArray array = new JSONArray();
                for (String a : getPhotos()) {
                    array.put(a);
                }
                obj.put(KEY_PHOTOS, array);
            } else  {
                obj.put(KEY_PHOTOS, JSONObject.NULL);
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
