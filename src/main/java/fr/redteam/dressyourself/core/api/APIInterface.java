package fr.redteam.dressyourself.core.api;

import java.util.List;

import fr.redteam.dressyourself.core.clothes.Clothe;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
public interface APIInterface {

  public List<Clothe> getClothesByType(String typeName);

  public Clothe getClothe(int id);
}
