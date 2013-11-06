package fr.redteam.dressyourself.core.api;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;
import fr.redteam.dressyourself.common.StreamTools;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
public abstract class APIAbstractHelper {

  /**
   * 
   * @param url
   * @return the content of the page `url`
   */
  public String getContent(URL url) {
    String content = "";
    HttpURLConnection urlConnection = null;
    try {
      urlConnection = (HttpURLConnection) url.openConnection();
      InputStream in = new BufferedInputStream(urlConnection.getInputStream());
      content = StreamTools.convertStreamToString(in);
      urlConnection.disconnect();
    } catch (IOException e) {
      Log.w("HTTP IO", e.getMessage() == null ? "No message" : e.getMessage());
    } finally {
      if (urlConnection != null) {
        urlConnection.disconnect();
      }
    }

    return content;
  }
}
