package fr.redteam.dressyourself.core.clothes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */


public class Outfit implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   * @ordered
   */

  private List<Clothe> clothesList;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Outfit() {
    this.clothesList = new ArrayList<Clothe>();
    // super();
  }

  /**
   * add clothe from list of clothes
   * 
   * @param Clothes
   */
  public void addClothes(List<Clothe> Clothes) {
    for (Clothe clothe : Clothes)
      this.clothesList.add(clothe);
  }

  /**
   * add a clothe in the outfit
   * 
   * @param clothe
   */
  public void addClothe(Clothe clothe) {
    this.clothesList.add(clothe);
  }

  /**
   * get the list of clothes
   * 
   * @return ClothesList
   */
  public List<Clothe> getClothes() {
    return this.clothesList;
  }

  /**
   * set the list of clothes
   * 
   * @return ClothesList
   */
  public void setClothes(List<Clothe> list) {
    this.clothesList = list;
  }

  /**
   * get the number of clothes which compose an outfit.
   * 
   * @return
   */
  public int nbClothes() {
    return this.getClothes().size();
  }
}
