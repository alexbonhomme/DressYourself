package fr.redteam.dressyourself.core.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.exceptions.DressyourselfRuntimeException;

public class APIZara extends APIAbstractHelper implements APIInterface {

  private static final String DB_PROTOCOL = "http";
  private static final String DB_HOST = "dev.alexandrebonhomme.fr";
  private static final String DB_PAGE = "/api.php";

  @Override
  public Clothe findClotheById(int id) {
    Clothe product = new Clothe();

    try {
      String jsonContent = getContent(new URL(DB_PROTOCOL, DB_HOST, DB_PAGE + "?id=" + id));
      product = buildClotheFromJSON(new JSONArray(jsonContent).getJSONObject(0));

    } catch (MalformedURLException e) {
      throw new DressyourselfRuntimeException(e);
    } catch (JSONException e) {
      throw new DressyourselfRuntimeException(e);
    }

    return product;
  }

  @Override
  public List<Clothe> findClothesByModelName(String modelName) {
    ArrayList<Clothe> listProducts = new ArrayList<Clothe>();

    try {
      String jsonContent =
          getContent(new URL(DB_PROTOCOL, DB_HOST, DB_PAGE + "?model=" + modelName));

      JSONArray arrayOfProducts = new JSONArray(jsonContent);
      for (int i = 0; i < arrayOfProducts.length(); i++) {
        Clothe product = buildClotheFromJSON(arrayOfProducts.getJSONObject(i));
        listProducts.add(product);
      }

    } catch (MalformedURLException e) {
      throw new DressyourselfRuntimeException(e);
    } catch (JSONException e) {
      throw new DressyourselfRuntimeException(e);
    }
    
    return listProducts;
  }

  @Override
  public List<Clothe> findClothesByType(String typeName) {
    // TODO Implement
    return Collections.emptyList();
  }

  @Override
  public List<Clothe> findAll(String query) {
    // TODO Implement
    return Collections.emptyList();
  }
}
