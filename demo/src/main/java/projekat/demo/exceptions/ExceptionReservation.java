package projekat.demo.exceptions;

public class ExceptionReservation extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExceptionReservation() { super(); }
	public ExceptionReservation(String message) { super(message); }
	public ExceptionReservation(String message, Throwable cause) { super(message, cause); }
	public ExceptionReservation(Throwable cause) { super(cause); }

}
