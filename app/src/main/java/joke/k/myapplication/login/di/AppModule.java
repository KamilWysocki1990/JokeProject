package joke.k.myapplication.login.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import joke.k.myapplication.login.ApplicationScope;
import joke.k.myapplication.login.data.AppDataManager;
import joke.k.myapplication.login.data.DataManager;
import joke.k.myapplication.login.data.prefs.PrefsManager;
import joke.k.myapplication.login.data.prefs.PrefsManagerInterface;

@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    Context provideContext() {
        return context;
    }

    @Provides
    @ApplicationScope
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @ApplicationScope
    PrefsManagerInterface providePrefsManagerInterface(PrefsManager prefsManager) {
        return prefsManager;
    }


}
