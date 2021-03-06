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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private FragmentActivity myContext;
    FloatingActionButton button;

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
        button=view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getContext(),addschedule.class);
                startActivity(i);
            }
        });

    }

    private void makefr(){
        arrayf=new ArrayList<>();
        schedul sunday=new schedul();
        Bundle args1=new Bundle();
        args1.putString("day","sunday");
        sunday.setArguments(args1);
        schedul monday=new schedul();
        Bundle args2=new Bundle();
        args2.putString("day","monday");
        monday.setArguments(args2);
        schedul tuesday=new schedul();
        Bundle args3=new Bundle();
        args3.putString("day","tuesday");
        tuesday.setArguments(args3);
        schedul wednesday=new schedul();
        Bundle args4=new Bundle();
        args4.putString("day","wednesday");
        wednesday.setArguments(args4);
        schedul thursday=new schedul();
        Bundle args5=new Bundle();
        args5.putString("day","thursday");
        thursday.setArguments(args5);
        arrayf.add(sunday);
        arrayf.add(monday);
        arrayf.add(tuesday);
        arrayf.add(wednesday);
        arrayf.add(thursday);

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
