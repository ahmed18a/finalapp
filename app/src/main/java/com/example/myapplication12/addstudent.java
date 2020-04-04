package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addstudent extends AppCompatActivity {
    EditText name,id,clas;
    Button save,retur;
    FirebaseDatabase database;
    DatabaseReference myRef;
    student student;
    FirebaseUser user;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);
        database= FirebaseDatabase.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        name=findViewById(R.id.name);
        id=findViewById(R.id.id);
        clas=findViewById(R.id.clas);
        save=findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                student=new student(name.getText().toString(),Float.parseFloat(id.getText().toString()),clas.getText().toString(),0,0);
                myRef=database.getReference("students"+clas.getText().toString()+uid);
                myRef.push().setValue(student);
            }
        });
        retur=findViewById(R.id.retur);
        retur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    }

