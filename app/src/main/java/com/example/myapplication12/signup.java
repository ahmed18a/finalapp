package com.example.myapplication12;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    EditText password,id,birthday,name,email;
    private FirebaseAuth mAuth;
    Button Signup,pass;
    FirebaseDatabase database;
    DatabaseReference myRef;
    teacher t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        password = (EditText) findViewById(R.id.password);
        id = (EditText) findViewById(R.id.id);
        birthday = (EditText) findViewById(R.id.birthday);
        Signup = (Button) findViewById(R.id.signup);
        database = FirebaseDatabase.getInstance();
        email = (EditText) findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null)
            startActivity(new Intent(signup.this,MainActivity.class));
        myRef = database.getReference("teacher");
        name = (EditText) findViewById(R.id.name);
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty())
                    name.setError("please fill a real name");
                if(email.getText().toString().isEmpty())
                    email.setError("please fill a true email");
                if(password.getText().toString().isEmpty())
                    password.setError("please fill a true password");
                if(id.getText().toString().isEmpty())
                    id.setError("please fill a true id");
                if(birthday.getText().toString().isEmpty())
                    birthday.setError("please fill a true birthday");
                else {
                    signupp();
                    t=new teacher(name.getText().toString(),email.getText().toString(),password.getText().toString(),Float.parseFloat(id.getText().toString()),birthday.getText().toString());
                    myRef.push().setValue(t);

                }

            }
        });
        pass=findViewById(R.id.pass);
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(signup.this,signin.class);
                startActivity(i);
            }
        });



    }

    public void signupp() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(signup.this,"you succesfully signed up",Toast.LENGTH_SHORT).show();
                            mAuth.getCurrentUser().sendEmailVerification();
                            Toast.makeText(signup.this, "verfication was sent to your email", Toast.LENGTH_SHORT).show();
                            Intent i =new Intent(signup.this,signin.class);
                            startActivity(i);

                        }
                        else{
                            Toast.makeText(signup.this,"failed to sign up please try again",Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i=getIntent();
        birthday.setText(i.getStringExtra("date"));
        Toast.makeText(this, ""+birthday.getText().toString(), Toast.LENGTH_SHORT).show();
    }
}

