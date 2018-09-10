package joke.k.myapplication.login.drawer;

import android.content.Context;

public interface DrawerContract {


    interface View{

            void showCustomTime(String time);
            void createCustomNotification(int hour, int minute);

        void shouldDialogBeDisplayed();
    }

    interface Presenter {


        void customTimeNotification(int hour, int minute);
        void validateFirstLogIn(Context context);

    }
}
