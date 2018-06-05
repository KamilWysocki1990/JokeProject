package joke.k.myapplication.login.login;

import javax.inject.Singleton;

import dagger.Subcomponent;


@Singleton
@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent {

    void inject(LoginActivity loginActivity);
}


