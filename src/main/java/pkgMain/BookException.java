package pkgMain;

public class BookException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BookException(String message) {
		super(message);
		System.out.println("\n" + message);
	}
}
