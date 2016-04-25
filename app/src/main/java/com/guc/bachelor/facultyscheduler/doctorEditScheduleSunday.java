package com.guc.bachelor.facultyscheduler;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class doctorEditScheduleSunday extends Activity
{    static final int DATE_DIALOG_ID = 0;

    Button done;
    Context context = this;

    private TextView displayDate;
    String dateApp;
    private Button pickDate;
    private int tyear;
    private int tmonth;
    private int tday;
    String finalTimings;



    private ListView lView;

    ArrayAdapter<String> adapter;

    String doctor_ID = DoctorsHomepage.storeDoctorID;








    String dayOfWeek;
    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                    Date date = new Date(year, monthOfYear, dayOfMonth - 1);
                    dayOfWeek = simpledateformat.format(date);
                    Log.d("DAY OF WEEK IS", dayOfWeek);
                    if (dayOfWeek.equals("Sunday")) {
                        tyear = year;
                        tmonth = monthOfYear;
                        tday = dayOfMonth;
                        updateDisplay();
                        Date d = new Date(tyear-1900,tmonth,tday);
                        SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");

                        dateApp = inFormat.format(d);
                        Toast.makeText(getApplicationContext(), new StringBuilder().append("Date choosen is ").append(dateApp), Toast.LENGTH_SHORT).show();


                    } else {
                        Toast.makeText(getApplicationContext(), "This day is not Sunday, Please choose another day.", Toast.LENGTH_LONG).show();

                    }
                }
            };

    private void updateDisplay() {
        //  if (dayOfWeek.equals("Saturday")) {

        displayDate.setText(
                new StringBuilder()
                        // Month is 0 based so add 1

                        .append(tyear).append("-")
                        .append(tmonth + 1).append("-")
                        .append(tday).append("")

        );
        //  dateApp = displayDate.getText().toString();
        // }
        // else {
        //   Toast.makeText(getApplicationContext(), "nooooot valid.", Toast.LENGTH_LONG).show();

        //   }
    }



    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(displayDate.getText()), Toast.LENGTH_SHORT).show();

    }




    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        tyear, tmonth, tday);
        }
        return null;
    }

    private String lv_items[] = { "8:30 -> 8:45", "8:45 -> 9:00", "9:00 -> 9:15",
            "9:15 -> 9:30", "9:30 -> 9:45" , "9:45 -> 10:00", "10:00 -> 10:15",
            "10:15 -> 10:30", "10:30 -> 10:45", "10:45 -> 11:00" ,
            "11:00 -> 11:15", "11:15 -> 11:30", "11:30 -> 11:45",
            "11:45 -> 12:00", "12:00 -> 12:15", "12:15 -> 12:30",
            "12:30 -> 12:45", "12:45 -> 1:00", "1:00 -> 1:15",
            "1:15 -> 1:30", "1:30 -> 1:45", "1:45 -> 2:00", "2:00 -> 2:15",
            "2:15 -> 2:30", "2:30 -> 2:45", "2:45 -> 3:00", "3:00 -> 3:15",
            "3:15 -> 3:30", "3:30 -> 3:45", "3:45 -> 4:00", "4:00 -> 4:15",
            "4:15 -> 4:30", "4:30 -> 4:45", "4:45 -> 5:00","5:00 -> 5:15",
            "5:15 -> 5:30", "5:30 -> 5:45",  "5:45 -> 6:00"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_edit_schedule_sunday);






        displayDate = (TextView) findViewById(R.id.displayDate);
        pickDate = (Button) findViewById(R.id.pickDate);

        pickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        final Calendar cal = Calendar.getInstance();
        tyear = cal.get(Calendar.YEAR);
        tmonth = cal.get(Calendar.MONTH);
        tday = cal.get(Calendar.DAY_OF_MONTH);



        /** Display the current date in the TextView */
        updateDisplay();





        done = (Button) findViewById(R.id.done);
        lView = (ListView) findViewById(R.id.ListView01);
//	Set option as Multiple Choice. So that user can able to select more the one option from list
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_multiple_choice, lv_items );
        lView.setAdapter(adapter);
        lView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);




        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String checkDate = displayDate.getText().toString();
                String[] check = checkDate.split("-");
                String yyyy = check[0];
                String mm = check[1];
                String dd = check[2];
                String input = yyyy + "-" + mm + "-" + dd;
                SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = inFormat.parse(input);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                String goal = outFormat.format(date);

               /* SimpleDateFormat s = new SimpleDateFormat("EEEE");
                Date checkd = new Date(tyear, tmonth +1 , tday);

                String day = s.format(checkd);*/


                if (goal.equals("Sunday")) {


                    SparseBooleanArray checked = lView.getCheckedItemPositions();
                    ArrayList<String> selectedItems = new ArrayList<String>();

                    for (int i = 0; i < checked.size(); i++) {
                        // Item position in adapter
                        int position = checked.keyAt(i);
                        // Add sport if it is checked i.e.) == TRUE!
                        if (checked.valueAt(i))
                            selectedItems.add(adapter.getItem(position));
                    }
                    String[] outputStrArr = new String[selectedItems.size()];

                    for (int i = 0; i < selectedItems.size(); i++) {
                        outputStrArr[i] = selectedItems.get(i);



                    }



                    StringBuilder builder = new StringBuilder();
                    for(String s : outputStrArr) {
                        builder.append(s).append("  ");
                    }


                    finalTimings =  builder.toString();

                    Log.d("OUTPUTTTTTT", finalTimings);
                    Log.d("timiiiiiiiiii", dateApp);








                    String method = "doctorEditScheduleSunday";
                    BackgroundTask backgroundTask = new BackgroundTask();
                    backgroundTask.execute(method);




                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();

                    // Setting Dialog Title
                    alertDialog.setTitle("Free Timings updated.");

                    // Setting Dialog Message
                    //alertDialog.setMessage("Continue to your appointments...");

                    // Setting Icon to Dialog
                    alertDialog.setIcon(R.drawable.tick);


                    alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog closed

                            // Toast.makeText(getApplicationContext(), "You clicked on OK", Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Showing Alert Message
                    alertDialog.show();

                }


            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_doctor_edit_schedule_sunday, menu);
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








































    public class BackgroundTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPreExecute() {
            //  alertDialog = new AlertDialog.Builder(ctx).create();
            // alertDialog.setTitle("Fetching Doctor's Schedule");
        }

        protected String doInBackground(String... params) {
            String method = params[0];
            if (method.equals("doctorEditScheduleSunday")) {
                try {

                    String setAppointment_URL = "http://192.168.1.2/faculty_scheduler/doctorEditScheduleSunday.php";
                    URL url = new URL(setAppointment_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("doctor_ID", "UTF-8") + "=" + URLEncoder.encode(doctor_ID, "UTF-8") + "&" +
                            URLEncoder.encode("datePassed", "UTF-8") + "=" + URLEncoder.encode(dateApp, "UTF-8") + "&" +
                            URLEncoder.encode("finalTimings", "UTF-8") + "=" + URLEncoder.encode(finalTimings, "UTF-8")
                            ;
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

