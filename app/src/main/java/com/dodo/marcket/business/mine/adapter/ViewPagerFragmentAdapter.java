package com.dodo.marcket.business.mine.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by yanghongyu on 2017/12/16.
 */

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private String[] titles = null;

    public ViewPagerFragmentAdapter(FragmentManager fm, String[] titles, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    public ViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        if (fragments != null && fragments.size() > 0)
            return fragments.get(position);
        else
            return new Fragment();
    }

    @Override
    public int getCount() {
        if (fragments != null && fragments.size() > 0)
            return fragments.size();
        else
            return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.length > 0)
            return titles[position];
        else
            return "";
    }
}
