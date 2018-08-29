package joke.k.myapplication.login.login;

import android.content.Context;

import joke.k.myapplication.R;
import joke.k.myapplication.login.data.PrefsManager;

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View view;
private PrefsManager prefsManager;


    public LoginPresenter(LoginContract.View view, PrefsManager prefsManager) {
        this.view = view;
        this.prefsManager=prefsManager;
    }

    @Override
    public boolean sendDataToCheck(String login, String Password, Context context) {
        boolean isCorrect;
        String passwordFromData= prefsManager.getPasswordByLogin(login, context);
        if (passwordFromData.contentEquals(Password)){
            isCorrect = true;
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
