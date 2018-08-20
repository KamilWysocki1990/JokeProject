package joke.k.myapplication.login.drawer;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import joke.k.myapplication.R;



public class NotificationAlarmService extends IntentService {

    public static final String CHANNEL_ID = "JokeID";
    public static final String NOTIFICATION_CHANNEL_ID = "Channel Joke ID";
    public static final String NOTIFICATION_CHANNEL_NAME = "Channel";
    public static final int NOTIFICATION_ID = 123;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NotificationAlarmService(String name) {
        super(name);
    }

    public NotificationAlarmService() {
        this("NotificationAlarmService");
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {


       final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_mood_black_24dp)
                    .setContentTitle("Smile Remider !")
                    .setContentText("Much longer text that cannot fit one line...")
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText("Much longer text that cannot fit one line..."))
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(NOTIFICATION_ID,mBuilder.build());


        }

    public static PendingIntent getReminderPendingIntent(Context context ) {

        Intent action = new Intent(context , NotificationAlarmService.class);
     //  action.setData(uri);
        return PendingIntent.getService(context, 0, action, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    }


