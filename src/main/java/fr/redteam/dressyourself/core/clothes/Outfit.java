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
  private static final long serialVersionUID = 8706175620359487087L;
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
    return this.getClothes();
  }

  /**
   * set the list of clothes
   * 
   * @return ClothesList
   */
  public void setClothes(List<Clothe> list) {
    this.clothesList = list;
  }
}
