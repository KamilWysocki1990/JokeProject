package joke.k.myapplication.login.data.prefs;

import android.content.Context;

public interface PrefsManagerInterface {

    String getPasswordByLoginn(String login,Context context);
    void setCurrentUserNamee(String login);
    void saveSignInInformationn(String login,String  password, String  city);
    boolean validateCreateAccountt(String login,Context context);
    String getCurrentUserNamee();
    String validateFirstLogInn(Context context,String login);
     void setFirstLogInn(String firstLogIn);
    void changeFirstLogInn(Context context);
}

