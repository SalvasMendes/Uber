package exceptions;

public class UserNotTravalledException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "Utilizador nao viajou.";
	}

}
