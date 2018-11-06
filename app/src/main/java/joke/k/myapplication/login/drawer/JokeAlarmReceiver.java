package joke.k.myapplication.login.drawer;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import joke.k.myapplication.R;
import joke.k.myapplication.login.JokeApplication;
import joke.k.myapplication.login.data.DataManager;
import joke.k.myapplication.login.data.RandomJokes;


public class JokeAlarmReceiver extends BroadcastReceiver {

    CompositeDisposable compositeDisposable = new CompositeDisposable();



    public static final String NOTIFICATION_CHANNEL_ID = "Channel Joke ID";
    public static final String NOTIFICATION_CHANNEL_NAME = "Channel";
    public static final int NOTIFICATION_ID = 123;

    @Inject
    DataManager dataManager;


    @Override
    public void onReceive(Context context, Intent intent) {
        ((JokeApplication)context.getApplicationContext()).getAppComponent()
                .plus(new JokeAlarmReceiverModule(context))
                .inject(this);


        getJokesToNotification(context);

    }

    public void createNotification(Context context) {

            final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_mood_black_24dp)
                    .setContentTitle("Smile Reminder :)")
                    .setContentText(dataManager.getJokeForNotification())
                    .setStyle(new NotificationCompat.BigTextStyle())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setAutoCancel(true);


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(
                        NOTIFICATION_CHANNEL_ID,
                        NOTIFICATION_CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT);

                NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                if (notificationManager != null) {
                    notificationManager.createNotificationChannel(channel);
                }
            }

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.notify(NOTIFICATION_ID, mBuilder.build());


        }



    public void getJokesToNotification(Context context){

        compositeDisposable.add(dataManager.getJokes()
               .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        randomJokes -> {
                            // onNext
                                dataManager.setJokeForNotification(randomJokes.getSetup(),randomJokes.getPunchline());
                            Log.d("WORKING","1st");
                            },
                        throwable -> {
                            // onError
                        },
                        () -> {
                            createNotification(context);

                            // onCompleted
                        })
        );
    }







    public static Intent getReminderIntent(Context context ) {

        Intent action = new Intent(context,JokeAlarmReceiver.class);
        return action;
    }

    public static PendingIntent getReminderPendingIntent(Context context ) {

        Intent action = new Intent(context,JokeAlarmReceiver.class);
        return  PendingIntent.getBroadcast(context,
                100,
                JokeAlarmReceiver.getReminderIntent(context),
                PendingIntent.FLAG_UPDATE_CURRENT);
    }



}
