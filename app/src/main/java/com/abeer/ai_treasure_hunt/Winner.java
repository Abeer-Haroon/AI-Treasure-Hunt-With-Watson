package com.abeer.ai_treasure_hunt;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Winner extends AppCompatActivity {

    private String hint = "Congratulations for winning the game.";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        final MediaPlayer mp = MediaPlayer.create(this,R.raw.win);



        mp.start();
    }
}
