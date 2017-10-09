package eds;

public class EmptyListException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage(){
		return "Pesquisa nao devolveu resultados.";
	}

}
