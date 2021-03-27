package com.richieoscar.quiztrivia;

import java.util.ArrayList;

public class Question {
    String question;
    String[] answers;

    private static String[] ans = {"Solar Energy", "Car Energy", "Block Energy"};
    private static String[] ans2 = {"Aliko Dangote", "Will Smith", "Bill Gates"};
    private static String[] ans3 = {"Cheetah", "Dog", "Horse"};
    private static String[] ans4 = {"3.142", "2.42", "9.8"};

    public Question(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
    }

    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question("Type of Energy?", ans));
        questions.add(new Question("Richest Man in Africa?", ans2));
        questions.add(new Question("Fastest Land Animal?", ans3));
        questions.add(new Question("Value of PI Constant?", ans4));
        return questions;
    }

    public  static ArrayList<String> getCorrectAnswers(){
        ArrayList<String> correctAnswers = new ArrayList<>();
        correctAnswers.add("Solar Energy");
        correctAnswers.add("Aliko Dangote");
        correctAnswers.add("Cheetah");
        //correctAnswers.add("3.142");

        return correctAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }
}
