package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DoctorsAppointments extends Activity {
    String json_stringAllAppointmentsofDoctor;
    JSONObject object;
    JSONArray jsonArray;
    ListView doctorsListView;
    Context context = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_appointments);

        doctorsListView = (ListView) findViewById(R.id.listView);


        json_stringAllAppointmentsofDoctor = getIntent().getExtras().getString("doctorsAppointments");
    try {
    object = new JSONObject(json_stringAllAppointmentsofDoctor);
        jsonArray= object.getJSONArray("doctorsAppointments");


        int count = 0;
        String doctor_name;

        while (count < jsonArray.length()) {
            JSONObject JO = jsonArray.getJSONObject(count);


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
}
