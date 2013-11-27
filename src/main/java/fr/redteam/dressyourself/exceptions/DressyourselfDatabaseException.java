package fr.redteam.dressyourself.exceptions;

public class DressyourselfDatabaseException extends RuntimeException{

  private static final long serialVersionUID = -877583918486436304L;

  public DressyourselfDatabaseException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public DressyourselfDatabaseException(String detailMessage) {
    super(detailMessage);
  }

  public DressyourselfDatabaseException(Throwable throwable) {
    super(throwable);
  }
}
