package com.gratitube;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.lang.reflect.Method;


public class MainActivity extends Activity {

    private WebView gratitubewebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        gratitubewebview = (WebView) findViewById(R.id.webView1);

        gratitubewebview.addJavascriptInterface(new WebAppInterface(this), "Android");
    }



    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == 0) {
            launchWebview();
        }

    }


    private void launchWebview() {
        gratitubewebview.setWebViewClient(new WebViewController());
        gratitubewebview.getSettings().setJavaScriptEnabled(true);
        gratitubewebview.getSettings().setLoadWithOverviewMode(true);
        gratitubewebview.getSettings().setUseWideViewPort(true);
        gratitubewebview.getSettings().setSupportMultipleWindows(true);
        gratitubewebview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        gratitubewebview.getSettings().setDomStorageEnabled(true);
        gratitubewebview.getSettings().setAppCacheEnabled(true);
        gratitubewebview.getSettings().setAllowFileAccess(true);
        gratitubewebview.getSettings().setGeolocationEnabled(true);
        gratitubewebview.getSettings().setJavaScriptEnabled(true);
        Context context = gratitubewebview.getContext();
        gratitubewebview.getSettings().setGeolocationDatabasePath( context.getFilesDir().getPath() );
        gratitubewebview.getSettings().setLoadWithOverviewMode(true);
        gratitubewebview.getSettings().setUseWideViewPort(true);
        gratitubewebview.setWebChromeClient(new WebChromeClient() {
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                callback.invoke(origin, true, false);
            }
        });

        gratitubewebview.loadUrl("http://gratitube-app.influxiq.com/");

        // settings.setPluginsEnabled(true);
        methodInvoke(gratitubewebview.getSettings(), "setPluginsEnabled", new Class[] { boolean.class }, new Object[] { true });
        // settings.setPluginState(PluginState.ON);
        methodInvoke(gratitubewebview.getSettings(), "setPluginState", new Class[] { WebSettings.PluginState.class }, new Object[] { WebSettings.PluginState.ON });
        // settings.setPluginsEnabled(true);
        methodInvoke(gratitubewebview.getSettings(), "setPluginsEnabled", new Class[] { boolean.class }, new Object[] { true });
        // settings.setAllowUniversalAccessFromFileURLs(true);
        methodInvoke(gratitubewebview.getSettings(), "setAllowUniversalAccessFromFileURLs", new Class[] { boolean.class }, new Object[] { true });
        // settings.setAllowFileAccessFromFileURLs(true);
        methodInvoke(gratitubewebview.getSettings(), "setAllowFileAccessFromFileURLs", new Class[] { boolean.class }, new Object[] { true });


        gratitubewebview.setWebChromeClient(new WebChromeClient() {



        });
    }

    private final static Object methodInvoke(Object obj, String method,
                                             Class<?>[] parameterTypes, Object[] args) {
        try {
            Method m = obj.getClass().getMethod(method,
                    new Class[] { boolean.class });
            m.invoke(obj, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
