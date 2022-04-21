package com.example.lygridgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//Name: David Ly
//Date: January 11, 2021

public class Instructions extends AppCompatActivity {
    //holds user's name
    String display = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        try {
            FileInputStream in = openFileInput("names.txt");
            //How long is the first name?
            int nameLength = in.read();
            //Read in that many ints, convert to chars
            for (int i = 0; i < nameLength; i++) {
                int data = in.read();
                char letter = (char) data;
                display += letter;
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //welcome dialog box pops up when entering instruction screen
        welcomedialogbox();
    }

    //sends user to game screen
    public void click(View view) {
        Intent i = new Intent(this, Game.class);
        startActivity(i);
    }

    //pop up dialog box to welcome the player
    public void welcomedialogbox() {
        new AlertDialog.Builder(this)
                //title on dialog box
                .setTitle("WELCOME!")
                //message in dialog box
                .setMessage("Welcome " + display + " to Poke Swap!")
                //What to do if the button is pressed
                .setPositiveButton("Go!", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do something if they click the button
//otherwise, it just dismisses the dialog
                    }
                }).show();
    }
}
