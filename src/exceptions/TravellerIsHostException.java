package exceptions;

public class TravellerIsHostException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "Viajante e proprietario";
	}

}
