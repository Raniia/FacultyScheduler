package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

public class StudentsHomepage extends Activity {
    String json_string;
    String allDoctors;
    TextView studentID;
    TextView studentName;
    TextView studentEmail;
    Context context = this;
     Button myAppointments;

    ImageView avatar;
    String student_picture;

    JSONArray jsonArray;
   static String storeStudentID;

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
            Log.d("STORE STUDENT ID", storeStudentID);

            String student_name = jsonArray.getJSONObject(0).getString("student_name");
            studentName = (TextView) findViewById(R.id.student_name);
            studentName.setText(student_name);


           String student_group = jsonArray.getJSONObject(0).getString("student_group");



            String student_email = jsonArray.getJSONObject(0).getString("student_email");
            studentEmail = (TextView) findViewById(R.id.student_email);
            studentEmail.setText("Student Email: " + student_email);


            student_picture = jsonArray.getJSONObject(0).getString("student_picture");
            avatar = (ImageView) findViewById(R.id.avatar);


            new DownloadImageTask(avatar).execute();



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
        String studentImage;

        @Override
        protected void onPreExecute() {
            viewDoctorsURL = "http://192.168.1.4/faculty_scheduler/getDoctors.php";
            passStudentSessionURL = "http://192.168.1.4/faculty_scheduler/setAppointment.php";
            studentImage = "http://192.168.1.4/faculty_scheduler/images/" + student_picture ;
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
                OutputStream OS = conn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("student_ID", "UTF-8") + "=" + URLEncoder.encode(storeStudentID, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = conn.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
// read the responseedReader = new BufferedReader(new InputStreamReader(in, "iso-8859-1"));
                //  String response = org.
                //   System.out.println("Response Code: " + conn.getResponseCode());
                //  InputStream in = new BufferedInputStream(conn.getInputStream());
                //    BufferedReader bufferapache.commons.io.IOUtils.toString(in, "UTF-8");
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
































    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay ="http://192.168.1.4/faculty_scheduler/images/" + student_picture ;

            Bitmap mIcon = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}
