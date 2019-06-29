package com.kshitizbali.twineup.ui.main;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kshitizbali.twineup.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        if (position == 0) {
            Log.i("Pos",""+position);
            return new EditFragment();
        }else if(position == 1){
            Log.i("Pos",""+position);
            return new ActivitySavingsFragment();
        }
        return null;
        /*return PlaceholderFragment.newInstance(position + 1);*/
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getResources().getString(TAB_TITLES[position]);
            case 1:
                return mContext.getResources().getString(TAB_TITLES[position]);
            default:
                return null;
        }
        /*return mContext.getResources().getString(TAB_TITLES[position]);*/
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}