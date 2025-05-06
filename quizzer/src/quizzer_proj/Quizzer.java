package quizzer_proj;
import java.sql.SQLException;
import java.util.Scanner;

public class Quizzer {
    public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		while(true) {
			String questiongroup[] = new String[6];
			System.out.println("Pick your choice: \n 1. Take up the test \n 2. Add a question \n 3. Exit");
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
				case 1:
					Questions q = new Questions(sc);
					q.getQuestion();
					q.printQuestion();
					break;
				case 2:
					System.out.println("Enter the question:");
					questiongroup[0]=sc.nextLine();
					System.out.println("Enter the first option");
					questiongroup[1]=sc.nextLine();
					System.out.println("Enter the second option");
					questiongroup[2]=sc.nextLine();
					System.out.println("Enter the third option");
					questiongroup[3]=sc.nextLine();
					System.out.println("Enter the fourth option");
					questiongroup[4]=sc.nextLine();
					System.out.println("Enter the answer");
					questiongroup[5]=sc.nextLine();
					AddQuestion aq = new AddQuestion(questiongroup);
					aq.Add();
					break;
				case 3:
					System.out.println("Exiting...");
					return;
				default:
					System.out.println("Invalid choice... exiting!");
					break;
			}
		}
	}

}
