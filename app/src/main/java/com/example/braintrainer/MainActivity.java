package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<>();
    int locationAnswer;
    TextView resultTextView;
    int score = 0;
    int questionNumber= 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button restartButton;
    ConstraintLayout gameLayout;


    public void restart(View view){
        score=0;
        questionNumber=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(questionNumber));
        newQuestion();
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        resultTextView.setText("");

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {

                resultTextView.setText("Done!!!");
                restartButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
        restartButton.setVisibility(View.INVISIBLE);

    }
    public void chooseAnswer(View view){

        if (Integer.toString(locationAnswer).equals(view.getTag().toString())){

            Log.i("Correct", "You got it");
            resultTextView.setText("Good!");
            score++;


        }else{
            resultTextView.setText("You are wrong");
            Log.i("Wrong", "You are Wrong!");

        }
        questionNumber++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(questionNumber));
        resultTextView.setVisibility(View.VISIBLE);
        newQuestion();

    }

    public void startGame(View view){

        startButton.setVisibility(View.INVISIBLE);
        restart(findViewById(R.id.scoreTextView));
        gameLayout.setVisibility(View.VISIBLE);

    }

    public void newQuestion(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationAnswer = rand.nextInt(4);

        answers.clear();

        for(int i=0; i<4; i++){

            if( i == locationAnswer){
                answers.add(a+b);
            }else {
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a+b){

                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }

        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLayout=findViewById(R.id.gameLayout);
        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        startButton = findViewById(R.id.startButton);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        restartButton = findViewById(R.id.restartButton);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}
