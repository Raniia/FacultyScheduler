package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ScheduleOfDoctors extends Activity {
    String json_string;
    JSONArray jsonArray;
    int doctor_ID;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_of_doctors);
        json_string =getIntent().getExtras().getString("doctor_schedule");
        try {
            JSONObject object = new JSONObject(json_string);

            jsonArray = object.getJSONArray("doctor_schedule");

            SaturdayfirstSlot = jsonArray.getJSONObject(0).getString("SaturdayfirstSlot");
            textview13 = (TextView) findViewById(R.id.textView13);
            if (SaturdayfirstSlot != "null") {

                textview13.setText(SaturdayfirstSlot);
            }
            else {
                textview13.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview13.setTextSize(20);
                textview13.setText("Add Appointment");
            }

            Saturdaygap1 = jsonArray.getJSONObject(0).getString("Saturdaygap1");
            textview14 = (TextView) findViewById(R.id.textView14);
            if (Saturdaygap1 != "null") {
                textview14.setText(Saturdaygap1);
            }
            else {
                textview14.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview14.setTextSize(20);
                textview14.setText("Add Appointment");
            }


            SaturdaysecondSlot = jsonArray.getJSONObject(0).getString("SaturdaysecondSlot");
            textview15 = (TextView) findViewById(R.id.textView15);
            if (SaturdaysecondSlot != "null") {
                textview15.setText(SaturdaysecondSlot);
            }
            else {
                textview15.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview15.setTextSize(20);
                textview15.setText("Add Appointment");
            }
            Saturdaygap2 = jsonArray.getJSONObject(0).getString("Saturdaygap2");
            textview16 = (TextView) findViewById(R.id.textView16);
            if(Saturdaygap2 != "null") {
                textview16.setText(Saturdaygap2);
            }
            else {
                textview16.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview16.setTextSize(20);
                textview16.setText("Add Appointment");
            }
            SaturdaythirdSlot = jsonArray.getJSONObject(0).getString("SaturdaythirdSlot");
            textview17 = (TextView) findViewById(R.id.textView17);
            if(SaturdaythirdSlot != "null") {
                textview17.setText(SaturdaythirdSlot);
            }
            else {
                textview17.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview17.setTextSize(20);
                textview17.setText("Add Appointment");
            }

            Saturdaygap3 = jsonArray.getJSONObject(0).getString("Saturdaygap3");
            textview18 = (TextView) findViewById(R.id.textView18);
            if (Saturdaygap3 != "null") {
                textview18.setText(Saturdaygap3);
            }
            else {
                textview18.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview18.setTextSize(20);
                textview18.setText("Add Appointment");
            }

            SaturdayfourthSlot = jsonArray.getJSONObject(0).getString("SaturdayfourthSlot");
            textview19 = (TextView) findViewById(R.id.textView19);
            if (SaturdayfourthSlot != "null") {
                textview19.setText(SaturdayfourthSlot);
            }
            else {
                textview19.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview19.setTextSize(20);
                textview19.setText("Add Appointment");
            }
            Saturdaygap4 = jsonArray.getJSONObject(0).getString("Saturdaygap4");
            textview20 = (TextView) findViewById(R.id.textView20);
            if (Saturdaygap4 !="null") {
                textview20.setText(Saturdaygap4);
            }
            else {
                textview20.setBackground(getResources().getDrawable(R.drawable.free_slot));
                textview20.setTextSize(20);
                textview20.setText("Add Appointment");
            }

            SaturdayfifthSlot = jsonArray.getJSONObject(0).getString("SaturdayfifthSlot");
            textview21 = (TextView) findViewById(R.id.textView21);
            if (SaturdayfifthSlot !="null") {
                textview21.setText(SaturdayfifthSlot);
            }
            else {
                //textview21.setBackgroundColor(Color.GRAY);
                textview21.setTextSize(20);
                textview21.setText("Add Appointment");
                textview21.setBackground(getResources().getDrawable(R.drawable.free_slot));
            }

            Saturdaygap5 = jsonArray.getJSONObject(0).getString("Saturdaygap5");
            textview22 = (TextView) findViewById(R.id.textView22);
            if (Saturdaygap5 != "null") {
                textview22.setText(Saturdaygap5);
            }
            else {
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
}
