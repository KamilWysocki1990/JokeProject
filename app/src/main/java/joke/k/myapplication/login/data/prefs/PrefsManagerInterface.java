package joke.k.myapplication.login.data.prefs;

import android.content.Context;

public interface PrefsManagerInterface {

    String getPasswordByLogin(String login, Context context);
    void setCurrentUserName(String login);
    void saveSignInInformation(String login, String  password, String  city);
    boolean validateCreateAccount(String login, Context context);
    String getCurrentUserNameFromPrefs();
    String validateFirstLogIn(Context context, String login);
    void setFirstLogIn(String firstLogIn);
    void changeFirstLogIn(Context context);
    String getLoginName();
    void setJokeForNotification(String askJoke, String bodyJoke);
    String getJokeForNotification();
}

