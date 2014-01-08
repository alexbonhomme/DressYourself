package fr.redteam.dressyourself.core.algorithm;

import java.util.ArrayList;
import java.util.List;

import fr.redteam.dressyourself.common.database.IntDBHelper;
import fr.redteam.dressyourself.core.Bodypart;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class OutfitDecider {
  private String currentWeather;
  private List<Clothe> listTop = new ArrayList<Clothe>();
  private List<Clothe> listBottom = new ArrayList<Clothe>();
  private List<Clothe> listFeet = new ArrayList<Clothe>();
  private final IntDBHelper db;

  public OutfitDecider(IntDBHelper dbhelper) {
    super();
    this.db = dbhelper;
    db.open();
    loadData();
    db.close();
  }

  private void loadData() {
    // recup top
    listTop = db.getListTop();

    // recup bottom
    listBottom = db.getListBottom();

    // recup feet
    listFeet = db.getListFeet();
  }

  // Need to be improved, should return a Clothe list I guess
  /**
   * Return clothes to display
   * 
   * @author boens
   * @param body the bodypart you ask for
   * @return a list with clothes
   */
  public List<Clothe> getBodypartClothes(Bodypart body) {
    switch (body) {
      case TOP:
        if (currentWeather != null) {
          return buildListWeather(listTop);
        }
        return this.listTop;
      case BOTTOM:
        if (currentWeather != null) {
          return buildListWeather(listBottom);
        }
        return this.listBottom;
      case SHOES:
        if (currentWeather != null) {
          return buildListWeather(listFeet);
        }
        return this.listFeet;
      default:
        return new ArrayList<Clothe>();
    }
  }

  public void setWeather(String weather) {
    this.currentWeather = weather;
  }

  /**
   * @author boens Build a new list with only clothes adapted for the current weather
   * @param listClothe the full list
   * @return list with only clothes adapted for the current weather
   */
  public List<Clothe> buildListWeather(List<Clothe> listClothe) {
    List<Clothe> listClotheWeather = new ArrayList<Clothe>();

    for (Clothe clothe : listClothe) {
      List<String> listWeather = clothe.getWeather();
      for (String clotheWeather : listWeather) {
        /*
         * clotheWeather will not be null after introducing weather attribute in bodies table. When
         * done, remove this if
         */
        if (clotheWeather != null) {
          if (clotheWeather.equals(currentWeather)) {
            listClotheWeather.add(clothe);
          }
        }
      }
    }

    return listClotheWeather;
  }
}
