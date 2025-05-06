package quizzer_proj;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddQuestion {
    private String question;
	private String optiona;
	private String optionb;
	private String optionc;
	private String optiond;
	private String answer;
	public AddQuestion(String[] questiontuple) {
		this.question = questiontuple[0];
		this.optiona = questiontuple[1];
		this.optionb = questiontuple[2];
		this.optionc = questiontuple[3];
		this.optiond = questiontuple[4];
		this.answer = questiontuple[5];
	}
	public void Add() {
		String sql = "INSERT INTO Quizzer (questions, optiona, optionb, optionc, optiond, answer) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DBConnection.getConnection();
	    PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, question);
	        stmt.setString(2, optiona);
	        stmt.setString(3, optionb);
	        stmt.setString(4, optionc);
	        stmt.setString(5, optiond);
	        stmt.setString(6, answer);
	        stmt.executeUpdate();
	        System.out.println("Question added successfully!");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}
		

}
