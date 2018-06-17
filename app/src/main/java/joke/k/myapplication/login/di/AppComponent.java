package joke.k.myapplication.login.di;



import dagger.Component;
import joke.k.myapplication.login.ApplicationScope;
import joke.k.myapplication.login.drawer.fragments.databaseFragment.DatabaseFragmentComponent;
import joke.k.myapplication.login.drawer.fragments.databaseFragment.DatabaseFragmentModule;
import joke.k.myapplication.login.drawer.fragments.jokeFragment.JokesComponent;
import joke.k.myapplication.login.drawer.fragments.jokeFragment.JokesModule;
import joke.k.myapplication.login.login.LoginComponent;
import joke.k.myapplication.login.login.LoginModule;

@ApplicationScope
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {


    LoginComponent plus(LoginModule loginModule);
    JokesComponent plus(JokesModule jokesModule);
    DatabaseFragmentComponent plus(DatabaseFragmentModule databaseFragmentModule);

}
