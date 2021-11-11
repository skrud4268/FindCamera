package com.example.admin.findcamera;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Signin extends AppCompatActivity {

    Button BtnSignIn;
    EditText inputID, inputPW;
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        BtnSignIn = (Button) findViewById(R.id.btn_sign_in);
        inputID = (EditText) findViewById(R.id.et_idname);
        inputPW = (EditText) findViewById(R.id.et_pwname);

        try{
            BtnSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog = ProgressDialog.show(Signin.this, "",
                            "Validating user...", true);
                    new Thread(new Runnable() {
                        public void run() {
                            login();
                        }
                    }).start();
                }
            });

        }
    catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void login() {
        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://13.125.49.224/login.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("username", inputID.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("password", inputPW.getText().toString()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);

            //System.out.println("Response : " + response);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //tv.setText(response);
                    dialog.dismiss();

                    String rp[] = response.split("<BR>");
                    String login = rp[0];

                    if (login.equalsIgnoreCase("b")) {
                        //runOnUiThread(new Runnable() {
                        //@Override
                        //public void run() {
                        Toast.makeText(Signin.this, "Login Success", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(MainActivity.this, rp[6], Toast.LENGTH_SHORT).show();

                        //}
                        //});

                        //finish();
                        Intent intent2 = new Intent(Signin.this, Home.class);
                        startActivity(intent2);
                    } else {
                        Toast.makeText(Signin.this, "Login Fail", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception e) {
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }
}
