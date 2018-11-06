package joke.k.myapplication.login.drawer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class AlarmScheduler {

    public static void scheduleAlarm(Context context, int alarmTimeHour, int alarmTimeMinute){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        //PendingIntent pendingIntent = PendingIntent.getService(context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        PendingIntent operation = PendingIntent.getBroadcast(context,
                100,
                JokeAlarmReceiver.getReminderIntent(context),
                PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,alarmTimeHour);
        calendar.set(Calendar.MINUTE,alarmTimeMinute);

        long alarmTime=calendar.getTimeInMillis();
      //  alarmTime = alarmTime + (AlarmManager.INTERVAL_DAY);


      //  alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,alarmTime,AlarmManager.INTERVAL_DAY,operation);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,alarmTime,2*60*1000,operation);
    }

    public static void cancelAlarm(Context context){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent cancelOperation = JokeAlarmReceiver.getReminderPendingIntent(context);
        alarmManager.cancel(cancelOperation);
    }

    public static  boolean checkAlarm(Context context){
        boolean alarmUp = (PendingIntent.getBroadcast(context, 100,
                new Intent(context,JokeAlarmReceiver.class),
                PendingIntent.FLAG_NO_CREATE) != null);

        return alarmUp;
    }

}
