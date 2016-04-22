package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudentsAppointments extends Activity {


    String json_stringAllAppointmentsofStudent;
    JSONObject object;
    JSONArray jsonArray;
    AppointmentsAdapter appointmentsAdapter;
    ListView appointments;
    Context context = this;

    String student_ID = StudentsHomepage.storeStudentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_appointments);

        appointments = (ListView) findViewById(R.id.listView);

        appointmentsAdapter = new AppointmentsAdapter(this, R.layout.doctors_appointment_layout);
        appointments.setAdapter(appointmentsAdapter);
        json_stringAllAppointmentsofStudent = getIntent().getExtras().getString("studentsAppointments");


        try {
            object = new JSONObject(json_stringAllAppointmentsofStudent);
            jsonArray = object.getJSONArray("studentsAppointments");



            int count = 0;
            String doctor_id;
            String timing;
            String date;
            String description;

            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                doctor_id = JO.getString("doctor_ID");
                timing = JO.getString("timing");
                date = JO.getString("dateAppointment");
                description = JO.getString("description");
                //  doctor_ID = JO.getString("doctor_ID");
                Appointments appointments = new Appointments(doctor_id,date,timing, description);
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
        getMenuInflater().inflate(R.menu.menu_students_appointments, menu);
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
                row = layoutInflater.inflate(R.layout.students_appointment_layout, parent, false);
                appointmentsHolder = new AppointmentsHolder();
                appointmentsHolder.doctor_id = (TextView) row.findViewById(R.id.doctorID);
                appointmentsHolder.timing = (TextView) row.findViewById(R.id.timeApp);
                appointmentsHolder.date = (TextView) row.findViewById(R.id.dateofApp);
                appointmentsHolder.description= (TextView) row.findViewById(R.id.description);
                row.setTag(appointmentsHolder);

            } else {
                appointmentsHolder = (AppointmentsHolder) row.getTag();
            }
            Appointments appointments = (Appointments) this.getItem(position);
            appointmentsHolder.doctor_id.setText(appointments.getStudent_ID());
            appointmentsHolder.timing.setText(appointments.getTiming());
            appointmentsHolder.date.setText(appointments.getDateAppointment());
            appointmentsHolder.description.setText(appointments.getDescription());
            return row;
        }


        public class AppointmentsHolder {
            TextView doctor_id;
            TextView timing;
            TextView date;
            TextView description;
        }






    }




























}
