package com.example.myapplication12;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link message#newInstance} factory method to
 * create an instance of this fragment.
 */
public class message extends Fragment {
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS=0;
    private EditText phoneNumber;
    private EditText smsBody;
    private Button smsManagerBtn;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public message() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment message.
     */
    // TODO: Rename and change types and number of parameters
    public static message newInstance(String param1, String param2) {
        message fragment = new message();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        smsManagerBtn=view.findViewById(R.id.smsManager);
        phoneNumber=view.findViewById(R.id.phoneNumber);
        smsBody=view.findViewById(R.id.smsBody);
        if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale( getActivity(),Manifest.permission.SEND_SMS)){

            }
            else{
                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }
        smsManagerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Get the default instance of the SmsManager
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber.getText().toString(),
                            null,
                            smsBody.getText().toString(),
                            null,
                            null);
                    Toast.makeText(getContext(), "Your sms has successfully sent!",
                            Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Toast.makeText(getContext(),"Your sms has failed...",
                            Toast.LENGTH_LONG).show();
                    ex.printStackTrace();
                }
            }
        });


    }
}
