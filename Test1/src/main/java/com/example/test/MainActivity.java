package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view){
      if(view.isShown()){
          Toast.makeText(this,"可见",Toast.LENGTH_SHORT).show();
      }else{
          Toast.makeText(this,"不可见",Toast.LENGTH_SHORT).show();
      }
    }
}
