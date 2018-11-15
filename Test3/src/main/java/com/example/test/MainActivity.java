package com.example.test;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
/**
 * @ describe 
 * 
 * @author lzl
 *
 * @ time 2018/9/11 15:52
 * 
 * @ param
 * 
 * @ return
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void dialogShow2(View view) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
     final   Dialog dialog=new Dialog(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.update_manage_dialog, null);
        TextView content = (TextView) v.findViewById(R.id.dialog_content);
        Button btn_sure = (Button) v.findViewById(R.id.dialog_btn_sure);
        Button btn_cancel = (Button) v.findViewById(R.id.dialog_btn_cancel);
//        final Dialog dialog = builder.create();
        dialog.setContentView(v);
        dialog.show();
        dialog.getWindow().setContentView(v);//自定义布局应该在这里添加，要在dialog.show()的后面

        //dialog.getWindow().setGravity(Gravity.CENTER);//可以设置显示的位置
        btn_sure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "no",  Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {



        String url="http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8";



        VideoView videoView=findViewById(R.id.mVideoView);



        videoView.setVideoPath(url);



        videoView.requestFocus();



        videoView.start();



    }




}
