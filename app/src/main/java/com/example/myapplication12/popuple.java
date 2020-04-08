package com.example.myapplication12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class popuple extends Activity {
TextView day,subject,clas,number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popuple);
        DisplayMetrics dm= new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int heigh=dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(heigh*.7));
        WindowManager.LayoutParams params= getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;
        getWindow().setAttributes(params);
        Intent i=getIntent();
        String s=i.getStringExtra("position");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(i.getStringExtra("day"));
        Query query=myRef.orderByChild("number").equalTo(s);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot d:dataSnapshot.getChildren()) {
                    lesson lesson = d.getValue(lesson.class);
                    Intent i=getIntent();
                    String s=i.getStringExtra("position");
                    if(s.equals(lesson.getNumber())) {
                        Toast.makeText(popuple.this, ""+"goooood", Toast.LENGTH_SHORT).show();
                        day = findViewById(R.id.day);
                        subject = findViewById(R.id.subject);
                        clas = findViewById(R.id.clas);
                        number = findViewById(R.id.number);
                        day.setText(lesson.getDay());
                        subject.setText(lesson.getSubject());
                        clas.setText(lesson.getClas());
                        number.setText(lesson.getNumber());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
