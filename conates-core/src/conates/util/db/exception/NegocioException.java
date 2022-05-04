package conates.util.db.exception;

public class NegocioException extends Exception{
	
	public NegocioException(String msg, Exception exception) {
		super(msg, exception);		
	}
	
	public NegocioException(String msg) {
		super(msg);
	}
}