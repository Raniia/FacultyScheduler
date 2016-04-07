package com.guc.bachelor.facultyscheduler;

import android.app.Activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


public class StudentsLogin extends Activity {

    private EditText student_email;
    private EditText student_password;
    String loginEmail;
    String loginPassword;
    String json_string;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_login);

        student_email = (EditText) findViewById(R.id.student_email);
        student_password = (EditText) findViewById(R.id.student_password);
    }

    public void userLogin(View view) {
        loginEmail = student_email.getText().toString();
        loginPassword = student_password.getText().toString();
        String method = "studentLogin";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, loginEmail, loginPassword);

        // parseJSON(view);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_students_login, menu);
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
        AlertDialog alertDialog;
        Context ctx;
        String TAG = "Database Information";

        BackgroundTask(Context ctx) {
            this.ctx = ctx;

        }

        @Override
        protected void onPreExecute() {
            alertDialog = new AlertDialog.Builder(ctx).create();
            alertDialog.setTitle("Login Information...");
        }


        @Override
        protected String doInBackground(String... params) {
           // String studentLogin_URL = "http://192.168.1.8/faculty_scheduler/loginStudents.php?student="+student_email;
            String method = params[0];
            if (method.equals("studentLogin")) {
                String student_email = params[1];
                String student_password = params[2];
                try {
                    String studentLogin_URL = "http://192.168.1.4/faculty_scheduler/loginStudents.php";
                     URL url = new URL(studentLogin_URL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("student_email", "UTF-8") + "=" + URLEncoder.encode(student_email, "UTF-8") + "&" +
                            URLEncoder.encode("student_password", "UTF-8") + "=" + URLEncoder.encode(student_password, "UTF-8");
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
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            json_string = result;

            TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(json_string);

            //alertDialog.setMessage(result);
            //alertDialog.show();
            if (!json_string.contains("student_ID") ) {
                Toast.makeText(getApplicationContext(), "Invalid User Details", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(ctx, StudentsHomepage.class);
                intent.putExtra("student_login", json_string);
                startActivity(intent);
            }
        }
    }
}











