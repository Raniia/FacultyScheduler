package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

public class DoctorsAppointments extends Activity {
    String json_stringAllAppointmentsofDoctor;
    String doctorsPendingAppointments;
    JSONObject object;
    JSONArray jsonArray;
    AppointmentsAdapter appointmentsAdapter;
    ListView appointments;
    Context context = this;

String doctor_ID = DoctorsHomepage.storeDoctorID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_appointments);

        appointments = (ListView) findViewById(R.id.listView);

        appointmentsAdapter = new AppointmentsAdapter(this, R.layout.doctors_appointment_layout);
        appointments.setAdapter(appointmentsAdapter);
        json_stringAllAppointmentsofDoctor = getIntent().getExtras().getString("doctorsAppointments");


        try {
            object = new JSONObject(json_stringAllAppointmentsofDoctor);
            jsonArray = object.getJSONArray("doctorsAppointments");



            int count = 0;
            String student_id;
            String timing;
            String date;
            String description;

            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                student_id = JO.getString("student_ID");
                timing = JO.getString("timing");
                date = JO.getString("dateAppointment");
                description = JO.getString("description");
              //  doctor_ID = JO.getString("doctor_ID");
                Appointments appointments = new Appointments(student_id,date,timing, description);
                appointmentsAdapter.add(appointments);
                count++;

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctors_appointments, menu);
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




public void viewPendingAppointments(View view) {
      BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute();
    }










    public class BackgroundTask extends AsyncTask<String, Void, String> {
        Context ctx;


        BackgroundTask(Context ctx) {
            this.ctx = ctx;

        }


        String viewMyPendingAppointments_URL;

        @Override
        protected void onPreExecute() {
            viewMyPendingAppointments_URL = "http://192.168.1.9/faculty_scheduler/getDoctorsPendingAppointments.php";
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(viewMyPendingAppointments_URL);
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

                Log.d("THE", "RESPONSEEE is: " + response);


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

        @Override
        protected void onPostExecute(String result) {
            doctorsPendingAppointments = result;


            if (!doctorsPendingAppointments.contains("timing")) {
                Toast.makeText(getApplicationContext(), "No pending Appointments", Toast.LENGTH_LONG).show();

            } else {
                Intent intent = new Intent(context, DoctorsAppointmentsPending.class);
                intent.putExtra("doctorsAppointmentsPending", doctorsPendingAppointments);
                startActivity(intent);
            }

        }
    }























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
                row = layoutInflater.inflate(R.layout.doctors_appointment_layout, parent, false);
                appointmentsHolder = new AppointmentsHolder();
                appointmentsHolder.student_id = (TextView) row.findViewById(R.id.studentID);
                appointmentsHolder.timing = (TextView) row.findViewById(R.id.timeApp);
                appointmentsHolder.date = (TextView) row.findViewById(R.id.dateofApp);
                appointmentsHolder.description= (TextView) row.findViewById(R.id.description);
                row.setTag(appointmentsHolder);

            } else {
                appointmentsHolder = (AppointmentsHolder) row.getTag();
            }
            Appointments appointments = (Appointments) this.getItem(position);
            appointmentsHolder.student_id.setText(appointments.getStudent_ID());
            appointmentsHolder.timing.setText(appointments.getTiming());
            appointmentsHolder.date.setText(appointments.getDateAppointment());
            appointmentsHolder.description.setText(appointments.getDescription());
            return row;
        }


        public class AppointmentsHolder {
            TextView student_id;
            TextView timing;
            TextView date;
            TextView description;
        }






    }
}
