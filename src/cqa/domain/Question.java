package cqa.domain;

import java.util.ArrayList;
import java.util.List;

public class Question {
	private List<String> answers = new ArrayList<>();
	private String question;

	public Question(String question, List<String> answers) {
		this.setQuestion(question);
		this.answers = answers;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void prettyPrintMe() {
		System.out.println(question);
		for (String a : answers) {
			System.out.println("     " + a);
		}
	}
}
