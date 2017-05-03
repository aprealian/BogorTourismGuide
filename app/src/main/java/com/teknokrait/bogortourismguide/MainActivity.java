package com.teknokrait.bogortourismguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.teknokrait.bogortourismguide.data.Promo;
import com.teknokrait.bogortourismguide.data.Wisata;
import com.teknokrait.bogortourismguide.view.wisata.CategoryActivity;
import com.teknokrait.bogortourismguide.view.dev.DeactivatedViewPager;
import com.teknokrait.bogortourismguide.view.home.HomeFragment;
import com.teknokrait.bogortourismguide.view.route.RouteFragment;
import com.teknokrait.bogortourismguide.view.home.CategoryFragment;
import com.teknokrait.bogortourismguide.view.wisata.DetailWisataActivity;

public class MainActivity extends AppCompatActivity implements HomeFragment.OnFragmentInteractionListener, CategoryFragment.OnFragmentInteractionListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DeactivatedViewPager viewPager = (DeactivatedViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        //Add tabs icon with setIcon() or simple text with .setText()
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tab_home));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tab_restaurant));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_tab_shuttle));

        //Add fragments
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new HomeFragment(), "HOME");
        adapter.addFragment(new CategoryFragment(), "TOURISM");
        adapter.addFragment(new RouteFragment(), "ROUTE");

        //Setting adapter
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {

                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });


    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }


    public void onClickCategory(String string){

        Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
        intent.putExtra("category", string);
        startActivity(intent);

    }


    @Override
    public void onWisataSelected(Wisata wisata) {

        Toast.makeText(this, wisata.toString(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MainActivity.this, DetailWisataActivity.class);
        intent.putExtra("wisata", wisata.toString());
        startActivity(intent);

    }

    @Override
    public void onPromoSelected(Promo promo) {

    }

}
