package eds;

public class EmptyStackException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "Stack is empty.";
	}

}
