package com.richieoscar.quiztrivia;

import java.util.ArrayList;

public class Question {
    String question;
    String[] answers;

    private static String[] ans = {"Solar Energy", "Chemical Energy", "Block Energy"};
    private static String[] ans2 = {"Aliko Dangote", "Will Smith", "Bill Gates"};
    private static String[] ans3 = {"Cheetah", "Dog", "Horse"};
    private static String[] ans4 = {"Java", "Kotlin", "English"};
    private static String[] ans5 = {"Barrack Obama", "Joe Biden", "Taylor Swift"};
    private static String[] ans6 = {"Bitcoin", "Ass Coin", "Triton"};

    public Question(String question, String[] answers) {
        this.question = question;
        this.answers = answers;
    }

    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question("1. Types of Energy?", ans));
        questions.add(new Question("2. Richest Man in Africa?", ans2));
        questions.add(new Question("3. Fastest Land Animal?", ans3));
        questions.add(new Question("4. Examples of Programming Languages?", ans4));
//        questions.add(new Question("Current US President?", ans5));
//        questions.add(new Question("Type of CryptoCurrency?", ans6));
        return questions;
    }

    public  static ArrayList<String> getCorrectAnswers() {
        ArrayList<String> correctAnswers = new ArrayList<>();
        correctAnswers.add("Solar Energy");
        correctAnswers.add("Chemical Energy");
        correctAnswers.add("Aliko Dangote");
        correctAnswers.add("Java");
        correctAnswers.add("Kotlin");
        correctAnswers.add("Cheetah");
        return correctAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getAnswers() {
        return answers;
    }
}
