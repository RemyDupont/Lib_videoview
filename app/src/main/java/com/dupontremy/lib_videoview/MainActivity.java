package com.dupontremy.lib_videoview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button simpleinit, custominit, scaletypes, commonmethods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleinit = (Button) findViewById(R.id.simpleinit);
        simpleinit.setOnClickListener(this);

        custominit = (Button) findViewById(R.id.custominit);
        custominit.setOnClickListener(this);

        scaletypes = (Button) findViewById(R.id.scaletype);
        scaletypes.setOnClickListener(this);

        commonmethods = (Button) findViewById(R.id.commonmethods);
        commonmethods.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.simpleinit:
                startActivity(new Intent(this, SimpleInitActivity.class));
                break;
            case R.id.custominit:
                startActivity(new Intent(this, CustomInitActivity.class));
                break;
            case R.id.scaletype:
                startActivity(new Intent(this, ScaleTypeActivity.class));
                break;
            case R.id.commonmethods:
                startActivity(new Intent(this, CommonMethodsActivity.class));
        }
    }
}
