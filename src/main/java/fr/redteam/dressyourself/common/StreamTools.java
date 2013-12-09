package fr.redteam.dressyourself.common;

import java.io.InputStream;


public class StreamTools {

  private StreamTools() {}
	
  /**
   * Convert an InputStream into a String object
   * 
   * Source : http://stackoverflow.com/a/5445161
   */
  public static String convertStreamToString(InputStream is) {
	    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}
