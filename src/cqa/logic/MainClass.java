package cqa.logic;

import java.util.Scanner;

import cqa.domain.Question;
import cqa.various.Messages;

public class MainClass {

	public static void main(String[] args) {
		LogicPart lp = new LogicPart();
		String insertedString = null;
		System.out.println("Welcome");
		Scanner in = new Scanner(System.in);
		boolean continueLoop = true;
		do {

			System.out.println("To insert question press 1");
			System.out.println("To ask             press 2");
			System.out.println("To Exit write 'QUIT'.");
			System.out.print(" >>> ");
			insertedString = in.nextLine();
			switch (insertedString) {
				case "1":
					System.out.print(" >>> ");
					insertedString = in.nextLine();
					try {
						lp.storeQA(insertedString);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					break;
				case "2":
					System.out.print(" >>> ");
					insertedString = in.nextLine();
					Question retrieved = lp.retrieve(insertedString);
					if (retrieved == null) {
						System.out.println(Messages.ANSWER_TO_EVERYTHING.getMessage());
					} else {
						retrieved.prettyPrintMe();
					}
					break;
				case "QUIT":
					continueLoop = false;
					break;

				default:
					System.out.println("--------------------------");
					break;
			}

			System.out.println("--------------------------");

		} while (continueLoop);

		System.out.println("-------------The End-------------");
		in.close();
	}

}
