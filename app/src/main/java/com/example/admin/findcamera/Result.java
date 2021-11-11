package com.example.admin.findcamera;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Result extends AppCompatActivity {
    Button BtnResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        BtnResult = (Button) findViewById(R.id.button_name);
        BtnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Result.this, Registration.class);
                startActivity(intent1);
            }
        });

    }
}