package com.example.myapplication12;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class schedul extends Fragment {
    private View convertview;
    FirebaseDatabase database ;
    DatabaseReference myRef2;
    private ListView List;
    ArrayList<String> user,lesson;
    String day;
    FirebaseUser use;
    String uid;

    public schedul() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(convertview==null)
            convertview=inflater.inflate(R.layout.fragment_schedul,container,false);
        return convertview;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database= FirebaseDatabase.getInstance();
        use= FirebaseAuth.getInstance().getCurrentUser();
        uid=use.getUid();
        if(getArguments()!=null)
            day=getArguments().getString("day");
        user = new ArrayList<>();
        List = view.findViewById(R.id.List);
        myRef2 = database.getReference(day+uid);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, user);
        List.setAdapter(adapter);
        lesson=new ArrayList<>();
        adapter.notifyDataSetChanged();
        myRef2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.child("subject").getValue().toString();
                adapter.add(value);
                adapter.notifyDataSetChanged();
                lesson.add(dataSnapshot.child("number").getValue().toString());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                adapter.remove(dataSnapshot.child("subject").getValue().toString());
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(),popuple.class);
                i.putExtra("position",String.valueOf(lesson.get(position)));
                i.putExtra("day",day);
                startActivity(i);
            }
        });
    }
}
