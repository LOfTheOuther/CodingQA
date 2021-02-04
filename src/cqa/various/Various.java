package cqa.various;

public enum Various {
	LENGTH_ERROR("Error: String too long."),
	STRING_ERROR("Error: String not validated."),
	NO_ANSWER_INSERTED("You need to insert at least one answer."),
	ANSWER_TO_EVERYTHING("The answer to life, universe and everything is 42"),
	QUIT("QUIT");

	private String message;

	Various(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
