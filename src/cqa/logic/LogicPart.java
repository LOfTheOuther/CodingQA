package cqa.logic;

import java.util.ArrayList;
import java.util.List;

import cqa.domain.Question;
import cqa.various.StringValidationException;
import cqa.various.Various;

public class LogicPart {

	private List<Question> questions = new ArrayList<Question>();

	public void storeQA(String insertedString) throws StringValidationException {
		String[] separated = insertedString.split("?");
		String question = separated[0];
		if (separated[1].length() == 0 || !separated[1].contains("\"")) {//add control over quantity of " (should be even)
			throw new StringValidationException(Various.STRING_ERROR.getMessage());//no answer found
		}

		char[] answersCharA = separated[1].toCharArray();
		//remove all spaces
		List<String> answers = new ArrayList<String>();//populate this in a for cycling the array
		int wordFrom = 0;
		boolean first = false;
		for (int j = 0; j < answersCharA.length; j++) {//extract words when the second " is found
			if (answersCharA[j] == '"' && !first) {
				first = true;
				wordFrom = j + 1;
			} else if (answersCharA[j] == '"' && first) {
				first = false;
				if (j - wordFrom > 255) {
					throw new StringValidationException(Various.LENGTH_ERROR.getMessage());
				} else if (j == wordFrom + 1) {
					throw new StringValidationException(Various.STRING_ERROR.getMessage());
				}
				answers.add(separated[1].substring(wordFrom, j));
			}
		}

		questions.add(new Question(question, answers));
	}

	public Question retrieve(String insertedString) {
		for (Question q : questions) {
			if (q.getQuestion().equals(insertedString)) {
				return q;
			}
		}
		return null;
	}

	public String validateLengthString(String str) {
		if (str == null) {
			return Various.STRING_ERROR.getMessage();
		}
		if (str.length() > 255) {
			return Various.LENGTH_ERROR.getMessage();
		}
		return null;
	}
}
