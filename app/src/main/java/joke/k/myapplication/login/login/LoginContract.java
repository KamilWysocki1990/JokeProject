package joke.k.myapplication.login.login;
public interface LoginContract {

    interface View{

        void clearErrors();

        void showEmailError(int id_error_message);

        void showPasswordError(int id_login_error_password);

        void logIn();

    }

    interface Presenter{

        void onLoginButtonClick(String email, String password);
    }
}
