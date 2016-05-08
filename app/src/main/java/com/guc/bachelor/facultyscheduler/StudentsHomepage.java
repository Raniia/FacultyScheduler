package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.List;

public class StudentsHomepage extends Activity {

/*
    String doctorIDDisapproved;
    String timingDisApproved;
    String dateDisapproved;
    AppointmentsAdapter appointmentsAdapter;*/



    String json_string;
    String allDoctors;
    TextView studentID;
    TextView studentName;
    TextView studentEmail;
    TextView studentMajor;
   TextView studentFaculty;
    Context context = this;
     String myAppointments;

    ImageView avatar;
    String student_picture;
    String student_ID;
    JSONArray jsonArray;
   static String storeStudentID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_homepage);


/*


         appointmentsAdapter = new AppointmentsAdapter(this,R.layout.disapproved_appointments);
        new AlertDialog.Builder(StudentsHomepage.this)
                .setAdapter(appointmentsAdapter, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO - Code when list item is clicked (int which - is param that gives you the index of clicked item)
                    }
                })
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setTitle("Disapproved Appointments")
                .setCancelable(false)
                .show();

*/


        json_string = getIntent().getExtras().getString("student_login");
        try {
            JSONObject object = new JSONObject(json_string);

            jsonArray = object.getJSONArray("student_login_details");

            //String student_group = jsonArray.getJSONObject(0).getString("student_group");

          student_ID = jsonArray.getJSONObject(0).getString("student_ID");
            studentID = (TextView) findViewById(R.id.student_ID);
            //studentID.setText( student_group + student_ID );
            studentID.setText(student_ID );

            storeStudentID = student_ID;
            Log.d("STORE STUDENT ID", storeStudentID);

            String student_name = jsonArray.getJSONObject(0).getString("student_name");
            studentName = (TextView) findViewById(R.id.student_name);
            studentName.setText(student_name);


            String student_email = jsonArray.getJSONObject(0).getString("student_email");
            studentEmail = (TextView) findViewById(R.id.student_email);
            studentEmail.setText( student_email);

            String student_major = jsonArray.getJSONObject(0).getString("student_major");
            studentMajor = (TextView) findViewById(R.id.student_major);
            studentMajor.setText(student_major);


            String student_faculty = jsonArray.getJSONObject(0).getString("student_faculty");
            studentFaculty = (TextView) findViewById(R.id.student_faculty);
            studentFaculty.setText(student_faculty);


            student_picture = jsonArray.getJSONObject(0).getString("student_picture");
            avatar = (ImageView) findViewById(R.id.avatar);


            new DownloadImageTask(avatar).execute();



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void viewDoctors(View view) {

        String method = "viewDoctors";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method);
    }


    public void studentsAppointments(View view) {
        String method = "studentsAppointments";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, student_ID);
    }





/*

    public class AppointmentsAdapter extends ArrayAdapter {
        List list = new ArrayList();


        public AppointmentsAdapter(Context context, int resource) {
            super(context, resource);
        }


        public void add(Appointments object) {
            super.add(object);
            list.add(object);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View row;
            row = convertView;
            AppointmentsHolder appointmentsHolder;

            if (row == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = layoutInflater.inflate(R.layout.disapproved_appointments, parent, false);
                appointmentsHolder = new AppointmentsHolder();
                row.setTag(appointmentsHolder);

            } else {
                appointmentsHolder = (AppointmentsHolder) row.getTag();
            }
            Appointments appointments = (Appointments) this.getItem(position);
            appointmentsHolder.message.setText("Doctor" + appointments.getDoctor_ID() + "has disapproved your appointment at" + appointments.getTiming() + "on" + appointments.getDateAppointment());

            return row;
        }


        public class AppointmentsHolder {
            TextView message;

        }


    }
*/



    class BackgroundTask extends AsyncTask<String, Void, String> {
        Context ctx;

        BackgroundTask(Context ctx) {
            this.ctx = ctx;

        }

        String viewDoctorsURL;
        String passStudentSessionURL;
        String studentImage;
        String viewMyAppointments_URL;
       // String getStudentsDisapprovedAppointments_URL;

        @Override
        protected void onPreExecute() {
            viewDoctorsURL = "http://192.168.43.88/faculty_scheduler/getDoctors.php";
            viewMyAppointments_URL = "http://192.168.43.88/faculty_scheduler/getStudentsAppointments.php";
            studentImage = "http://192.168.43.88/faculty_scheduler/images/" + student_picture;
           // getStudentsDisapprovedAppointments_URL = "http://192.168.1.3/faculty_scheduler/disApprovedAppointments.php";


        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {


            String method = params[0];
            if (method.equals("viewDoctors")) {
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
            } else if (method.equals("studentsAppointments")) {
                String student_ID = params[1];

                try {
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
        protected void onPostExecute(String result) {
          /*  if (result.contains("disapproved")) {
                int count = 0;
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray jsonArray = object.getJSONArray("disapprovedAppointments");


                    while (count < jsonArray.length()) {
                        JSONObject JO = jsonArray.getJSONObject(count);
                        doctorIDDisapproved = JO.getString("doctor_ID");
                        timingDisApproved = JO.getString("timing");
                        dateDisapproved = JO.getString("dateAppointment");
                        //  doctor_ID = JO.getString("doctor_ID");
                        Appointments appointments = new Appointments(doctorIDDisapproved, dateDisapproved, timingDisApproved);
                        appointmentsAdapter.add(appointments);
                        count++;

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

*/
            if (result.contains("doctor_name")) {
                allDoctors = result;
                Intent intent = new Intent(ctx, ViewListDoctors.class);
                intent.putExtra("viewDoctors", allDoctors);
                startActivity(intent);
            } else if (result.contains("timing")) {

                myAppointments = result;


                if (!myAppointments.contains("doctor_ID")) {
                    Toast.makeText(getApplicationContext(), "You have no appointments.", Toast.LENGTH_LONG).show();

                } else {

                    Intent intent = new Intent(context, StudentsAppointments.class);
                    intent.putExtra("studentsAppointments", myAppointments);
                    startActivity(intent);
                }
            }
        }























































    }






    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay ="http://192.168.43.88/faculty_scheduler/studentAvatars/" + student_picture ;

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
