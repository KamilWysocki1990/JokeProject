package joke.k.myapplication.login.data;

import io.reactivex.Flowable;
import joke.k.myapplication.login.data.dao.JokesDaoInterface;
import joke.k.myapplication.login.data.prefs.PrefsManagerInterface;

public interface DataManager extends PrefsManagerInterface,JokesDaoInterface{

    Flowable<RandomJokes> getJokes();

}
