package com.gratitube;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Debasis-01 on 7/29/2015.
 */
public class WebViewController extends WebViewClient {

    private WebView myWebView;





    public boolean shouldOverrideUrlLoading(WebView view, String url) {

        view.loadUrl(url);
        return  false;
    }





}
