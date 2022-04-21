package com.example.lygridgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

//Name: David Ly
//Date: January 11, 2021

public class MainActivity extends AppCompatActivity {
    //rows in board
    int row = 4;
    //columns in board
    int col = 4;
    //original board
    int board[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 0, 15},};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loads the board
        try {
            FileOutputStream out = openFileOutput("level.txt", Activity.MODE_PRIVATE);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    out.write(board[i][j]);
                }
            }
            out.write(1);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //loads move counter
        try {
            FileOutputStream out = openFileOutput("movecounter.txt", Activity.MODE_PRIVATE);
            out.write(0);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //loads level display
        try {
            FileOutputStream out = openFileOutput("leveldisplay.txt", Activity.MODE_PRIVATE);
            out.write(0);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //sends user to instruction screen
    public void click(View view) {
        //Get the names out of the EditTexts
        EditText name = (EditText) findViewById(R.id.name);
        String n = name.getText().toString();
        try {
            FileOutputStream out = openFileOutput("names.txt", Activity.MODE_PRIVATE);
            //Print out the length of name 1
            out.write(n.length());
            //Print out each ASCII letter in the name
            for (int i = 0; i < n.length(); i++) {
                out.write((int) (n.charAt(i)));
            }
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //go to instruction screen
        Intent i = new Intent(this, Instructions.class);
        startActivity(i);
    }
}
