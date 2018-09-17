package joke.k.myapplication.login.data;

import android.content.Context;

import javax.inject.Inject;

import joke.k.myapplication.login.data.dao.JokesDao;
import joke.k.myapplication.login.data.dao.JokesDaoInterface;
import joke.k.myapplication.login.data.prefs.PrefsManagerInterface;

public class AppDataManager implements DataManager {

    private PrefsManagerInterface prefsManager;
    private JokesDaoInterface jokesDao;


    @Inject
    public AppDataManager(PrefsManagerInterface prefsManagerInterface, JokesDaoInterface jokesDao)
    {
        prefsManager=prefsManagerInterface;
        this.jokesDao = jokesDao;
    }


    @Override
    public void sendDataToSave() {

    }

    @Override
    public String getPasswordByLoginn(String login, Context context) {
        return prefsManager.getPasswordByLoginn(login, context);
    }

    @Override
    public void setCurrentUserNamee(String login) {
        prefsManager.setCurrentUserNamee(login);
    }

    @Override
    public void saveSignInInformationn(String login, String password, String city) {
        prefsManager.saveSignInInformationn(login,password,city);
    }

    @Override
    public boolean validateCreateAccountt(String login, Context context) {
        return prefsManager.validateCreateAccountt(login,context);
    }

    @Override
    public String getCurrentUserNamee() {
        return prefsManager.getCurrentUserNamee();
    }

    @Override
    public String validateFirstLogInn(Context context, String login) {
        return prefsManager.validateFirstLogInn(context,login);
    }

    @Override
    public void setFirstLogInn(String firstLogIn) {
        prefsManager.setFirstLogInn(firstLogIn);
    }

    @Override
    public void changeFirstLogInn(Context context) {
        prefsManager.changeFirstLogInn(context);
    }

    @Override
    public String getLoginNamee() {
        return prefsManager.getLoginNamee();
    }

    @Override
    public void insert(RandomJokes randomJokes) {
        jokesDao.insert(randomJokes);
    }
}
