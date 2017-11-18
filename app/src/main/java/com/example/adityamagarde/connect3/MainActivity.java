package com.example.adityamagarde.connect3;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    //  0=yellow, 1=red
    int active_player = 0;
    boolean gameRunning = true;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningState = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};


    public void Turn(View view)
    {
        ImageView counter = (ImageView) view;
        int currentGameState = Integer.parseInt(counter.getTag().toString());

        if(gameState[currentGameState]==2 && gameRunning == true)
        {
            gameState[currentGameState] = active_player;
            counter.setTranslationY(-1000f);

            if(active_player==0)
            {
                counter.setImageResource(R.drawable.yellow);
                active_player = 1;
//                gameState[currentGameState] = 0;
            }
            else
            {
                counter.setImageResource(R.drawable.red);
                active_player = 0;
//                gameState[currentGameState] = 1;
            }

            counter.animate().translationY(0f).setDuration(1500);
            counter.animate().rotation(360).setDuration(500);

            for (int[] winningPosition : winningState)
            {
                if(gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2)
                {
                    gameRunning = false;
                    String Winner = "Red";

                    if(gameState[winningPosition[0]] == 0)
                    {
                        Winner = "Yellow";
                    }
//                    int Winner = gameState[winningPosition[0]];
                    System.out.println(gameState[winningPosition[0]]);


                    TextView winnerMessage = findViewById(R.id.winnerMessage);
                    winnerMessage.setText("Player " + Winner + " has won..!!");
                    LinearLayout layout = findViewById(R.id.playAgainLayout);
                    layout.setVisibility(view.VISIBLE);

                }
                else
                {
                    boolean gameOver = true;
                    for(int counterState : gameState)
                    {
                        if(counterState == 2)
                            gameOver = false;
                    }

                    if(gameOver)
                    {
                        TextView winnerMessage = findViewById(R.id.winnerMessage);
                        winnerMessage.setText("It's a draw");
                        LinearLayout layout = findViewById(R.id.playAgainLayout);
                        layout.setVisibility(view.VISIBLE);


                    }
                }

            }

        }


    }

    public void playAgainMethod(View view)
    {
        active_player = 0;

        for(int i=0; i<gameState.length; i++)
        {
            gameState[i] = 2;
        }

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0; i<gridLayout.getChildCount(); i++)
        {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        gameRunning = true;

        LinearLayout layout = findViewById(R.id.playAgainLayout);
        layout.setVisibility(view.INVISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
