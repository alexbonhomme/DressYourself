package fr.redteam.dressyourself.exceptions;

public class DressyourselfDatabaseException extends RuntimeException{
	
	public DressyourselfDatabaseException(String detailMessage, Throwable throwable) {
	    super(detailMessage, throwable);
	  }

	public DressyourselfDatabaseException(String detailMessage) {
	    super(detailMessage);
	  }
	
	public DressyourselfDatabaseException( Throwable throwable) {
	    super( throwable);
	  }
}
