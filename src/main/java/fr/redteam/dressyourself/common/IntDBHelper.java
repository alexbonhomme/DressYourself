package fr.redteam.dressyourself.common;

import java.util.ArrayList;

import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;


public interface IntDBHelper {
  // les requete d'insertion
  public long insertBrand(String brand);
  
  public long insertColor(String color);

  public long insertWeather(String weather);

  public long insertBodies(String bodies);

  public long insertType(String type, int id_bodies);

  public long insertClothes(Clothe clothe);

  public long insertOutfit(String name, Clothe[] clothes);

  // les requetes de recupération des id
  public int getIDColor(String color);

  public int getIDWeather(String weather);

  public int getIDBodies(String bodies);

  public int getIDType(String type);

  public int getIDClothes(String clothes);

  public int getIDOutfit(String outfit);

  public int getIDBrand(String brand);

  // les requetes de recupération des infos
  public String getColor(long id);

  public String getBodies(long id);

  public String getWeather(long id);

  public String getType(long id);

  public String getBrand(long id);

  public Clothe getClothe(long id);

  public ArrayList<Clothe> getListTop();

  public ArrayList<Clothe> getListFeet();

  public ArrayList<Clothe> getListClothes();

  public ArrayList<String> getAllColors();

  public ArrayList<String> getAllTypes();

  public long updateClothe(Clothe clothe);

  public Outfit getOutfit(long id);
}
