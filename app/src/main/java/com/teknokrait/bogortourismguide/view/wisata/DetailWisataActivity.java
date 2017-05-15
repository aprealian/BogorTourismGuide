package com.teknokrait.bogortourismguide.view.wisata;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.teknokrait.bogortourismguide.MainActivity;
import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.Wisata;
import com.teknokrait.bogortourismguide.helper.BTGConstant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.teknokrait.bogortourismguide.R.id.carouselView;

public class DetailWisataActivity extends AppCompatActivity implements OnMapReadyCallback, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private GoogleMap map;
    private SliderLayout mDemoSlider;
    private ImageAdapter imageAdapter;

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

        //MAPS
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //IMAGE
        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        url_maps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        url_maps.put("House of Cards", "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        url_maps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("Hannibal",R.drawable.promo2);
        file_maps.put("Big Bang Theory",R.drawable.promo2);
        file_maps.put("House of Cards",R.drawable.promo2);
        file_maps.put("Game of Thrones", R.drawable.promo2




        );

        for(String url : wisata.getPhotos()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(null)
                    .image("http://mydummyproject.hol.es/get/photo/"+url+"/200")
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            Log.e("URL : ","http://mydummyproject.hol.es/get/photo/"+url+"/200");

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",url);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);





        //image slider
        imageAdapter = new ImageAdapter(getApplicationContext());
        imageAdapter.setUrl(wisata.getPhotos());

        CarouselView carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(wisata.getPhotos().size());
        carouselView.setImageListener(imageListener);

    }


    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            //imageView.setImageResource(imageAdapter.getItemPosition(position));
            imageView.setImageBitmap(imageAdapter.getImage(position));
        }
    };

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

        /*map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
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
        });*/
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


        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(wisata.getLat(), wisata.getLng()), 10);
        map.animateCamera(cameraUpdate);

        LatLng HAMBURG = new LatLng(wisata.getLat(), wisata.getLng());

        map.addMarker(new MarkerOptions().position(HAMBURG).title(wisata.getNama()));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 20f));
        map.animateCamera(CameraUpdateFactory.zoomTo(16f), 2000, null);


    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
