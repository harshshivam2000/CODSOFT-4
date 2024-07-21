package task_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Quiz {
    List<Question> questions;
    private Scanner scanner;
    private Timer timer;
    private int score;
    private boolean quizRunning;

    public Quiz() {
        questions = new ArrayList<>();
        scanner = new Scanner(System.in);
        timer = new Timer();
        score = 0;
        quizRunning = false;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void startQuiz() {
        quizRunning = true;
        score = 0;
        System.out.println("Welcome to the Quiz!");
        System.out.println("You will have 60 seconds to answer as many questions as possible.");
        System.out.println("Press enter to begin...");
        scanner.nextLine();

        // Set up the timer for 60 seconds
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                endQuiz();
            }
        }, 60000); // 60 seconds

        // Start asking questions
        for (Question question : questions) {
            askMultipleChoiceQuestion(question);
        }

        // After all questions are asked or time is up
        endQuiz();
    }

    private void askMultipleChoiceQuestion(Question question) {
        System.out.println(question.getQuestion());

        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        int userChoice = getMultipleChoiceInput(options.length);

        if (userChoice == question.getCorrectAnswerIndex() + 1) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is: " + options[question.getCorrectAnswerIndex()]);
        }

        System.out.println();
    }

    private void endQuiz() {
        if (quizRunning) {
            quizRunning = false;
            timer.cancel();

            System.out.println("Quiz ended!");
            System.out.println("Your score is: " + score);

            if (getYesNoInput("Do you want to play again? (yes/no)")) {
                startQuiz();
            } else {
                System.out.println("Thank you for playing!");
            }
        }
    }

    private int getMultipleChoiceInput(int range) {
        int choice;
        do {
            System.out.print("Enter your choice (1-" + range + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
            choice = scanner.nextInt();
        } while (choice < 1 || choice > range);
        scanner.nextLine();
        return choice;
    }

    private boolean getYesNoInput(String message) {
        System.out.println(message);
        String input;
        do {
            input = scanner.nextLine().trim().toLowerCase();
        } while (!input.equals("yes") && !input.equals("no"));
        return input.equals("yes");
    }
}