package fr.redteam.dressyourself.core.algorithm;

import java.util.List;
import java.util.Random;

import fr.redteam.dressyourself.core.clothes.Clothe;


/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class OutfitDecider {
  private boolean weather;
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

  public OutfitDecider(boolean weather) {
    this.weather = weather;
  }

  public Clothe decideTop(List<Clothe> listTop) {
    int randomInt;

    if (!weather) {
      randomInt = randomGenerator.nextInt(listTop.size());
      while (randomInt == currentTop) {
        randomInt = randomGenerator.nextInt(listTop.size());
      }
      currentTop = randomInt;
      return listTop.get(currentTop);
    } else {
      // TODO prendre en compte la météo
      return listTop.get(0);
    }
  }

  public Clothe decideBottom(List<Clothe> listBottom) {
    int randomInt;

    if (!weather) {
      randomInt = randomGenerator.nextInt(listBottom.size());
      while (randomInt == currentBottom) {
        randomInt = randomGenerator.nextInt(listBottom.size());
      }
      currentBottom = randomInt;
      return listBottom.get(currentBottom);
    } else {
      // TODO prendre en compte la météo
      return listBottom.get(0);
    }
  }

  public Clothe decideFeet(List<Clothe> listFeet) {
    int randomInt;

    if (!weather) {
      randomInt = randomGenerator.nextInt(listFeet.size());
      while (randomInt == currentFeet) {
        randomInt = randomGenerator.nextInt(listFeet.size());
      }
      currentFeet = randomInt;
      return listFeet.get(currentFeet);
    } else {
      // TODO prendre en compte la météo
      return listFeet.get(0);
    }
  }
}
