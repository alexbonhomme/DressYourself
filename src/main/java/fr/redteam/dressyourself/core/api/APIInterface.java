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
   * TODO do!
   * 
   * @param id
   * @return un objet Clothe
   */
  Clothe findClotheById(int id);

  /**
   * TODO do!
   * 
   * @param modelName
   * @return une liste d'objects Clothe
   */
  List<Clothe> findClothesByModelName(String modelName);

  /**
   * TODO do!
   * 
   * @param typeName
   * @return une liste d'objects Clothe
   */
  List<Clothe> findClothesByType(String typeName);

  /**
   * Effectue une recherche sur tout les champs de la base de donnée
   * 
   * @param query
   * @return une liste d'objects Clothe
   */
  List<Clothe> findAll(String query);
}
