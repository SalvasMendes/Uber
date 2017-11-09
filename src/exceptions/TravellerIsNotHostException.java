package exceptions;

public class TravellerIsNotHostException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "Viajante nao e o proprietario.";
	}

}
