package com.example.myapplication12;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class picktime extends AppCompatActivity {
    private Object DatePicker;
    private TextView textViewDate;
    private TimePickerDialog timePickerDialog;
    private DatePickerDialog datePickerDialog;
    TextView timeshow,dateshow;
    Button ret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picktime);
        setContentView(R.layout.activity_picktime);
        //////////////////////////////////////////////////////////////////////////////////////////
        //DATEPICKER[ACTUALLY WORKS]
        Button datepicker = findViewById(R.id.datepicker);
        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call PickDate for Date
                PickDate();
            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////
        //TIMEPICKER[ACTUALLY WORKS]
        Button timepick = findViewById(R.id.timepick);
        timepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call PickTime for Time
                PickTime();
            }
        });
        textViewDate = findViewById(R.id.dateshow);
        ret=findViewById(R.id.ret);
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(picktime.this,signup.class);
                dateshow=findViewById(R.id.dateshow);
                i.putExtra("date",dateshow.getText().toString());
                startActivity(i);
            }
        });

    }


    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TextView tv_timeshow = findViewById(R.id.timeshow);
        tv_timeshow.setText("Hour: " + hourOfDay + " Minute: " + minute);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////
    Calendar calendar = Calendar.getInstance();
    String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

    private void PickDate() {
        final java.util.Calendar calendar = java.util.Calendar.getInstance();
        datePickerDialog = new DatePickerDialog( picktime.this, new DatePickerDialog.OnDateSetListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int dayOfMonth) {
                String date = dayOfMonth+"/"+(month+1)+"/"+year;
                /// here set date value in txtview
                dateshow=findViewById(R.id.dateshow);
                dateshow.setText(date);
            }
        },calendar.get( java.util.Calendar.YEAR),calendar.get( java.util.Calendar.MONTH ),calendar.get( java.util.Calendar.DAY_OF_MONTH ) );
        datePickerDialog.show();

    }

    private void PickTime(){
        timePickerDialog = new TimePickerDialog(picktime.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String time = hourOfDay + ":"+minute;
                /// here set time value in txtview
                timeshow=findViewById(R.id.timeshow);
                timeshow.setText(time);


            }
        },calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true );

        timePickerDialog.show();
    }
    public void setTextViewDate(TextView textViewDate) {
        this.textViewDate = textViewDate;
        textViewDate.setText(currentDate);
    }
    }

