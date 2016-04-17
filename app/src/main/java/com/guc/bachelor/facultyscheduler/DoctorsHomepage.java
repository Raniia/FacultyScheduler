package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class DoctorsHomepage extends Activity {
    String json_string;
    TextView doctorID;
    TextView doctorName;
    TextView doctorEmail;
    TextView doctorOffice;
    TextView doctorDepartment;
    Context context = this;
    Button myAppointments;
    Button mySchedule;

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


            String doctor_ID = jsonArray.getJSONObject(0).getString("doctor_ID");
            doctorID = (TextView) findViewById(R.id.doctor_ID);
            doctorID.setText(doctor_ID);
            storeDoctorID = doctor_ID;
            Log.d("STORE DOCTOR ID", storeDoctorID);

            String doctor_name = jsonArray.getJSONObject(0).getString("doctor_name");
            doctorName = (TextView) findViewById(R.id.doctor_name);
            doctorName.setText(doctor_name);


            String doctor_email = jsonArray.getJSONObject(0).getString("doctor_email");
            doctorEmail = (TextView) findViewById(R.id.doctor_email);
            doctorEmail.setText(doctor_email);

            String doctor_department = jsonArray.getJSONObject(0).getString("doctor_department");
            doctorDepartment = (TextView) findViewById(R.id.doctor_department);
            doctorDepartment.setText(doctor_department);


            String doctor_office = jsonArray.getJSONObject(0).getString("doctor_office");
            doctorOffice = (TextView) findViewById(R.id.doctor_office);
            doctorOffice.setText(doctor_office);


            doctor_picture = jsonArray.getJSONObject(0).getString("doctor_picture");
            avatar = (ImageView) findViewById(R.id.avatar);


            new DownloadImageTask(avatar).execute();



        } catch (JSONException e) {
            e.printStackTrace();
        }


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























    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay ="http://192.168.1.4/faculty_scheduler/doctorAvatars/" + doctor_picture ;

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
