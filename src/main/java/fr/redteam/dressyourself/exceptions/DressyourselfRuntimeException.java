package fr.redteam.dressyourself.exceptions;

public class DressyourselfRuntimeException extends RuntimeException {

  private static final long serialVersionUID = -6833445489659952596L;

  public DressyourselfRuntimeException() {
    super();
  }

  public DressyourselfRuntimeException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  public DressyourselfRuntimeException(String detailMessage) {
    super(detailMessage);
  }

  public DressyourselfRuntimeException(Throwable throwable) {
    super(throwable);
  }

}
