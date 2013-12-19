package fr.redteam.dressyourself.common.database;

import java.util.List;

import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;


public interface IntDBHelper {
  // Les requetes d'insertion dans la table de labse de données, retourne l'id de l'element inserer
  public void open();

  public void close();

  public long insertBrand(String brand);

  public long insertColor(String color);

  public long insertWeather(String weather);

  public long insertBodies(String bodies);

  public long insertType(String type, long idBodies);


  public long insertClothes(Clothe clothe);

  public long insertOutfit(String name, Clothe[] clothes);

  // les requetes de recupération des id, retourne l'id de l'ement sinon -1
  public long getIDColor(String color);

  public long getIDWeather(String weather);

  public long getIDBodies(String bodies);

  public long getIDType(String type);

  public long getIDClothes(String clothes);

  public long getIDOutfit(String outfit);

  public long getIDBrand(String brand);

  // les requetes de recupération des infos, retourne l'element sinon null
  public String getColor(long id);

  public String getBodies(long id);

  public String getWeather(long id);

  public String getType(long id);

  public String getBrand(long id);

  public Clothe getClothe(long id);

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
