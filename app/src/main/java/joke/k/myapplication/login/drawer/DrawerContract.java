package joke.k.myapplication.login.drawer;

public interface DrawerContract {


    interface View{

            void showCustomTime(String time);
            void createCustomNotification(int hour, int minute);
    }

    interface Presenter {


        void customTimeNotification(int hour, int minute);


    }
}
