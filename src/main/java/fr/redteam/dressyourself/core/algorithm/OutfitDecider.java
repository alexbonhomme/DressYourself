package fr.redteam.dressyourself.core.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;
import fr.redteam.dressyourself.common.database.IntDBHelper;
import fr.redteam.dressyourself.core.Bodypart;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.exceptions.DressyourselfRuntimeException;

public class OutfitDecider {
  private String currentWeather;
  private Clothe currentTop;
  private Clothe currentBottom;
  private Clothe currentFeet;
  private List<Clothe> listTop = new ArrayList<Clothe>();
  private List<Clothe> listBottom = new ArrayList<Clothe>();
  private List<Clothe> listFeet = new ArrayList<Clothe>();
  private final IntDBHelper db;
  private Random randomGenerator = new Random();

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

  public void setWeather(String weather) {
    this.currentWeather = weather;
  }

  /**
   * @author boens
   * @param body The body part to find Call the decider with the corresponding list
   */
  public Clothe decide(Bodypart body) {
    List<Clothe> listClothes = new ArrayList<Clothe>();
    Clothe clothe;

    switch (body) {
      case TOP:
        listClothes = listTop;
        break;
      case BOTTOM:
        listClothes = listBottom;
        break;
      case SHOES:
        listClothes = listFeet;
        break;
      default:
        break;
    }

    if (currentWeather != null) {
      listClothes = buildListWeather(listClothes);
    }

    clothe = decide(listClothes, body);

    return clothe;
  }

  /**
   * @author boens
   * @param listClothes The list to proceed
   * @param body The body part to find
   * @return the new clothe or null if exception thrown
   */
  public Clothe decide(List<Clothe> listClothes, Bodypart body) {
    int randomInt;

    if (listClothes.size() > 1) {
      randomInt = randomGenerator.nextInt(listClothes.size());

      while (listClothes.get(randomInt) == currentTop) {
        randomInt = randomGenerator.nextInt(listClothes.size());
      }
      switch (body) {
        case TOP:
          currentTop = listClothes.get(randomInt);
          return currentTop;
        case BOTTOM:
          currentBottom = listClothes.get(randomInt);
          return currentBottom;
        case SHOES:
          currentFeet = listClothes.get(randomInt);
          return currentFeet;
        default:
          break;
      }
    } else {
      Log.d("generation", "decide couldn't chose " + body + "clothe");
      throw new DressyourselfRuntimeException("decide couldn't chose " + body + "clothe");
    }
    return null;
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
