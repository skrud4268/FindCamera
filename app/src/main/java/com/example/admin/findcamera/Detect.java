package com.example.admin.findcamera;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import static android.content.Context.WIFI_SERVICE;

public class Detect extends AppCompatActivity {

    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detect);
    }

    /*public void Sort{
        Intent intent1 = getIntent();
        String resultssid[] = intent1.getStringArrayExtra("strings");
        int resultlevel[] = intent1.getIntArrayExtra("int");
        int group=0;
        int j =resultssid.length;
        int l = resultlevel.length;
        for (int i=0; i<j; i++){
            if (resultssid[i].length()>10)
            {
                group=1;
            }
        }
            if (group == 1) {
            for(int k=0; k<l;k++)
            {
                if(resultlevel[k]>-50)
                {
                    group = 2;
                }
                else if(resultlevel[k]>-70 & resultlevel[k]<-50)
                {
                    group =3;
                }
                else
                {
                    group =4;
                }
            }

            }
        }
*/
}