package com.example.aidlservicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyService extends Service {
    private static final String Tag = "MyService---";

    @Override
    public void onCreate() {
        Log.i(Tag,"onCreate");
        Log.i(Tag, "MyService thread id is " + Thread.currentThread().getId());
        super.onCreate();

        // 设置了点击通知后就打开MainActivity
        Intent notificationIntent = new Intent(getApplicationContext(), ServiceActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        Notification notification = new Notification.Builder(this)
                    .setAutoCancel(true)
                    .setContentTitle("服务器端运行中...")
                    .setContentText("AIDL")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setWhen(System.currentTimeMillis())
                    .setOngoing(true)
                    .build();
        // 调用startForeground()方法就可以让MyService变成一个前台Service
        // 方便查看服务是否在运行
        startForeground(1, notification);
    }

    @Override
    public void onLowMemory() {
        Log.i(Tag,"onLowMemory");
        super.onLowMemory();
    }

    @Override
    public void onDestroy() {
        Log.i(Tag,"onDestroy");
        super.onDestroy();
    }

    @Override
    public void onTrimMemory(int level) {
        Log.i(Tag,"onTrimMemory");
        super.onTrimMemory(level);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(Tag,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(Tag,"onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(Tag,"onRebind");
        super.onRebind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(Tag,"onBind");
        return new ServiceBinder();
    }

    class ServiceBinder extends IMyServiceInterface.Stub{

        @Override
        public int add(int a, int b) throws RemoteException {
            return a+b;
        }

        @Override
        public String showPerson(Person person) throws RemoteException {
            return person.toString();
        }

        @Override
        public List<Person> getPersonList(Person person) throws RemoteException {
            List<Person> personList = new ArrayList<>();
            personList.add(person);
            return personList;
        }

        @Override
        public Map getMap(String key, Person person) throws RemoteException {
            Map map = new HashMap();
            map.put(key,key);
            map.put("name",person.getName());
            map.put("sex",person.getSex());
            return map;
        }

        @Override
        public Person getPerson() throws RemoteException {
            Person person = new Person();
            person.setName("Tom");
            person.setSex(0);
            return person;
        }
    }
}
