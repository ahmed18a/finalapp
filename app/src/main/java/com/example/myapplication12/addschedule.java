package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addschedule extends AppCompatActivity {
    FirebaseDatabase database ;
    DatabaseReference myRef2;
    EditText day,lesson;
    Button save,ret;
    int c=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addschedule);
        database= FirebaseDatabase.getInstance();
        save=findViewById(R.id.save);
        if(c<8) {
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    makedaysched();
                    c++;
                }
            });
        }
        else{
            save.setVisibility(View.INVISIBLE);
        }
        ret=findViewById(R.id.ret);
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void makedaysched(){
        day=findViewById(R.id.day);
        lesson=findViewById(R.id.lesson);
        myRef2 = database.getReference(day.getText().toString());
        myRef2.push().setValue(lesson.getText().toString());


    }
}
