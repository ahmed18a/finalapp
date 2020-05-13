package com.example.myapplication12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

public class popuple extends Activity {
TextView day,subject,clas,number;
    FirebaseUser user;
    String uid;
    Button delete, save;
    EditText learned , homework;
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
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        learned=findViewById(R.id.learned);
        homework=findViewById(R.id.homework);
        final DatabaseReference myRef = database.getReference(i.getStringExtra("day")+uid);
        Query query=myRef.orderByChild("number").equalTo(Integer.parseInt(s));
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                for (DataSnapshot d:dataSnapshot.getChildren()) {
                    day = findViewById(R.id.day);
                    subject = findViewById(R.id.subject);
                    clas = findViewById(R.id.clas);
                    number = findViewById(R.id.number);
                    day.setText(d.child("day").getValue().toString());
                    subject.setText(d.child("subject").getValue().toString());
                    clas.setText(d.child("clas").getValue().toString());
                    number.setText(d.child("number").getValue().toString());
                    learned.setText(d.child("learned").getValue().toString());
                    homework.setText(d.child("homework").getValue().toString());
                    delete=findViewById(R.id.delete);
                   final String b=d.getKey();
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            myRef.child(b).removeValue();
                            finish();
                        }
                    });
                    save=findViewById(R.id.save);
                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            lesson l= new lesson(clas.getText().toString(),day.getText().toString(),Integer.parseInt(number.getText().toString()),subject.getText().toString(),learned.getText().toString(),homework.getText().toString());
                            myRef.child(b).setValue(l);
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
