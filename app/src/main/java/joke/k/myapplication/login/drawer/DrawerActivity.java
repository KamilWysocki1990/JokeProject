package joke.k.myapplication.login.drawer;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import butterknife.OnClick;
import joke.k.myapplication.R;
import joke.k.myapplication.login.JokeApplication;
import joke.k.myapplication.login.drawer.fragments.cameraShare.CameraShare;
import joke.k.myapplication.login.drawer.fragments.webview.FirstWebview;
import joke.k.myapplication.login.drawer.fragments.webview.ThirdFragment;
import joke.k.myapplication.login.drawer.fragments.TimePickerFragment;
import joke.k.myapplication.login.drawer.fragments.databaseFragment.DatabaseFragment;
import joke.k.myapplication.login.drawer.fragments.jokeFragment.JokesFragment;
import joke.k.myapplication.login.drawer.fragments.webview.WebViewJoeMonster;
import joke.k.myapplication.login.login.LoginActivity;

public class DrawerActivity extends AppCompatActivity implements DrawerContract.View ,TimePickerFragment.TimeSetListenerForParentActivity,JokesFragment.CommunicationWithDrawerActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.drawer_navigation)
    NavigationView navigationView;

    @BindView(R.id.drawer_toolbar)
    Toolbar toolbar;

    @Inject
    DrawerContract.Presenter presenter;


    FragmentManager fragmentManager = getSupportFragmentManager();
    boolean isCancelButtonClicked=false;
    String actualJoke="Nothing to share right now";


    public DrawerActivity() {
    }





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        ButterKnife.bind(this);
        ((JokeApplication) getApplication()).getAppComponent()
                .plus(new DrawerModule(this))
                .inject(this);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            getSupportActionBar().setTitle("Jokes Application");
        }
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();
            showFragment(item.getItemId());
            return true;
        });

        showFragment(R.id.item_drawer_joke_fragment);

        presenter.validateFirstLogIn(getBaseContext());

        }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(Gravity.START);
            return true;
        }
        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawers();
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStackImmediate();
        } else {
            finish();
        }
    }


    private void showFragment(int itemId) {
        String fragmentTag = String.valueOf(itemId);
        if(isCancelButtonClicked){
            fragmentTag = null;
        }
        if (fragmentManager.findFragmentByTag(fragmentTag) == null) {
            // there's no such a fragment on back stack - add it
            Fragment fragmentToAdd = null;

            switch (itemId) {
                case R.id.item_drawer_joke_fragment: {
                    fragmentToAdd = new JokesFragment();
                    break;
                }
                case R.id.item_drawer_database: {
                    fragmentToAdd = new DatabaseFragment();
                    break;
                }
                case R.id.item_drawer_third: {
                    fragmentToAdd = new ThirdFragment();
                    break;
                }


                case R.id.item_camera_share: {
                    fragmentToAdd = new CameraShare();
                    break;
                }

                case R.id.item_drawer_webview1: {
                    fragmentToAdd = new FirstWebview();
                    break;
                }

                case R.id.item_drawer_webview_joemonster: {
                    fragmentToAdd = new WebViewJoeMonster();
                    break;
                }


            }

            if (fragmentToAdd != null) {
                if(fragmentToAdd instanceof JokesFragment)
                {
                    setDataOnJokeFragment(fragmentToAdd);

                }
                if(!isCancelButtonClicked) {
                    fragmentManager
                            .beginTransaction()
                            .replace(R.id.drawer_container, fragmentToAdd, fragmentTag)
                            .addToBackStack(fragmentTag)
                            .commit();
                } else{
                    removeJokeFragment(fragmentToAdd);
                    recreateJokeFragment(fragmentTag, fragmentToAdd);
                }
            }


        } else {
            // pop this fragment from back stack
            fragmentManager.popBackStackImmediate(fragmentTag, 0);
        }
    }

    private void recreateJokeFragment(String fragmentTag, Fragment fragmentToAdd) {
        fragmentManager.beginTransaction()
                .add(R.id.drawer_container,fragmentToAdd,fragmentTag)
                .commit();
        isCancelButtonClicked=false;
    }

    private void removeJokeFragment(Fragment fragmentToAdd) {
        fragmentManager.beginTransaction()
                .remove(fragmentToAdd)
                .commit();
    }

    private void setDataOnJokeFragment(Fragment fragmentJoke) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("CancelClicked",isCancelButtonClicked);
        fragmentJoke.setArguments(bundle);

    }

    @OnClick(R.id.shareButton)
    public void shareText () {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, actualJoke);
        startActivity(Intent.createChooser(intent, "Choose your sharing method"));
    }

    @OnClick(R.id.logOutButton)
    public void logOut () {
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        finishAffinity();
        startActivity(intent);
        Toast.makeText(this,"You were successfully logged out",Toast.LENGTH_LONG).show();
    }

    @Override
    public void providingTimeFromTimePicker(int hour, int minute) {
        presenter.customTimeNotification(hour, minute);


    }

    @Override
    public void cancelSignal() {
        isCancelButtonClicked = true;
        showFragment(R.id.item_drawer_joke_fragment);

        }






    @Override
    public void showCustomTime(String time) {
        Toast.makeText(this, getString(R.string.set_notification_time) + " " + time, Toast.LENGTH_LONG).show();
    }


    @Override
    public void createCustomNotification(int hour, int minute) {
        AlarmScheduler.scheduleAlarm(this, hour, minute);
    }

    @Override
    public void shouldDialogBeDisplayed() {
        createInfoDialog();
    }


    public void createInfoDialog() {
        DialogFragment alertDialog = new AlertDialogFirstLogInInfo();
        alertDialog.show(getSupportFragmentManager(),"AlertDialog");
    }

    @Override
    public void sendActualJokeToShare(String askJoke, String askBody) {
        actualJoke=askJoke+"\n"+askBody;
    }



    public static class AlertDialogFirstLogInInfo extends DialogFragment {


        public AlertDialogFirstLogInInfo() {
        }

        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage(
                    "Welcome !\n\n"+
                    "Try swap from right to left to save joke if u like it:)\n" +
                    "Or swap from left to right to get a new one\n" +"\n"+
                    "Hope u will enjoy it ! :D ")
                    .setPositiveButton("Got It !", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

            // Create the AlertDialog object and return it

            AlertDialog alertDialog = builder.create();
            return alertDialog;
        }
    }

}

