package joke.k.myapplication.login.drawer.fragments.jokeFragment;

import javax.inject.Singleton;

import dagger.Subcomponent;

@Singleton
@Subcomponent (modules = {JokesModule.class})
public interface JokesComponent {
    void inject (JokesActivity jokesActivity);
}
