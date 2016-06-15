package com.example.aidlservicetest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.Map;

/**
 * AIDL 服务器端
 */
public class ServiceActivity extends AppCompatActivity {
    private static final String Tag = "ServiceActivity----";
    private IMyServiceInterface serviceBinder;
    private Intent intent;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(Tag,"onCreate");
        Log.i(Tag, "ServiceActivity thread id is " + Thread.currentThread().getId());

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                serviceBinder = IMyServiceInterface.Stub.asInterface(iBinder);
                try {
                    Person p = serviceBinder.getPerson();
                    Log.i(Tag,"getPerson:"+p.toString());

                    int res = serviceBinder.add(2,3);
                    Log.i(Tag,"add(2,3):"+res);

                    Person pp = new Person();
                    pp.setName("JOHN");
                    pp.setSex(0);

                    Map mm = serviceBinder.getMap("Key",pp);
                    Log.i(Tag,"getMap(\"Key\",pp):"+mm);

                    String str = serviceBinder.showPerson(pp);
                    Log.i(Tag,"showPerson:"+str);

                    List<Person> list = serviceBinder.getPersonList(pp);
                    Log.i(Tag,"getPersonList:"+list);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.i(Tag,"onServiceDisconnected:unbind");
            }
        };

        intent = new Intent(ServiceActivity.this,MyService.class);
//        startService(intent);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        Log.i(Tag,"onDestroy");
        unbindService(serviceConnection);
        super.onDestroy();
    }
}
