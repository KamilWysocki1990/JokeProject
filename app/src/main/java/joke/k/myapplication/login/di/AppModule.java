package joke.k.myapplication.login.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import joke.k.myapplication.login.ApplicationScope;

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
}
