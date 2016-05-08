package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ScheduleOfDoctors extends Activity {

    Context context = this;

    String json_string;
    JSONArray jsonArray;


//Saturday

    TextView textview13;
    String SaturdayfirstSlot;

    TextView textview14;
    String Saturdaygap1;

    TextView textview15;
    String SaturdaysecondSlot;

    TextView textview16;
    String Saturdaygap2;

    TextView textview17;
    String SaturdaythirdSlot;

    TextView textview18;
    String Saturdaygap3;

    TextView textview19;
    String SaturdayfourthSlot;

    TextView textview20;
    String Saturdaygap4;

    TextView textview21;
    String SaturdayfifthSlot;

    TextView textview22;
    String Saturdaygap5;


    //Sunday

    TextView textview24;
    String SundayfirstSlot;

    TextView textview25;
    String Sundaygap1;

    TextView textview26;
    String SundaysecondSlot;

    TextView textview27;
    String Sundaygap2;

    TextView textview28;
    String SundaythirdSlot;

    TextView textview29;
    String Sundaygap3;

    TextView textview30;
    String SundayfourthSlot;

    TextView textview31;
    String Sundaygap4;

    TextView textview32;
    String SundayfifthSlot;

    TextView textview33;
    String Sundaygap5;









    //Monday


    TextView textview35;
    String MondayfirstSlot;

    TextView textview36;
    String  Mondaygap1;

    TextView textview37;
    String MondaysecondSlot;

    TextView textview38;
    String Mondaygap2;

    TextView textview39;
    String MondaythirdSlot;

    TextView textview40;
    String Mondaygap3;

    TextView textview41;
    String MondayfourthSlot;

    TextView textview42;
    String Mondaygap4;

    TextView textview43;
    String MondayfifthSlot;

    TextView textview44;
    String Mondaygap5;











    //Tuesday


    TextView textview46;
    String TuesdayfirstSlot;

    TextView textview47;
    String  Tuesdaygap1;

    TextView textview48;
    String TuesdaysecondSlot;

    TextView textview49;
    String Tuesdaygap2;

    TextView textview50;
    String TuesdaythirdSlot;

    TextView textview51;
    String Tuesdaygap3;

    TextView textview52;
    String TuesdayfourthSlot;

    TextView textview53;
    String Tuesdaygap4;

    TextView textview54;
    String TuesdayfifthSlot;

    TextView textview55;
    String Tuesdaygap5;




    //Wednesday


    TextView textview57;
    String WednesdayfirstSlot;

    TextView textview58;
    String  Wednesdaygap1;

    TextView textview59;
    String WednesdaysecondSlot;

    TextView textview60;
    String Wednesdaygap2;

    TextView textview61;
    String WednesdaythirdSlot;

    TextView textview62;
    String Wednesdaygap3;

    TextView textview63;
    String WednesdayfourthSlot;

    TextView textview64;
    String Wednesdaygap4;

    TextView textview65;
    String WednesdayfifthSlot;

    TextView textview66;
    String Wednesdaygap5;





    //Thursday


    TextView textview68;
    String ThursdayfirstSlot;

    TextView textview69;
    String  Thursdaygap1;

    TextView textview70;
    String ThursdaysecondSlot;

    TextView textview71;
    String Thursdaygap2;

    TextView textview72;
    String ThursdaythirdSlot;

    TextView textview73;
    String Thursdaygap3;

    TextView textview74;
    String ThursdayfourthSlot;

    TextView textview75;
    String Thursdaygap4;

    TextView textview76;
    String ThursdayfifthSlot;

    TextView textview77;
    String Thursdaygap5;





























    String datePassed;
    static String finalDate;
    String doctor_ID;
    String dayOfWeek;

    static final int saturdaySecond = 0;
    static final int saturdaygap1  = 1;

    // variables to save user selected date and time
    private int mYear, mMonth, mDay;

    Date date2;
    private void updateDate(){


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mYear).append("-")
                .append(mMonth + 1).append("-")
                .append(mDay).append("");


        finalDate = stringBuilder.toString();


    }

    private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
        // the callback received when the user "sets" the Date in the DatePickerDialog
        public void onDateSet(DatePicker view, int yearSelected,
                              int monthOfYear, int dayOfMonth) {
            SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
            Date date = new Date(yearSelected, monthOfYear, dayOfMonth - 1);
            dayOfWeek = simpledateformat.format(date);

            Log.d("DAY OF WEEK IS", dayOfWeek);
            if (dayOfWeek.equals("Saturday")) {
                mYear = yearSelected;
                mMonth = monthOfYear;
                mDay = dayOfMonth;

                updateDate();
                Date d = new Date(mYear-1900,mMonth,mDay);
                SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");

                finalDate = inFormat.format(d);
                Toast.makeText(getApplicationContext(), new StringBuilder().append("Date choosen is ").append(finalDate), Toast.LENGTH_SHORT).show();

          //  Toast.makeText(getApplicationContext(), "Date selected is: " + mDay + "-" + mMonth + "-" + mYear, Toast.LENGTH_LONG).show();
            //datePassed =  mYear + "-" + mMonth + "-" + mDay;

                Log.d("daaaaaate", finalDate);

            String method = "setAppointmentSaturdaySecond";
            BackgroundTask backgroundTask = new BackgroundTask();
            backgroundTask.execute(method,doctor_ID,finalDate);



            } else {
                Toast.makeText(getApplicationContext(), "This day is not Saturday, Please choose another day.", Toast.LENGTH_LONG).show();

            }
        }
    };





    private DatePickerDialog.OnDateSetListener aDateSetListener = new DatePickerDialog.OnDateSetListener() {
        // the callback received when the user "sets" the Date in the DatePickerDialog
        public void onDateSet(DatePicker view, int yearSelected,
                              int monthOfYear, int dayOfMonth) {
            SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
            Date date = new Date(yearSelected, monthOfYear, dayOfMonth - 1);
            dayOfWeek = simpledateformat.format(date);

            Log.d("DAY OF WEEK IS", dayOfWeek);
            if (dayOfWeek.equals("Saturday")) {
                mYear = yearSelected;
                mMonth = monthOfYear;
                mDay = dayOfMonth;

                updateDate();
                Date d = new Date(mYear-1900,mMonth,mDay);
                SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");

                finalDate = inFormat.format(d);
                Toast.makeText(getApplicationContext(), new StringBuilder().append("Date choosen is ").append(finalDate), Toast.LENGTH_SHORT).show();

                //  Toast.makeText(getApplicationContext(), "Date selected is: " + mDay + "-" + mMonth + "-" + mYear, Toast.LENGTH_LONG).show();
                //datePassed =  mYear + "-" + mMonth + "-" + mDay;

                Log.d("daaaaaate", finalDate);

                String method = "setAppointmentSaturdaygap1";
                BackgroundTask backgroundTask = new BackgroundTask();
                backgroundTask.execute(method,doctor_ID,finalDate);



            } else {
                Toast.makeText(getApplicationContext(), "This day is not Saturday, Please choose another day.", Toast.LENGTH_LONG).show();

            }
        }
    };
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case saturdaySecond:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);

            case saturdaygap1:
                return new DatePickerDialog(this,
                        aDateSetListener,
                        mYear, mMonth, mDay);


        }
        return null;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_of_doctors);


        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

    // Date d = new Date(mYear,mMonth,mDay);
       // SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");

        updateDate();
       // finalDate = inFormat.format(d);


        json_string = getIntent().getExtras().getString("doctor_schedule");
        try {
            JSONObject object = new JSONObject(json_string);

            jsonArray = object.getJSONArray("doctor_schedule");

            doctor_ID = jsonArray.getJSONObject(0).getString("doctor_ID");

            SaturdayfirstSlot = jsonArray.getJSONObject(0).getString("SaturdayfirstSlot");
            textview13 = (TextView) findViewById(R.id.textView13);
            if (SaturdayfirstSlot != "null") {

                textview13.setText(SaturdayfirstSlot);
            } else {
                textview13.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview13.setTextSize(20);
                textview13.setText("Add Appointment");
            }

            Saturdaygap1 = jsonArray.getJSONObject(0).getString("Saturdaygap1");
            textview14 = (TextView) findViewById(R.id.textView14);
            if (Saturdaygap1 != "null") {
                textview14.setText(Saturdaygap1);
            } else {
                textview14.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview14.setTextSize(20);
                textview14.setText("Add Appointment");

                textview14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDialog(saturdaygap1);

                    }
                });

            }


            SaturdaysecondSlot = jsonArray.getJSONObject(0).getString("SaturdaysecondSlot");
            textview15 = (TextView) findViewById(R.id.textView15);
            if (SaturdaysecondSlot != "null") {
                textview15.setText(SaturdaysecondSlot);
            } else {
                textview15.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview15.setTextSize(20);
                textview15.setText("Add Appointment");

                textview15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       /* String method = "setAppointmentSaturdaySecond";
                        BackgroundTask backgroundTask = new BackgroundTask();
                        backgroundTask.execute(method,doctor_ID);*/


                        showDialog(saturdaySecond);




                        /*String method = "setAppointmentSaturdaySecond";
                        BackgroundTask backgroundTask = new BackgroundTask();
                        backgroundTask.execute(method,doctor_ID,datePassed);
                        */
                    }
                });
            }
            Saturdaygap2 = jsonArray.getJSONObject(0).getString("Saturdaygap2");
            textview16 = (TextView) findViewById(R.id.textView16);
            if (Saturdaygap2 != "null") {
                textview16.setText(Saturdaygap2);
            } else {
                textview16.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview16.setTextSize(20);
                textview16.setText("Add Appointment");
            }
            SaturdaythirdSlot = jsonArray.getJSONObject(0).getString("SaturdaythirdSlot");
            textview17 = (TextView) findViewById(R.id.textView17);
            if (SaturdaythirdSlot != "null") {
                textview17.setText(SaturdaythirdSlot);
            } else {
                textview17.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview17.setTextSize(20);
                textview17.setText("Add Appointment");
            }

            Saturdaygap3 = jsonArray.getJSONObject(0).getString("Saturdaygap3");
            textview18 = (TextView) findViewById(R.id.textView18);
            if (Saturdaygap3 != "null") {
                textview18.setText(Saturdaygap3);
            } else {
                textview18.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview18.setTextSize(20);
                textview18.setText("Add Appointment");
            }

            SaturdayfourthSlot = jsonArray.getJSONObject(0).getString("SaturdayfourthSlot");
            textview19 = (TextView) findViewById(R.id.textView19);
            if (SaturdayfourthSlot != "null") {
                textview19.setText(SaturdayfourthSlot);
            } else {
                textview19.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview19.setTextSize(20);
                textview19.setText("Add Appointment");
            }
            Saturdaygap4 = jsonArray.getJSONObject(0).getString("Saturdaygap4");
            textview20 = (TextView) findViewById(R.id.textView20);
            if (Saturdaygap4 != "null") {
                textview20.setText(Saturdaygap4);
            } else {
                textview20.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview20.setTextSize(20);
                textview20.setText("Add Appointment");
            }

            SaturdayfifthSlot = jsonArray.getJSONObject(0).getString("SaturdayfifthSlot");
            textview21 = (TextView) findViewById(R.id.textView21);
            if (SaturdayfifthSlot != "null") {
                textview21.setText(SaturdayfifthSlot);
            } else {
                //textview21.setBackgroundColor(Color.GRAY);
                textview21.setTextSize(20);
                textview21.setText("Add Appointment");
                textview21.setBackground(getResources().getDrawable(R.drawable.free_slot));
            }

            Saturdaygap5 = jsonArray.getJSONObject(0).getString("Saturdaygap5");
            textview22 = (TextView) findViewById(R.id.textView22);
            if (Saturdaygap5 != "null") {
                textview22.setText(Saturdaygap5);
            } else {
                textview22.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview22.setTextSize(20);
                textview22.setText("Add Appointment");
            }



















//Sunday

            SundayfirstSlot = jsonArray.getJSONObject(1).getString("SundayfirstSlot");
            textview24 = (TextView) findViewById(R.id.textView24);
            if (SundayfirstSlot != "null") {
                textview24.setText(SundayfirstSlot);
            } else {
                textview24.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview24.setTextSize(20);
                textview24.setText("Add Appointment");
            }

            Sundaygap1 = jsonArray.getJSONObject(1).getString("Sundaygap1");
            textview25 = (TextView) findViewById(R.id.textView25);
            if (Sundaygap1 != "null") {
                textview25.setText(Sundaygap1);
            } else {
                textview25.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview25.setTextSize(20);
                textview25.setText("Add Appointment");
            }


            SundaysecondSlot = jsonArray.getJSONObject(1).getString("SundaysecondSlot");
            textview26 = (TextView) findViewById(R.id.textView26);

            if (SundaysecondSlot != "null") {
                textview26.setText(SundaysecondSlot);
            } else {
                textview26.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview26.setTextSize(20);
                textview26.setText("Add Appointment");
            }

            Sundaygap2 = jsonArray.getJSONObject(1).getString("Sundaygap2");
            textview27 = (TextView) findViewById(R.id.textView27);
            if (Sundaygap2 != "null") {
                textview27.setText(Sundaygap2);
            } else {
                textview27.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview27.setTextSize(20);
                textview27.setText("Add Appointment");
            }

            SundaythirdSlot = jsonArray.getJSONObject(1).getString("SundaythirdSlot");
            textview28 = (TextView) findViewById(R.id.textView28);
            if (SundaythirdSlot != "null") {
                textview28.setText(SundaythirdSlot);
            } else {
                textview28.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview28.setTextSize(20);
                textview28.setText("Add Appointment");
            }

            Sundaygap3 = jsonArray.getJSONObject(1).getString("Sundaygap3");
            textview29 = (TextView) findViewById(R.id.textView29);
            if (Sundaygap3 != "null") {
                textview29.setText(Sundaygap3);
            } else {
                textview29.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview29.setTextSize(20);
                textview29.setText("Add Appointment");
            }


            SundayfourthSlot = jsonArray.getJSONObject(1).getString("SundayfourthSlot");
            textview30 = (TextView) findViewById(R.id.textView30);
            if (SundayfourthSlot != "null") {
                textview30.setText(SundayfourthSlot);
            } else {
                textview30.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview30.setTextSize(20);
                textview30.setText("Add Appointment");
            }

            Sundaygap4 = jsonArray.getJSONObject(1).getString("Sundaygap4");
            textview31 = (TextView) findViewById(R.id.textView31);
            if (Sundaygap4 != "null") {
                textview31.setText(Sundaygap4);
            } else {
                textview31.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview31.setTextSize(20);
                textview31.setText("Add Appointment");
            }


            SundayfifthSlot = jsonArray.getJSONObject(1).getString("SundayfifthSlot");
            textview32 = (TextView) findViewById(R.id.textView32);
            if (SundayfifthSlot != "null") {
                textview32.setText(SundayfifthSlot);
            } else {
                textview32.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview32.setTextSize(20);
                textview32.setText("Add Appointment");
            }

            Sundaygap5 = jsonArray.getJSONObject(1).getString("Sundaygap5");
            textview33 = (TextView) findViewById(R.id.textView33);
            if (Sundaygap5 != "null") {
                textview33.setText(Sundaygap5);
            } else {
                textview33.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview33.setTextSize(20);
                textview33.setText("Add Appointment");
            }


//Monday

            MondayfirstSlot = jsonArray.getJSONObject(2).getString("MondayfirstSlot");
            textview35 = (TextView) findViewById(R.id.textView35);
            if (MondayfirstSlot != "null") {
                textview35.setText(MondayfirstSlot);
            } else {
                textview35.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview35.setTextSize(20);
                textview35.setText("Add Appointment");
            }

            Mondaygap1 = jsonArray.getJSONObject(2).getString("Mondaygap1");
            textview36 = (TextView) findViewById(R.id.textView36);
            if (Mondaygap1 != "null") {
                textview36.setText(Mondaygap1);
            } else {
                textview36.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview36.setTextSize(20);
                textview36.setText("Add Appointment");
            }

            MondaysecondSlot = jsonArray.getJSONObject(2).getString("MondaysecondSlot");
            textview37 = (TextView) findViewById(R.id.textView37);
            if (MondaysecondSlot != "null") {
                textview37.setText(MondaysecondSlot);
            } else {
                textview37.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview37.setTextSize(20);
                textview37.setText("Add Appointment");
            }

            Mondaygap2 = jsonArray.getJSONObject(2).getString("Mondaygap2");
            textview38 = (TextView) findViewById(R.id.textView38);
            if (Mondaygap2 != "null") {
                textview38.setText(Mondaygap2);
            } else {
                textview38.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview38.setTextSize(20);
                textview38.setText("Add Appointment");
            }

            MondaythirdSlot = jsonArray.getJSONObject(2).getString("MondaythirdSlot");
            textview39 = (TextView) findViewById(R.id.textView39);
            if (MondaythirdSlot != "null") {
                textview39.setText(MondaythirdSlot);
            } else {
                textview39.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview39.setTextSize(20);
                textview39.setText("Add Appointment");
            }

            Mondaygap3 = jsonArray.getJSONObject(2).getString("Mondaygap3");
            textview40 = (TextView) findViewById(R.id.textView40);
            if (Mondaygap3 != "null") {
                textview40.setText(Mondaygap3);
            } else {
                textview40.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview40.setTextSize(20);
                textview40.setText("Add Appointment");
            }

            MondayfourthSlot = jsonArray.getJSONObject(2).getString("MondayfourthSlot");
            textview41 = (TextView) findViewById(R.id.textView41);
            if (MondayfourthSlot != "null") {
                textview41.setText(MondayfourthSlot);
            } else {
                textview41.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview41.setTextSize(20);
                textview41.setText("Add Appointment");
            }

            Mondaygap4 = jsonArray.getJSONObject(2).getString("Mondaygap4");
            textview42 = (TextView) findViewById(R.id.textView42);
            if (Mondaygap4 != "null") {
                textview42.setText(Mondaygap4);
            } else {
                textview42.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview42.setTextSize(20);
                textview42.setText("Add Appointment");
            }


            MondayfifthSlot = jsonArray.getJSONObject(2).getString("MondayfifthSlot");
            textview43 = (TextView) findViewById(R.id.textView43);
            if (MondayfifthSlot != "null") {
                textview43.setText(MondayfifthSlot);
            } else {
                textview43.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview43.setTextSize(20);
                textview43.setText("Add Appointment");
            }


            Mondaygap5 = jsonArray.getJSONObject(2).getString("Mondaygap5");
            textview44 = (TextView) findViewById(R.id.textView44);

            if (Mondaygap5 != "null") {
                textview44.setText(Mondaygap5);
            } else {
                textview44.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview44.setTextSize(20);
                textview44.setText("Add Appointment");
            }




//Tuesday

            TuesdayfirstSlot = jsonArray.getJSONObject(3).getString("TuesdayfirstSlot");
            textview46 = (TextView) findViewById(R.id.textView46);

            if (TuesdayfirstSlot != "null") {
                textview46.setText(TuesdayfirstSlot);
            } else {
                textview46.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview46.setTextSize(20);
                textview46.setText("Add Appointment");
            }


            Tuesdaygap1 = jsonArray.getJSONObject(3).getString("Tuesdaygap1");
            textview47 = (TextView) findViewById(R.id.textView47);

            if (Tuesdaygap1 != "null") {
                textview47.setText(Tuesdaygap1);
            } else {
                textview47.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview47.setTextSize(20);
                textview47.setText("Add Appointment");
            }



            TuesdaysecondSlot = jsonArray.getJSONObject(3).getString("TuesdaysecondSlot");
            textview48 = (TextView) findViewById(R.id.textView48);
            if (TuesdaysecondSlot != "null") {
                textview48.setText(TuesdaysecondSlot);
            } else {
                textview48.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview48.setTextSize(20);
                textview48.setText("Add Appointment");
            }


            Tuesdaygap2 = jsonArray.getJSONObject(3).getString("Tuesdaygap2");
            textview49 = (TextView) findViewById(R.id.textView49);
            if (Tuesdaygap2 != "null") {
                textview49.setText(Tuesdaygap2);
            } else {
                textview49.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview49.setTextSize(20);
                textview49.setText("Add Appointment");
            }


            TuesdaythirdSlot = jsonArray.getJSONObject(3).getString("TuesdaythirdSlot");
            textview50 = (TextView) findViewById(R.id.textView50);
            if (TuesdaythirdSlot != "null") {
                textview50.setText(TuesdaythirdSlot);
            } else {
                textview50.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview50.setTextSize(20);
                textview50.setText("Add Appointment");
            }

            Tuesdaygap3 = jsonArray.getJSONObject(3).getString("Tuesdaygap3");
            textview51 = (TextView) findViewById(R.id.textView51);
            if (Tuesdaygap3 != "null") {
                textview51.setText(Tuesdaygap3);
            } else {
                textview51.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview51.setTextSize(20);
                textview51.setText("Add Appointment");
            }


            TuesdayfourthSlot = jsonArray.getJSONObject(3).getString("TuesdayfourthSlot");
            textview52 = (TextView) findViewById(R.id.textView52);
            if (TuesdayfourthSlot != "null") {
                textview52.setText(TuesdayfourthSlot);
            } else {
                textview52.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview52.setTextSize(20);
                textview52.setText("Add Appointment");
            }

            Tuesdaygap4 = jsonArray.getJSONObject(3).getString("Tuesdaygap4");
            textview53 = (TextView) findViewById(R.id.textView53);
            if (Tuesdaygap4 != "null") {
                textview53.setText(Tuesdaygap4);
            } else {
                textview53.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview53.setTextSize(20);
                textview53.setText("Add Appointment");
            }

            TuesdayfifthSlot = jsonArray.getJSONObject(3).getString("TuesdayfifthSlot");
            textview54 = (TextView) findViewById(R.id.textView54);
            if (TuesdayfifthSlot != "null") {
                textview54.setText(TuesdayfifthSlot);
            } else {
                textview54.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview54.setTextSize(20);
                textview54.setText("Add Appointment");
            }

            Tuesdaygap5 = jsonArray.getJSONObject(3).getString("Tuesdaygap5");
            textview55 = (TextView) findViewById(R.id.textView55);
            if (Tuesdaygap5 != "null") {
                textview55.setText(Tuesdaygap5);
            } else {
                textview55.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview55.setTextSize(20);
                textview55.setText("Add Appointment");
            }




//Wednesday

            WednesdayfirstSlot = jsonArray.getJSONObject(4).getString("WednesdayfirstSlot");
            textview57 = (TextView) findViewById(R.id.textView57);
            if (WednesdayfirstSlot != "null") {
                textview57.setText(WednesdayfirstSlot);
            } else {
                textview57.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview57.setTextSize(20);
                textview57.setText("Add Appointment");
            }

            Wednesdaygap1 = jsonArray.getJSONObject(4).getString("Wednesdaygap1");
            textview58 = (TextView) findViewById(R.id.textView58);
            if (Wednesdaygap1 != "null") {
                textview58.setText(Wednesdaygap1);
            } else {
                textview58.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview58.setTextSize(20);
                textview58.setText("Add Appointment");
            }


            WednesdaysecondSlot = jsonArray.getJSONObject(4).getString("WednesdaysecondSlot");
            textview59 = (TextView) findViewById(R.id.textView59);
            if (WednesdaysecondSlot != "null") {
                textview59.setText(WednesdaysecondSlot);
            } else {
                textview59.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview59.setTextSize(20);
                textview59.setText("Add Appointment");
            }

            Wednesdaygap2 = jsonArray.getJSONObject(4).getString("Wednesdaygap2");
            textview60 = (TextView) findViewById(R.id.textView60);
            if (Wednesdaygap2 != "null") {
                textview60.setText(Wednesdaygap2);
            } else {
                textview60.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview60.setTextSize(20);
                textview60.setText("Add Appointment");
            }

            WednesdaythirdSlot = jsonArray.getJSONObject(4).getString("WednesdaythirdSlot");
            textview61 = (TextView) findViewById(R.id.textView61);
            if (WednesdaythirdSlot != "null") {
                textview61.setText(WednesdaythirdSlot);
            } else {
                textview61.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview61.setTextSize(20);
                textview61.setText("Add Appointment");
            }

            Wednesdaygap3 = jsonArray.getJSONObject(4).getString("Wednesdaygap3");
            textview62 = (TextView) findViewById(R.id.textView62);
            if (Wednesdaygap3 != "null") {
                textview62.setText(Wednesdaygap3);
            } else {
                textview62.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview62.setTextSize(20);
                textview62.setText("Add Appointment");
            }


            WednesdayfourthSlot = jsonArray.getJSONObject(4).getString("WednesdayfourthSlot");
            textview63 = (TextView) findViewById(R.id.textView63);
            if (WednesdayfourthSlot != "null") {
                textview63.setText(WednesdayfourthSlot);
            } else {
                textview63.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview63.setTextSize(20);
                textview63.setText("Add Appointment");
            }


            Wednesdaygap4 = jsonArray.getJSONObject(4).getString("Wednesdaygap4");
            textview64 = (TextView) findViewById(R.id.textView64);
            if (Wednesdaygap4 != "null") {
                textview64.setText(Wednesdaygap4);
            } else {
                textview64.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview64.setTextSize(20);
                textview64.setText("Add Appointment");
            }

            WednesdayfifthSlot = jsonArray.getJSONObject(4).getString("WednesdayfifthSlot");
            textview65 = (TextView) findViewById(R.id.textView65);
            if (WednesdayfifthSlot != "null") {
                textview65.setText(WednesdayfifthSlot);
            } else {
                textview65.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview65.setTextSize(20);
                textview65.setText("Add Appointment");
            }


            Wednesdaygap5 = jsonArray.getJSONObject(4).getString("Wednesdaygap5");
            textview66 = (TextView) findViewById(R.id.textView66);
            if (Wednesdaygap5 != "null") {
                textview66.setText(Wednesdaygap5);
            } else {
                textview66.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview66.setTextSize(20);
                textview66.setText("Add Appointment");
            }



//Thursday

            ThursdayfirstSlot = jsonArray.getJSONObject(5).getString("ThursdayfirstSlot");
            textview68 = (TextView) findViewById(R.id.textView68);
            if (ThursdayfirstSlot != "null") {
                textview68.setText(ThursdayfirstSlot);
            } else {
                textview68.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview68.setTextSize(20);
                textview68.setText("Add Appointment");
            }

            Thursdaygap1 = jsonArray.getJSONObject(5).getString("Thursdaygap1");
            textview69 = (TextView) findViewById(R.id.textView69);
            if (Thursdaygap1 != "null") {
                textview69.setText(Thursdaygap1);
            } else {
                textview69.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview69.setTextSize(20);
                textview69.setText("Add Appointment");
            }

            ThursdaysecondSlot = jsonArray.getJSONObject(5).getString("ThursdaysecondSlot");
            textview70 = (TextView) findViewById(R.id.textView70);
            if (ThursdaysecondSlot != "null") {
                textview70.setText(ThursdaysecondSlot);
            } else {
                textview70.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview70.setTextSize(20);
                textview70.setText("Add Appointment");
            }


            Thursdaygap2 = jsonArray.getJSONObject(5).getString("Thursdaygap2");
            textview71 = (TextView) findViewById(R.id.textView71);
            if (Thursdaygap2 != "null") {
                textview71.setText(Thursdaygap2);
            } else {
                textview71.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview71.setTextSize(20);
                textview71.setText("Add Appointment");
            }

            ThursdaythirdSlot = jsonArray.getJSONObject(5).getString("ThursdaythirdSlot");
            textview72 = (TextView) findViewById(R.id.textView72);
            if (ThursdaythirdSlot != "null") {
                textview72.setText(ThursdaythirdSlot);
            } else {
                textview72.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview72.setTextSize(20);
                textview72.setText("Add Appointment");
            }


            Thursdaygap3 = jsonArray.getJSONObject(5).getString("Thursdaygap3");
            textview73 = (TextView) findViewById(R.id.textView73);
            if (Thursdaygap3 != "null") {
                textview73.setText(Thursdaygap3);
            } else {
                textview73.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview73.setTextSize(20);
                textview73.setText("Add Appointment");
            }
            ThursdayfourthSlot = jsonArray.getJSONObject(5).getString("ThursdayfourthSlot");
            textview74 = (TextView) findViewById(R.id.textView74);
            if (ThursdayfourthSlot != "null") {
                textview74.setText(ThursdayfourthSlot);
            } else {
                textview74.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview74.setTextSize(20);
                textview74.setText("Add Appointment");
            }



            Thursdaygap4 = jsonArray.getJSONObject(5).getString("Thursdaygap4");
            textview75 = (TextView) findViewById(R.id.textView75);
            if (Thursdaygap4 != "null") {
                textview75.setText(Thursdaygap4);
            } else {
                textview75.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview75.setTextSize(20);
                textview75.setText("Add Appointment");
            }

            ThursdayfifthSlot = jsonArray.getJSONObject(5).getString("ThursdayfifthSlot");
            textview76 = (TextView) findViewById(R.id.textView76);
            if (ThursdayfifthSlot != "null") {
                textview76.setText(ThursdayfifthSlot);
            } else {
                textview76.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview76.setTextSize(20);
                textview76.setText("Add Appointment");
            }


            Thursdaygap5 = jsonArray.getJSONObject(5).getString("Thursdaygap5");
            textview77 = (TextView) findViewById(R.id.textView77);
            if (Thursdaygap5 != "null") {
                textview77.setText(Thursdaygap5);
            } else {
                textview77.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview77.setTextSize(20);
                textview77.setText("Add Appointment");
            }




        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule_of_doctors, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class BackgroundTask extends AsyncTask<String, Void, String> {
        String saturdaySecondSlotAppointments;
        String saturdayGap1;
        JSONArray jsonArray;
//Satgap1
        String seventhFree;
        String eighthFree;
      //Satsot2
        String ninthFree;
        String tenthFree;
        String eleventhFree;
        String twelfthFree;
        String thirteenthFree;
        String fourteenthFree;


        String dateAppointment;

        @Override
        protected void onPreExecute() {
            //  alertDialog = new AlertDialog.Builder(ctx).create();
            // alertDialog.setTitle("Fetching Doctor's Schedule");
        }


        @Override
        protected String doInBackground(String... params) {
            String method = params[0];
            if (method.equals("setAppointmentSaturdaySecond")) {
                String doctor_ID = params[1];
                String datePassed = params[2];

                try {
                    String setAppointmentSaturdaySecond_URL = "http://192.168.43.88" +
                            "+ /faculty_scheduler/studentSetAppointmentSaturdaySecond.php";
                    URL url = new URL(setAppointmentSaturdaySecond_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("doctor_ID", "UTF-8") + "=" + URLEncoder.encode(doctor_ID, "UTF-8")+ "&" +
                            URLEncoder.encode("datePassed", "UTF-8") + "=" + URLEncoder.encode(datePassed, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }

                    Log.d("doctor id is", "Response is: " + response);


                    try {
                        saturdaySecondSlotAppointments = response;
                        JSONObject object = new JSONObject(saturdaySecondSlotAppointments);
                        jsonArray = object.getJSONArray("saturdaySecondSlotAppointments");

                        ninthFree = jsonArray.getJSONObject(0).getString("ninthFree");
                        tenthFree = jsonArray.getJSONObject(0).getString("tenthFree");
                        eleventhFree = jsonArray.getJSONObject(0).getString("eleventhFree");
                        twelfthFree = jsonArray.getJSONObject(0).getString("twelfthFree");
                        thirteenthFree = jsonArray.getJSONObject(0).getString("thirteenthFree");
                        fourteenthFree = jsonArray.getJSONObject(0).getString("fourteenthFree");
                        dateAppointment= jsonArray.getJSONObject(0).getString("dateAppointment");



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    bufferedReader.close();
                    IS.close();
                    httpURLConnection.disconnect();
                    return response;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (method.equals("setAppointmentSaturdaygap1")) {
                String doctor_ID = params[1];
                String datePassed = params[2];

                try {
                    String setAppointmentSaturdaySecond_URL = "http://192.168.43.88/faculty_scheduler/studentSetAppointmentSaturdaygap1.php";
                    URL url = new URL(setAppointmentSaturdaySecond_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("doctor_ID", "UTF-8") + "=" + URLEncoder.encode(doctor_ID, "UTF-8")+ "&" +
                            URLEncoder.encode("datePassed", "UTF-8") + "=" + URLEncoder.encode(datePassed, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream IS = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                    String response = "";
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }

                    Log.d("doctor id is", doctor_ID + datePassed);

                    Log.d("doctor id is", "Response is: " + response);


                    try {
                        saturdayGap1 = response;
                        JSONObject object = new JSONObject(saturdayGap1);
                        jsonArray = object.getJSONArray("saturdayGap1");

                        seventhFree = jsonArray.getJSONObject(0).getString("seventhFree");
                        eighthFree = jsonArray.getJSONObject(0).getString("eighthFree");


                        dateAppointment= jsonArray.getJSONObject(0).getString("dateAppointment");



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    bufferedReader.close();
                    IS.close();
                    httpURLConnection.disconnect();
                    return response;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        protected void onPostExecute(String result) {

            if (result.contains("ninthFree")) {
                saturdaySecondSlotAppointments = result;
                if (ninthFree.equals("0") && tenthFree.equals("0") && eleventhFree.equals("0") && twelfthFree.equals("0") && thirteenthFree.equals("0") && fourteenthFree.equals("0")) {
                    Toast.makeText(getApplicationContext(), "No free timings available. Choose another time.", Toast.LENGTH_LONG).show();


                } else {
                    Intent intent = new Intent(context, StudentSetAppointmentSaturdaySecond.class);
                    intent.putExtra("saturdaySecondSlotAppointments", saturdaySecondSlotAppointments);
                    startActivity(intent);

                }
            }

            else  if(result.contains("seventhFree")) {
                saturdayGap1 = result;
                if (seventhFree.equals("0") && eighthFree.equals("0")) {
                    Toast.makeText(getApplicationContext(), "No free timings available. Choose another time.", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent(context, StudentSetAppointmentSaturdayGapOne.class);
                    intent.putExtra("saturdayGap1", saturdayGap1);
                    startActivity(intent);
                }
            }


            else {
                Toast.makeText(getApplicationContext(), "Free slots in this date is not yet set by the doctor, please choose an earlier date.", Toast.LENGTH_LONG).show();

            }

        }

    }
}
