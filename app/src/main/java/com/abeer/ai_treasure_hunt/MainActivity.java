package com.abeer.ai_treasure_hunt;

import android.os.Bundle;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Ganesh(View View)
    {
        String button_text;
        button_text =((Button)View).getText().toString();
        if (button_text.equals("Start"))
        {
            Intent mass = new Intent(MainActivity.this, Level1.class);
            startActivity(mass);

        }
    }

    public void onBackPressed(){

    }


}

