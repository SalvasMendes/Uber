package exceptions;

public class PropertyExistsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "Propriedade existente";
	}

}
