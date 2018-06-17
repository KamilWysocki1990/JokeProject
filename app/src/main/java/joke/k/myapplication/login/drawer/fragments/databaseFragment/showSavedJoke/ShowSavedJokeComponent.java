package joke.k.myapplication.login.drawer.fragments.databaseFragment.showSavedJoke;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent(modules = {ShowSavedJokeModule.class})
public interface ShowSavedJokeComponent {
    void inject(ShowSavedJokeActivity showSavedJokeActivity);
}
