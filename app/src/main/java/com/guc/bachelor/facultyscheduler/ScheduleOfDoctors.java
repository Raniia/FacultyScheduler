package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

public class ScheduleOfDoctors extends Activity {

    Context context = this;

    String json_string;
    JSONArray jsonArray;

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


    String doctor_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_of_doctors);
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
                        BackgroundTask backgroundTask = new BackgroundTask();
                        backgroundTask.execute();
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
        JSONArray jsonArray;
        String ninthFree;
        String tenthFree;
        String eleventhFree;
        String twelfthFree;
        String thirteenthFree;
        String fourteenthFree;

        @Override
        protected void onPreExecute() {
            //  alertDialog = new AlertDialog.Builder(ctx).create();
            // alertDialog.setTitle("Fetching Doctor's Schedule");
        }


        @Override
        protected String doInBackground(String... params) {

            try {
                String setAppointmentSaturdaySecond_URL = "http://192.168.1.4/faculty_scheduler/studentSetAppointmentSaturdaySecond.php";
                URL url = new URL(setAppointmentSaturdaySecond_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("doctor_ID", "UTF-8") + "=" + URLEncoder.encode(doctor_ID, "UTF-8");
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

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        protected void onPostExecute(String result) {

            if (ninthFree.equals("0") && tenthFree.equals("0") && eleventhFree.equals("0") && twelfthFree.equals("0") && thirteenthFree.equals("0") && fourteenthFree.equals("0")) {
                Toast.makeText(getApplicationContext(), "No free timings available. Choose another time.", Toast.LENGTH_LONG).show();


            }
            else {
                Intent intent = new Intent(context, StudentSetAppointmentSaturday.class);
                intent.putExtra("saturdaySecondSlotAppointments", saturdaySecondSlotAppointments);
                startActivity(intent);

            }

        }

    }
}
