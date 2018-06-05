package joke.k.myapplication.login.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import joke.k.myapplication.R;
import joke.k.myapplication.login.JokeApplication;
import joke.k.myapplication.login.joke.JokeActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {


    @BindView(R.id.login_email)
    TextInputLayout loginEmail;

    @BindView(R.id.login_password)
    TextInputLayout loginPassword;

    @Inject
    LoginContract.Presenter presenter;

    @OnClick(R.id.login_button)
    public void onButtonClick() {
        presenter.onLoginButtonClick(
                loginEmail.getEditText().getText().toString(),
                loginPassword.getEditText().getText().toString()
        );
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((JokeApplication) getApplication()).getAppComponent()
                .plus(new LoginModule(this))
                .inject(this);
        ButterKnife.bind(this);

    }

    @Override
    public void showEmailError(int id_error_message) {
        loginEmail.setError(getString(id_error_message));
    }

    @Override
    public void showPasswordError(int id_login_error_password) {
        loginPassword.setError(getString(id_login_error_password));
    }

    @Override
    public void logIn() {
        startActivityJokes();
    }

    @Override
    public void clearErrors() {
        loginEmail.setError(null);
        loginPassword.setError(null);
    }

    private void startActivityJokes(){
        Intent intent = new Intent(getApplicationContext(), JokeActivity.class);
        startActivity(intent);
    }



}
