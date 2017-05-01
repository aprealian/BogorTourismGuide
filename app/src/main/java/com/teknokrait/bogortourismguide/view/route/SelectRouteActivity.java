package com.teknokrait.bogortourismguide.view.route;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.teknokrait.bogortourismguide.R;
import com.teknokrait.bogortourismguide.data.Route;

import java.util.Arrays;

public class SelectRouteActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText searchEditText;
    private SearchableRouteAdapter mSearchableRouteAdapter;
    private ListView listView;
    private String tag = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_route);


        Intent myIntent = getIntent(); // gets the previously created intent
        tag = myIntent.getStringExtra("tag");


        //mSearchableRouteAdapter = new SearchableRouteAdapter(getApplicationContext(), Arrays.asList("Taman Pintar", "IPB Baranang Siang", "Botani Square"));

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                TextView name = (TextView) view.findViewById(R.id.nameTextView);
                //Toast.makeText(getApplicationContext(),name.getText().toString(), Toast.LENGTH_SHORT).show();
                onBackPressed(name.getText().toString());


            }
        });

        /*movieNamesArrayList = new ArrayList<>();
        array_sort = new ArrayList<>();

        for (int i = 0; i < moviewList.length; i++) {
            MovieNames movieNames = new MovieNames(moviewList[i]);
            // Binds all strings into an array
            movieNamesArrayList.add(movieNames);
            array_sort.add(movieNames);
        }*/

        mSearchableRouteAdapter = new SearchableRouteAdapter(this, Arrays.asList("Taman Pintar", "IPB Baranang Siang", "Botani Square"));
        listView.setAdapter(mSearchableRouteAdapter);


        searchEditText = (EditText) findViewById(R.id.searchEditText);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                System.out.println("Text ["+s+"]");

                mSearchableRouteAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed(String title) {
        super.onBackPressed();
        Route a = new Route();
        if (tag.equals("depart")){
            a.setDepart(title);
        } else if (tag.equals("destination")) {
            a.setDestination(title);
        }
    }
}
