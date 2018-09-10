package joke.k.myapplication.login.drawer.fragments.webview;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebClient {

    public WebClient callWebClient(WebView webView, String url){
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);

        return new WebClient();
    }


}
