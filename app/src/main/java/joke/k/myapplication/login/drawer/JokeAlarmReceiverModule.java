package joke.k.myapplication.login.drawer;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import joke.k.myapplication.login.data.DataManager;
import joke.k.myapplication.login.data.RandomJokes;
import joke.k.myapplication.login.data.prefs.PrefsManager;

@Module
public class JokeAlarmReceiverModule {

    Context context;

    public JokeAlarmReceiverModule(Context context) {
        this.context = context;
    }
    @Provides
    @Singleton

    JokeAlarmReceiver provideJokeAlarmReceiver(DataManager dataManager){
        return (JokeAlarmReceiver) dataManager;
    }




}