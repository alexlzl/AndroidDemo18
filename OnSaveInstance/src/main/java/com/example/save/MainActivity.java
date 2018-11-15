package com.example.save;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.test.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    BlankFragment blankFragment;
    private Method noteStateNotSavedMethod;
    private Object fragmentMgr;
    private String[] activityClassName = {"Activity", "FragmentActivity"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(MainActivity.this.getClass().getName(),"onCreate=====");
        setContentView(R.layout.activity_main);

        Timer timer = new Timer();
        //延迟一个小时后执行
        TimerTask task = new TimerTask(){

            @Override
            public void run() {

                show();
            }
        };
        timer.schedule(task,1000*3);


    }

    class TestRun implements Runnable{

        @Override
        public void run() {

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(MainActivity.this.getClass().getName(),"onPause=====");
    }

    private void show(){
        if(isFinishing()){
            return;
        }
        blankFragment=new BlankFragment();
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,blankFragment);
        fragmentTransaction.commitAllowingStateLoss();
//        fragmentTransaction.commit();
    }
       public void ontest(View view){
//        startActivityForResult(new Intent(this,Main2Activity.class),1001);
//           finish();
           android.os.Process.killProcess(android.os.Process.myPid());
       }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(MainActivity.this.getClass().getName(),"onSaveInstanceState====="+"isFinish=="+isFinishing());
//        invokeFragmentManagerNoteStateNotSaved();

    }

    @Override
    public void finish() {
        super.finish();
        Log.e(MainActivity.this.getClass().getName(),"finish=====");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(MainActivity.this.getClass().getName(),"onStop====="+"isFinish=="+isFinishing());
//        fragmentTransaction.add(R.id.fragment_container,blankFragment);
//        fragmentTransaction.commit();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"isFinish"+isFinishing(),Toast.LENGTH_SHORT).show();
        Log.e(MainActivity.this.getClass().getName(),"onDestroy====="+"isFinish=="+isFinishing());
//        fragmentTransaction.add(R.id.fragment_container,blankFragment);
//        fragmentTransaction.commit();
    }

    private void invokeFragmentManagerNoteStateNotSaved() {
        //java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return;
        }
        try {
            if (noteStateNotSavedMethod != null && fragmentMgr != null) {
                noteStateNotSavedMethod.invoke(fragmentMgr);
                return;
            }
            Class cls = getClass();
            do {
                cls = cls.getSuperclass();
            } while (!(activityClassName[0].equals(cls.getSimpleName())
                    || activityClassName[1].equals(cls.getSimpleName())));

            Field fragmentMgrField = prepareField(cls, "mFragments");
            if (fragmentMgrField != null) {
                fragmentMgr = fragmentMgrField.get(this);
                noteStateNotSavedMethod = getDeclaredMethod(fragmentMgr, "noteStateNotSaved");
                if (noteStateNotSavedMethod != null) {
                    noteStateNotSavedMethod.invoke(fragmentMgr);
                }
            }

        } catch (Exception ex) {
        }
    }

    private Field prepareField(Class<?> c, String fieldName) throws NoSuchFieldException {
        while (c != null) {
            try {
                Field f = c.getDeclaredField(fieldName);
                f.setAccessible(true);
                return f;
            } finally {
                c = c.getSuperclass();
            }
        }
        throw new NoSuchFieldException();
    }

    private Method getDeclaredMethod(Object object, String methodName, Class<?>... parameterTypes) {
        Method method = null;
        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
                return method;
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1001&&resultCode==1000){
            Toast.makeText(this,"test",Toast.LENGTH_SHORT).show();
        }
    }
}
