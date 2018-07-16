package com.example.kevin.braintrainer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // layout and button decleration
    ConstraintLayout gameLayout;
    GridLayout buttonsLayout;

    Button startButton;
    Button playAgainButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    // array to hold potential answers
    ArrayList<Integer> answers = new ArrayList<Integer>();

    // flags for the information relating to the score
    int rightAnswerPosition;
    int score = 0;
    int totalQuestions;

    // text view decleration
    TextView resultTextView;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;

    // function for the user to play again
    public void playAgain(View view)
    {
        score = 0;
        totalQuestions = 0;

        // sets the time to 30 seconds
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(totalQuestions));

        // calls new question function
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        buttonsLayout.setVisibility(View.VISIBLE);
        // creates new timer
        new CountDownTimer(3100, 1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("DONE!");
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setText("");
                buttonsLayout.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    // method to determine if the button clicked by the user is correct
    public void chooseAnswer(View view)
    {
        totalQuestions++;
        answers.clear();
        if(Integer.toString(rightAnswerPosition).equals(view.getTag().toString()))
        {
            Log.i("Status:", "Correct!");
            resultTextView.setText("CORRECT!");
            score++;
        } else{
            resultTextView.setText("WRONG!");
        }
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(totalQuestions));
        newQuestion();
    }

    // resets the questions
    public void newQuestion()
    {
        Random rand = new Random();

        rightAnswerPosition = rand.nextInt(4);

        int x = rand.nextInt(21);
        int y = rand.nextInt(21);
        int z;

        sumTextView.setText(Integer.toString(x) + " + " + Integer.toString(y));

        for(int i = 0; i < 4; i++)
        {
            if(i == rightAnswerPosition){ answers.add(x + y); }
            else {
                // we want to check if the random number is equal to the answer so we
                // don't add the wrong answer
                z = rand.nextInt(41);
                while(z == (x + y)) { z = rand.nextInt(41); }
                answers.add(z);

            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    // method to start the app
    public void start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(resultTextView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // decleration of the views/buttons
        startButton = (Button)findViewById(R.id.startButton);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        startButton = (Button)findViewById(R.id.startButton);

        sumTextView = (TextView)findViewById(R.id.sumTextView);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        scoreTextView = (TextView)findViewById(R.id.scoreTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);

        gameLayout = (ConstraintLayout)findViewById(R.id.gameLayout);
        buttonsLayout = (GridLayout)findViewById(R.id.buttonsLayout);

        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);

        startButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
