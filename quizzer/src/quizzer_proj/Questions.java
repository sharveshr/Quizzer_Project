package quizzer_proj;
import java.sql.*;
import java.util.*;

public class Questions {
private final Scanner scanner;
    private final List<String[]> questionsList = new ArrayList<>();

    public Questions(Scanner scanner) {
        this.scanner = scanner;
    }

    public void getQuestion() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT questions, optiona, optionb, optionc, optiond, answer FROM Quizzer");

        while (rs.next()) {
            String[] q = new String[6];
            q[0] = rs.getString("questions");
            q[1] = rs.getString("optiona");
            q[2] = rs.getString("optionb");
            q[3] = rs.getString("optionc");
            q[4] = rs.getString("optiond");
            q[5] = rs.getString("answer");
            questionsList.add(q);
        }

        Collections.shuffle(questionsList); 
    }

    public void printQuestion() {
        int score = 0;
        int total = questionsList.size();

        for (String[] q : questionsList) {
            System.out.println("Q: " + q[0]);

            List<String> options = new ArrayList<>(Arrays.asList(q).subList(1, 5));
            Collections.shuffle(options);

            char opt = 'A';
            Map<Character, String> optionMap = new LinkedHashMap<>();
            for (String option : options) {
                optionMap.put(opt, option);
                System.out.println(opt + ". " + option);
                opt++;
            }
            System.out.print("Enter your answer (A/B/C/D): ");
            String input = scanner.nextLine().toUpperCase();
            String selected = optionMap.getOrDefault(input.length() == 1 ? input.charAt(0) : ' ', "");

            if (selected.equals(q[5])) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! Correct answer: " + q[5]);
            }

            System.out.println();
        }

        System.out.println("Your final score: " + score + "/" + total);
    }

}
