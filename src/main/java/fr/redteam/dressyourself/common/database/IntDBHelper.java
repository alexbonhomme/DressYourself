package fr.redteam.dressyourself.common.database;

import java.util.ArrayList;

import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;


public interface IntDBHelper {
  // Les requetes d'insertion dans la table de labse de données, retourne l'id de l'element inserer
  public long insertBrand(String brand);

  public long insertColor(String color);

  public long insertWeather(String weather);

  public long insertBodies(String bodies);

  public long insertType(String type, long id_bodies);


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

  public ArrayList<Clothe> getListTop();

  public ArrayList<Clothe> getListBottom();

  public ArrayList<Clothe> getListFeet();

  public ArrayList<Clothe> getListClothes();

  public ArrayList<String> getAllColors();

  public ArrayList<String> getAllTypes();

  public long updateClothe(Clothe clothe);

  public Outfit getOutfit(long id);

  public void removeClothes(String Clothes);
}
