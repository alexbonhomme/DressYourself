package fr.redteam.dressyourself.core.api;

import java.util.List;

import fr.redteam.dressyourself.core.clothes.Clothe;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
public interface APIInterface {

  /**
   * Récupére un object Clothe directement a partir de son ID dans la base de donnée distante.
   * 
   * @param id
   * @return un objet Clothe
   */
  Clothe findClotheById(int id);

  /**
   * Recupère tout les articles qui contiennent `modelName` dans leur nom de modèle.
   * 
   * @param modelName
   * @return une liste d'objets Clothe
   */
  List<Clothe> findClothesByModel(String modelName);

  /**
   * Récupère tout les articles qui contiennent `brandName` dans leur marque.
   * 
   * @param brandName
   * @return
   */
  List<Clothe> findClothesByBrand(String brandName);

  /**
   * Récupère tout les articles qui contiennent `colorName` dans leur couleur.
   * 
   * @param colorName
   * @return
   */
  List<Clothe> findClothesByColor(String colorName);

  /**
   * Récupère tout les articles qui contiennent `typeName` dans leur type.
   * 
   * @param typeName
   * @return une liste d'objets Clothe
   */
  List<Clothe> findClothesByType(String typeName);

  /**
   * Récupère tout les articles qui contiennent `bodyPart` dans leur attribut `bodies`.
   * 
   * @param bodyPart
   * @return une liste d'objets Clothe
   */
  List<Clothe> findClothesByBodyPart(String bodyPart);

  /**
   * Récupère tout les articles qui contiennent `query` dans un moins un des attributs suivants :
   * model, brand, color, type, bodies
   * 
   * @param query
   * @return une liste d'objets Clothe
   */
  List<Clothe> findAll(String query);
}
