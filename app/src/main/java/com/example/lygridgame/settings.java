package com.example.lygridgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentProviderClient;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//Name: David Ly
//Date: January 11, 2021
//Purpose: Unit 5 Project - Grid Game

public class settings extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //holds the song choice of the user
    int song = 0;
    //holds the background choice of the user
    int bground = 0;
    //used for radio buttons
    RadioGroup radioGroup;
    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        //creating the dropdown
        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.songs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        //radiobuttons
        radioGroup = findViewById(R.id.radioGroup);

    }
    //verifies user's choice of ratinbg
    public void checkButton(View view){
        //determines the user's choice of background
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        //finds user's pick of background
        String pick = radioButton.getText().toString();
        //toast shows user's choice
        Toast.makeText(this,"You gave the game a " + radioButton.getText() + " rating!",Toast.LENGTH_SHORT).show();
    }

    //sends user back to game screen
    public void click(View view) {
        //go to game screen
        Intent i = new Intent(this, Game.class);
        startActivity(i);
        //used to write the user's song choice to the game screen
        try {
            FileOutputStream out = openFileOutput("song.txt", Activity.MODE_PRIVATE);
            out.write(song);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //used to write the user's background choice to the game screen
        try {
            FileOutputStream out = openFileOutput("bground.txt", Activity.MODE_PRIVATE);
            out.write(bground);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        //if user chooses none for their song, global variable song will equal to 0
        if (text.equals("None")) {
            song = 0;
            //if user chooses victory vs wild pokemon as their song, global variable song will equal to 1
        } else if (text.equals("victory vs wild pokemon")) {
            song = 1;
            //if user chooses victory vs wild pokemon as their song, global variable song will equal to 2
        } else if (text.equals("victory vs trainer")) {
            song = 2;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}