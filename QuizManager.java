package quizzing;

import java.sql.*;
import java.util.*;

public class QuizManager {
    private List<String[]> questionsList;

    public QuizManager() throws SQLException, ClassNotFoundException {
        questionsList = new ArrayList<>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
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

    public List<String[]> getQuestions() {
        return questionsList;
    }
}
