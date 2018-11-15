package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.text);
     float f=   getDensity();
        textView.setText(""+f+"value=="+getDensityq());
        Log.e(getClass().getName(),"onCreate");
    }

    public void test(View view ){
        finish();
//        startActivityForResult(new Intent(this,Main2Activity.class),1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    private float getDensity() {
        DisplayMetrics dm = new DisplayMetrics();
      getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.density;
    }
    private int getDensityq() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.densityDpi;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(getClass().getName(),"onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(getClass().getName(),"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(getClass().getName(),"onStop");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(getClass().getName(),"onSaveInstanceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(getClass().getName(),"onDestroy");
    }
}
