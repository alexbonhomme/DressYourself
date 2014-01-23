package fr.redteam.dressyourself.core.api;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import fr.redteam.dressyourself.common.filemanager.FileManager;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.exceptions.DressyourselfIOException;
import fr.redteam.dressyourself.exceptions.DressyourselfRuntimeException;
import fr.redteam.dressyourself.utils.StreamTools;

/**
 * This abstract class grouped some commons methods to brands APIs.
 * 
 * @author Alexandre Bonhomme
 * 
 */
public abstract class APIAbstractHelper {

  private final FileManager manager;

  protected APIAbstractHelper(FileManager manager) {
    this.manager = manager;
  }

  /**
   * Get a plain text content from an {@link java.net.URL url}
   */
  protected String getContent(URL url) {
    String content = "";
    HttpURLConnection urlConnection = null;

    try {
      urlConnection = (HttpURLConnection) url.openConnection();
      InputStream in = new BufferedInputStream(urlConnection.getInputStream());
      content = StreamTools.convertStreamToString(in);

    } catch (IOException e) {
      throw new DressyourselfIOException(e);
    } finally {
      try {
        urlConnection.disconnect();
      } catch (NullPointerException _) {
        ; // DO NOTHING (we just check is the connection is established before)
      }
    }

    return content;
  }
  
  /**
   * Build a {@link Clothe Clothe} object from a JSON representation
   * 
   * @throws JSONException
   */
  protected Clothe buildClotheFromJSONObject(JSONObject json) throws JSONException {
    Clothe product = new Clothe();

    product.setId(json.getInt("id"));
    product.setModel(json.getString("model"));
    product.setBrand(json.getString("brand"));
    product.setColor(json.getString("color"));
    product.setType(json.getString("type"));
    product.setBodies(json.getString("bodies"));
    product.setImageRelativePath(json.getString("imagePath"));
    
    // Writing image into the device storage from a web stream
    try {
      URL imageUrl = new URL(json.getString("imageUrl"));
      InputStream imageStream = new BufferedInputStream(imageUrl.openStream());      
      manager.writeFileToStorage(product.getImageRelativePath(), imageStream);
      imageStream.close();

    } catch (MalformedURLException e) {
      throw new DressyourselfRuntimeException("MalformedURLException", e);
    } catch (IOException e) {
      throw new DressyourselfIOException(e);
    }

    return product;
  }
}
