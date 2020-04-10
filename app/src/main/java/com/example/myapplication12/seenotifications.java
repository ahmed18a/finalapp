package com.example.myapplication12;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class seenotifications extends AppCompatActivity {
    FirebaseDatabase database ;
    DatabaseReference myRef2;
    ListView List;
    ArrayList<String> user,Id;
    FirebaseUser use;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seenotifications);
        use= FirebaseAuth.getInstance().getCurrentUser();
        uid=use.getUid();
        database=FirebaseDatabase.getInstance();
        myRef2 = database.getReference("notifications" +uid);
        user = new ArrayList<>();
        List = findViewById(R.id.List);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(seenotifications.this, android.R.layout.simple_list_item_1, user);
        List.setAdapter(adapter);
        Id=new ArrayList<>();
        myRef2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue().toString();
                user.add(value);
                adapter.notifyDataSetChanged();
                Id.add(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

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
                myRef2.child(Id.get(position)).removeValue();
            }
        });
    }
}
