package com.guc.bachelor.facultyscheduler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


public class BackgroundTask extends AsyncTask<String, Void, String> {
    AlertDialog alertDialog;
    Context ctx;
    String json_string;
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
        String studentLogin_URL = "http://192.168.1.7/faculty_scheduler/loginStudents.php";
        String method = params[0];
        if(method.equals("studentLogin")) {
            String student_email = params[1];
            String student_password = params[2];
            try {
                URL url = new URL(studentLogin_URL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("student_email", "UTF-8") +"=" +URLEncoder.encode(student_email,"UTF-8") + "&" +
                        URLEncoder.encode("student_password", "UTF-8") +"=" +URLEncoder.encode(student_password,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS, "iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response+=line;
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

                Log.d(TAG,"Response is: " + response);
                bufferedReader.close();
                IS.close();
                httpURLConnection.disconnect();
                return response;

            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
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
        // alertDialog.setMessage(result);
        //alertDialog.show();


    }



}
