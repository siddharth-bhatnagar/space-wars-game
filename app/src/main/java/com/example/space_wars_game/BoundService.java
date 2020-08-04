package com.example.space_wars_game;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Chronometer;



public class BoundService extends Service {

    private static String LOG_TAG = "BoundService";
    private IBinder mBinder = new BoundServiceBinder();
    private Chronometer mChronometer;

    @Override
    public void onCreate() {
        super.onCreate();

        mChronometer = new Chronometer(this);
        mChronometer.setBase(SystemClock.elapsedRealtime());
        mChronometer.start();
    }

    @Override
    public IBinder onBind(Intent intent) {

        return mBinder;
    }

    @Override
    public void onRebind(Intent intent) {

        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {

        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mChronometer.stop();
    }

    public String getTimestamp() {
        long elapsedMillis = SystemClock.elapsedRealtime()
                - mChronometer.getBase();
        int hours = (int) (elapsedMillis / 3600000);
        int minutes = (int) (elapsedMillis - hours * 3600000) / 60000;
        int seconds = (int) (elapsedMillis - hours * 3600000 - minutes * 60000) / 1000;
        int millis = (int) (elapsedMillis - hours * 3600000 - minutes * 60000 - seconds * 1000);
        return hours + ":" + minutes + ":" + seconds;
    }

    public class BoundServiceBinder extends Binder {
        //Return object of BoundService class which can be used to access all the public methods of this class
        BoundService getService() {
            return BoundService.this;
        }
    }
}
