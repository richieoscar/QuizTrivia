package com.richieoscar.quiztrivia;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int ONE = 1;
    private TextView questionOne, questionTwo, questionThree, questionFour;
    private MaterialRadioButton radioButtonOne, radioButtonTwo, radioButtonThree;
    private CheckBox checkBoxQ1One, checkBoxQ1Two, checkBoxQ1Three, checkBoxQ4One, checkBoxQ4Two, checkBoxQ4Three;
    private EditText inputAnswer;
    ArrayList<Question> questions = Question.getQuestions();
    ArrayList<String> selectedAnswer = new ArrayList<>();
    private Button submit;
    private int questionIndex;
    private RadioGroup radioGroup;
    private Button playAgain;
    AlertDialog alertDialog = null;
    private ArrayList<String> numOfAns;
    private ArrayList<String> correctAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();


        setQuestions();
        //getSelectedAnswers();
        submit();
        playAgain();

    }

    private void initializeViews() {
        questionOne = findViewById(R.id.textView_question_one);
        questionTwo = findViewById(R.id.textView_question_two);
        questionThree = findViewById(R.id.textView_question_three);
        questionFour = findViewById(R.id.textView_question_four);
        submit = findViewById(R.id.button_submit);
        radioButtonOne = findViewById(R.id.radioButton_one);
        radioButtonTwo = findViewById(R.id.radioButton_two);
        radioButtonThree = findViewById(R.id.radioButton_three);
        radioGroup = findViewById(R.id.radioGroup);
        playAgain = findViewById(R.id.button_play_again);
        checkBoxQ1One = findViewById(R.id.checkBox_q1_one);
        checkBoxQ1Two = findViewById(R.id.checkBox_q1_two);
        checkBoxQ1Three = findViewById(R.id.checkBox_q1_three);
        checkBoxQ4One = findViewById(R.id.checkBox_q4_one);
        checkBoxQ4Two = findViewById(R.id.checkBox_q4_two);
        checkBoxQ4Three = findViewById(R.id.checkBox_q4_three);
        inputAnswer = findViewById(R.id.editText_input_answer);

    }


    public void setQuestions() {
        submit.setClickable(true);
        Question item = null;
        String[] ans = null;
        for (int i = 0; i < questions.size(); i++) {
            switch (i) {
                case 0:
                    item = questions.get(i);
                    ans = item.getAnswers();
                    questionOne.setText(item.getQuestion());
                    checkBoxQ1One.setText(ans[0]);
                    checkBoxQ1Two.setText(ans[1]);
                    checkBoxQ1Three.setText(ans[2]);
                    break;

                case 1:
                    item = questions.get(i);
                    ans = item.getAnswers();
                    questionTwo.setText(item.getQuestion());
                    radioButtonOne.setText(ans[0]);
                    radioButtonTwo.setText(ans[1]);
                    radioButtonThree.setText(ans[2]);
                    break;

                case 2:
                    item = questions.get(i);
                    ans = item.getAnswers();
                    questionThree.setText(item.getQuestion());
                    break;
                case 3:
                    item = questions.get(i);
                    ans = item.getAnswers();
                    questionFour.setText(item.getQuestion());
                    checkBoxQ4One.setText(ans[0]);
                    checkBoxQ4Two.setText(ans[1]);
                    checkBoxQ4Three.setText(ans[2]);
                    break;

                default:
                    break;
            }
        }
    }


    private void playAgain() {
        playAgain.setOnClickListener(v -> {
            selectedAnswer.clear();
            inputAnswer.setText("");
            checkBoxQ1One.setChecked(false);
            checkBoxQ1Two.setChecked(false);
            checkBoxQ1Three.setChecked(false);
            checkBoxQ4One.setChecked(false);
            checkBoxQ4Two.setChecked(false);
            checkBoxQ4Three.setChecked(false);
            radioButtonOne.setChecked(false);
            setQuestions();

        });


    }

    private void nextQuestion() {
        submit.setOnClickListener(v -> {
            if (!radioButtonOne.isChecked() && !radioButtonTwo.isChecked() && !radioButtonThree.isChecked()) {
                Toast.makeText(this, "Select an answer", Toast.LENGTH_SHORT).show();
            } else if (questionIndex <= questions.size() - ONE) {
                //getSelectedAnswers();
                setQuestions();
            } else {
                processAnswers();
                playAgain();

            }
        });


    }

    public void getSelectedAnswers() {

        if (radioButtonOne.isChecked()) {
            selectedAnswer.add(radioButtonOne.getText().toString());
        } else if (radioButtonTwo.isChecked()) {
            selectedAnswer.add(radioButtonTwo.getText().toString());
        } else if (radioButtonThree.isChecked()) {
            selectedAnswer.add(radioButtonThree.getText().toString());
        } else if (checkBoxQ1One.isChecked()) {
            selectedAnswer.add(checkBoxQ1One.getText().toString());
        } else if (checkBoxQ1Two.isChecked()) {
            selectedAnswer.add(checkBoxQ1Two.getText().toString());
        } else if (checkBoxQ1Three.isChecked()) {
            selectedAnswer.add(checkBoxQ1Three.getText().toString());
        } else if (checkBoxQ4One.isChecked()) {
            selectedAnswer.add(checkBoxQ4One.getText().toString());

        } else if (checkBoxQ4Two.isChecked()) {
            selectedAnswer.add(checkBoxQ4Two.getText().toString());
        } else if (checkBoxQ4Three.isChecked()) {
            selectedAnswer.add(checkBoxQ4Three.getText().toString());
        } else if (!inputAnswer.getText().toString().isEmpty()) {
            selectedAnswer.add(inputAnswer.getText().toString());
        }


    }

    private void validate() {
        if (!radioButtonOne.isChecked() && !radioButtonTwo.isChecked() && !radioButtonThree.isChecked()) {
            Toast.makeText(this, "Select an answer for question 2", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!checkBoxQ1One.isChecked() && !checkBoxQ1Two.isChecked() && !checkBoxQ1Three.isChecked()) {
            Toast.makeText(this, "Select an answer for question 1", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!checkBoxQ4One.isChecked() && !checkBoxQ4Two.isChecked() && !checkBoxQ4Three.isChecked()) {
            Toast.makeText(this, "Select an answer for question 4", Toast.LENGTH_SHORT).show();
            return;
        }
        if (inputAnswer.getText().toString().isEmpty()) {
            Toast.makeText(this, "Enter an answer for question 3", Toast.LENGTH_SHORT).show();
            return;
        }


    }

    private void submit() {
        submit.setOnClickListener(v -> {
            validate();
            //getSelectedAnswers();
            processAnswers();
        });

    }

    private void processAnswers() {

        correctAnswers = Question.getCorrectAnswers();
        getSelectedAnswers();
        if (correctAnswers.equals(selectedAnswer)) {
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
        builder.setMessage("Score " + numOfAns.size() + "/" + correctAnswers.size());
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