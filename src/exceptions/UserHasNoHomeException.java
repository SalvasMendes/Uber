package exceptions;

public class UserHasNoHomeException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "Utilizador nao e proprietario.";
	}
	
}
