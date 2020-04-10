package com.example.myapplication12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class perla extends AppCompatActivity {
    FirebaseDatabase database ;
    DatabaseReference myRef;
    TextView absence,late;
    ImageView addabsence,addlate;
    FirebaseUser user;
    String uid;
    Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perla);
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
        database= FirebaseDatabase.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        addabsence=findViewById(R.id.addabsence);
        addlate=findViewById(R.id.addlate);
        addabsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=getIntent();
                String s=i.getStringExtra("name");
                String z=i.getStringExtra("class");
                myRef=database.getReference("students"+z+uid);
                Query query=myRef.orderByChild("name").equalTo(s);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for (DataSnapshot d:dataSnapshot.getChildren()) {
                                student s=new student(d.child("name").getValue().toString(),Float.parseFloat(d.child("id").getValue().toString()),d.child("clas").getValue().toString(),Integer.parseInt(d.child("absence").getValue().toString()),Integer.parseInt(d.child("late").getValue().toString()));
                                s.setAbsence(s.getAbsence()+1);
                                myRef.child(d.getKey()).setValue(s);
                                absence.setText(d.child("absence").getValue().toString());
                                late.setText(d.child("late").getValue().toString());

                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        addlate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=getIntent();
                String s=i.getStringExtra("name");
                String z=i.getStringExtra("class");
                myRef=database.getReference("students"+z+uid);
                Query query=myRef.orderByChild("name").equalTo(s);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for (DataSnapshot d:dataSnapshot.getChildren()) {
                                student s=new student(d.child("name").getValue().toString(),Float.parseFloat(d.child("id").getValue().toString()),d.child("clas").getValue().toString(),Integer.parseInt(d.child("absence").getValue().toString()),Integer.parseInt(d.child("late").getValue().toString()));
                                s.setLate(s.getLate()+1);
                                myRef.child(d.getKey()).setValue(s);
                                absence.setText(d.child("absence").getValue().toString());
                                late.setText(d.child("late").getValue().toString());

                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        Intent i=getIntent();
        String s=i.getStringExtra("name");
        String z=i.getStringExtra("class");
        myRef=database.getReference("students"+z+uid);
        Query query=myRef.orderByChild("name").equalTo(s);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot d:dataSnapshot.getChildren()) {
                        absence=findViewById(R.id.absence);
                        late=findViewById(R.id.late);
                        absence.setText(d.child("absence").getValue().toString());
                        late.setText(d.child("late").getValue().toString());
                        delete=findViewById(R.id.delete);
                        final String b=d.getKey();
                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                myRef.child(b).removeValue();
                            }
                        });

                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
