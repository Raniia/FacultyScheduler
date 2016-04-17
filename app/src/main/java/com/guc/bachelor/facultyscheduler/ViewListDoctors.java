package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

public class ViewListDoctors extends Activity {
    String json_stringAllDoctors;
    JSONObject jsonObject;
    JSONArray jsonArray;
    DoctorAdapter doctorAdapter;
    ListView listView;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_list_doctors);
        listView = (ListView) findViewById(R.id.doctorsListView);

        doctorAdapter = new DoctorAdapter(this, R.layout.row_layout);
        listView.setAdapter(doctorAdapter);
        json_stringAllDoctors = getIntent().getExtras().getString("viewDoctors");
        try {
            jsonObject = new JSONObject(json_stringAllDoctors);
            jsonArray = jsonObject.getJSONArray("allDoctors");

            int count = 0;
            String doctor_name;

            while (count < jsonArray.length()) {
                JSONObject JO = jsonArray.getJSONObject(count);
                doctor_name = JO.getString("doctor_name");
                Doctors doctors = new Doctors(doctor_name);
                doctorAdapter.add(doctors);
                count++;

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_list_doctors, menu);
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


    public class DoctorAdapter extends ArrayAdapter {
        List list = new ArrayList();
        String doctorsNameString;
        String doctor_details;

        public DoctorAdapter(Context context, int resource) {
            super(context, resource);
        }


        public void add(Doctors object) {
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
            DoctorHolder doctorHolder;
            if (row == null) {
                LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = layoutInflater.inflate(R.layout.row_layout, parent, false);
                doctorHolder = new DoctorHolder();
                doctorHolder.doctorName = (TextView) row.findViewById(R.id.doctorName);
                doctorHolder.checkSchedule = (Button) row.findViewById(R.id.viewSchedule);
                doctorHolder.checkSchedule.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getContext(), "Button was clicked for doctor." + position, Toast.LENGTH_SHORT).show();
                        RelativeLayout r1 = (RelativeLayout) v.getParent();
                        TextView tv = (TextView) r1.findViewById(R.id.doctorName);
                        doctorsNameString = tv.getText().toString();
                  /*  HttpClient client = new DefaultHttpClient();
                    HttpPost getMethod = new HttpPost("http://192.168.1.4/faculty_scheduler/showDoctorSchedule.php");
                    ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                    nameValuePairs.add(new BasicNameValuePair("doctorName", text));
                    try {
                        getMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
                    }
                    catch (UnsupportedEncodingException  e) {
                        e.printStackTrace();
                    }
                    try {
                        client.execute(getMethod);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    */
                        BackgroundTask backgroundTask = new BackgroundTask();
                        backgroundTask.execute();


                        Toast.makeText(getContext(), doctorsNameString, Toast.LENGTH_SHORT).show();
                    }
                });

                row.setTag(doctorHolder);

                row.setTag(doctorHolder);
            } else {
                doctorHolder = (DoctorHolder) row.getTag();
            }

            Doctors doctors = (Doctors) this.getItem(position);
            doctorHolder.doctorName.setText(doctors.getDoctor_name());
            return row;
        }


        public class DoctorHolder {
            TextView doctorName;
            Button checkSchedule;

        }



        public class BackgroundTask extends AsyncTask<String, Void, String> {
            AlertDialog alertDialog;
            Context ctx;
            String TAG = "Database Information";


         //   public BackgroundTask(Context ctx) {
            //    this.ctx = ctx;
            //}

            @Override
            protected void onPreExecute() {
              //  alertDialog = new AlertDialog.Builder(ctx).create();
               // alertDialog.setTitle("Fetching Doctor's Schedule");
            }


            @Override
            protected String doInBackground(String... params) {

                try {
                    String showDoctorSchedule_URL = "http://192.168.1.5/faculty_scheduler/showDoctorSchedule.php";
                    URL url = new URL(showDoctorSchedule_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("doctorsNameString", "UTF-8") + "=" + URLEncoder.encode(doctorsNameString, "UTF-8");
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

               /* try {
                    String jsonStr = response; //{"student_login_details":[{"student_ID":"3102","student_name":"Rania","student_email":"raniimenem@gmail.com","student_faculty":"Engineering","student_major":"Media Engineering and Technology","student_group":"28-"}]}
                    JSONObject obj = new JSONObject(jsonStr);
                    String ID = obj.getString("student_ID");
                }
                catch(JSONException e) {
                    e.printStackTrace();
                }
*/


                    Log.d(TAG, "Response is: " + response);
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
                doctor_details = result;

               /* TextView textView = (TextView) findViewById(R.id.textView4);
                textView.setText(doctor_details);*/
             //   alertDialog.setMessage(result);
               // alertDialog.show();

                if(!doctor_details.contains("doctor_ID")) {
                    Toast.makeText(getApplicationContext(), "No schedule available", Toast.LENGTH_LONG).show();

                }
                else {
                    Intent intent = new Intent(context, ScheduleOfDoctors.class);
                    intent.putExtra("doctor_schedule", doctor_details);
                    startActivity(intent);
                }

            }
        }


    }


}
