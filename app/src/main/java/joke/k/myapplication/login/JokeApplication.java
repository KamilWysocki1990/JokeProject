package joke.k.myapplication.login;

import android.app.Application;

import joke.k.myapplication.login.di.AppComponent;
import joke.k.myapplication.login.di.AppModule;
import joke.k.myapplication.login.di.DaggerAppComponent;
import joke.k.myapplication.login.di.DataModule;

public class JokeApplication extends Application {

    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();


        // Dagger 2
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .build();





    }
}
