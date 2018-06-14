package joke.k.myapplication.login;

import android.app.Application;
import android.arch.persistence.room.Room;

import joke.k.myapplication.login.di.AppComponent;
import joke.k.myapplication.login.di.AppModule;
import joke.k.myapplication.login.di.DaggerAppComponent;
import joke.k.myapplication.login.di.DataModule;
import joke.k.myapplication.login.room.JokesDatabase;

public class JokeApplication extends Application {

    public static JokesDatabase database;
    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static JokesDatabase getRoom() {
        return database;
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

        // Room
        database = Room.databaseBuilder(this,JokesDatabase.class,JokesDatabase.NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();




    }
}
