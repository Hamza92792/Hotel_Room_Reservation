package com.example.hotel_room_reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class roomdetails2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roomdetails2);
        final Button b1 =(android.widget.Button)findViewById(R.id.back);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj1=new
                        Intent(getApplicationContext(),Main_Page.class);
                startActivity(obj1);

                Toast.makeText(roomdetails2.this, "Room Selection", Toast.LENGTH_SHORT).show();

            }
        });
    }
}