import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class quiz{

    // Helper class to hold question data structure
    static class Question {
        private String prompt;
        private List<String> options;
        private int correctAnswerIndex; // 0-based index (0=A, 1=B, etc.)

        public Question(String prompt, List<String> options, int correctAnswerIndex) {
            this.prompt = prompt;
            this.options = options;
            this.correctAnswerIndex = correctAnswerIndex;
        }

        public String getPrompt() { return prompt; }
        public List<String> getOptions() { return options; }
        
        public boolean isCorrect(int userChoice) {
            return userChoice == correctAnswerIndex;
        }

        public String getCorrectAnswerText() {
            return options.get(correctAnswerIndex);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> quizBank = new ArrayList<>();

        // 1. Add your questions here
        quizBank.add(new Question(
            "Which data type is used to create a variable that should store text in Java?",
            Arrays.asList("String", "txt", "char", "myString"),
            0 // "String" is at index 0
        ));

        quizBank.add(new Question(
            "How do you insert COMMENTS in Java code?",
            Arrays.asList("# This is a comment", "// This is a comment", "/* This is a comment", "<!-- This is a comment -->"),
            1 // "// This is a comment" is at index 1
        ));

        quizBank.add(new Question(
            "Which method can be used to find the length of a string in Java?",
            Arrays.asList("getSize()", "getLength()", "length()", "len()"),
            2 // "length()" is at index 2
        ));

        System.out.println("=== Welcome to the Java Quiz! ===");
        System.out.println("Answer by typing the option number (1, 2, 3, or 4).\n");

        int score = 0;

        // 2. Loop through the questions
        for (int i = 0; i < quizBank.size(); i++) {
            Question currentQuestion = quizBank.get(i);
            System.out.println("Question " + (i + 1) + ": " + currentQuestion.getPrompt());
            
            List<String> options = currentQuestion.getOptions();
            for (int j = 0; j < options.size(); j++) {
                System.out.println("  " + (j + 1) + ". " + options.get(j));
            }

            // 3. User input validation loop
            int userChoice = -1;
            while (true) {
                System.out.print("Your answer: ");
                if (scanner.hasNextInt()) {
                    userChoice = scanner.nextInt();
                    if (userChoice >= 1 && userChoice <= options.size()) {
                        break; 
                    }
                } else {
                    scanner.next(); // Clear invalid non-integer string
                }
                System.out.println("Invalid choice. Please enter a number between 1 and " + options.size());
            }

            // 4. Score tracking (subtract 1 to match 0-based index)
            if (currentQuestion.isCorrect(userChoice - 1)) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Wrong. The correct answer was: " + currentQuestion.getCorrectAnswerText() + "\n");
            }
        }

        // 5. Final results display
        System.out.println("=== Quiz Over! ===");
        System.out.println("Your Final Score: " + score + " / " + quizBank.size());
        double percentage = ((double) score / quizBank.size()) * 100;
        System.out.printf("Percentage: %.2f%%\n", percentage);

        scanner.close();
    }
}