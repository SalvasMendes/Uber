package exceptions;

public class UserExistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "Utilizador existente.";
	}

}
