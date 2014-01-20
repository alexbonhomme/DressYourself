package fr.redteam.dressyourself.common.database;

import java.util.List;

import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;


public interface IntDBHelper {
  // Les requetes d'insertion dans la table de labse de données, retourne l'id de l'element inserer
  public void open();

  public void close();

  public long insertClothes(Clothe clothe);

  public List<Clothe> getListTop();

  public List<Clothe> getListBottom();

  public List<Clothe> getListFeet();

  public List<Clothe> getListClothes();

  public List<String> getAllColors();

  public List<String> getAllTypes();

  public long updateClothe(Clothe clothe);

  public Outfit getOutfit(long id);

  public void removeClothes(String clothes);
}
