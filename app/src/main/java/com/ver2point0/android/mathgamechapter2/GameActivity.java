package com.ver2point0.android.mathgamechapter2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends Activity implements View.OnClickListener {

    private int mCorrectAnswer;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private TextView mTextViewFirstOperand;
    private TextView mTextViewSecondOperand;
    private TextView mTextLevel;
    private TextView mTextScore;
    private int mCurrentScore = 0;
    private int mCurrentLevel = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTextViewFirstOperand = (TextView) findViewById(R.id.tv_first_operand);
        mTextViewSecondOperand = (TextView) findViewById(R.id.tv_second_operand);
        mTextScore = (TextView) findViewById(R.id.tv_score);
        mTextLevel = (TextView) findViewById(R.id.tv_level);

        mButtonChoice1 = (Button) findViewById(R.id.bt_choice_one);
        mButtonChoice2 = (Button) findViewById(R.id.bt_choice_two);
        mButtonChoice3 = (Button) findViewById(R.id.bt_choice_three);

        mButtonChoice1.setOnClickListener(this);
        mButtonChoice2.setOnClickListener(this);
        mButtonChoice3.setOnClickListener(this);

        setQuestion();
    }

    public void onClick(View view) {
        int answerGiven = 0;
        switch (view.getId()) {
            case R.id.bt_choice_one:
                // initialize new value in first button choice
                answerGiven = Integer.parseInt("" + mButtonChoice1.getText());


                break;

            case R.id.bt_choice_two:
                // initialize new value in second button choice
                answerGiven = Integer.parseInt("" + mButtonChoice2.getText());


                break;

            case R.id.bt_choice_three:
                // initialize new value in first button choice
                answerGiven = Integer.parseInt("" + mButtonChoice3.getText());

                break;
        }
        updateScoreAndLevel(answerGiven);
        setQuestion();
    }

    public void setQuestion() {
        // generate question parts
        int numberRange = mCurrentLevel * 3;
        Random randInt = new Random();

        int firstOperand = randInt.nextInt(numberRange);
        firstOperand++; // no zero values

        int secondOperand = randInt.nextInt(numberRange);
        secondOperand++; // no zero values

        mCorrectAnswer = firstOperand * secondOperand;
        int wrongAnswer1 = mCorrectAnswer - 2;
        int wrongAnswer2 = mCorrectAnswer + 2;

        mTextViewFirstOperand.setText("" + firstOperand);
        mTextViewSecondOperand.setText("" + secondOperand);

        // set multi choice buttons
        // a number between 0 and 2
        int buttonLayout = randInt.nextInt(3);
        switch (buttonLayout) {
            case 0:
                mButtonChoice1.setText("" + mCorrectAnswer);
                mButtonChoice2.setText("" + wrongAnswer1);
                mButtonChoice3.setText("" + wrongAnswer2);
                break;

            case 1:
                mButtonChoice2.setText("" + mCorrectAnswer);
                mButtonChoice3.setText("" + wrongAnswer1);
                mButtonChoice1.setText("" + wrongAnswer2);
                break;

            case 2:
                mButtonChoice3.setText("" + mCorrectAnswer);
                mButtonChoice1.setText("" + wrongAnswer1);
                mButtonChoice2.setText("" + wrongAnswer2);
                break;
        }
    } // end method setQuestion()

    public void updateScoreAndLevel(int answerGiven) {
       if (isCorrect(answerGiven)) {
           for (int i = 1; i <= mCurrentLevel; i++) {
               mCurrentScore = mCurrentScore + i;
           }
           mCurrentLevel++;
       } else {
           mCurrentScore = 0;
           mCurrentLevel = 1;
       }
        mTextScore.setText("Score: " + mCurrentScore);
        mTextLevel.setText("Level: " + mCurrentLevel);
    } // end method updateScoreAndLevel()

    boolean isCorrect(int answerGiven) {
        boolean correctTrueOrFalse;
        if (answerGiven == mCorrectAnswer) { // Yay!
            Toast.makeText(getApplicationContext(), "Well done!", Toast.LENGTH_LONG).show();
            correctTrueOrFalse = true;
        } else { // Incorrect
            Toast.makeText(getApplicationContext(), "Sorry", Toast.LENGTH_LONG).show();
            correctTrueOrFalse = false;
        }
        return correctTrueOrFalse;
    } // end method isCorrect()

} // end class GameActivity

