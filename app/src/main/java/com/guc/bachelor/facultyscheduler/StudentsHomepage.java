package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class StudentsHomepage extends Activity {
    String json_string;
    String allDoctors;
    TextView studentID;
    TextView studentName;
    TextView studentEmail;
    Context context = this;
     Button myAppointments;
    JSONArray jsonArray;
    String storeStudentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_homepage);
       myAppointments = (Button) findViewById(R.id.myAppointments);
      myAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StudentSetAppointmentSaturday.class);
                startActivity(intent);
            }
        });


        json_string = getIntent().getExtras().getString("student_login");
        try {
            JSONObject object = new JSONObject(json_string);

            jsonArray = object.getJSONArray("student_login_details");

            String student_ID = jsonArray.getJSONObject(0).getString("student_ID");
            studentID = (TextView) findViewById(R.id.student_ID);
            studentID.setText("Student ID: " + student_ID);
            storeStudentID = student_ID;

            String student_name = jsonArray.getJSONObject(0).getString("student_name");
            studentName = (TextView) findViewById(R.id.student_name);
            studentName.setText("Student Name: " + student_name);

            String student_email = jsonArray.getJSONObject(0).getString("student_email");
            studentEmail = (TextView) findViewById(R.id.student_email);
            studentEmail.setText("Student Email: " + student_email);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void viewDoctors(View view) {
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute();
    }


    class BackgroundTask extends AsyncTask<Void, Void, String> {
        Context ctx;

        BackgroundTask(Context ctx) {
            this.ctx = ctx;

        }

        String viewDoctorsURL;
        String passStudentSessionURL;

        @Override
        protected void onPreExecute() {
            viewDoctorsURL = "http://192.168.1.4/faculty_scheduler/getDoctors.php";
            passStudentSessionURL = "http://192.168.1.4/faculty_scheduler/setAppointment.php?studentID=" + storeStudentID;
            Log.i("The URL is", passStudentSessionURL);

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(viewDoctorsURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                while ((allDoctors = bufferedReader.readLine()) != null) {

                    stringBuilder.append(allDoctors + "\n");

                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                String response = stringBuilder.toString().trim();

                return response; //response is the list of doctors

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                URL url = new URL(passStudentSessionURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
// read the response
                //   System.out.println("Response Code: " + conn.getResponseCode());
                //  InputStream in = new BufferedInputStream(conn.getInputStream());
                //    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
                //  String response = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
                //System.out.println(response);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(String result) {

            allDoctors = result;
            Intent intent = new Intent(ctx, ViewListDoctors.class);
            intent.putExtra("viewDoctors", allDoctors);
            startActivity(intent);

        }
    }


}
