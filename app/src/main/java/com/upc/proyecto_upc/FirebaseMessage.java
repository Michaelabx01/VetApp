package com.upc.proyecto_upc;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessage extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        if (message.getData().size() > 0){
            mostrarNotificacion(message.getData().get("title"), message.getData().get("message"));
        }

        if (message.getNotification() != null){
            mostrarNotificacion(message.getNotification().getTitle(), message.getNotification().getBody());
        }
    }
    private RemoteViews getCustomDesign(String title, String message){
        RemoteViews views = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notificacion);
        views.setTextViewText(R.id.titulo, title);
        views.setTextViewText(R.id.mensaje, message);
        views.setImageViewResource(R.id.icono, R.drawable.vet);
        return views;
    }

    /*public void mostrarNotificacion(String title, String message){
        Intent intent = new Intent(this,LoginActivity.class);
        String channel_id = "web_app_channel";
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channel_id)
                .setSmallIcon(R.drawable.vet)
                .setSound(uri)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN){
            builder = builder.setContent(getCustomDesign(title, message));
        }else {
            builder = builder.setContentTitle(title).setContentText(message).setSmallIcon(R.drawable.vet);
        }
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(channel_id,"web_app",NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(uri,null);
            manager.createNotificationChannel(notificationChannel);
        }
        manager.notify(0, builder.build());

    }*/
    public void mostrarNotificacion(String title, String message){

        Intent intent = new Intent(this,InicioActivity.class);
        String channel_id = "web_app_channel";
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channel_id)
                .setSmallIcon(R.drawable.vet)
                .setSound(uri)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            builder = builder.setContent(getCustomDesign(title, message));

        }else{
            builder = builder.setContentTitle(title).setContentText(message).setSmallIcon(R.drawable.vet);

        }

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(channel_id,"web_app", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(uri,null);
            manager.createNotificationChannel(notificationChannel);
        }

        manager.notify(0, builder.build());

    }
}
