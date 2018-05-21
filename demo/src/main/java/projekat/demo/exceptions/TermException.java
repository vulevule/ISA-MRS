package projekat.demo.exceptions;

import projekat.demo.model.Term;

public class TermException extends RuntimeException {

	private Term term;
	private String message;

	public TermException() {
	}

	public TermException(Term term, String message) {
		this();
		this.term = term;
		this.message = message;
	}

	public Term getTerm() {
		return term;
	}

	public void setTerm(Term term) {
		this.term = term;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
