package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.content.Context;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
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

public class DoctorsAppointmentsPending extends Activity {
    String json_stringpendingAppointmentsofDoctor;
    JSONObject object;
    JSONArray jsonArray;
    ListView appointmentsPendinglistview;
    Context context = this;
    AppointmentsPendingAdapter appointmentsAdapter;





    String doctor_ID = DoctorsHomepage.storeDoctorID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_appointments_pending);
        appointmentsPendinglistview = (ListView) findViewById(R.id.listView);

        appointmentsAdapter = new AppointmentsPendingAdapter(this, R.layout.doctors_pending_appointment_layout);
        appointmentsPendinglistview.setAdapter(appointmentsAdapter);
        json_stringpendingAppointmentsofDoctor = getIntent().getExtras().getString("doctorsAppointmentsPending");


        try {
            object = new JSONObject(json_stringpendingAppointmentsofDoctor);
            jsonArray = object.getJSONArray("doctorsPendingAppointments");



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





    public class AppointmentsPendingAdapter extends ArrayAdapter {
        List list = new ArrayList();


        String studentIDString;
        String dateString;
        String timingString;
        String doctorIDString;



        public AppointmentsPendingAdapter(Context context, int resource) {
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
            final AppointmentsPendingHolder appointmentsHolder;

            if (row == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = layoutInflater.inflate(R.layout.doctors_pending_appointment_layout, parent, false);
                appointmentsHolder = new AppointmentsPendingHolder();
                appointmentsHolder.student_id = (TextView) row.findViewById(R.id.studentID);
                appointmentsHolder.timing = (TextView) row.findViewById(R.id.timeApp);
                appointmentsHolder.date = (TextView) row.findViewById(R.id.dateofApp);
                appointmentsHolder.description= (TextView) row.findViewById(R.id.description);
                appointmentsHolder.approve= (Button) row.findViewById(R.id.approve);
                appointmentsHolder.disapprove = (Button) row.findViewById(R.id.disapprove);


                appointmentsHolder.approve.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      RelativeLayout t1 = (RelativeLayout) v.getParent();
                      // TableRow t1 = (TableRow) v.getParent();


                        TextView st = (TextView) t1.findViewById(R.id.studentID);
                        TextView ti =  (TextView) t1.findViewById(R.id.timeApp);
                        TextView dat= (TextView) t1.findViewById(R.id.dateofApp);


                        studentIDString = st.getText().toString();
                        timingString = ti.getText().toString();
                        dateString = dat.getText().toString();


                        String method = "approvePending";
                        BackgroundTask backgroundTask = new BackgroundTask(context);
                        backgroundTask.execute(method, studentIDString, timingString, dateString, doctor_ID);

                        list.remove(getItem(position));
                        AppointmentsPendingAdapter.this.notifyDataSetChanged();

                    //Appointments tobeRemoved = (Appointments)appointmentsAdapter.getItem(position);
                        //appointmentsAdapter.remove(tobeRemoved);
                       Toast.makeText(getContext(), studentIDString + " " + timingString + " " + dateString + " APPROVED", Toast.LENGTH_SHORT).show();


                    }
                });
                row.setTag(appointmentsHolder);

            } else {
                appointmentsHolder = (AppointmentsPendingHolder) row.getTag();
            }
            Appointments appointments = (Appointments) this.getItem(position);
            appointmentsHolder.student_id.setText(appointments.getStudent_ID());
            appointmentsHolder.timing.setText(appointments.getTiming());
            appointmentsHolder.date.setText(appointments.getDateAppointment());
            appointmentsHolder.description.setText(appointments.getDescription());
            return row;
        }


        public class AppointmentsPendingHolder {
            TextView student_id;
            TextView timing;
            TextView date;
            TextView description;
            Button approve;
            Button disapprove;


        }






    }































    class BackgroundTask extends AsyncTask<String, Void, String> {
        Context ctx;

        BackgroundTask(Context ctx) {
            this.ctx = ctx;

        }


        String approvePending_URL;
        String disapprovePending_URL;

        protected void onPreExecute() {
            approvePending_URL = "http://192.168.1.3/faculty_scheduler/doctorApprovedAppointment.php";
            disapprovePending_URL = "http://192.168.1.3/faculty_scheduler/doctorDisapprovedPending.php";
        }

        @Override
        protected String doInBackground(String... params) {
            String method = params[0];
            if (method.equals("approvePending")) {


                String studentIDString =params[1];
                String timingString = params[2];
                String dateString = params[3];
                String doctorIDString = params[4];

                try {
                    URL url = new URL(approvePending_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);



                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("studentIDString", "UTF-8") + "=" + URLEncoder.encode(studentIDString, "UTF-8") + "&" +
                            URLEncoder.encode("timingString", "UTF-8") + "=" + URLEncoder.encode(timingString, "UTF-8") + "&" +
                            URLEncoder.encode("dateString", "UTF-8") + "=" + URLEncoder.encode(dateString, "UTF-8")+ "&" +
                            URLEncoder.encode("doctorIDString", "UTF-8") + "=" + URLEncoder.encode(doctorIDString, "UTF-8");
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
            } else if (method.equals("disapprovePending")) {


                String studentIDString =params[1];
                String timingString = params[2];
                String dateString = params[3];
                String doctorIDString = params[4];

                try {
                    URL url = new URL(disapprovePending_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);



                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("studentIDString", "UTF-8") + "=" + URLEncoder.encode(studentIDString, "UTF-8") + "&" +
                            URLEncoder.encode("timingString", "UTF-8") + "=" + URLEncoder.encode(timingString, "UTF-8") + "&" +
                            URLEncoder.encode("dateString", "UTF-8") + "=" + URLEncoder.encode(dateString, "UTF-8")+ "&" +
                            URLEncoder.encode("doctorIDString", "UTF-8") + "=" + URLEncoder.encode(doctorIDString, "UTF-8");
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
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }


        protected void onPostExecute(String result) {



            }


        }

    }






