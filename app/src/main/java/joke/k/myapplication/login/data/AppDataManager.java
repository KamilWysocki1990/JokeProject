package joke.k.myapplication.login.data;

import android.content.Context;

import javax.inject.Inject;

import joke.k.myapplication.login.data.prefs.PrefsManagerInterface;

public class AppDataManager implements DataManager {

    PrefsManagerInterface prefsManager;

    @Inject
    public AppDataManager(PrefsManagerInterface prefsManagerInterface)
    {
        prefsManager=prefsManagerInterface;
    }


    @Override
    public void sendDataToSave() {

    }

    @Override
    public String getPasswordByLoginn(String login, Context context) {
        return prefsManager.getPasswordByLoginn(login, context);
    }
}
