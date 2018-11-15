package com.example.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void test2(View view){
//        startActivityForResult(new Intent(this,Main3Activity.class),2000);
//        startActivity(new Intent(this,Main3Activity.class));
        setResult(1001);
        finish();
    }
}
