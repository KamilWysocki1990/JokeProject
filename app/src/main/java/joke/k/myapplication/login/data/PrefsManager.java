package joke.k.myapplication.login.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

public class PrefsManager {


    public void setFirstLogIn(String firstLogIn) {
        this.firstLogIn = firstLogIn;
    }

    public String getFirstLogIn() {
        return firstLogIn;
    }

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

    public void saveSignInInformation(String login, String password, String city) {

        sharedPreferences
                .edit()
                .putString(login, attachDataToLogin(password, city))
                .apply();
    }

    public boolean validateCreateAccount(String login, Context context) {
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

    public String getPasswordByLogin(String login, Context context) {
        String password;
        String passwordFromData="0";

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

    public String validateFirstLogIn(Context context, String login) {


        sharedPreferences = context.getApplicationContext().getSharedPreferences(login, Context.MODE_PRIVATE);
        String dataByLogin = sharedPreferences.getString(login, "0");
        String firstLogIn = checkFirstLogInFromLogin(dataByLogin);

        return firstLogIn;

    }


    public static String encrypt(String input) {
        // This is base64 encoding, which is not an encryption
        return Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
    }

    public static String decrypt(String input) {
        return new String(Base64.decode(input, Base64.DEFAULT));
    }

    public String getLoginName() {
        return currentUserName;
    }

    public void setCurrentUserName(String name) {
        currentUserName = name;
    }

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
}






