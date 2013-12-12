package fr.redteam.dressyourself.core.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;
import fr.redteam.dressyourself.core.clothes.Clothe;


/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class OutfitDecider {
  private String currentWeather;
  private int currentTop = 0;
  private int currentBottom = 0;
  private int currentFeet = 0;
  private Random randomGenerator = new Random();

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public OutfitDecider() {
    super();
  }

  public void setWeather(String weather) {
    this.currentWeather = weather;
  }

  public Clothe decideTop(List<Clothe> listTop) {
    int randomInt;
    List<Clothe> listTopWeather = new ArrayList<Clothe>();

    if (currentWeather == null) {
      randomInt = randomGenerator.nextInt(listTop.size());

      while (randomInt == currentTop) {
        randomInt = randomGenerator.nextInt(listTop.size());
      }
      currentTop = randomInt;

      return listTop.get(currentTop);
    } else {
      listTopWeather = buildListWeather(listTop);
      if (listTopWeather.size() > 0) {
        randomInt = randomGenerator.nextInt(listTopWeather.size());

        while (randomInt == currentTop) {
          randomInt = randomGenerator.nextInt(listTopWeather.size());
        }
        currentTop = randomInt;

        return listTopWeather.get(currentTop);
      }
    }
    Log.d("erreur", "decideTop could not decide");
    return listTop.get(0);
  }

  public Clothe decideBottom(List<Clothe> listBottom) {
    int randomInt;
    List<Clothe> listBottomWeather = new ArrayList<Clothe>();

    if (currentWeather == null) {
      randomInt = randomGenerator.nextInt(listBottom.size());

      while (randomInt == currentTop) {
        randomInt = randomGenerator.nextInt(listBottom.size());
      }
      currentBottom = randomInt;

      return listBottom.get(currentBottom);
    } else {
      listBottomWeather = buildListWeather(listBottom);
      if (listBottomWeather.size() > 0) {
        randomInt = randomGenerator.nextInt(listBottomWeather.size());

        while (randomInt == currentTop) {
          randomInt = randomGenerator.nextInt(listBottomWeather.size());
        }
        currentBottom = randomInt;

        return listBottomWeather.get(currentBottom);
      }
    }
    Log.d("erreur", "decideBottom could not decide");
    return listBottom.get(0);
  }

  public Clothe decideFeet(List<Clothe> listFeet) {
    int randomInt;
    List<Clothe> listFeetWeather = new ArrayList<Clothe>();

    if (currentWeather == null) {
      randomInt = randomGenerator.nextInt(listFeet.size());

      while (randomInt == currentFeet) {
        randomInt = randomGenerator.nextInt(listFeet.size());
      }
      currentFeet = randomInt;

      return listFeet.get(currentFeet);
    } else {
      listFeetWeather = buildListWeather(listFeet);
      if (listFeetWeather.size() > 0) {
        randomInt = randomGenerator.nextInt(listFeetWeather.size());

        while (randomInt == currentFeet) {
          randomInt = randomGenerator.nextInt(listFeetWeather.size());
        }
        currentFeet = randomInt;

        return listFeetWeather.get(currentFeet);
      }
    }
    Log.d("erreur", "decideFeet could not decide");
    return listFeet.get(0);
  }

  // build a new list with only clothes adapted for the current weather
  public List<Clothe> buildListWeather(List<Clothe> listClothe) {
    List<Clothe> listClotheWeather = new ArrayList<Clothe>();

    for (Clothe clothe : listClothe) {
      List<String> listWeather = clothe.getWeather();
      for (String clotheWeather : listWeather) {
        // clotheWeather will not be null after introducing weather attribute in bodies table. When
        // done, remove this if
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
