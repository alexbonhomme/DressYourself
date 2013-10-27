package main.java.fr.redteam.dressyourself.core.api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import main.java.fr.redteam.dressyourself.core.clothes.Clothe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class APIShopSense extends APIAbstractHelper implements APIInterface {

  private static final String API_BASE = "http://api.shopstyle.com/api/";
  private static final String API_PID = "uid7744-23872588-74";
  private static final String API_FORMAT = "json";


  /**
   * 
   */
  public APIShopSense() {
    super();

    JSONObject dresses = new JSONObject();

    try {
      dresses = getProducts("dresses");
    } catch (MalformedURLException e) {
      Log.e("MalformedURLException", e.getMessage());
    } catch (JSONException e) {
      Log.e("JSONException", e.getMessage());
    }


    printJSON(dresses);
  }

  /*
   * Testing
   */
  public void printJSON(JSONObject obj) {
    System.out.println(obj.toString());
  }

  /*
   * Testing
   */
  public JSONObject getProducts(String category) throws MalformedURLException, JSONException {
    URL url =
        new URL(API_BASE + "products?" + "pid=" + API_PID + "&format=" + API_FORMAT + "&cat="
            + category);

    String contentJSON = getJSONContent(url);

    return new JSONObject(contentJSON);

  }

  @Override
  public ArrayList<Clothe> getClothesByCategory(String category) {
    ArrayList<Clothe> listClothes = new ArrayList<Clothe>();

    try {
      URL url =
          new URL(API_BASE + "products?" + "pid=" + API_PID + "&format=" + API_FORMAT + "&cat="
              + category);

      String content = getJSONContent(url);
      JSONArray listClothesJSON = new JSONObject(content).getJSONArray("produts");

      for (int i = 0; i < listClothesJSON.length(); i++) {
        Clothe clothe = new Clothe();

        clothe.setModel(listClothesJSON.getJSONObject(i).getString("name"));
        clothe.setImageUrl(listClothesJSON.getJSONObject(i).getJSONObject("image")
            .getJSONObject("size").getJSONObject("Iphone").getString("url"));

      }

    } catch (MalformedURLException e) {
      Log.e("MalformedURLException", e.getMessage());
    } catch (JSONException e) {
      Log.e("JSONException", e.getMessage());
    }

    return listClothes;
  }

  @Override
  public Clothe getClothe(String id) {
    // TODO Auto-generated method stub
    return new Clothe();
  }
}
