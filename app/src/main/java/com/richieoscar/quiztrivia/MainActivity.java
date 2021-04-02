package com.richieoscar.quiztrivia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.radiobutton.MaterialRadioButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView questionOne;
    private TextView questionTwo;
    private TextView questionThree;
    private TextView questionFour;
    private MaterialRadioButton radioButtonOne;
    private MaterialRadioButton radioButtonTwo;
    private MaterialRadioButton radioButtonThree;
    private CheckBox checkBoxQ1One;
    private CheckBox checkBoxQ1Two;
    private CheckBox checkBoxQ1Three;
    private CheckBox checkBoxQ4One;
    private CheckBox checkBoxQ4Two;
    private CheckBox checkBoxQ4Three;
    private EditText inputAnswer;
    private ArrayList<Question> questions = Question.getQuestions();
    private ArrayList<String> selectedAnswer = new ArrayList<>();
    private Button submit;
    private Button playAgain;
    private ArrayList<String> correctAnswers;
    private boolean check;
    private int score;
    private boolean checkTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setQuestions();
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
        playAgain.setEnabled(false);
    }

    private void playAgain() {
        playAgain.setOnClickListener(v -> {
            selectedAnswer.clear();
            score = 0;
            check = false;
            checkTwo = false;
            inputAnswer.setText("");
            checkBoxQ1One.setChecked(false);
            checkBoxQ1Two.setChecked(false);
            checkBoxQ1Three.setChecked(false);
            checkBoxQ4One.setChecked(false);
            checkBoxQ4Two.setChecked(false);
            checkBoxQ4Three.setChecked(false);
            radioButtonOne.setChecked(false);
            setQuestions();
            submit.setEnabled(true);
        });
    }

    private void getAnswerForQuestionOne() {
        if (isAllCheckedQuestionOne()) {
            //Failed the question if all checkbox is checked
            score = 0;
        } else {
            if (checkBoxQ1One.isChecked()) {
                selectedAnswer.add(checkBoxQ1One.getText().toString());
            }
            if (checkBoxQ1Two.isChecked()) {
                selectedAnswer.add(checkBoxQ1Two.getText().toString());
            }
            if (checkBoxQ1Three.isChecked()) {
                selectedAnswer.add(checkBoxQ1Three.getText().toString());
            }
        }
    }

    private void getAnswerForQuestionFour() {
        if (isAllCheckedQuestionFour()) {
            //failed  the question if all checkbox is checked
            score = 0;
        } else {
            if (checkBoxQ4One.isChecked()) {
                selectedAnswer.add(checkBoxQ4One.getText().toString());
            }
            if (checkBoxQ4Two.isChecked()) {
                selectedAnswer.add(checkBoxQ4Two.getText().toString());
            }
            if (checkBoxQ4Three.isChecked()) {
                selectedAnswer.add(checkBoxQ4Three.getText().toString());
            }
        }
    }

    private void getAnswerForTwoAndThree() {
        if (radioButtonOne.isChecked()) {
            selectedAnswer.add(radioButtonOne.getText().toString());
        }
        if (radioButtonTwo.isChecked()) {
            selectedAnswer.add(radioButtonTwo.getText().toString());
        }
        if (radioButtonThree.isChecked()) {
            selectedAnswer.add(radioButtonThree.getText().toString());
        }

        if (!inputAnswer.getText().toString().isEmpty()) {
            selectedAnswer.add(inputAnswer.getText().toString().trim().toUpperCase());
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
            processAnswers();
            submit.setEnabled(false);
            playAgain.setEnabled(true);
        });
    }

    /*
   This method makes sure the user selects the correct options for question one
   The User is only awarded a score when he/she selects the correct options
    */
    private boolean isAllCheckedQuestionOne() {
        if (checkBoxQ1One.isChecked() && checkBoxQ1Two.isChecked() && checkBoxQ1Three.isChecked()) {
            check = true;
        }
        if (checkBoxQ1One.isChecked() && checkBoxQ1Three.isChecked()) {
            check = true;
        }
        if (checkBoxQ1Two.isChecked() && checkBoxQ1Three.isChecked()) {
            check = true;
        }
        if (checkBoxQ1One.isChecked() && !checkBoxQ1Two.isChecked() && !checkBoxQ1Three.isChecked()) {
            check = true;
        }
        if (!checkBoxQ1One.isChecked() && checkBoxQ1Two.isChecked() && !checkBoxQ1Three.isChecked()) {
            check = true;
        }
        if (!checkBoxQ1One.isChecked() && !checkBoxQ1Two.isChecked() && checkBoxQ1Three.isChecked()) {
            check = true;
        }
        return check;
    }

    /*
    This method makes sure the user selects the correct options for question four
    The User is only awarded a score when he/she selects the correct options
     */
    private boolean isAllCheckedQuestionFour() {
        if (checkBoxQ4One.isChecked() && checkBoxQ4Two.isChecked() && checkBoxQ4Three.isChecked()) {
            checkTwo = true;
        }
        if (checkBoxQ4One.isChecked() && checkBoxQ4Three.isChecked()) {
            checkTwo = true;
        }
        if (checkBoxQ4Two.isChecked() && checkBoxQ4Three.isChecked()) {
            checkTwo = true;
        }
        if (checkBoxQ4One.isChecked() && !checkBoxQ4Two.isChecked() && !checkBoxQ4Three.isChecked()) {
            checkTwo = true;
        }
        if (!checkBoxQ4One.isChecked() && checkBoxQ4Two.isChecked() && !checkBoxQ4Three.isChecked()) {
            checkTwo = true;
        }
        if (!checkBoxQ4One.isChecked() && !checkBoxQ4Two.isChecked() && checkBoxQ4Three.isChecked()) {
            checkTwo = true;
        }
        return checkTwo;
    }

    private void processAnswers() {
        correctAnswers = Question.getCorrectAnswers();
        getAnswerForQuestionOne();
        getAnswerForTwoAndThree();
        getAnswerForQuestionFour();

        // check for correct answers in the list and give score
        if (selectedAnswer.contains(checkBoxQ1One.getText().toString())) score++;
        if (selectedAnswer.contains(checkBoxQ1Two.getText().toString())) score++;
        if (selectedAnswer.contains(checkBoxQ4One.getText().toString())) score++;
        if (selectedAnswer.contains(checkBoxQ4Two.getText().toString())) score++;
        if (selectedAnswer.contains(radioButtonOne.getText().toString())) score++;
        if (selectedAnswer.contains(correctAnswers.get(correctAnswers.size() - 1))) score++;
        Toast.makeText(this, "Your Score: " + score + "/" + correctAnswers.size(), Toast.LENGTH_SHORT).show();
    }
}