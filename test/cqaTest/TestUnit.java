package cqaTest;

import org.junit.Test;

import cqa.domain.Question;
import cqa.logic.LogicPart;
import cqa.various.Messages;

public class TestUnit {

	private final LogicPart lp = new LogicPart();

	@Test
	public void testMyProgram() {
		System.out.println("---------Inserting---------");
		final String[] questionsAndAnswers = { "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"", "What is Peters favorite food \"pizza\"", "What is Peters favorite food? \"Pizza",
		        "What is Peters favorite food? ", "What is the unicorn color? \"Pink\"" };

		for (String s : questionsAndAnswers) {
			try {
				System.out.println(s);
				lp.storeQA(s);
				System.out.println("---------OK---------");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			} finally {
				System.out.println("");
			}
		}

		System.out.println("---------Asking---------");
		final String frage1 = "What is Peters favorite food?";
		System.out.println(frage1);

		Question retrieved1 = lp.retrieve(frage1);
		if (retrieved1 == null) {
			System.out.println(Messages.ANSWER_TO_EVERYTHING.getMessage());
		} else {
			retrieved1.prettyPrintMe();
		}

		System.out.println("");
		System.out.println("---------Asking---------");
		final String frage2 = "When is Peters birthday?";
		System.out.println(frage2);

		Question retrieved2 = lp.retrieve(frage2);
		if (retrieved2 == null) {
			System.out.println(Messages.ANSWER_TO_EVERYTHING.getMessage());
		} else {
			retrieved2.prettyPrintMe();
		}
	}

}
