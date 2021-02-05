package cqaTest;

import org.junit.Test;

import cqa.domain.Question;
import cqa.logic.LogicPart;
import cqa.various.Messages;

public class TestUnit {

	@Test
	public void testMyProgram() {
		System.out.println("---------Inserting---------");
		final String frageMitAntwort = "What is Peters favorite food? \"Pizza\" \"Spaghetti\" \"Ice cream\"";
		System.out.println(frageMitAntwort);
		final LogicPart lp = new LogicPart();
		try {
			lp.storeQA(frageMitAntwort);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("---------Asking---------");
		final String frage1 = "What is Peters favorite food?";

		Question retrieved1 = lp.retrieve(frage1);
		if (retrieved1 == null) {
			System.out.println(Messages.ANSWER_TO_EVERYTHING.getMessage());
		} else {
			retrieved1.prettyPrintMe();
		}

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
