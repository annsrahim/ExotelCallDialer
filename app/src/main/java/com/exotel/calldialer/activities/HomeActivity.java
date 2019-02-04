package com.exotel.calldialer.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.exotel.calldialer.R;
import com.exotel.calldialer.adapters.HomePagerAdapter;

public class HomeActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private HomePagerAdapter mHomePagerAdapter;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setViewPager();
    }
    private void setViewPager() {
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mHomePagerAdapter = new HomePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mHomePagerAdapter);
        mViewPager.setOffscreenPageLimit(3);

        mTabLayout = (TabLayout)findViewById(R.id.tab);
        mTabLayout.setupWithViewPager(mViewPager);

    }
}
