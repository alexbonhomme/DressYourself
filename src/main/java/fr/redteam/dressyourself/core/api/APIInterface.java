package fr.redteam.dressyourself.core.api;

import java.util.List;

import fr.redteam.dressyourself.core.Bodypart;
import fr.redteam.dressyourself.core.clothes.Clothe;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
public interface APIInterface {

  /**
   * Return a `Clothe` object from is ID in the database API
   * 
   * @param id ID of the product in the remote database
   */
  Clothe findClotheById(int id);

  /**
   * Return a list of `Clothe` objects when their names contains `model`
   */
  List<Clothe> findClothesByModel(String model);

  /**
   * Return a list of `Clothe` objects when their brand contains `brand`
   */
  List<Clothe> findClothesByBrand(String brand);

  /**
   * Return a list of `Clothe` objects when their color contains `color`
   */
  List<Clothe> findClothesByColor(String color);

  /**
   * Return a list of `Clothe` objects when their types contains `type`
   */
  List<Clothe> findClothesByType(String type);

  /**
   * Return a list of `Clothe` objects when their `bodies` attribute contains `bodyPart`
   * 
   * @param bodyPart Bodypart is an Enum type which list all parts of the body available in the API
   */
  List<Clothe> findClothesByBodyPart(Bodypart bodyPart);

  /**
   * Return a list of `Clothe` objects which contains `query` in at least one of the following
   * attributes : model - brand - color - type - bodies
   */
  List<Clothe> findAll(String query);
}
