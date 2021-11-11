package com.example.admin.findcamera;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import static android.content.ContentValues.TAG;

public class Signup extends Activity {

    private EditText mEditid;
    private EditText mEditpw;
    private EditText mEditRRN;
    private EditText mEdittelephone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mEditid = (EditText) findViewById(R.id.et_sign_id);
        mEditpw = (EditText) findViewById(R.id.et_sign_pw);
        mEditRRN = (EditText) findViewById(R.id.et_rrn);
        mEdittelephone = (EditText) findViewById(R.id.et_telephone);

    }


    public void insert(View v) {

        String id = mEditid.getText().toString();
        String pw = mEditpw.getText().toString();
        String rrn = mEditRRN.getText().toString();
        String telephone = mEdittelephone.getText().toString();
        iTD(id,pw,rrn,telephone);
    }

    private void iTD(String id, String pw, String rrn, String telephone)
    {
        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog progressDialog;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(Signup.this,
                        "Please Wait", null, true, true);
            }


            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                progressDialog.dismiss();
            }


            @Override
            protected String doInBackground(String... params) {
                try {
                    String id= (String) params[0];
                    String pw = (String) params[1];
                    String rrn = (String) params[2];
                    String telephone = (String) params[3];
                    String serverURL = "http://13.125.49.224/insert.php";
                    String postParameters = "id=" + id + "&pw=" + pw +
                            "&rrn=" + rrn + "&telephone=" + telephone;

                    URL url = new URL(serverURL);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                    httpURLConnection.setReadTimeout(5000);
                    httpURLConnection.setConnectTimeout(5000);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.connect();


                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    outputStream.write(postParameters.getBytes("UTF-8"));
                    outputStream.flush();
                    outputStream.close();


                    int responseStatusCode = httpURLConnection.getResponseCode();
                    Log.d(TAG, "POST response code - " + responseStatusCode);

                    InputStream inputStream;
                    if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                        inputStream = httpURLConnection.getInputStream();
                    } else {
                        inputStream = httpURLConnection.getErrorStream();
                    }


                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while ((line = bufferedReader.readLine()) != null) {
                        sb.append(line);
                    }

                    bufferedReader.close();

                    return sb.toString();
                } catch (Exception e) {
                    Log.d(TAG, "InsertData: Error ", e);
                    return new String("Error: " + e.getMessage());
                }
            }
        }
        InsertData task = new InsertData();
        task.execute(id, pw, rrn, telephone);
    }
}
