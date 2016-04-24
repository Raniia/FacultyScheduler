package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StudentSetAppointmentSaturday extends Activity {
    static final int DATE_DIALOG_ID = 0;
    Context context = this;
    Button button;
    JSONArray jsonArray;
    String fifteenMin;
    Button button2;
    String ninthFree;
    Button button3;
    String tenthFree;
    Button button4;
    String eleventhFree;
    Button button5;
    String twelfthFree;
    Button button6;
    String thirteenthFree;
    Button button7;
    String fourteenthFree;

    String student_ID = StudentsHomepage.storeStudentID;
    String doctor_ID;
    TextView textView79;
    TextView textView80;

String appointmentPurpose;
    EditText purpose;
    String timing;

    String slotNumber;
    private TextView displayDate;
    String dateApp;
    private Button pickDate;
    private int tyear;
    private int tmonth;
    private int tday;

    String dayOfWeek;
    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                    Date date = new Date(year, monthOfYear, dayOfMonth - 1);
                    dayOfWeek = simpledateformat.format(date);
                    Log.d("DAY OF WEEK IS", dayOfWeek);
                    if (dayOfWeek.equals("Saturday")) {
                        tyear = year;
                        tmonth = monthOfYear;
                        tday = dayOfMonth;
                        updateDisplay();
                        displayToast();
                    } else {
                        Toast.makeText(getApplicationContext(), "This day is not Saturday, Please choose another day.", Toast.LENGTH_LONG).show();

                    }
                }
            };

    private void updateDisplay() {
        //  if (dayOfWeek.equals("Saturday")) {

        displayDate.setText(
                new StringBuilder()
                        // Month is 0 based so add 1

                        .append(tyear).append("-")
                        .append(tmonth + 1).append("-")
                        .append(tday).append("")

        );
        dateApp = displayDate.getText().toString();
        // }
        // else {
        //   Toast.makeText(getApplicationContext(), "nooooot valid.", Toast.LENGTH_LONG).show();

        //   }
    }

    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(displayDate.getText()), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_set_appointment_saturday);

        purpose = (EditText) findViewById(R.id.purpose);
        appointmentPurpose = purpose.getText().toString();

        displayDate = (TextView) findViewById(R.id.displayDate);
        pickDate = (Button) findViewById(R.id.pickDate);

        pickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar cal = Calendar.getInstance();
        tyear = cal.get(Calendar.YEAR);
        tmonth = cal.get(Calendar.MONTH);
        tday = cal.get(Calendar.DAY_OF_MONTH);



        /** Display the current date in the TextView */
        updateDisplay();

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String checkDate = displayDate.getText().toString();
                String [] check = checkDate.split("-");
                String yyyy =check[0];
                String mm=check[1];
                String dd=check[2];
                String input = yyyy+"-"+mm+"-"+dd;
                SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = inFormat.parse(input);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                String goal = outFormat.format(date);

               /* SimpleDateFormat s = new SimpleDateFormat("EEEE");
                Date checkd = new Date(tyear, tmonth +1 , tday);

                String day = s.format(checkd);*/
                Log.d("bossssssssiii", input);
                Log.d("DATEEEEEEEEEEEEEEE IS", checkDate);
                Log.d("DAAAAAAAAAAAAAAAAAAAAAY", goal);
                appointmentPurpose = purpose.getText().toString();

                if(!(slotNumber.isEmpty())) {
                    if (goal.equals("Saturday")) {
                        if (!(appointmentPurpose.isEmpty())) {
                            String method = "setAppointmentSaturdaySecond";
                            BackgroundTask backgroundTask = new BackgroundTask();
                            backgroundTask.execute(method);




                            AlertDialog alertDialog = new AlertDialog.Builder(context).create();

                            // Setting Dialog Title
                            alertDialog.setTitle("Appointment Booked.");

                            // Setting Dialog Message
                            alertDialog.setMessage("Continue to your appointments...");

                            // Setting Icon to Dialog
                            alertDialog.setIcon(R.drawable.tick);


                            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // Write your code here to execute after dialog closed
                                    String method = "showStudentsAppointments";
                                    BackgroundTask backgroundTask = new BackgroundTask();
                                    backgroundTask.execute(method);
                                   // Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                                }
                            });

                            // Showing Alert Message
                            alertDialog.show();

















                        } else {
                            Toast.makeText(getApplicationContext(), "Please specify the purpose of this appointment.", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "This day isn't a Saturday.. Please choose another date.", Toast.LENGTH_LONG).show();

                    }
                   
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please choose a certain timing.", Toast.LENGTH_LONG).show();

                }
            }
        });
        fifteenMin = getIntent().getExtras().getString("saturdaySecondSlotAppointments");
        try {
            JSONObject object = new JSONObject(fifteenMin);
            jsonArray = object.getJSONArray("saturdaySecondSlotAppointments");

            doctor_ID = jsonArray.getJSONObject(0).getString("doctor_ID");

            button2 = (Button) findViewById(R.id.button2);
            ninthFree = jsonArray.getJSONObject(0).getString("ninthFree");
            if (ninthFree.equals("0")) {
                button2.setVisibility(View.VISIBLE);
                button2.setText("10:30 -> 10:45");
                button2.setPaintFlags(button2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                button2.setBackgroundColor(Color.rgb(204,204,255));

            } else {
                button2.setVisibility(View.VISIBLE);
                button2.setBackgroundColor(Color.rgb(102, 102, 255));
                button2.setText("10:30 -> 10:45");
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "An appointment at 10:30 -> 11:45. Please book to send the doctor a request. ", Toast.LENGTH_LONG).show();
                        textView79 = (TextView) findViewById(R.id.textView79);
                        textView80 = (TextView) findViewById(R.id.textView80);
                        textView80.setText(button2.getText());
                        timing = textView80.getText().toString();
                        textView79.setText("9");

                        slotNumber = textView79.getText().toString();

                    }
                });
            }


            button3 = (Button) findViewById(R.id.button3);
            tenthFree = jsonArray.getJSONObject(0).getString("tenthFree");
            if (tenthFree.equals("0")) {
                button3.setVisibility(View.VISIBLE);
                button3.setText("10:45 -> 11:00");
                button3.setPaintFlags(button2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                button3.setBackgroundColor(Color.rgb(204,204,255));

            } else {
                button3.setVisibility(View.VISIBLE);
                button3.setBackgroundColor(Color.rgb(102, 102, 255));
                button3.setText("10:45 -> 11:00");
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "An appointment at 10:45 -> 11:00. Please book to send the doctor a request. ", Toast.LENGTH_LONG).show();
                        textView79 = (TextView) findViewById(R.id.textView79);

                        textView80 = (TextView) findViewById(R.id.textView80);
                        textView80.setText(button3.getText());
                        timing = textView80.getText().toString();

                        textView79.setText("10");
                        slotNumber = textView79.getText().toString();
                    }
                });
            }

            button4 = (Button) findViewById(R.id.button4);
            eleventhFree = jsonArray.getJSONObject(0).getString("eleventhFree");
            if (eleventhFree.equals("0")) {
                button4.setText("11:00 -> 11:15");
                button4.setVisibility(View.VISIBLE);
                button4.setPaintFlags(button2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                button4.setBackgroundColor(Color.rgb(204,204,255));

            } else {
                button4.setVisibility(View.VISIBLE);
                button4.setBackgroundColor(Color.rgb(102, 102, 255));
                button4.setText("11:00 -> 11:15");
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "An appointment at 11:00 -> 11:15. Please book to send the doctor a request. ", Toast.LENGTH_LONG).show();
                        textView79 = (TextView) findViewById(R.id.textView79);

                        textView79.setText("11");
                        slotNumber = textView79.getText().toString();
                        textView80 = (TextView) findViewById(R.id.textView80);
                        textView80.setText(button4.getText());
                        timing = textView80.getText().toString();
                    }
                });
            }

            button5 = (Button) findViewById(R.id.button5);
            twelfthFree = jsonArray.getJSONObject(0).getString("twelfthFree");
            if (twelfthFree.equals("0")) {
                button5.setVisibility(View.VISIBLE);
                button5.setText("11:15 -> 11:30");
                button5.setPaintFlags(button2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                button5.setBackgroundColor(Color.rgb(204,204,255));

            } else {
                button5.setVisibility(View.VISIBLE);
                button5.setBackgroundColor(Color.rgb(102, 102, 255));
                button5.setText("11:15 -> 11:30");
                button5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "An appointment at 11:15 -> 11:30. Please book to send the doctor a request. ", Toast.LENGTH_LONG).show();
                        textView79 = (TextView) findViewById(R.id.textView79);
                        textView79.setText("12");
                        slotNumber = textView79.getText().toString();
                        textView80 = (TextView) findViewById(R.id.textView80);
                        textView80.setText(button5.getText());
                        timing = textView80.getText().toString();
                    }
                });
            }


            button6 = (Button) findViewById(R.id.button6);
            thirteenthFree = jsonArray.getJSONObject(0).getString("thirteenthFree");
            if (thirteenthFree.equals("0")) {
                button6.setVisibility(View.VISIBLE);
                button6.setText("11:30 -> 11:45");
                button6.setPaintFlags(button2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                button6.setBackgroundColor(Color.rgb(204,204,255));

            } else {
                button6.setVisibility(View.VISIBLE);
                button6.setBackgroundColor(Color.rgb(102, 102, 255));
                button6.setText("11:30 -> 11:45");
                button6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "An appointment at 11:30 -> 11:45. Please book to send the doctor a request. ", Toast.LENGTH_LONG).show();
                        textView79 = (TextView) findViewById(R.id.textView79);
                        textView79.setText("13");
                        slotNumber = textView79.getText().toString();
                        textView80 = (TextView) findViewById(R.id.textView80);
                        textView80.setText(button6.getText());
                        timing = textView80.getText().toString();
                    }
                });
            }


            button7 = (Button) findViewById(R.id.button7);
            fourteenthFree = jsonArray.getJSONObject(0).getString("fourteenthFree");
            if (fourteenthFree.equals("0")) {
                button7.setVisibility(View.VISIBLE);
                button7.setText("11:45 -> 12:00");
                button7.setPaintFlags(button2.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                button7.setBackgroundColor(Color.rgb(204,204,255));

            } else {
                button7.setVisibility(View.VISIBLE);
                button7.setBackgroundColor(Color.rgb(102, 102, 255));
                button7.setText("11:45 -> 12:00");
                button7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "An appointment at 11:45 -> 12:00. Please book to send the doctor a request. ", Toast.LENGTH_LONG).show();
                        textView79 = (TextView) findViewById(R.id.textView79);

                        textView79.setText("14");
                        slotNumber = textView79.getText().toString();
                        textView80 = (TextView) findViewById(R.id.textView80);
                        textView80.setText(button7.getText());
                        timing = textView80.getText().toString();
                    }
                });
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        tyear, tmonth, tday);
        }
        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_set_appointment, menu);
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


        @Override
        protected void onPreExecute() {
            //  alertDialog = new AlertDialog.Builder(ctx).create();
            // alertDialog.setTitle("Fetching Doctor's Schedule");
        }

        protected String doInBackground(String... params) {
            String method = params[0];
            if (method.equals("setAppointmentSaturdaySecond")) {
                try {

                    String setAppointment_URL = "http://192.168.1.9/faculty_scheduler/setAppointment.php";
                    URL url = new URL(setAppointment_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("slotNumber", "UTF-8") + "=" + URLEncoder.encode(slotNumber, "UTF-8") + "&" +
                            URLEncoder.encode("doctor_ID", "UTF-8") + "=" + URLEncoder.encode(doctor_ID, "UTF-8") + "&" +
                            URLEncoder.encode("student_ID", "UTF-8") + "=" + URLEncoder.encode(student_ID, "UTF-8") + "&" +
                            URLEncoder.encode("timing", "UTF-8") + "=" + URLEncoder.encode(timing, "UTF-8") + "&" +
                            URLEncoder.encode("appointmentPurpose", "UTF-8") + "=" + URLEncoder.encode(appointmentPurpose, "UTF-8") + "&" +
                            URLEncoder.encode("dateApp", "UTF-8") + "=" + URLEncoder.encode(dateApp, "UTF-8");
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

                    Log.d("THE", "STUDENT ID is: " + response);


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

            else if (method.equals("showStudentsAppointments")){
                try{
                String  viewMyAppointments_URL = "http://192.168.1.3/faculty_scheduler/getStudentsAppointments.php";
                URL url = new URL(viewMyAppointments_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("student_ID", "UTF-8") + "=" + URLEncoder.encode(student_ID, "UTF-8");
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

                Log.d("THE", "STUDENT ID is: " + response);


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
            if (result.contains("timing")) {
                String myAppointments = result;
                Intent intent = new Intent(context, StudentsAppointments.class);
                intent.putExtra("studentsAppointments", myAppointments);
                startActivity(intent);


            }
        }

    }


}

