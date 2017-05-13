package com.teknokrait.bogortourismguide.view.wisata;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.Wisata;
import com.teknokrait.bogortourismguide.helper.BTGConstant;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class DetailWisataActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;

    @InjectView(R.id.name_TextView)
    TextView nameTextView;

    @InjectView(R.id.rating_TextView)
    TextView ratingTextView;

    @InjectView(R.id.category_TextView)
    TextView categoryTextView;

    @InjectView(R.id.price_TextView)
    TextView priceTextView;

    @InjectView(R.id.address_TextView)
    TextView addressTextView;

    @InjectView(R.id.mapView)
    MapView mapView;

    private Wisata wisata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_wisata);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            wisata = new Wisata();
            try {
                JSONObject obj = new JSONObject(bundle.getString(BTGConstant.WISATA));
                wisata.parse(obj);

                nameTextView.setText(wisata.getNama());
                if (wisata.getRating()==-1) {
                    ratingTextView.setText("-");
                } else {
                    ratingTextView.setText(String.valueOf(wisata.getRating()));
                }

                if (wisata.getHarga()==0){
                    priceTextView.setText("Price : -");
                } else {
                    priceTextView.setText("Price : "+String.valueOf(wisata.getHarga()));
                }

                categoryTextView.setText(wisata.getKategori());
                addressTextView.setText(wisata.getAlamat());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }





        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        mapView.getMapAsync(this);
        //map.getUiSettings().setMyLocationButtonEnabled(false);
        //map.setMyLocationEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Updates the location and zoom of the MapView
        //CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(wisata.getLat(), wisata.getLng()), 10);
        //map.animateCamera(cameraUpdate);



    }



    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                Log.d("Camera postion change" + "", cameraPosition + "");
                map.clear();
                try {

                    Location mLocation = new Location("");
                    mLocation.setLatitude(wisata.getLat());
                    mLocation.setLongitude(wisata.getLng());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setCompassEnabled(false);
        map.getUiSettings().setRotateGesturesEnabled(false);


    }
}
