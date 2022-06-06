package com.example.hotel_room_reservation;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button b1 =(Button)findViewById(R.id.Reservation);
        final Button b2 =(Button)findViewById(R.id.sign_in);
        final EditText ed1=(EditText)findViewById(R.id.editText);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = ed1.getText().toString();
                if (name.equals("Hamza")) {
                    Intent obj1=new
                            Intent(getApplicationContext(),admin_page.class);
                    startActivity(obj1);
                    Toast.makeText(MainActivity.this, "Successful Login", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this, "Try Again", Toast.LENGTH_SHORT).show();
                } }});
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj1=new
                        Intent(getApplicationContext(),Main_Page.class);
                startActivity(obj1);
                Toast.makeText(MainActivity.this, "Reservation Page", Toast.LENGTH_SHORT).show();
            }}); }}