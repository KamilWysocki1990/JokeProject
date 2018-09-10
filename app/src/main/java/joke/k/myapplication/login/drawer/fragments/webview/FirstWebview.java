package joke.k.myapplication.login.drawer.fragments.webview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import joke.k.myapplication.R;

public class FirstWebview extends Fragment {
    @BindView(R.id.wv9gag)
    WebView wv9gag;
    public FirstWebview() {
    }

    WebClient webClient = new WebClient();
    String url = " https://9gag.com/";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_drawer, container, false);
        ButterKnife.bind(this,view);
        webClient.callWebClient(wv9gag,url);
        return view;
    }


}
