package joke.k.myapplication.login.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import javax.inject.Inject;

import joke.k.myapplication.login.ApplicationScope;

public class PrefsManager implements PrefsManagerInterface {

    private String holdActualAskJoke;
    private String holdActualBodyJoke;

    private String firstLogIn = "Yes";
    private String currentUserName;

    public String getCurrentUserName() {
        return currentUserName;
    }


    private SharedPreferences sharedPreferences;
    public static final String PREFS_FILE_NAME = "joke.k.myapplication.PREFS_FILE";
    private static final String valueForEmpty = "0";


    public PrefsManager(Context context) {
        sharedPreferences = context.getSharedPreferences(
                PREFS_FILE_NAME, Context.MODE_PRIVATE);
    }



    public String attachDataToLogin(String password, String city) {
        String dataToSave = firstLogIn + ":" + password + ":" + city;
        return dataToSave;
    }


    public String getDataFromLogin(String savedData) {
        String[] dataArray = savedData.split(":", -2);
        String retrivedPassword = dataArray[1];
        return retrivedPassword;
    }



    public String[] getFirstLogInStatusFromLogin(String savedData) {
        String[] dataArray = savedData.split(":", -2);
        return dataArray;
    }



    public String checkFirstLogInFromLogin(String savedData) {
        String[] dataArray = savedData.split(":", -2);
        String checkedCondition = dataArray[0];
        return checkedCondition;
    }




    public static String encrypt(String input) {
        // This is base64 encoding, which is not an encryption
        return Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
    }

    public static String decrypt(String input) {
        return new String(Base64.decode(input, Base64.DEFAULT));
    }


    @Override
    public String getPasswordByLogin(String login, Context context) {
        String password;
        String passwordFromData="0";
/// TODO: 16.09.2018 move logic to loginPresenter from prefs
        sharedPreferences = context.getApplicationContext().getSharedPreferences(login, Context.MODE_PRIVATE);
        String dataByLogin = sharedPreferences.getString(login, "0");
        if(!dataByLogin.contentEquals("0"))
            passwordFromData = getDataFromLogin(dataByLogin);
        if (passwordFromData.contentEquals("0")) {
            password = "INC";
        } else {
            password = passwordFromData;
        }

        return password;
    }

    @Override
    public void setCurrentUserName(String login) {
        currentUserName = login;
    }

    @Override
    public void saveSignInInformation(String login, String password, String city) {
        sharedPreferences
                .edit()
                .putString(login, attachDataToLogin(password, city))
                .apply();
    }

    @Override
    public boolean validateCreateAccount(String login, Context context) {
// TODO: 16.09.2018 move logic to loginPresenter

        sharedPreferences = context.getApplicationContext().getSharedPreferences(login, Context.MODE_PRIVATE);
        boolean possibilityToCreateAccount;
        String validate = sharedPreferences.getString(login, "0");
        if (validate.contentEquals("0")) {
            possibilityToCreateAccount = true;

        } else {
            possibilityToCreateAccount = false;
        }
        return possibilityToCreateAccount;
    }

    @Override
    public String getCurrentUserNameFromPrefs() {
        return currentUserName;
    }

    @Override
    public String validateFirstLogIn(Context context, String login) {
        sharedPreferences = context.getApplicationContext().getSharedPreferences(login, Context.MODE_PRIVATE);
        String dataByLogin = sharedPreferences.getString(login, "0");
        String firstLogIn = checkFirstLogInFromLogin(dataByLogin);

        return firstLogIn;
    }

    @Override
    public void setFirstLogIn(String firstLogIn) {
        this.firstLogIn = firstLogIn;
    }

    @Override
    public void changeFirstLogIn(Context context) {

        String changedData;
        sharedPreferences = context.getApplicationContext().getSharedPreferences(getCurrentUserName(), Context.MODE_PRIVATE);
        String dataByLogin = sharedPreferences.getString(getCurrentUserName(), "0");
        String dataToChange[] = getFirstLogInStatusFromLogin(dataByLogin);
        dataToChange[0]="No";
        changedData = dataToChange[0]+":"+dataToChange[1]+":"+dataToChange[2];

        sharedPreferences
                .edit()
                .putString(getCurrentUserName(),changedData)
                .apply();


    }

    @Override
    public String getLoginName() {
        return currentUserName;
    }

    @Override
    public void setJokeForNotification(String askJoke, String bodyJoke) {
       holdActualAskJoke=askJoke;
       holdActualBodyJoke=bodyJoke;
    }

    @Override
    public String getJokeForNotification() {
        return holdActualAskJoke+"\n"+holdActualBodyJoke;
    }


}






