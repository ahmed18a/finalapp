package com.example.myapplication12;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class signin extends AppCompatActivity {
    private FirebaseAuth auth;
    EditText email;
    Button signin, forgot;
    EditText password;
    FirebaseAuth mAuth;
    String birthday;
    String name;
    FirebaseDatabase database;
    DatabaseReference myRef;
    public static final String CHANNEL_ID = "channel";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        email= findViewById(R.id.email);
        signin=findViewById(R.id.signin);
        password = findViewById(R.id.password);
        Date currentdate=new Date();
        SimpleDateFormat timeformat= new SimpleDateFormat("mm");
        final String t= timeformat.format(currentdate);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email.getText().toString().isEmpty())
                    email.setError("please enter your mail");
                if(password.getText().toString().isEmpty())
                    password.setError("please enter password");
                signin();

                }

        });
        forgot=findViewById(R.id.forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                if(!email.getText().toString().isEmpty())
                    mAuth.sendPasswordResetEmail(email.getText().toString());

            }
        });

    }
    public void signin(){
        email= findViewById(R.id.email);
        signin=findViewById(R.id.signin);
        password = findViewById(R.id.password);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("teacher");
        Query query=myRef.orderByChild("username").equalTo(email.getText().toString());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot d:dataSnapshot.getChildren()) {
                        birthday=d.child("birthday").getValue().toString();
                        String temp="";
                        for (int i=0 ; i<5; i++){
                            temp=temp+birthday.charAt(i);
                        }
                        birthday=temp;
                        name=d.child("name").getValue().toString();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i= new Intent(signin.this,MainActivity.class);
                            startActivity(i);
                            String currentDate = new SimpleDateFormat("dd/MM", Locale.getDefault()).format(new Date());
                            if(birthday.equals(currentDate)) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    CharSequence name = "birthday";
                                    String description = "hello";
                                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                                    channel.setDescription(description);
                                    NotificationManager notificationManager = getSystemService(NotificationManager.class);
                                    notificationManager.createNotificationChannel(channel);
                                }

                                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                        .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                                        .setContentTitle("happy birthday")
                                        .setContentText("happy birthday"+" "+name+" "+"wish you long and happy life")
                                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

                                notificationManager.notify(1, builder.build());
                            }

                            Toast.makeText(signin.this,"succesfly signed in",Toast.LENGTH_SHORT).show();


                        }
                        else{
                            Toast.makeText(signin.this,"there was a problem please try again",Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }


}
