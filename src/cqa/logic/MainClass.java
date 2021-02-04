package cqa.logic;

import java.util.Scanner;

import cqa.domain.Question;
import cqa.various.Various;

public class MainClass {

	public static void main(String[] args) {
		LogicPart lp = new LogicPart();
		String insertedString = null;
		System.out.println("To insert use ?");
		System.out.println("To ask avoid it");
		System.out.println("To Exit write 'QUIT'.");
		Scanner in = new Scanner(System.in);

		do {
			insertedString = in.nextLine();
			if (Various.QUIT.getMessage().equalsIgnoreCase(insertedString)) {
				break;
			}

			String validated = lp.validateLengthString(insertedString);
			if (validated != null) {
				System.out.println(validated);
				System.out.println("Retry >>>");
			}

			if (insertedString.contains("?")) {//|| insertedString.charAt(insertedString.length() - 1) != '?'
				try {
					lp.storeQA(insertedString);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					System.out.println("Retry >>>");
				}
			} else {
				Question retrieved = lp.retrieve(insertedString);
				if (retrieved == null) {
					System.out.println(Various.ANSWER_TO_EVERYTHING.getMessage());
				} else {
					retrieved.prettyPrintMe();
				}
			}
		} while (true);
		in.close();
	}

}
