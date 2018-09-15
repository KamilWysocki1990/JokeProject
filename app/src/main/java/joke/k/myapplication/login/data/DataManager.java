package joke.k.myapplication.login.data;

import joke.k.myapplication.login.data.prefs.PrefsManagerInterface;

public interface DataManager extends PrefsManagerInterface{

    public void sendDataToSave();
}
