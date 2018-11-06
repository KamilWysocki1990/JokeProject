package joke.k.myapplication.login.data;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import joke.k.myapplication.login.data.dao.JokesDaoInterface;
import joke.k.myapplication.login.data.network.Api;
import joke.k.myapplication.login.data.prefs.PrefsManagerInterface;

public class AppDataManager implements DataManager {

    private PrefsManagerInterface prefsManager;
    private JokesDaoInterface jokesDao;

    @Inject
    Api api;


    @Inject
    public AppDataManager(PrefsManagerInterface prefsManagerInterface, JokesDaoInterface jokesDao)
    {
        prefsManager=prefsManagerInterface;
        this.jokesDao = jokesDao;
    }




    @Override
    public String getPasswordByLogin(String login, Context context) {
        return prefsManager.getPasswordByLogin(login, context);
    }

    @Override
    public void setCurrentUserName(String login) {
        prefsManager.setCurrentUserName(login);
    }

    @Override
    public void saveSignInInformation(String login, String password, String city) {
        prefsManager.saveSignInInformation(login,password,city);
    }

    @Override
    public boolean validateCreateAccount(String login, Context context) {
        return prefsManager.validateCreateAccount(login,context);
    }

    @Override
    public String getCurrentUserNameFromPrefs() {
        return prefsManager.getCurrentUserNameFromPrefs();
    }

    @Override
    public String validateFirstLogIn(Context context, String login) {
        return prefsManager.validateFirstLogIn(context,login);
    }

    @Override
    public void setFirstLogIn(String firstLogIn) {
        prefsManager.setFirstLogIn(firstLogIn);
    }

    @Override
    public void changeFirstLogIn(Context context) {
        prefsManager.changeFirstLogIn(context);
    }

    @Override
    public String getLoginName() {
        return prefsManager.getLoginName();
    }

    @Override
    public void setJokeForNotification(String askJoke, String bodyJoke) {
        prefsManager.setJokeForNotification(askJoke, bodyJoke);
    }

    @Override
    public String getJokeForNotification() {
        return prefsManager.getJokeForNotification();
    }

    @Override
    public void insert(RandomJokes randomJokes) {
        jokesDao.insert(randomJokes);
    }

    @Override
    public void delete(RandomJokes randomJokes) {
        jokesDao.delete(randomJokes);
    }

    @Override
    public RandomJokes getJokeById(int jokeId) {
        return jokesDao.getJokeById(jokeId);
    }

    @Override
    public List<RandomJokes> getJokeByAccount(String account) {
       return jokesDao.getJokeByAccount(account);
    }


    @Override
    public Flowable<RandomJokes> getJokes() {
        return api.getJokes();
    }
}
