package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lib1.Test;
import com.example.mylibrary.Lib2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void test(View view ){
        Toast.makeText(this, Test.test()+ Lib2.test(),Toast.LENGTH_SHORT).show();
    }
}
