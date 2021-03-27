package com.richieoscar.quiztrivia;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView questionsText, nextQuestion;
    private MaterialRadioButton radioButtonOne;
    private MaterialRadioButton radioButtonTwo;
    private MaterialRadioButton radioButtonThree;
    ArrayList<Question> questions = Question.getQuestions();
    ArrayList<String> selectedAnswer = new ArrayList<>();
    int questionIndex;
    private RadioGroup radioGroup;
    private Button playAgain;
    AlertDialog alertDialog = null;
    private ArrayList<String> numOfAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        questionsText = findViewById(R.id.textView_questions);
        nextQuestion = findViewById(R.id.textView_next_question);
        radioButtonOne = findViewById(R.id.radioButton_ans_one);
        radioButtonTwo = findViewById(R.id.radioButton_ans_two);
        radioButtonThree = findViewById(R.id.radioButton_ans_three);
        radioGroup = findViewById(R.id.radioGroup);
        playAgain = findViewById(R.id.button_play_again);
        setQuestions();
        nextQuestion();

    }


    public void setQuestions() {
        nextQuestion.setClickable(true);
        Question item = questions.get(questionIndex);
        questionsText.setText(item.getQuestion());
        String[] ans = item.getAnswers();
        radioButtonOne.setText(ans[0]);
        radioButtonTwo.setText(ans[1]);
        radioButtonThree.setText(ans[2]);
        questionIndex++;
    }

    private void playAgain() {
        if (questionIndex == 4) {
            questionIndex = 0;
            selectedAnswer.clear();
            playAgain.setVisibility(View.VISIBLE);
            playAgain.setOnClickListener(v -> {

                nextQuestion.setText("Next Question");
                setQuestions();
                playAgain.setVisibility(View.INVISIBLE);

            });
        }
    }

    private void nextQuestion() {
        nextQuestion.setOnClickListener(v -> {
            if (!radioButtonOne.isChecked() && !radioButtonTwo.isChecked() && !radioButtonThree.isChecked()) {
                Toast.makeText(this, "Select an answer", Toast.LENGTH_SHORT).show();
            } else if (questionIndex <= questions.size() - 1) {

                isAnswerSelected();
                // radioButtonOne.setChecked(false);
                // radioButtonTwo.setChecked(false);
                //radioButtonThree.setChecked(false);
                setQuestions();
            } else if (questionIndex == 4) {

                processAnswers();
                playAgain();
              
            }
        });


    }

    public boolean isAnswerSelected() {
        int id = radioGroup.getCheckedRadioButtonId();
        switch (id) {
            case R.id.radioButton_ans_one:
                if (radioButtonOne.isChecked()) {
                    selectedAnswer.add(radioButtonOne.getText().toString());
                    Toast.makeText(this, radioButtonOne.getText().toString(), Toast.LENGTH_SHORT).show();
                    return true;
                }


            case R.id.radioButton_ans_two:
                if (radioButtonTwo.isChecked()) {
                    selectedAnswer.add(radioButtonTwo.getText().toString());
                    Toast.makeText(this, radioButtonTwo.getText().toString(), Toast.LENGTH_SHORT).show();
                    return true;
                }

            case R.id.radioButton_ans_three:
                if (radioButtonThree.isChecked()) {
                    selectedAnswer.add(radioButtonThree.getText().toString());
                    Toast.makeText(this, radioButtonThree.getText().toString(), Toast.LENGTH_SHORT).show();
                    return true;
                }

            default:
                return false;
        }


    }

    private void processAnswers() {
        ArrayList<String> correctAnswers = Question.getCorrectAnswers();
        if (correctAnswers.equals(selectedAnswer)) {
            nextQuestion.setText("End of Quiz");
            nextQuestion.setClickable(false);
            showSuccesAlert();
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } else if (!correctAnswers.equals(selectedAnswer)) {
            numOfAns = new ArrayList<>();
            for (String ans : selectedAnswer) {
                if (correctAnswers.contains(ans)) {
                    numOfAns.add(ans);
                }
            }
            showQuestionsCorrect();
            nextQuestion.setText("End of Quiz");
            nextQuestion.setClickable(false);
        }

    }

    private void showSuccesAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");
        builder.setMessage("Congratulations! \nYou got all questions correctly");
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);
    }

    private void showQuestionsCorrect() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Result");
        builder.setMessage("You got " + numOfAns.size() + " questions correctly");
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCanceledOnTouchOutside(false);
    }
}