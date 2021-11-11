package com.example.admin.findcamera;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Detect_no extends Activity {
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_no);
        result = (TextView) findViewById(R.id.tv_result);
    }
}

