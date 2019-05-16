package com.example.meau;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessageService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d("NotificationTeste", remoteMessage.getData().toString());
        final String type = remoteMessage.getData().get("type");
        final String namePet = remoteMessage.getData().get("pet");
        final String nameUser = remoteMessage.getData().get("user");
        final String petUid = remoteMessage.getData().get("petUid");

        Bundle bundle = new Bundle();
        bundle.putString("UID_PET", petUid);
        Intent intent = new Intent(getApplicationContext(), PerfilAnimal.class);
        intent.putExtras(bundle);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent((int) (System.currentTimeMillis() / 1000), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "meau_notification")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setColor(getColor(R.color.colorPrimary))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent);

        if (type.equals("adopt")) {
            mBuilder.setContentTitle("Adoção")
                    .setContentText(String.format("%s quer adotar %s", nameUser, namePet));
        } else {
            mBuilder.setContentTitle("Parabéns!")
                    .setContentText(String.format("Você adotou %s de %s", namePet, nameUser));
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MessageService.this);

        Log.d("NotificationTeste", mBuilder.toString());
        notificationManager.notify((int) (System.currentTimeMillis() / 1000), mBuilder.build());

        super.onMessageReceived(remoteMessage);
    }

    @Override
    public void onNewToken(String token) {
        Usuario model = UserHelper.getUserModel(getApplicationContext());
        model.setToken(token);
        UserHelper.setUserModel(getApplicationContext(), model);
    }

}
