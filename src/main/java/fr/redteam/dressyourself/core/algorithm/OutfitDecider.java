package fr.redteam.dressyourself.core.algorithm;

import java.util.ArrayList;
import java.util.Random;

import fr.redteam.dressyourself.core.clothes.Clothe;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class OutfitDecider
{
  private boolean weather;
  private int currentTop = 0;
  private int currentBottom = 0;
  private int currentFeet = 0;
  private Random randomGenerator = new Random();

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public OutfitDecider(){
		super();
	}

  public OutfitDecider(boolean weather) {
    this.weather = weather;
  }

  public Clothe DecideTop(ArrayList<Clothe> listTop) {
    int randomInt;

    if (!weather) {
      randomInt = randomGenerator.nextInt(listTop.size());
      while (randomInt == currentTop) {
        randomInt = randomGenerator.nextInt(listTop.size());
      }
      return listTop.get(randomInt);
    }

    // TODO prendre en compte la météo
    else {
      return listTop.get(0);
    }
  }

  public Clothe DecideBottom(ArrayList<Clothe> listBottom) {
    int randomInt;

    if (!weather) {
      randomInt = randomGenerator.nextInt(listBottom.size());
      while (randomInt == currentBottom) {
        randomInt = randomGenerator.nextInt(listBottom.size());
      }
      return listBottom.get(randomInt);
    }

    // TODO prendre en compte la météo
    else {
      return listBottom.get(0);
    }
  }

  public Clothe DecideFeet(ArrayList<Clothe> listFeet) {
    int randomInt;

    if (!weather) {
      randomInt = randomGenerator.nextInt(listFeet.size());
      while (randomInt == currentFeet) {
        randomInt = randomGenerator.nextInt(listFeet.size());
      }
      return listFeet.get(randomInt);
    }

    // TODO prendre en compte la météo
    else {
      return listFeet.get(0);
    }
  }
}

