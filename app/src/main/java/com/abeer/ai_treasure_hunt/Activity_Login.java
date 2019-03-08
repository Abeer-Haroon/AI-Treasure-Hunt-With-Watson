package com.abeer.ai_treasure_hunt;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibm.cloud.appid.android.api.AppID;
import com.ibm.cloud.appid.android.api.AuthorizationException;
import com.ibm.cloud.appid.android.api.AuthorizationListener;
import com.ibm.cloud.appid.android.api.LoginWidget;
import com.ibm.cloud.appid.android.api.tokens.AccessToken;
import com.ibm.cloud.appid.android.api.tokens.IdentityToken;
import com.ibm.cloud.appid.android.api.tokens.RefreshToken;

public class Activity_Login extends AppCompatActivity {

    RelativeLayout rellay1, rellay2;
   // private Activity activity;
    private Button btn_login;
    private TextView tv_login;
    private TextView t_login;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
        //    rellay2.setVisibility(View.VISIBLE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppID.getInstance().initialize(getApplicationContext(), "apikey", AppID.REGION_US_SOUTH);



        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
       

        handler.postDelayed(runnable, 2000); //2000 is the timeout for the splash


        btn_login = (Button) findViewById(R.id.btn_login);
        tv_login = (TextView) findViewById(R.id.tv_login);
        t_login = (TextView) findViewById(R.id.t_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                btn_login.setVisibility(View.GONE);
                tv_login.setVisibility(View.GONE);
                t_login.setVisibility(View.VISIBLE);




                LoginWidget loginWidget = AppID.getInstance().getLoginWidget();
                loginWidget.launch(Activity_Login.this, new AuthorizationListener() {
                    @Override
                    public void onAuthorizationFailure (AuthorizationException exception) {
                        //Exception occurred
                    }

                    @Override
                    public void onAuthorizationCanceled () {
                        //Authentication canceled by the user
                    }

                    @Override
                    public void onAuthorizationSuccess (AccessToken accessToken, IdentityToken identityToken, RefreshToken refreshToken) {
                        //User authenticated

                        Intent intent = new Intent(Activity_Login.this, MainActivity.class);
                        startActivity(intent);
                    }


                });





            }
        });





    }

    public void onBackPressed(){

    }
}
