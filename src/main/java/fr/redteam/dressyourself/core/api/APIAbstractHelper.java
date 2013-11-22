package fr.redteam.dressyourself.core.api;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import fr.redteam.dressyourself.common.StreamTools;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.exceptions.DressyourselfRuntimeException;

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

    } catch (IOException e) {
      throw new DressyourselfRuntimeException("IOException", e);
    } finally {
      try {
        urlConnection.disconnect();
      } catch (NullPointerException _) {
        ;
      }
    }

    return content;
  }
  
  /**
   * Forge un objet Clothe à partir d'une représentation JSON des informations
   * 
   * @param json
   * @return
   * @throws JSONException
   */
  public Clothe buildClotheFromJSON(JSONObject json) throws JSONException {
    Clothe product = new Clothe();

    product.setId(json.getInt("id"));
    product.setModel(json.getString("model"));
    // TODO : clothe.setImage()
    product.setBrand(json.getString("brand"));
    product.setColor(json.getString("color"));
    product.setType(json.getString("type"));
    product.setBodies(json.getString("bodies"));

    return product;
  }
}
