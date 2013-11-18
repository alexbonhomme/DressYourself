package fr.redteam.dressyourself.core.api;

import java.util.List;

import fr.redteam.dressyourself.core.clothes.Clothe;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
public interface APIInterface {

  List<Clothe> getClothesByType(String typeName);

  Clothe getClothe(int id);

  List<Clothe> searchAll(String query);
}
