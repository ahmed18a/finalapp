package com.example.myapplication12.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

public class viewpageradapter extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> arrayf;
    private ArrayList<String> arrayList;

    public viewpageradapter(@NonNull FragmentManager fm,ArrayList<Fragment> arrayf,ArrayList<String>arrayList) {
        super(fm);
        this.arrayf=arrayf;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return arrayf.get(position);
    }

    @Override
    public int getCount() {
        return arrayf.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayList.get(position);
    }
}
