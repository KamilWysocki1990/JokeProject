package joke.k.myapplication.login.drawer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import joke.k.myapplication.login.data.DataManager;
import joke.k.myapplication.login.data.prefs.PrefsManager;

@Module
public class DrawerModule {

    DrawerContract.View view;

    public DrawerModule(DrawerContract.View view) {
        this.view = view;
    }

    @Provides
    @Singleton
    DrawerContract.Presenter provideDrawerPresenter (DataManager dataManager){
     return new DrawerPresenter(view,dataManager);

    }
}
