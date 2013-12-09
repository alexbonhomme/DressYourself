package fr.redteam.dressyourself.core.api;

import java.util.List;

import fr.redteam.dressyourself.core.Bodypart;
import fr.redteam.dressyourself.core.clothes.Clothe;

/**
 * Implementation available :
 * 
 * APIZara.getInstance()
 * 
 * @author Alexandre Bonhomme
 * 
 */
public interface API {

  /**
   * Return a {@link Clothe Clothe} object from is ID in the database API
   * 
   * @param id ID of the product in the remote database
   */
  Clothe findClotheById(int id);

  /**
   * Return a list of {@link Clothe Clothe} objects when their names contains <code>model</code>
   */
  List<Clothe> findClothesByModel(String model);

  /**
   * Return a list of {@link Clothe Clothe} objects when their brand contains <code>brand</code>
   */
  List<Clothe> findClothesByBrand(String brand);

  /**
   * Return a list of {@link Clothe Clothe} objects when their color contains <code>color</code>
   */
  List<Clothe> findClothesByColor(String color);

  /**
   * Return a list of {@link Clothe Clothe} objects when their types contains <code>type</code>
   */
  List<Clothe> findClothesByType(String type);

  /**
   * Return a list of {@link Clothe Clothe} objects when their <code>bodies</code> attribute
   * contains <code>bodyPart</code>
   * 
   * @param bodyPart {@link Bodypart Bodypart} is an Enum type which list all parts of the body
   *        available in the API
   */
  List<Clothe> findClothesByBodyPart(Bodypart bodyPart);

  /**
   * Return a list of {@link Clothe Clothe} objects which contains <code>query</code> in at least
   * one of the following attributes : model - brand - color - type - bodies
   */
  List<Clothe> findAll(String query);
}
