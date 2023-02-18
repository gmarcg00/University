package aplicacion;

public class AutoKingException extends Exception{
	  private static final long serialVersionUID = 1L;
	  
	    /**
	     * Tiramos una excepción propia para manejar los casos extraños.
	     *
	     * @param message El mensaje de la excepcion a tratar
	     */
	    public AutoKingException(String message) {
	        super(message);
	    }
}
