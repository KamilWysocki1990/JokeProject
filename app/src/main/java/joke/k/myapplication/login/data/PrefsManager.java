package joke.k.myapplication.login.data;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsManager {

    private SharedPreferences sharedPreferences;
    public static final String PREFS_FILE_NAME = "joke.k.myapplication.PREFS_FILE";
    private static final String valueForEmpty = "0" ;


    public PrefsManager(Context context) {
        sharedPreferences = context.getSharedPreferences(
                PREFS_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void saveSignInInformation(String login, String password, String city ){

        sharedPreferences
                .edit()
                .putString(login,attachDataToLogin(password,city))
                .apply();
    }

    public boolean validateCreateAccount(String login, Context context){
        sharedPreferences = context.getApplicationContext().getSharedPreferences(login, Context.MODE_PRIVATE);
        boolean possibilityToCreateAccount;
        String validate = sharedPreferences.getString(login,"0");
        if(validate.contentEquals("0")){
            possibilityToCreateAccount= true;

        }
            else {
            possibilityToCreateAccount = false;
        } return possibilityToCreateAccount;
        }

        public String getPasswordByLogin(String login, Context context)
        {
            String password;
            sharedPreferences = context.getApplicationContext().getSharedPreferences(login, Context.MODE_PRIVATE);
            String dataByLogin = sharedPreferences.getString(login,"0");
            String passwordFromData=getDataFromLogin(dataByLogin);
            if(passwordFromData.contentEquals("0")){
                password = "INC";
            } else {
                password = passwordFromData;
            }

                return password;
        }

        public String attachDataToLogin(String password, String city){
            String dataToSave=password+":"+city;
        return dataToSave;
        }

    public String getDataFromLogin(String savedData){
        String[] dataArray = savedData.split(":",-2);
        String retrivedPassword= dataArray[0];
        return retrivedPassword;
    }

    }




