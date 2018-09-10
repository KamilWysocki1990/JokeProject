package joke.k.myapplication.login.drawer.fragments.webview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import joke.k.myapplication.R;

public class WebViewJoeMonster extends Fragment {
    String url = "https://joemonster.org";

    @BindView(R.id.wvJoeMonster)
    WebView wvJokemonster;

    WebClient webClient = new WebClient();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer_webview_joemonter, container, false);
        ButterKnife.bind(this,view);
        wvJokemonster.loadUrl(url);
        webClient.callWebClient(wvJokemonster,url);
        return view;
    }

}