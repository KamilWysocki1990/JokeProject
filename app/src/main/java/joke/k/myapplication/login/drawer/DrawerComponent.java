package joke.k.myapplication.login.drawer;


import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = {DrawerModule.class})
public interface DrawerComponent {
    void inject (DrawerActivity drawerActivity);
}
