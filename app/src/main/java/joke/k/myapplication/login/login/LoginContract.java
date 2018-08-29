package joke.k.myapplication.login.login;

import android.content.Context;

public interface LoginContract {

    interface View{

        void clearErrors();

        void showEmailError(int id_error_message);
        void showPasswordError(int id_login_error_password);
        void verificationWithSavedUsersData(String login, String password, Context context);
        void sendDenyRespondForSignIn();
        void sendConfirmRespondForSignIn();
        void errorLoginFromSavedData();
        void errorPasswordFromData();
        void showLoginErrorInSignIn();

        void showPasswordErrorInSignIn();
    }

    interface Presenter{
        boolean sendDataToCheck (String login, String Password, Context context);
        void onLoginButtonClick(String email, String password, Context context);
        void onSignInButtonClick (String login, String password, String city, Context context);

    }
}
