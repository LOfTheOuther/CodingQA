package cqa.various;

public enum Messages {
	LENGTH_ERROR("Error: String too long."),
	STRING_ERROR("Error: String not validated."),
	QUESTION_DUPLICATED("Error: This question is already in our system."),
	QUESTION_NOT_FOUND("Error: Question mark '?' not found."),
	NO_ANSWER_INSERTED("You need to insert at least one answer."),
	ANSWER_TO_EVERYTHING("The answer to life, universe and everything is 42");

	private String message;

	Messages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
