package joke.k.myapplication.login.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import joke.k.myapplication.R;
import joke.k.myapplication.login.JokeApplication;
import joke.k.myapplication.login.drawer.DrawerActivity;
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
                loginPassword.getEditText().getText().toString(),
                getBaseContext()
        );
    }

    @OnClick(R.id.signIn_button)
    public void onSignInButtonClick() {
    startSignInDialog();
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
    public void verificationWithSavedUsersData(String login, String password, Context context) {
       boolean verificationComplete;
       verificationComplete = presenter.sendDataToCheck(login,password,context);
       if(verificationComplete){
           startActivityDrawer();
       }
    }

    @Override
    public void sendDenyRespondForSignIn() {
        Toast.makeText(this, "This Login is already taken. Choose different", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void sendConfirmRespondForSignIn() {
        Toast.makeText(this, "Sign In Successful ! :)", Toast.LENGTH_SHORT).show();
        startActivityDrawer();
    }

    @Override
    public void errorLoginFromSavedData() {
        Toast.makeText(this,"Wrong login, try again with correct one ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void errorPasswordFromData() {
        Toast.makeText(this,"Wrong password, try again with correct one ",Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoginErrorInSignIn() {
        Toast.makeText(this,R.string.login_error_email,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showPasswordErrorInSignIn() {
        Toast.makeText(this,R.string.login_error_password,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void clearErrors() {
        loginEmail.setError(null);
        loginPassword.setError(null);
    }

    private void startActivityDrawer(){
        Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
        startActivity(intent);
    }

    private void startSignInDialog(){

        DialogViewHolder dialogViewHolder = new DialogViewHolder();
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.sign_in_dialog,null);
        ButterKnife.bind(dialogViewHolder,dialogView);
        dialogBuilder.setView(dialogView);



        dialogBuilder.setTitle("Sign In ");
        dialogBuilder.setMessage("Enter needed information");
        dialogBuilder.setPositiveButton("Sign Me In !", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

             presenter.onSignInButtonClick(
                     dialogViewHolder.editTextLoginSignIn.getText().toString(),
                     dialogViewHolder.editTextPasswordSignIn.getText().toString(),
                     dialogViewHolder.editTextCitySignIn.getText().toString(),
                     getBaseContext()
             );
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog signInDialog = dialogBuilder.create();
        signInDialog.show();


    }


}

class DialogViewHolder{



            @BindView(R.id.editTextLoginSignIn)
            EditText editTextLoginSignIn;

            @BindView(R.id.editTextPasswordSignIn)
            EditText editTextPasswordSignIn;

            @BindView(R.id.editTextCitySignIn)
            EditText editTextCitySignIn;

            @BindView(R.id.editTextPasswordConfirmationSignIn)
            EditText editTextPasswordConfirmationSignIn;

}
