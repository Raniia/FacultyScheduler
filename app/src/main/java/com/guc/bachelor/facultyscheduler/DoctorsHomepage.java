package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class DoctorsHomepage extends Activity {
    String scheduleOfDoctor;
    String json_string;
    TextView doctorID;
    TextView doctorName;
    TextView doctorEmail;
    TextView doctorOffice;
    TextView doctorDepartment;
    Context context = this;
    Button myAppointments;
    Button mySchedule;

    String doctor_ID;
    String doctor_name;
    String doctor_email;
    String doctor_department;
    String doctor_office;


    ImageView avatar;
    String doctor_picture;

    JSONArray jsonArray;
    static String storeDoctorID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_homepage);


        json_string = getIntent().getExtras().getString("doctor_login");
        try {
            JSONObject object = new JSONObject(json_string);

            jsonArray = object.getJSONArray("doctor_login_details");


            doctor_ID= jsonArray.getJSONObject(0).getString("doctor_ID");
            doctorID = (TextView) findViewById(R.id.doctor_ID);
            doctorID.setText(doctor_ID);
            storeDoctorID = doctor_ID;
            Log.d("STORE DOCTOR ID", storeDoctorID);

            doctor_name = jsonArray.getJSONObject(0).getString("doctor_name");
            doctorName = (TextView) findViewById(R.id.doctor_name);
            doctorName.setText(doctor_name);


            doctor_email = jsonArray.getJSONObject(0).getString("doctor_email");
            doctorEmail = (TextView) findViewById(R.id.doctor_email);
            doctorEmail.setText(doctor_email);

            doctor_department= jsonArray.getJSONObject(0).getString("doctor_department");
            doctorDepartment = (TextView) findViewById(R.id.doctor_department);
            doctorDepartment.setText(doctor_department);


            doctor_office = jsonArray.getJSONObject(0).getString("doctor_office");
            doctorOffice = (TextView) findViewById(R.id.doctor_office);
            doctorOffice.setText(doctor_office);


            doctor_picture = jsonArray.getJSONObject(0).getString("doctor_picture");
            avatar = (ImageView) findViewById(R.id.avatar);


            new DownloadImageTask(avatar).execute();



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void doctorViewSchedule(View view) {
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctors_homepage, menu);
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




    class BackgroundTask extends AsyncTask<Void, Void, String> {
        Context ctx;

        BackgroundTask(Context ctx) {
            this.ctx = ctx;

        }


        String viewMySchedule_URL;


        protected void onPreExecute() {
            viewMySchedule_URL = "http://192.168.1.2/faculty_scheduler/doctorViewMySchedule.php";

        }


        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(viewMySchedule_URL);
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

                Log.d("THE", "DOCTOR ID is: " + response);


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

            scheduleOfDoctor = result;


                Intent intent = new Intent(context, doctorsPersonalSchedule.class);
            intent.putExtra("scheduleOfDoctor", scheduleOfDoctor);

            startActivity(intent);



        }

    }

































    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay ="http://192.168.1.2/faculty_scheduler/doctorAvatars/" + doctor_picture ;

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
