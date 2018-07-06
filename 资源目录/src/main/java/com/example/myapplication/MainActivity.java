package com.example.myapplication;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        XmlResourceParser xml = getResources().getXml(R.xml.test);
        try {
            xml.next();
            int eventType = xml.getEventType();
            boolean inTitle = false;
            while(eventType != XmlPullParser.END_DOCUMENT) {

                //到达title节点时标记一下
                if(eventType == XmlPullParser.START_TAG) {
                    if(xml.getName().equals("title")) {
                        inTitle = true;
                    }
                }

                //如过到达标记的节点则取出内容
                if(eventType == XmlPullParser.TEXT && inTitle) {
                    ((TextView)findViewById(R.id.test)).setText(
                            xml.getText()
                    );
                }

                xml.next();
                eventType = xml.getEventType();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
