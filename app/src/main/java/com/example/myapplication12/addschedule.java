package com.example.myapplication12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
        c=0;
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        save=findViewById(R.id.save);
        Toast.makeText(this, "you can enter 7 7ss", Toast.LENGTH_SHORT).show();
        lessons=new lesson[8];
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                day=findViewById(R.id.day);
                subject=findViewById(R.id.lesson);
                clas=findViewById(R.id.clas);
                number=findViewById(R.id.number);
                lessons[c]=new lesson(clas.getText().toString(),day.getText().toString(),Integer.parseInt(number.getText().toString()),subject.getText().toString(),"","");
                c++;
                if (c==7) {
                    makedaysched();
                    finish();
                }
                myRef2=database.getReference(day.getText().toString()+uid);
                Query query=myRef2.orderByChild("number").equalTo(Integer.parseInt(number.getText().toString()));
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for (DataSnapshot d:dataSnapshot.getChildren()) {
                                myRef2.child(d.getKey()).removeValue();
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
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
                lessons[c]=new lesson(clas.getText().toString(),day.getText().toString(),Integer.parseInt(number.getText().toString()),subject.getText().toString(),"","");
                if (c<7) {
                    makedaysched();
                }
                finish();
            }
        });



    }
    public void makedaysched() {
        boolean b = true;
        for (int i = 0; i < c; i++) {
            if (!lessons[i].getDay().equals(lessons[i + 1]))
                b = false;
        }
        if (b) {
            lesson lesson2[] = new lesson[8];
            for (int i = 1; i < 8; i++) {
                for (int j = 1; j < c + 1; j++) {
                    if (lessons[j - 1].getNumber() == i)
                        lesson2[j-1] = lessons[j - 1];
                }

            }
            sched = new schedule(lesson2, lessons[0].getDay());
            myRef2 = database.getReference(sched.getDay() + uid);
            for (int i = 0; i < c + 1; i++)
                myRef2.push().setValue(lesson2[i]);
            c = 0;
        }
        else{
            for (int i=0; i<c;i++){
                myRef2=database.getReference(lessons[i].getDay()+uid);
                myRef2.push().setValue(lessons[i]);
            }
        }
    }


}
