package com.example.hotel_room_reservation;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class admin_page extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editSurname,nat ,editTextId,ed1;

    Button btnviewAll;
    Button btnDelete;
    Button btnviewUpdate;
    Button clearbtn;
    Button close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        myDb = new DatabaseHelper(this);
        ed1 = (EditText)findViewById(R.id.ed1);

        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        nat = (EditText)findViewById(R.id.editText_Marks);
        editTextId = (EditText)findViewById(R.id.editText_id);

        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate= (Button)findViewById(R.id.button_update);
        btnDelete= (Button)findViewById(R.id.button_delete);
        clearbtn=(Button)findViewById(R.id.clearbtn);
        close = (Button)findViewById(R.id.Close);

        viewAll();
        UpdateData();
        DeleteData();


        /**
         Validations
         */
        String NameValue = editName.getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (NameValue.isEmpty() || NameValue.length() >= 15 || !NameValue.matches(noWhiteSpace)) {
            editName.setError("Field cannot be empty" + "\n" + "name is too long" + "\n" + "White Spaces are not allowed");
        }

        String surValue = editSurname.getText().toString();
        String noWhiteSpace2 = "\\A\\w{4,20}\\z";
        if (surValue.isEmpty() || surValue.length() >= 15 || !surValue.matches(noWhiteSpace2)) {
            editSurname.setError("Field cannot be empty" + "\n" + "name is too long" + "\n" + "White Spaces are not allowed");
        }

        String NameValue2 = nat.getText().toString();
        String noWhiteSpace3 = "\\A\\w{4,20}\\z";
        if (NameValue2.isEmpty() || NameValue2.length() >= 15 || !NameValue2.matches(noWhiteSpace3)) {
            nat.setError("Field cannot be empty" + "\n" + "name is too long" + "\n" + "White Spaces are not allowed");
        }



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(admin_page.this);
                alertDialogBuilder.setTitle("Exit Application?");
                alertDialogBuilder
                        .setMessage("Click yes to exit!")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        moveTaskToBack(true);
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                    }
                                })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }


        });

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editName.setText("");
                editSurname.setText("");
                nat.setText("");
                editTextId.setText("");
            }
        });
    }
    public void DeleteData()
    {
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows =

                        myDb.deleteData(editTextId.getText().toString());
                if(deletedRows > 0)
                    Toast.makeText(admin_page.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(admin_page.this,"Data not Deleted",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void UpdateData()
    {
        btnviewUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                double room1 = Double.parseDouble(ed1.getText().toString());
                double sum= room1*0;

                boolean isUpdate =
                        myDb.updateData(editTextId.getText().toString(),
                                editName.getText().toString(),
                                editSurname.getText().toString(),nat.getText().toString(),+sum);
                if(isUpdate == true)
                    Toast.makeText(admin_page.this,"Data Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(admin_page.this,"Data not Updated",Toast.LENGTH_LONG).show();
            }
        });
    }


    public void viewAll()
    {
        btnviewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0) {
                    // show message
                    showMessage("Error","Nothing found");
                    return;

                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Reservation ID :"+ res.getString(0)+"\n");
                    buffer.append("Guest Name :"+ res.getString(1)+"\n");
                    buffer.append("Guest Surname :"+ res.getString(2)+"\n");
                    buffer.append("Guest Nationality :"+ res.getString(3)+"\n\n");
                    buffer.append("Room Price is :" + res.getString(4)+"  "+"OMR"+"\n\n");
                }
                // Show all data
                showMessage("Reservation Details",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder = new
                AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}