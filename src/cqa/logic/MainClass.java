package cqa.logic;

import cqa.domain.Question;
import cqa.various.Various;

public class MainClass {

	public static void main(String[] args) {
		LogicPart lp = new LogicPart();
		String insertedString = null;

		System.out.println("To Exit write 'QUIT'.");

		do {
			//insert via consolle

			if (Various.QUIT.getMessage().equalsIgnoreCase(insertedString)) {

			}

			String validated = lp.validateLengthString(insertedString);

			if (validated == null) {
				System.out.println(validated);
				break;
			}

			if (insertedString.contains("?")) {
				//store q and a
				try {
					lp.storeQA(insertedString);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} else {
				Question retrieved = lp.retrieve(insertedString);
				if (retrieved == null) {
					System.out.println(Various.ANSWER_TO_EVERYTHING.getMessage());
				} else {
					retrieved.prettyPrintMe();
				}
			}
		} while (true);//TODO condition
	}

}
