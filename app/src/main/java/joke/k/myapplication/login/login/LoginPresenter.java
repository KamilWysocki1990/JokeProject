package joke.k.myapplication.login.login;

import android.content.Context;

import joke.k.myapplication.R;
import joke.k.myapplication.login.data.AppDataManager;
import joke.k.myapplication.login.data.DataManager;
import joke.k.myapplication.login.data.prefs.PrefsManager;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
private PrefsManager prefsManager;
private DataManager dataManager;


    public LoginPresenter(LoginContract.View view, PrefsManager prefsManager, DataManager dataManager) {
        this.view = view;
        this.prefsManager=prefsManager;
        this.dataManager=dataManager;
    }



    @Override
    public boolean sendDataToCheck(String login, String password, Context context) {
        boolean isCorrect;
           String passwordFromData=dataManager.getPasswordByLoginn(login,context);
        if (passwordFromData.contentEquals(password)){
            isCorrect = true;
            prefsManager.setCurrentUserName(login);
        } else if(passwordFromData.contentEquals("INC")){
            view.errorLoginFromSavedData();
            isCorrect = false;
        } else{
            isCorrect=false;
            view.errorPasswordFromData();
        }
 return isCorrect;

    }

    @Override
    public void onLoginButtonClick(String email, String password, Context context) {
        view.clearErrors();
        if (!validate(email)) {
            view.showEmailError(R.string.login_error_email);
        } else if (!validate(password)) {
            view.showPasswordError(R.string.login_error_password);
        } else  {
            view.verificationWithSavedUsersData(email,password,context);
        }
    }

    @Override
    public void onSignInButtonClick(String login, String password, String city, Context context) {
       boolean isNumberOfCharLegit = true;
        if(!validate(login)){
            isNumberOfCharLegit = false;
            view.showLoginErrorInSignIn();
        }

        if(!validate(password)){
            isNumberOfCharLegit = false;
            view.showPasswordErrorInSignIn();

        }
        if (isNumberOfCharLegit) {
        boolean isSignInInformationPassable = prefsManager.validateCreateAccount(login, context);
        if(isSignInInformationPassable){

                prefsManager.saveSignInInformation(login, password, city);
                prefsManager.setCurrentUserName(login);
                view.sendConfirmRespondForSignIn();
            }
        }
        else {
            if(isNumberOfCharLegit)
            view.sendDenyRespondForSignIn();
        }



    }

    private boolean validate(String input) {
        return input.length() >= 5;
    }







}
