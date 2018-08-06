package joke.k.myapplication.login.drawer;

public interface DrawerContract {


    interface View{

            void showCustomTime(String time);
    }

    interface Presenter {


        void customTimeNotification(int hour, int minute);


    }
}
