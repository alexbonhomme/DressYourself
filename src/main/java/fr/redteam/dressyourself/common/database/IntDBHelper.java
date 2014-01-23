package fr.redteam.dressyourself.common.database;

import java.util.List;

import fr.redteam.dressyourself.core.clothes.Clothe;
/**
 * 
 * @author buisine
 * 
 * Cette interface permet l'abstraction des méthodes nécéssaires au bon fonctionnement de la base de données de l'application 
 *  
 *
 */

public interface IntDBHelper {
/**
 * Open the Database
 */
  public void open();
  
/**
 * Close the Database
 */
  public void close();
  
  
/**
 * insert a new clothe in the database
 * @param clothe
 * @return id of clothe 
 */
  public long insertClothes(Clothe clothe);

 /**
  * Get the List of Top clothe
  * @return List of Top clothe
  */
  public List<Clothe> getListTop();

  /**
   * Get the List of Bottom clothe
   * @return List of Bottom clothe
   */
  public List<Clothe> getListBottom();
  
  /**
   * Get the List of Feet clothe
   * @return List of Feet clothe
   */
  public List<Clothe> getListFeet();
  
  /**
   * Get the List of Clothe
   * @return List of clothe
   */
  public List<Clothe> getListClothes();
  
  /**
   * Get the List of Color
   * @return List of Color
   */
  public List<String> getAllColors();
  
/**
 * Get the List of Type
 * @return List of Type
 */
  public List<String> getAllTypes();

 /**
 * update a clothe
 * @param clothe
 * @return id of clothe
 */
  public long updateClothe(Clothe clothe);

 /**
  * remove a clothe
  * @param clothes
  */
  public void removeClothes(String clothes);
}
