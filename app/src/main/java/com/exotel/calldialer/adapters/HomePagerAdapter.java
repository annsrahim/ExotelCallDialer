package com.exotel.calldialer.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.exotel.calldialer.fragments.CalllogFragments;
import com.exotel.calldialer.fragments.ContactsFragment;
import com.exotel.calldialer.fragments.DialerFragments;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    private static int TAB_COUNT = 3;

    public HomePagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return CalllogFragments.newInstance();
            case 1:
                return DialerFragments.newInstance();
            case 2:
                return ContactsFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position)
        {
            case 0:
                return CalllogFragments.title;

            case 1:
                return DialerFragments.title;

            case 2:
                return ContactsFragment.title;
        }

        return super.getPageTitle(position);
    }
}
