package com.example.lygridgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

//Name: David Ly
//Date: January 11, 2021
//Purpose: Unit 5 Project - Grid Game

public class winscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winscreen);
        //plays the victory music
        MediaPlayer ring = MediaPlayer.create(winscreen.this, R.raw.wingame);
        ring.start();
    }

    //sends user to game screen if they wish to play again
    public void click(View view){
    Intent i = new Intent(this, Game.class);
    startActivity(i);
    }

}