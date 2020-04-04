package com.example.myapplication12;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication12.adapter.viewpageradapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class weeksched extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private viewpageradapter adapter;
    private ArrayList<Fragment> arrayf;
    private ArrayList<String>arrayList;
    Button add;
    private FragmentActivity myContext;

    public weeksched() {
        // Required empty public constructor
    }
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_weeksched, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout=view.findViewById(R.id.tabs);
        viewPager=view.findViewById(R.id.view_pager);
        makefr();
        makea();
        setViewPager();
        add=view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),addschedule.class);
                startActivity(i);
            }
        });

    }

    private void makefr(){
        arrayf=new ArrayList<>();
        arrayf.add(new schedul("sunday"));
        arrayf.add(new schedul("monday"));
        arrayf.add(new schedul("tuesday"));
        arrayf.add(new schedul("wednesday"));
        arrayf.add(new schedul("thursday"));

    }
    private void makea(){
        arrayList=new ArrayList<>();
        arrayList.add("sunday");
        arrayList.add("monday");
        arrayList.add("tuesday");
        arrayList.add("wednesday");
        arrayList.add("thursday");
    }
    private void setViewPager(){
        adapter=new viewpageradapter(myContext.getSupportFragmentManager(),arrayf,arrayList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
