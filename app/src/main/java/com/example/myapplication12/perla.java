package com.example.myapplication12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class perla extends AppCompatActivity {
    FirebaseDatabase database ;
    DatabaseReference myRef;
    TextView absence,late;
    ImageView addabsence,addlate;
    FirebaseUser user;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perla);
        database= FirebaseDatabase.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        uid=user.getUid();
        myRef = database.getReference("students");
        absence=findViewById(R.id.absence);
        late=findViewById(R.id.late);
        addabsence=findViewById(R.id.addabsence);
        addlate=findViewById(R.id.addlate);
        addabsence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=getIntent();
                String s=i.getStringExtra("name");
                String z=i.getStringExtra("class");
                Toast.makeText(perla.this, ""+s+z, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
