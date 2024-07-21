package task_3;
import java.util.Scanner;

public class QuizApplication {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        displayMenu(quiz);
    }

    private static void displayMenu(Quiz quiz) {
        int choice;
        do {
            printMenu();
            choice = getIntInput();

            switch (choice) {
                case 1:
                    addQuestion(quiz);
                    break;
                case 2:
                    if (quiz.questions.isEmpty()) {
                        System.out.println("Please add questions before starting the quiz.");
                    } else {
                        quiz.startQuiz();
                    }
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        } while (choice != 3);
    }

    private static void printMenu() {
        System.out.println("========== Quiz Menu ==========");
        System.out.println("1. Add Question");
        System.out.println("2. Start Quiz");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addQuestion(Quiz quiz) {
        System.out.print("Enter the question: ");
        String question = scanner.nextLine();

        System.out.print("Enter the number of options: ");
        int numOptions = getIntInput();

        String[] options = new String[numOptions];
        for (int i = 0; i < numOptions; i++) {
            System.out.print("Enter option " + (i + 1) + ": ");
            options[i] = scanner.nextLine();
        }

        System.out.print("Enter the index of the correct answer (1-" + numOptions + "): ");
        int correctAnswerIndex = getIntInput() - 1;

        quiz.addQuestion(new Question(question, options, correctAnswerIndex));
        System.out.println("Question added successfully!");
        System.out.println();
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over after nextInt()
        return input;
    }
}