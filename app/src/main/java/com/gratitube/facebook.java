package com.gratitube;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import com.facebook.Request;
/*import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;*/
import com.facebook.CallbackManager;
import  com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class facebook extends Activity {
    LoginButton loginButton;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_facebook);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email", "user_likes", "user_friends");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //callback registration

                LoginManager.getInstance().registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                // App code

                                LoginManager.getInstance().logInWithReadPermissions(facebook.this, Arrays.asList("public_profile", "user_friends"));
                                Log.e("-->", Arrays.asList("public_profile", "user_friends").toString());
                                Toast.makeText(getApplication(), "success", Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onCancel() {
                                // App code
                                Toast.makeText(getApplication(),"fail",Toast.LENGTH_SHORT).show();
                            }


                            public void onError(FacebookException exception) {
                                // App code
                                Toast.makeText(getApplication(),"error",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

    }



    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}