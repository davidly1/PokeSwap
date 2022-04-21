package com.example.lygridgame;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
//import org.w3c.dom.Text;

//Name: David Ly
//Date: January 11, 2021

public class Game extends AppCompatActivity {

    //rows of board
    int row = 4;
    //columns of board
    int col = 4;
    //initialize last at -1
    int last = -1;
    //starts level count at lvl1
    int number = 1;
    //move counter starts at 0
    int count = 0;
    //check starts at 0 so that the user has to complete a level to be able to go next level
    int check = 0;
    //users song choice
    int song = 0;
    //initial board
    int board[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 0, 15},};
    //level 1
    int level1[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 0, 15},};
    //level 2
    int level2[][] = {{1, 3, 5, 7}, {8, 10, 12, 9}, {6, 0, 14, 11}, {4, 2, 15, 13},};
    //level 3
    int level3[][] = {{0, 15, 14, 13}, {12, 11, 10, 9}, {8, 7, 6, 5}, {4, 3, 2, 1},};
    //level 4
    int level4[][] = {{1, 5, 9, 13}, {2, 6, 10, 14}, {3, 7, 11, 15}, {4, 8, 12, 0},};
    //level 5
    int level5[][] = {{12, 13, 14, 15}, {8, 9, 10, 11}, {4, 5, 6, 7}, {1, 2, 3, 0},};
    //level 6
    int level6[][] = {{1, 2, 4, 3}, {0, 9, 11, 6}, {14, 13, 15, 8}, {5, 12, 10, 7},};
    //level 7
    int level7[][] = {{13, 12, 11, 10}, {14, 3, 4, 9}, {15, 2, 5, 8}, {0, 1, 6, 7},};
    //level 8
    int level8[][] = {{12, 2, 1, 15}, {7, 9, 10, 4}, {11, 5, 6, 8}, {0, 14, 13, 3},};
    //level 9
    int level9[][] = {{7, 6, 5, 4}, {8, 15, 14, 3}, {9, 0, 13, 2}, {10, 11, 12, 1},};
    //level 10
    int level10[][] = {{0, 14, 11, 7}, {15, 12, 8, 4}, {13, 9, 5, 2}, {10, 6, 3, 1},};
    //finished board
    int done[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0},};
    //1d widget array
    ImageView pics[] = new ImageView[row * col];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //reads in the user's board prior to going different screen
        try {
            FileInputStream in = openFileInput("level.txt");
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    board[i][j] = in.read();
                }
            }
            number = in.read();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //reads in amount of moves the user had made prior to going different screen
        try {
            FileInputStream in = openFileInput("movecounter.txt");
            count = in.read();
            in.close();
            TextView move = (TextView) findViewById(R.id.movecount);
            move.setText("Moves: " + count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //reads in the user's level and redisplays it at the top
        try {
            FileInputStream in = openFileInput("leveldisplay.txt");
            TextView level = (TextView) findViewById(R.id.displaylevel);
            level.setText("" + number);
            number = in.read();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //reads in the user's choice in theme in settings screen
        try {
            FileInputStream in = openFileInput("song.txt");
            song = in.read();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        GridLayout g = (GridLayout) findViewById(R.id.grid);
        int m = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                pics[m] = new ImageView(this);
                setpicStart(pics[m], m);
                pics[m].setId(m);
                pics[m].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClick(v.getId());
                    }
                });
                g.addView(pics[m]);
                m++;
            }
        }
    }

    public void setpicStart(ImageView i, int pos) {
        int x = pos / col;
        int y = pos % col;
        int picnum = board[x][y];
        if (picnum == 0)
            i.setImageResource(R.drawable.blank);
        else if (picnum == 1)
            i.setImageResource(R.drawable.b0);
        else if (picnum == 2)
            i.setImageResource(R.drawable.b1);
        else if (picnum == 3)
            i.setImageResource(R.drawable.b2);
        else if (picnum == 4)
            i.setImageResource(R.drawable.b3);
        else if (picnum == 5)
            i.setImageResource(R.drawable.b4);
        else if (picnum == 6)
            i.setImageResource(R.drawable.b5);
        else if (picnum == 7)
            i.setImageResource(R.drawable.b6);
        else if (picnum == 8)
            i.setImageResource(R.drawable.b7);
        else if (picnum == 9)
            i.setImageResource(R.drawable.b8);
        else if (picnum == 10)
            i.setImageResource(R.drawable.b9);
        else if (picnum == 11)
            i.setImageResource(R.drawable.b10);
        else if (picnum == 12)
            i.setImageResource(R.drawable.b11);
        else if (picnum == 13)
            i.setImageResource(R.drawable.b12);
        else if (picnum == 14)
            i.setImageResource(R.drawable.b13);
        else if (picnum == 15)
            i.setImageResource(R.drawable.b14);

    }

    public void gridButtonClick(int pos) {
        if (last == -1) {
            //click 1
            last = pos;
        } else {
            //click 2, if move is possible
            if (isValid(pos, last)) {
                int curX = pos / col;
                int curY = pos % col;
                int lastX = last / col;
                int lastY = last % col;
                int temp = board[lastX][lastY];
                board[lastX][lastY] = board[curX][curY];
                board[curX][curY] = temp;
                redraw();
                //if player makes move, increase move counter
                count++;
                //displays move counter
                TextView move = (TextView) findViewById(R.id.movecount);
                move.setText("Moves: " + count);
            } else {
                //if move cant be made, error dialog box appears
                errordialogbox();
            }
            //sets last back to -1
            last = -1;
            //if user wins level, send them a dialog box
            if (win()) {
                nextleveldialogbox();
                //write's a 1 since the user had completed the level, used to check if they are eligible to go next
                try {
                    FileOutputStream out = openFileOutput("levelcheck.txt", Activity.MODE_PRIVATE);
                    out.write(1);
                    out.flush();
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //checks if the current board matches the finished board
    public boolean win() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] != done[i][j])
                    return false;
            }
        }
        return true;
    }

    //checks if moves can be made, returns true if it can, returns false if it can not
    public boolean isValid(int pos, int last) {
        int curX = pos / col;
        int curY = pos % col;
        int lastX = last / col;
        int lastY = last % col;
        if (board[curX][curY] != 0 && board[lastX][lastY] != 0)
            return false;
        else if (curX - 1 == lastX && curY == lastY)
            return true;
        else if (curX + 1 == lastX && curY == lastY)
            return true;
        else if (curX == lastX && curY - 1 == lastY)
            return true;
        else if (curX == lastX && curY + 1 == lastY)
            return true;
        else
            return false;
    }

    //redraws board
    public void redraw() {
        int m = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 0)
                    pics[m].setImageResource(R.drawable.blank);
                else if (board[i][j] == 1)
                    pics[m].setImageResource(R.drawable.b0);
                else if (board[i][j] == 2)
                    pics[m].setImageResource(R.drawable.b1);
                else if (board[i][j] == 3)
                    pics[m].setImageResource(R.drawable.b2);
                else if (board[i][j] == 4)
                    pics[m].setImageResource(R.drawable.b3);
                else if (board[i][j] == 5)
                    pics[m].setImageResource(R.drawable.b4);
                else if (board[i][j] == 6)
                    pics[m].setImageResource(R.drawable.b5);
                else if (board[i][j] == 7)
                    pics[m].setImageResource(R.drawable.b6);
                else if (board[i][j] == 8)
                    pics[m].setImageResource(R.drawable.b7);
                else if (board[i][j] == 9)
                    pics[m].setImageResource(R.drawable.b8);
                else if (board[i][j] == 10)
                    pics[m].setImageResource(R.drawable.b9);
                else if (board[i][j] == 11)
                    pics[m].setImageResource(R.drawable.b10);
                else if (board[i][j] == 12)
                    pics[m].setImageResource(R.drawable.b11);
                else if (board[i][j] == 13)
                    pics[m].setImageResource(R.drawable.b12);
                else if (board[i][j] == 14)
                    pics[m].setImageResource(R.drawable.b13);
                else if (board[i][j] == 15)
                    pics[m].setImageResource(R.drawable.b14);
                m++;
            }
        }
    }

    //used to change boards for levels
    public void copyOver(int a[][], int b[][]) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                a[i][j] = b[i][j];
            }
        }
    }

    //next button
    public void next(View view) {
        TextView level = (TextView) findViewById(R.id.displaylevel);
        //checks if player has completed the level
        try {
            FileInputStream in = openFileInput("levelcheck.txt");
            check = in.read();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //if player has completed the level (check = 1), go to next level
        if (check == 1) {
            number++;
            //if at level 1 and pressed next, number will equal to 2 and if it is equal to 2, go to level 2
            if (number == 2) {
                //copies to level 2 board
                copyOver(board, level2);
                //displays level
                level.setText("" + number);
                //if player has chosen the victory vs wild pokemon theme in settings, play it
                if (song == 1) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victory);
                    ring.start();
                    //if player has chosen the victory vs trainer theme in settings, play it
                } else if (song == 2) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victorytrainer);
                    ring.start();
                }
                //if at level 2 and pressed next, number will equal to 3 and if it is equal to 3, go to level 3
            } else if (number == 3) {
                //copies to level 3 board
                copyOver(board, level3);
                //displays level
                level.setText("" + number);
                //if player has chosen the victory vs wild pokemon theme in settings, play it
                if (song == 1) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victory);
                    ring.start();
                    //if player has chosen the victory vs trainer theme in settings, play it
                } else if (song == 2) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victorytrainer);
                    ring.start();
                }
                //if at level 3 and pressed next, number will equal to 4 and if it is equal to 4, go to level 4
            } else if (number == 4) {
                //copies to level 4 board
                copyOver(board, level4);
                //displays level
                level.setText("" + number);
                //if player has chosen the victory vs wild pokemon theme in settings, play it
                if (song == 1) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victory);
                    ring.start();
                    //if player has chosen the victory vs trainer theme in settings, play it
                } else if (song == 2) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victorytrainer);
                    ring.start();
                }
                //if at level 4 and pressed next, number will equal to 5 and if it is equal to 5, go to level 5
            } else if (number == 5) {
                //copies to level 5 board
                copyOver(board, level5);
                //displays level
                level.setText("" + number);
                //if player has chosen the victory vs wild pokemon theme in settings, play it
                if (song == 1) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victory);
                    ring.start();
                    //if player has chosen the victory vs trainer theme in settings, play it
                } else if (song == 2) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victorytrainer);
                    ring.start();
                }
                //if at level 5 and pressed next, number will equal to 6 and if it is equal to 6, go to level 6
            } else if (number == 6) {
                //copies to level 6 board
                copyOver(board, level6);
                //displays level
                level.setText("" + number);
                //if player has chosen the victory vs wild pokemon theme in settings, play it
                if (song == 1) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victory);
                    ring.start();
                    //if player has chosen the victory vs trainer theme in settings, play it
                } else if (song == 2) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victorytrainer);
                    ring.start();
                }
                //if at level 6 and pressed next, number will equal to 7 and if it is equal to 7, go to level 7
            } else if (number == 7) {
                //copies to level 7 board
                copyOver(board, level7);
                //displays level
                level.setText("" + number);
                //if player has chosen the victory vs wild pokemon theme in settings, play it
                if (song == 1) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victory);
                    ring.start();
                    //if player has chosen the victory vs trainer theme in settings, play it
                } else if (song == 2) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victorytrainer);
                    ring.start();
                }
                //if at level 7 and pressed next, number will equal to 8 and if it is equal to 8, go to level 8
            } else if (number == 8) {
                //copies to level 8 board
                copyOver(board, level8);
                //displays level
                level.setText("" + number);
                //if player has chosen the victory vs wild pokemon theme in settings, play it
                if (song == 1) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victory);
                    ring.start();
                    //if player has chosen the victory vs trainer theme in settings, play it
                } else if (song == 2) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victorytrainer);
                    ring.start();
                }
                //if at level 8 and pressed next, number will equal to 9 and if it is equal to 9, go to level 9
            } else if (number == 9) {
                //copies to level 9 board
                copyOver(board, level9);
                //displays level
                level.setText("" + number);
                //if player has chosen the victory vs wild pokemon theme in settings, play it
                if (song == 1) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victory);
                    ring.start();
                    //if player has chosen the victory vs trainer theme in settings, play it
                } else if (song == 2) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victorytrainer);
                    ring.start();
                }
                //if at level 9 and pressed next, number will equal to 10 and if it is equal to 10, go to level 10
            } else if (number == 10) {
                //copies to level 10 board
                copyOver(board, level10);
                //displays level
                level.setText("" + number);
                //if player has chosen the victory vs wild pokemon theme in settings, play it
                if (song == 1) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victory);
                    ring.start();
                    //if player has chosen the victory vs trainer theme in settings, play it
                } else if (song == 2) {
                    MediaPlayer ring = MediaPlayer.create(Game.this, R.raw.victorytrainer);
                    ring.start();
                }
                //if at level 10 and pressed next, number will equal to 11 and if it is equal to 11, go to victory screen
            } else if (number == 11) {
                //sends user to win screen
                Intent i = new Intent(this, winscreen.class);
                startActivity(i);
            } else {
                //last resort, copies to level 1 board
                copyOver(board, level1);
                //sets number back to 1, restart
                number = 1;
                //displays level
                level.setText("" + number);
            }
            //draws board
            redraw();
            //sets last back to -1
            last = -1;
            //sets move counter back to 0
            count = 0;
            TextView move = (TextView) findViewById(R.id.movecount);
            //displays the amount of moves the user has made
            move.setText("Moves: " + count);
            //after moving to next level, write 0 so that the user has to complete level to go next
            try {
                FileOutputStream out = openFileOutput("levelcheck.txt", Activity.MODE_PRIVATE);
                out.write(0);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //Create a dialog box
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("OK", null);
            //Build the ImageView to add
            ImageView i = new ImageView(this);
            i.setImageResource(R.drawable.noo);
            builder.setView(i);
            //Pick the other settings
            builder.setTitle("ERROR!");
            builder.setMessage("You haven't completed the level! Please complete it!");
            //Show dialog box on the screen
            builder.show();
        }
    }

    //previous button to go back a level
    public void previous(View view) {
        TextView level = (TextView) findViewById(R.id.displaylevel);
        number--;
        //if user is not on level 1 and they wish to go back a level, allow them to go back a level with a free pass of that level.
        //Example, user is on level 2 and wishes to go back to level 1, they will be able to click on next to go back to level 2
        if (number == 1) {
            try {
                FileOutputStream out = openFileOutput("levelcheck.txt", Activity.MODE_PRIVATE);
                out.write(1);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //prevents user from clicking on previous to go to level 2 if they are on level 1
        } else {
            try {
                FileOutputStream out = openFileOutput("levelcheck.txt", Activity.MODE_PRIVATE);
                out.write(0);
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //Create a dialog box
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("OK", null);
            //Build the ImageView to add
            ImageView i = new ImageView(this);
            i.setImageResource(R.drawable.noo);
            builder.setView(i);
            //Pick the other settings
            builder.setTitle("ERROR!");
            builder.setMessage("You are on the first level, you can't go any further!");
            //Show dialog box on the screen
            builder.show();
        }
        //if at level 2 and pressed previous, number will equal to 1 and if it is equal to 1, go to level 1
        if (number == 2) {
            copyOver(board, level1);
            //displays level
            level.setText("" + number);
            //if at level 3 and pressed previous, number will equal to 2 and if it is equal to 2, go to level 2
        } else if (number == 3) {
            copyOver(board, level2);
            //displays level
            level.setText("" + number);
            //if at level 4 and pressed previous, number will equal to 3 and if it is equal to 3, go to level 3
        } else if (number == 4) {
            copyOver(board, level3);
            //displays level
            level.setText("" + number);
            //if at level 5 and pressed previous, number will equal to 4 and if it is equal to 4, go to level 4
        } else if (number == 5) {
            copyOver(board, level4);
            //displays level
            level.setText("" + number);
            //if at level 6 and pressed previous, number will equal to 5 and if it is equal to 5, go to level 5
        } else if (number == 6) {
            copyOver(board, level5);
            //displays level
            level.setText("" + number);
            //if at level 7 and pressed previous, number will equal to 6 and if it is equal to 6, go to level 6
        } else if (number == 7) {
            copyOver(board, level6);
            //displays level
            level.setText("" + number);
            //if at level 8 and pressed previous, number will equal to 7 and if it is equal to 7, go to level 7
        } else if (number == 8) {
            copyOver(board, level7);
            //displays level
            level.setText("" + number);
            //if at level 9 and pressed previous, number will equal to 8 and if it is equal to 8, go to level 8
        } else if (number == 9) {
            copyOver(board, level8);
            //displays level
            level.setText("" + number);
            //if at level 10 and pressed previous, number will equal to 9 and if it is equal to 9, go to level 9
        } else if (number == 10) {
            copyOver(board, level9);
            //displays level
            level.setText("" + number);
        } else {
            //last resort, sets board to level 1
            copyOver(board, level1);
            //sets number back to 1 to restart
            number = 1;
            //displays level
            level.setText("" + number);
        }
        //draws board
        redraw();
        //sets last back to -1
        last = -1;
        //sets moves back to 0
        count = 0;
        //displays move counter
        TextView move = (TextView) findViewById(R.id.movecount);
        move.setText("Moves: " + count);
    }

    //resets board
    public void reset(View view) {
        //if on level 2, resets board to level 2
        if (number == 2) {
            copyOver(board, level2);
            //if on level 3, resets board to level 3
        } else if (number == 3) {
            copyOver(board, level3);
            //if on level 4, resets board to level 4
        } else if (number == 4) {
            copyOver(board, level4);
            //if on level 5, resets board to level 5
        } else if (number == 5) {
            copyOver(board, level5);
            //if on level 6, resets board to level 6
        } else if (number == 6) {
            copyOver(board, level6);
            //if on level 7, resets board to level 7
        } else if (number == 7)
            copyOver(board, level7);
            //if on level 8, resets board to level 8
        else if (number == 8) {
            copyOver(board, level8);
            //if on level 9, resets board to level 9
        } else if (number == 9) {
            copyOver(board, level9);
            //if on level 10, resets board to level 10
        } else if (number == 10) {
            copyOver(board, level10);
            //last resort, sets board back to level 1
        } else {
            copyOver(board, level1);
        }
        //draws board
        redraw();
        //move counter back to 0
        count = 0;
        //displays move counter
        TextView move = (TextView) findViewById(R.id.movecount);
        move.setText("Moves: " + count);
    }

    //dialog box for errors
    public void errordialogbox() {
        //Create a dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", null);
        //Build the ImageView to add
        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.noo);
        builder.setView(i);
        //Pick the other settings
        builder.setTitle("ERROR!");
        builder.setMessage("Pikachu disagrees with your choice of movement! Hint: Pick a spot with an empty space.");
        //Show dialog box on the screen
        builder.show();
    }

    //dialog box for completing levels
    public void nextleveldialogbox() {
        //Create a dialog box
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", null);
        //Build the ImageView to add
        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.yayy);
        builder.setView(i);
        //Pick the other settings
        builder.setTitle("Hurray!");
        builder.setMessage("Pikachu approves! You finished in " + count + " moves. Go to the next level.");
        //Show dialog box on the screen
        builder.show();
    }

    //sends you to instruction screen if user needs help
    public void instructionclick(View view) {
        //saves user's board when he goes onto the instruction screen
        try {
            FileOutputStream out = openFileOutput("level.txt", Activity.MODE_PRIVATE);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    out.write(board[i][j]);
                }
            }
            out.write(number);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //saves user's move counter when moving to instruction screen
        try {
            FileOutputStream out = openFileOutput("movecounter.txt", Activity.MODE_PRIVATE);
            out.write(count);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //saves user's level display when moving to instruction screen
        try {
            FileOutputStream out = openFileOutput("leveldisplay.txt", Activity.MODE_PRIVATE);
            out.write(number);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //sends user to instruction screen
        Intent i = new Intent(this, Instructions.class);
        startActivity(i);
    }

    //sends user to settings screen
    public void settingsclick(View view) {
        //saves user's board when he goes onto the settings screen
        try {
            FileOutputStream out = openFileOutput("level.txt", Activity.MODE_PRIVATE);
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    out.write(board[i][j]);
                }
            }
            out.write(number);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //saves user's move counter when he goes onto the settings screen
        try {
            FileOutputStream out = openFileOutput("movecounter.txt", Activity.MODE_PRIVATE);
            out.write(count);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //saves user's level display when he goes onto the settings screen
        try {
            FileOutputStream out = openFileOutput("leveldisplay.txt", Activity.MODE_PRIVATE);
            out.write(number);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //sends user to settings screen
        Intent i = new Intent(this, settings.class);
        startActivity(i);
    }

    //skipping the levels for convenient purposes
    public void skip(View view) {
        TextView level = (TextView) findViewById(R.id.displaylevel);
        number++;
        //if at level 1 and pressed next, number will equal to 2 and if it is equal to 2, go to level 2
        if (number == 2) {
            copyOver(board, level2);
            //displays level
            level.setText("" + number);
            //if at level 2 and pressed next, number will equal to 3 and if it is equal to 3, go to level 3
        } else if (number == 3) {
            copyOver(board, level3);
            //displays level
            level.setText("" + number);
            //if at level 3 and pressed next, number will equal to 4 and if it is equal to 4, go to level 4
        } else if (number == 4) {
            copyOver(board, level4);
            //displays level
            level.setText("" + number);
            //if at level 4 and pressed next, number will equal to 5 and if it is equal to 5, go to level 5
        } else if (number == 5) {
            copyOver(board, level5);
            //displays level
            level.setText("" + number);
            //if at level 5 and pressed next, number will equal to 6 and if it is equal to 6, go to level 6
        } else if (number == 6) {
            copyOver(board, level6);
            //displays level
            level.setText("" + number);
            //if at level 6 and pressed next, number will equal to 7 and if it is equal to 7, go to level 7
        } else if (number == 7) {
            copyOver(board, level7);
            //displays level
            level.setText("" + number);
            //if at level 7 and pressed next, number will equal to 8 and if it is equal to 8, go to level 8
        } else if (number == 8) {
            copyOver(board, level8);
            //displays level
            level.setText("" + number);
            //if at level 8 and pressed next, number will equal to 9 and if it is equal to 9, go to level 9
        } else if (number == 9) {
            copyOver(board, level9);
            //displays level
            level.setText("" + number);
            //if at level 9 and pressed next, number will equal to 10 and if it is equal to 10, go to level 10
        } else if (number == 10) {
            copyOver(board, level10);
            //displays level
            level.setText("" + number);
            //if at level 10 and pressed next, number will equal to 11 and sends user to win screen
        } else if (number == 11) {
            Intent i = new Intent(this, winscreen.class);
            startActivity(i);
        } else {
            //last resort, sets board back to level 1
            copyOver(board, level1);
            //sets number back to 1 to restart game
            number = 1;
            //displays level
            level.setText("" + number);
        }
        //draws board
        redraw();
        //resets last to -1
        last = -1;
        //resets move counter to 0
        count = 0;
        //displays move counter
        TextView move = (TextView) findViewById(R.id.movecount);
        move.setText("Moves: " + count);

    }
}
