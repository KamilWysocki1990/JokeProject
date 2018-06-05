package joke.k.myapplication.login.di;



import dagger.Component;
import joke.k.myapplication.login.ApplicationScope;
import joke.k.myapplication.login.login.LoginComponent;
import joke.k.myapplication.login.login.LoginModule;

@ApplicationScope
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {


    LoginComponent plus(LoginModule loginModule);

}
