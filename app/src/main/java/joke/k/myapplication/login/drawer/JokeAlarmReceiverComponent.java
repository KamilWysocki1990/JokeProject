package joke.k.myapplication.login.drawer;

import javax.inject.Singleton;

import dagger.Subcomponent;



    @Singleton
    @Subcomponent(modules = {JokeAlarmReceiverModule.class})
    public interface JokeAlarmReceiverComponent {


        void inject (JokeAlarmReceiver jokeAlarmReceiver);



    }




