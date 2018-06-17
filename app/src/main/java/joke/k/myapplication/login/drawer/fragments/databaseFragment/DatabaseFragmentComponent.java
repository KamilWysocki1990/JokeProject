package joke.k.myapplication.login.drawer.fragments.databaseFragment;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = {DatabaseFragmentModule.class})
public interface DatabaseFragmentComponent {
    void inject(DatabaseFragment databaseFragment);
}
