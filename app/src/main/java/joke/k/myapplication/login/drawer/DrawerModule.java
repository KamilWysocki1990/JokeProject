package joke.k.myapplication.login.drawer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import joke.k.myapplication.login.data.PrefsManager;

@Module
public class DrawerModule {

    DrawerContract.View view;

    public DrawerModule(DrawerContract.View view) {
        this.view = view;
    }

    @Provides
    @Singleton
    DrawerContract.Presenter provideDrawerPresenter (PrefsManager prefsManager){
     return new DrawerPresenter(view,prefsManager);

    }
}
