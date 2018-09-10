package joke.k.myapplication.login.drawer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;

import java.util.Calendar;

public class AlarmScheduler {

    public static void scheduleAlarm(Context context,int alarmTimeHour, int alarmTimeMinute){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        //PendingIntent pendingIntent = PendingIntent.getService(context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        PendingIntent operation = NotificationAlarmService.getReminderPendingIntent(context);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,alarmTimeHour);
        calendar.set(Calendar.MINUTE,alarmTimeMinute);

        long alarmTime=calendar.getTimeInMillis();
      //  alarmTime = alarmTime + (AlarmManager.INTERVAL_DAY);



        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,alarmTime,AlarmManager.INTERVAL_DAY,operation);
    }

}
