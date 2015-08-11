package com.gratitube;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.GeolocationPermissions;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebViewController extends WebViewClient  {

    private static final String LOG_TAG = "test";
    ProgressDialog progressDialog;
    private WebView myWebView;
    private Button btn1;


    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {









        if(url.contains("http://gratitube-app.influxiq.com/"))
        {
            if(url.contains("facebook"))
            {

                Context context = view.getContext();
                Intent cameraintent = new Intent(context, HelloFacebookSampleActivity.class);


                // Launch default browser
                context.startActivity(cameraintent);

                return true;


            }


            if(url.contains("uploadvideoapp"))
            {

                Context context = view.getContext();
                Intent cameraintent = new Intent(context, videoActivity.class);


                // Launch default browser
                context.startActivity(cameraintent);

                return true;


            }if(url.contains("uploadvideochoose"))
            {

                Context context = view.getContext();
                Intent cameraintent = new Intent(context, upload.class);


                // Launch default browser
                context.startActivity(cameraintent);

                return true;


            }

            if(url.contains("uploadimageapp"))
            {

                Context context = view.getContext();
                Intent cameraintent = new Intent(context, videolist.class);


                // Launch default browser
                context.startActivity(cameraintent);

                return true;


            }





            view.loadUrl(url);
            return  false;
        }
        else{

            Context context = view.getContext();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            // Launch default browser
            context.startActivity(browserIntent);

            return true;
        }
    }


    public void onLoadResource (WebView view, String url) {
        if (progressDialog == null) {
            // in standard case YourActivity.this
            Context context = view.getContext();
            progressDialog = new ProgressDialog(context);
           // progressDialog.setMessage("Loading...");
           // progressDialog.show();
        }
    }
    public void onPageFinished(WebView view, String url) {
        try{
            if (progressDialog.isShowing()) {
               // progressDialog.dismiss();
               // progressDialog = null;
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }





    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler,     SslError error) {
        super.onReceivedSslError(view, handler, error);

        // this will ignore the Ssl error and will go forward to your site
        handler.proceed();
        error.getCertificate();
    }



    public class GeoWebChromeClient extends WebChromeClient {
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin,
                                                       GeolocationPermissions.Callback callback) {
            // Always grant permission since the app itself requires location
            // permission and the user has therefore already granted it
            callback.invoke(origin, true, false);
        }
    }



    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        Log.d(LOG_TAG, message);
        // This shows the dialog box.  This can be commented out for dev
        Context context = view.getContext();
        AlertDialog.Builder alertBldr = new AlertDialog.Builder(context);
        alertBldr.setMessage(message);
        alertBldr.setTitle("Alert");
        alertBldr.show();
        result.confirm();
        return true;
    }

    public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
        callback.invoke(origin, true, false);
    }

}
