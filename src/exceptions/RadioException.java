package exceptions;

public class RadioException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RadioException() {
		super();
	}

	public RadioException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public RadioException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public RadioException(String arg0) {
		super(arg0);
	}

	public RadioException(Throwable arg0) {
		super(arg0);
	}

}