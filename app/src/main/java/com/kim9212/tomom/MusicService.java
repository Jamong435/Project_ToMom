package com.kim9212.tomom;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public class MusicService extends Service {

    MediaPlayer mp;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationManager notificationManager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

            NotificationChannel channel= new NotificationChannel("ch1","뮤직서비스", NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(channel);

            NotificationCompat.Builder builder= new NotificationCompat.Builder(this, "ch1");


            builder.setSmallIcon(R.drawable.ic_baseline_accessibility_new_24);
            builder.setContentTitle("Music Servie");
            builder.setContentText("뮤직서비스가 실행중입니다.");
            Intent i= new Intent(this, MainActivity.class);

            PendingIntent pendingIntent= PendingIntent.getActivity(this, 10, i, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            builder.setAutoCancel(true);

            Notification notification= builder.build();

            startForeground(1, notification);
        }


        if(mp==null){
            mp= MediaPlayer.create(this, R.raw.jazz);
            mp.setLooping(true);
            mp.setVolume(1.0f, 1.0f);
        }
        mp.start();


        return START_STICKY;
    }


    @Override
    public void onDestroy() {

        if( mp!=null && mp.isPlaying() ){
            mp.stop();
            mp.release();
            mp= null;
        }

        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
