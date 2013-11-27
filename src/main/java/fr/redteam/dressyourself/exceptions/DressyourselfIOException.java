package fr.redteam.dressyourself.exceptions;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
public class DressyourselfIOException extends RuntimeException {

  private static final long serialVersionUID = -4553676426831329801L;

  /**
   * 
   */
  public DressyourselfIOException() {
    super();
  }

  /**
   * @param detailMessage
   * @param throwable
   */
  public DressyourselfIOException(String detailMessage, Throwable throwable) {
    super(detailMessage, throwable);
  }

  /**
   * @param detailMessage
   */
  public DressyourselfIOException(String detailMessage) {
    super(detailMessage);
  }

  /**
   * @param throwable
   */
  public DressyourselfIOException(Throwable throwable) {
    super(throwable);
  }



}
