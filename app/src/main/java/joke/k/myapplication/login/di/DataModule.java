package joke.k.myapplication.login.di;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;
import joke.k.myapplication.login.ApplicationScope;
import joke.k.myapplication.login.api.Api;
import joke.k.myapplication.login.dao.JokesDao;
import joke.k.myapplication.login.data.PrefsManager;
import joke.k.myapplication.login.room.JokesDatabase;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class DataModule {

    @Provides
    @ApplicationScope
    JokesDatabase provideJokesDatabase(Context context) {
        return  Room.databaseBuilder(context,JokesDatabase.class,JokesDatabase.NAME)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @ApplicationScope
    PrefsManager providePrefsManager(Context context) {
        return new PrefsManager(context);
    }




    @Provides
    @ApplicationScope
    Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    @ApplicationScope
    Api provideApi(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }


    @Provides
    @ApplicationScope
    JokesDao provideJokesDao(JokesDatabase jokesDatabase) {
        return jokesDatabase.jokesDao();
    }





}
