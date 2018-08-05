package joke.k.myapplication.login.drawer;

public class DrawerPresenter implements DrawerContract.Presenter {

    private DrawerContract.View view;

    public DrawerPresenter(DrawerContract.View view) {
        this.view = view;
    }
}
