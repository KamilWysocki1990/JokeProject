package joke.k.myapplication.login.drawer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DrawerModule {

    DrawerContract.View view;

    public DrawerModule(DrawerContract.View view) {
        this.view = view;
    }

    @Provides
    @Singleton
    DrawerContract.Presenter provideDrawerPresenter (){
     return new DrawerPresenter(view);

    }
}
