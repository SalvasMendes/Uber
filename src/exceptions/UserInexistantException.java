package exceptions;

public class UserInexistantException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "Utilizador inexistente.";
	}

}
