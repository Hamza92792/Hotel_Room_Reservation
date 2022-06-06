package com.example.hotel_room_reservation;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.DatePickerDialog;
import java.util.Calendar;
import android.widget.DatePicker;
import android.widget.TextView;


public class Main_Page extends AppCompatActivity {

    private DatePickerDialog datePicker;
     Button dateButton;
     Button dateButton2;
     Button dateButton3;
     Button dateButton4;
    DatabaseHelper myDb;
    Button btnAddData;
    Button btnviewAll;
    Button btnviewUpdate;
    EditText ed1,ed2,ed3 ,ed4,name,sur,nat,editTextId;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        initDatePicker();
        myDb = new DatabaseHelper(this);

         dateButton= (Button)findViewById(R.id.cal);
         dateButton2= (Button)findViewById(R.id.cal2);
         dateButton3=(Button)findViewById(R.id.cal3);
          dateButton4=(Button)findViewById(R.id.cal4);


        final Button b1 =(Button)findViewById(R.id.details1);
        final Button b2 =(Button)findViewById(R.id.details2);
        final Button b3 =(Button)findViewById(R.id.details3);
        final Button b4 =(Button)findViewById(R.id.details4);
        final Button b11 =(Button)findViewById(R.id.book1);
        final Button b22 =(Button)findViewById(R.id.book2);
        final Button b33 =(Button)findViewById(R.id.book3);
        final Button b44 =(Button)findViewById(R.id.book4);


/**
Database
 */
        final Button b5 =(Button)findViewById(R.id.bookall);
        final Button b6 =(Button)findViewById(R.id.clear);
        final Button close =(Button)findViewById(R.id.Close);
        btnAddData = (Button)findViewById(R.id.button_ADD);
        editTextId = (EditText)findViewById(R.id.editText_id);
        btnviewUpdate= (Button)findViewById(R.id.update);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);


        name = (EditText)findViewById(R.id.name);
        sur = (EditText)findViewById(R.id.surname);
        nat = (EditText)findViewById(R.id.nationality);
        editTextId = (EditText)findViewById(R.id.editText_id);
/**
database end
 */
         ed1=(EditText)findViewById(R.id.days1);
         ed2=(EditText)findViewById(R.id.days2);
         ed3=(EditText)findViewById(R.id.days3);
         ed4=(EditText)findViewById(R.id.days4);
        final TextView res1=(TextView)findViewById(R.id.Res1);
        final TextView res2=(TextView)findViewById(R.id.Res2);
        final TextView res3=(TextView)findViewById(R.id.Res3);
        final TextView res4=(TextView)findViewById(R.id.Res4);


/**
 Validations
 */



            String NameValue = name.getText().toString();
            String noWhiteSpace = "\\A\\w{4,20}\\z";
            if (NameValue.isEmpty() || NameValue.length() >= 15 || !NameValue.matches(noWhiteSpace)) {
                name.setError("Field cannot be empty" + "\n" + "name is too long" + "\n" + "White Spaces are not allowed");
            }

        String surValue = sur.getText().toString();
        String noWhiteSpace2 = "\\A\\w{4,20}\\z";
        if (surValue.isEmpty() || surValue.length() >= 15 || !surValue.matches(noWhiteSpace2)) {
            sur.setError("Field cannot be empty" + "\n" + "name is too long" + "\n" + "White Spaces are not allowed");
        }

        String NameValue2 = nat.getText().toString();
        String noWhiteSpace3 = "\\A\\w{4,20}\\z";
        if (NameValue2.isEmpty() || NameValue2.length() >= 15 || !NameValue2.matches(noWhiteSpace3)) {
            nat.setError("Field cannot be empty" + "\n" + "name is too long" + "\n" + "White Spaces are not allowed");
        }






/**
 Calender
 */

        if(dateButton!=null){
        dateButton.setText(getTodaysDate());
        dateButton2.setText(getTodaysDate());
        dateButton3.setText(getTodaysDate());
        dateButton4.setText(getTodaysDate());}
/**
 Methods
 */
        AddData();
        UpdateData();

        viewAll();

close.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Main_Page.this);
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

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }


});

// selected price
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    double room1 = Double.parseDouble(ed1.getText().toString());
                    double room2 = Double.parseDouble(ed2.getText().toString());
                    double room3 = Double.parseDouble(ed3.getText().toString());
                    double room4 = Double.parseDouble(ed4.getText().toString());
                    double sum = (room1 * 23) + (room2 * 29) + (room3 * 42) + (room4 * 19);

                    AlertDialog.Builder ad = new AlertDialog.Builder(Main_Page.this);
                    ad.setMessage("The Value for your booking is" + " \n " + sum + " " + "OMR")
                            .setCancelable(false)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    AlertDialog art = ad.create();
                    art.setTitle("Reservation details");
                    art.show();





            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ed1.setText("      ");
                ed2.setText("      ");
                ed3.setText("      ");
                ed4.setText("      ");
                res1.setText("      ");
                res2.setText("      ");
                res3.setText("      ");
                res4.setText("      ");
                name.setText("  ");
                sur.setText("  ");
                nat.setText("  ");


            }
        });




        b11.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View view) {


                if(ed1.getText().toString().isEmpty())
                {

                    Toast.makeText(Main_Page.this, "Please Fill the Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    double day = Double.parseDouble(ed1.getText().toString());
                    double sum = day * 23;


                    AlertDialog.Builder ad = new AlertDialog.Builder(Main_Page.this);
                    ad.setMessage("Your Booking Will begin in " +" \n "+dateButton.getText().toString()+ " \n "
                            +"The Value for"+ " "+ ed1.getText().toString()+" "+"days is"+ " \n " + sum+ "OMR")
                            .setCancelable(false)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    AlertDialog art = ad.create();
                    art.setTitle("Reservation details");
                    art.show();



                    res1.setText("Reserved");

                    b11.setEnabled(false);
                    b11.setBackgroundColor(R.color.grey);



                }
            }
        });

        b22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ed2.getText().toString().isEmpty())
                {

                    Toast.makeText(Main_Page.this, "Please Fill the Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    double day = Double.parseDouble(ed2.getText().toString());
                    double sum = day * 29;

                    AlertDialog.Builder ad = new AlertDialog.Builder(Main_Page.this);
                    ad.setMessage("You have Booked on " +" \n "+dateButton2.getText().toString()+ " \n "+"The Vale for"+ " "+ ed2.getText().toString()+" "+"days is"+ " \n " + sum+ "OMR")
                            .setCancelable(false)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    AlertDialog art = ad.create();
                    art.setTitle("Reservation details");
                    art.show();
                    res2.setText("Reserved");
                }
            }

        });

        b33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ed3.getText().toString().isEmpty())
                {

                    Toast.makeText(Main_Page.this, "Please Fill the Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    double day = Double.parseDouble(ed3.getText().toString());
                    double sum = day * 42;

                    AlertDialog.Builder ad = new AlertDialog.Builder(Main_Page.this);
                    ad.setMessage("You have Booked on " +" \n "+dateButton3.getText().toString()+ " \n "+"The Vale for"+ " "+ ed3.getText().toString()+" "+"days is"+ " \n " + sum+ "OMR")
                            .setCancelable(false)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    AlertDialog art = ad.create();
                    art.setTitle("Reservation details");
                    art.show();
                    res3.setText("Reserved");
                }
            }
        });
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                    calendar = Calendar.getInstance();
                    year = calendar.get(Calendar.YEAR);
                    month = calendar.get(Calendar.MONTH);
                    dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                    datePickerDialog = new DatePickerDialog(Main_Page.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                                    dateButton.setText(day + "/" + (month + 1) + "/" + year);
                                }
                            }, year, month, dayOfMonth);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                    datePickerDialog.show();
                }
            });
        dateButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Main_Page.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dateButton2.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        dateButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Main_Page.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dateButton3.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
        dateButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Main_Page.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                dateButton4.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

        b44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ed4.getText().toString().isEmpty())
                {

                    Toast.makeText(Main_Page.this, "Please Fill the Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    double day = Double.parseDouble(ed4.getText().toString());
                    double sum = day * 19;

                    AlertDialog.Builder ad = new AlertDialog.Builder(Main_Page.this);
                    ad.setMessage("You have Booked on " +" \n "+dateButton4.getText().toString()+ " \n "+"The Vale for"+ " "+ ed4.getText().toString()+" "+"days is"+ " \n " + sum+ "OMR")
                            .setCancelable(false)
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    AlertDialog art = ad.create();
                    art.setTitle("Reservation details");
                    art.show();
                    res4.setText("Reserved");
                }
            }
        });



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj1=new
                        Intent(getApplicationContext(),roomdetails1.class);
                startActivity(obj1);

                Toast.makeText(Main_Page.this, "Single deluxe room", Toast.LENGTH_SHORT).show();

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj1=new
                        Intent(getApplicationContext(),roomdetails2.class);
                startActivity(obj1);

                Toast.makeText(Main_Page.this, "single Business deluxe room", Toast.LENGTH_SHORT).show();

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj1=new
                        Intent(getApplicationContext(),roomdetails3.class);
                startActivity(obj1);

                Toast.makeText(Main_Page.this, "Family Suit", Toast.LENGTH_SHORT).show();

            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj1=new
                        Intent(getApplicationContext(),roomdetails4.class);
                startActivity(obj1);

                Toast.makeText(Main_Page.this, "single room", Toast.LENGTH_SHORT).show();

            }
        });



    }
    /**
     Methods
     */




    private void UpdateData() {
        btnviewUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {


                double room1 = Double.parseDouble(ed1.getText().toString());
                double room2 = Double.parseDouble(ed2.getText().toString());
                double room3 = Double.parseDouble(ed3.getText().toString());
                double room4 = Double.parseDouble(ed4.getText().toString());
                double sum = (room1 * 23) + (room2 * 29) + (room3 * 42) + (room4 * 19);
                boolean isUpdate = myDb.updateData(editTextId.getText().toString(),
                                name.getText().toString(),
                                sur.getText().toString(),nat.getText().toString(),+ sum);
                if(isUpdate == true)
                    Toast.makeText(Main_Page.this,"Data Update",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Main_Page.this,"Data not Updated",Toast.LENGTH_LONG).show();
            }
        });
    }

    
    private void AddData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double room1 = Double.parseDouble(ed1.getText().toString());
                double room2 = Double.parseDouble(ed2.getText().toString());
                double room3 = Double.parseDouble(ed3.getText().toString());
                double room4 = Double.parseDouble(ed4.getText().toString());
                double sum = (room1 * 23) + (room2 * 29) + (room3 * 42) + (room4 * 19);


                boolean isInserted = myDb.insertData(name.getText().toString(),
                                sur.getText().toString(),
                                nat.getText().toString(),+ sum );
                if(isInserted ==true)
                    Toast.makeText(Main_Page.this,"Data Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(Main_Page.this,"Data not Inserted",Toast.LENGTH_LONG).show();
            }
        });

    }

    private void viewAll() {
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
        androidx.appcompat.app.AlertDialog.Builder builder = new
                androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    /**
     Calender Code
     */



    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MARCH);
        month = month +1;
        int Day = cal.get(Calendar.DAY_OF_MONTH);
       return makeDateString(Day ,month, year);

    }



    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int Year, int month, int day) {
month = month +1;

String date = makeDateString(day, month, Year);
                String[] templist = new  String [4];
                templist[0]= (String) dateButton.getText();
                templist[1]= (String) dateButton2.getText();
                templist[2]= (String) dateButton3.getText();
                templist[3]= (String) dateButton4.getText();

                if(templist[0].isEmpty())
                    dateButton.setText(date);
                if(templist[1].isEmpty())
                     dateButton2.setText(date);
                if(templist[2].isEmpty())
                    dateButton3.setText(date);
                if(templist[3].isEmpty())
                    dateButton4.setText(date);

            }};


        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MARCH);
        int Day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePicker= new DatePickerDialog(this, style, dateSetListener, year, month,Day);
        datePicker.getDatePicker().setMinDate(System.currentTimeMillis());


        {
    }
}

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + "-"+ day +"-"+ year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";
        return "JAN";
    }
public void Date(View view){

datePicker.show();
}



}