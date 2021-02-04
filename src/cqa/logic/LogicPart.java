package cqa.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cqa.domain.Question;
import cqa.various.StringValidationException;
import cqa.various.Various;

public class LogicPart {

	private List<Question> questions = new ArrayList<Question>();

	public void storeQA(String insertedString) throws StringValidationException {
		StringBuffer sb = new StringBuffer(insertedString);
		int k = sb.indexOf("?");
		final String questionI = sb.substring(0, k);
		String answersI = sb.substring(k + 1, insertedString.length());
		if (!answersI.contains("\"")) {//shoud be even
			throw new StringValidationException(Various.NO_ANSWER_INSERTED.getMessage());
		}

		System.out.println("q: " + questionI);
		System.out.println("a: " + answersI);

		char[] answersCharA = answersI.toCharArray();
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
				answers.add(answersI.substring(wordFrom, j));
			}
		}
		for (String s : answers) {
			System.out.println("a :" + s + ":");
		}
		questions.add(new Question(questionI, answers));
	}

	public Question retrieve(String insertedString) {
		return questions.stream().filter(q -> q.getQuestion().equals(insertedString)).collect(Collectors.toList()).get(0);//with null fails TODO
	}

	public String validateLengthString(String str) {
		if (str == null || str.isEmpty()) {
			return Various.STRING_ERROR.getMessage();
		}
		if (str.length() > 255) {
			return Various.LENGTH_ERROR.getMessage();
		}
		return null;
	}
}
