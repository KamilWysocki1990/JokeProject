package joke.k.myapplication.login.drawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import joke.k.myapplication.R;
import joke.k.myapplication.login.drawer.fragments.FirstFragment;
import joke.k.myapplication.login.drawer.fragments.SecondFragment;
import joke.k.myapplication.login.drawer.fragments.ThirdFragment;

public class DrawerActivity extends AppCompatActivity{

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.drawer_navigation)
    NavigationView navigationView;

    @BindView(R.id.drawer_toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            getSupportActionBar().setTitle("Database Option");
        }

        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();
            showFragment(item.getItemId());
            return true;
        });

        showFragment(R.id.item_drawer_first);


    }
    private void showFragment(int itemId) {
        String fragmentTag = String.valueOf(itemId);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(fragmentTag) == null) {
            // there's no such a fragment on back stack - add it
            Fragment fragmentToAdd = null;

            switch (itemId) {
                case R.id.item_drawer_first: {
                    fragmentToAdd = new FirstFragment();
                    break;
                }
                case R.id.item_drawer_second: {
                    fragmentToAdd = new SecondFragment();
                    break;
                }
                case R.id.item_drawer_third: {
                    fragmentToAdd = new ThirdFragment();
                    break;
                }
            }

            if (fragmentToAdd != null) {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.drawer_container, fragmentToAdd, fragmentTag)
                        .addToBackStack(fragmentTag)
                        .commit();
            }
        } else {
            // pop this fragment from back stack
            fragmentManager.popBackStackImmediate(fragmentTag, 0);
        }
    }
}
