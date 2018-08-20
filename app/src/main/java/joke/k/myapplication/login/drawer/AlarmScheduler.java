package joke.k.myapplication.login.drawer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;

public class AlarmScheduler {

    public static void scheduleAlarm(Context context,long alarmTimeHour, long alarmTimeMinute){
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        //PendingIntent pendingIntent = PendingIntent.getService(context,0,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        PendingIntent operation = NotificationAlarmService.getReminderPendingIntent(context);
        long timeInMillis = (alarmTimeHour*60*60*1000)+(alarmTimeMinute*60*1000);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,timeInMillis,60*1000*60*24,operation);
    }

}
