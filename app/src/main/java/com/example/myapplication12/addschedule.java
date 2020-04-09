package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addschedule extends AppCompatActivity {
    FirebaseDatabase database ;
    DatabaseReference myRef2;
    EditText day,subject,clas,number;
    Button save,ret;
    int c=0;
    schedule sched;
    lesson lessons [];
    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addschedule);
        database= FirebaseDatabase.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        save=findViewById(R.id.save);
        Toast.makeText(this, "you can enter 7 7ss", Toast.LENGTH_SHORT).show();
        lessons=new lesson[8];
        if(c<8) {
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    day=findViewById(R.id.day);
                    subject=findViewById(R.id.lesson);
                    clas=findViewById(R.id.clas);
                    number=findViewById(R.id.number);
                    lessons[c]=new lesson(clas.getText().toString(),day.getText().toString(),Integer.parseInt(number.getText().toString()),subject.getText().toString());
                    c++;
                    if (c==7) {
                        makedaysched();
                        c=0;
                    }
                }
            });
            ret=findViewById(R.id.ret);
            ret.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    day=findViewById(R.id.day);
                    subject=findViewById(R.id.lesson);
                    clas=findViewById(R.id.clas);
                    number=findViewById(R.id.number);
                    lessons[c]=new lesson(clas.getText().toString(),day.getText().toString(),Integer.parseInt(number.getText().toString()),subject.getText().toString());
                    c++;
                    if (c<7) {
                        makedaysched();
                        c=0;
                    }
                    finish();
                }
            });


        }
        else{
            save.setVisibility(View.INVISIBLE);
        }

    }
    public void makedaysched(){
        lesson lesson2[]=new lesson[c+1];
        for (int i=1;i<c+1;i++){
            for (int j=1;j<c+1;j++){
                if(lessons[j-1].getNumber()==i)
                    lesson2[i-1]=lessons[j-1];
            }

        }
        sched=new schedule(lesson2,lesson2[0].getDay());
        myRef2=database.getReference(sched.getDay()+uid);
        for (int i=0;i<c+1;i++)
            myRef2.push().setValue(lesson2[i]);
    }
}
