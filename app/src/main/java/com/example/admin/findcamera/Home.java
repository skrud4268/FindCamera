package com.example.admin.findcamera;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class Home extends AppCompatActivity {
    Button BtnDetect, BtnSearch;
    WifiManager manager;
    private List<ScanResult> scanResults;
    String resultssid[];
    int resultlevel[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BtnDetect = (Button) findViewById(R.id.button_detect);
        BtnSearch = (Button) findViewById(R.id.button_search);
        BtnDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentFilter intentFilter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
                registerReceiver(receiver,intentFilter);
                manager.startScan();
                Intent intent1 = new Intent(Home.this, Detect.class);
                intent1.putExtra("strings", resultssid);
                intent1.putExtra("int",resultlevel);
                startActivity(intent1);
            }
        });
            manager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
            if (!manager.isWifiEnabled()) {
                manager.setWifiEnabled(true); // wifi 가 켜져있지 않을 경우 자동으로 wifi를 켜줍니다.
            }
        }
        private BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String action = intent.getAction();
                if(action.equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)){
                    scanResults = manager.getScanResults();
                    for(int i=0; i<scanResults.size(); i++)
                    {
                        resultssid[i] = scanResults.get(i).SSID;
                        resultlevel[i] = scanResults.get(i).level;

                    }
                    /*for (int i=0; i< scanResults.size(); i++)
                    {
                        Log.d("ssid["+i+"]", scanResults.get(i).SSID);
                    }*/
            }else if(action.equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){
                    sendBroadcast(new Intent("wifi.ON_NETWORK_STATE_CHANGED"));
                }
        }
    };


}