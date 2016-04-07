package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class DoctorsSchedule extends Activity {
GridView gridView;
    Context context = this;
ScheduleAdapter scheduleAdapter;
    private static final int ROW_ITEMS = 6;


    String doctor_details;
    JSONArray jsonArray;
    final  ArrayList<String> schedule = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_schedule);

        gridView = (GridView) findViewById(R.id.ScheduleGrid);




       // String[] scheduleHeadings= {"Time Slots", "1st slot", "gap", "2nd slot", "gap", "3rd slot", "gap", "4th slot", "gap", "5th slot","gap", "Saturday", "", "", "", "", "", "", "", "", "", "", "Sunday", "", "", "", "", "", "", "", "", "", "", "Monday", "", "", "", "", "", "", "", "", "", "", "Tuesday", "", "", "", "", "", "", "", "", "", "", "Wednesday", "", "", "", "", "", "", "", "", "", "", "Thursday", "", "", "", "", "", "", "", "", "", ""};
       // ArrayAdapter<String> adp = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1, scheduleHeadings);

       // gridView.setAdapter(new ScheduleAdapter(this, scheduleHeadings));

        /*gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(DoctorsSchedule.this, "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        */
        gridView.setAdapter(new ScheduleAdapter(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctors_schedule, menu);
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


class SingleSlot {
    String slot;
    SingleSlot(String slot) {
        this.slot = slot;
    }
}
    public class ScheduleAdapter extends BaseAdapter {

ArrayList<SingleSlot> singleSlots;
        Context ctx;
       ScheduleAdapter(Context ctx) {
           this.ctx = ctx;
           singleSlots = new ArrayList<SingleSlot>();
           doctor_details = getIntent().getExtras().getString("doctor_schedule");
           try {
               JSONObject object = new JSONObject(doctor_details);

               jsonArray = object.getJSONArray("doctor_schedule");

               int count = 0;
               int doctor_ID;
               String SaturdayfirstSlot;
               String Saturdaygap1;
               String SaturdaysecondSlot;
               String Saturdaygap2;
               String SaturdaythirdSlot;
               String Saturdaygap3;
               String SaturdayfourthSlot;
               String Saturdaygap4;
               String SaturdayfifthSlot;
               String Saturdaygap5;


               while (count <= jsonArray.length()) {
                   JSONObject JO = jsonArray.getJSONObject(count);
                   doctor_ID = JO.getInt("doctor_ID");
                   SaturdayfirstSlot = JO.getString("SaturdayfirstSlot");
                   Saturdaygap1 = JO.getString("Saturdaygap1");
                   SaturdaysecondSlot = JO.getString("SaturdaysecondSlot");
                   Saturdaygap2 = JO.getString("Saturdaygap2");
                   SaturdaythirdSlot = JO.getString("SaturdaythirdSlot");
                   Saturdaygap3 = JO.getString("Saturdaygap3");
                   SaturdayfourthSlot = JO.getString("SaturdayfourthSlot");
                   Saturdaygap4 = JO.getString("Saturdaygap4");
                   SaturdayfifthSlot = JO.getString("SaturdayfifthSlot");
                   Saturdaygap5 = JO.getString("Saturdaygap5");

String [] saturday = {SaturdayfirstSlot, Saturdaygap1, SaturdaysecondSlot, Saturdaygap2, SaturdaythirdSlot, Saturdaygap3, SaturdayfourthSlot, Saturdaygap4, SaturdayfifthSlot, Saturdaygap5};
                   for (int i = 0; i< 10; i++) {
                       SingleSlot singleSlot = new SingleSlot(saturday[i]);
                       singleSlots.add(singleSlot);
                   }
               }

           }
           catch (JSONException e) {
               e.printStackTrace();
           }


       }


        @Override
        public int getCount() {
            return singleSlots.size();
        }

        @Override
        public Object getItem(int position) {
            return singleSlots.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            ScheduleHolder holder = null;
if (row == null) {
    LayoutInflater inflater =(LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    inflater.inflate(R.layout.slot_details_layout, parent, false);
    holder = new ScheduleHolder(row);
    row.setTag(holder);
}
            else  {
holder = (ScheduleHolder) row.getTag();
            }
           SingleSlot element =  singleSlots.get(position);
            holder.tv.setText(element.slot);

          return row;

        }
        public class ScheduleHolder
        {
            TextView tv;
            ScheduleHolder(View v) {
                tv = (TextView) v.findViewById(R.id.courseName);
            }
        }
    }
}
