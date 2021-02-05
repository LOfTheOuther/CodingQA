package cqa.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cqa.domain.Question;
import cqa.various.Messages;
import cqa.various.StringValidationException;

public class LogicPart {

	private List<Question> questions = new ArrayList<Question>();

	public void storeQA(String insertedString) throws StringValidationException {
		final StringBuffer sb = new StringBuffer(insertedString);
		if (!insertedString.contains("?")) {
			throw new StringValidationException(Messages.QUESTION_NOT_FOUND.getMessage());
		} else if (!insertedString.contains("\"")) {
			throw new StringValidationException(Messages.NO_ANSWER_INSERTED.getMessage());
		}
		int k = sb.indexOf("?");
		final String questionI = sb.substring(0, k + 1);
		validateLengthString(questionI);

		final String answersI = sb.substring(k + 1, insertedString.length());
		char[] answersCharA = answersI.toCharArray();
		if (countHowManyChar(answersCharA) % 2 != 0) {
			throw new StringValidationException(Messages.STRING_ERROR.getMessage());
		} else if (retrieve(questionI) != null) {
			throw new StringValidationException(Messages.QUESTION_DUPLICATED.getMessage());
		}

		List<String> answers = new ArrayList<String>();
		int wordFrom = 0;
		boolean first = false;
		for (int j = 0; j < answersCharA.length; j++) {
			if (answersCharA[j] == '"' && !first) {
				first = true;
				wordFrom = j + 1;
			} else if (answersCharA[j] == '"' && first) {
				first = false;
				if (j - wordFrom > 255) {
					throw new StringValidationException(Messages.LENGTH_ERROR.getMessage());
				} else if (j == wordFrom + 1) {
					throw new StringValidationException(Messages.STRING_ERROR.getMessage());
				}
				answers.add(answersI.substring(wordFrom, j));
			}
		}
		questions.add(new Question(questionI, answers));
	}

	public Question retrieve(String insertedString) {
		List<Question> qs = questions.stream().filter(q -> q.getQuestion().equals(insertedString)).collect(Collectors.toList());
		if (qs != null && qs.size() > 0) {
			return qs.get(0);
		}
		return null;
	}

	private void validateLengthString(String str) throws StringValidationException {
		if (str == null || str.isEmpty()) {
			throw new StringValidationException(Messages.STRING_ERROR.getMessage());
		}
		if (str.length() > 255) {
			throw new StringValidationException(Messages.LENGTH_ERROR.getMessage());
		}
	}

	private int countHowManyChar(char[] array) {
		int count = 0;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == '"') {
				count++;
			}
		}
		return count;
	}
}
