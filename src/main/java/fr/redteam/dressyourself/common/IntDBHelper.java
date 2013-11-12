package fr.redteam.dressyourself.common;

import java.util.ArrayList;

import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;


public interface IntDBHelper {
  // les requete d'insertion
  public long insertColor(String couleur);

  public long insertWeather(String weather);

  public long insertBodies(String bodies);

  public long insertType(String type, int id_bodies);
  
  public long insertBrand(String brand);

  public long insertClothes(Clothe clothe);

  public long insertOutfit(String name, Clothe[] clothes);

  // les requetes de recupération des id
  public long getIDColor(String color);

  public long getIDWeather(String weather);

  public long getIDBodies(String bodies);

  public long getIDType(String type);

  public long getIDClothes(String clothes);

  public long getIDOutfit(String outfit);

  public long getIDBrand(String brand);

  // les requetes de recupération des infos
  public String getColor(int id);

  public String getBodies(int id);

  public String getWeather(int id);

  public String getType(int id);

  public String getBrand(int id);

  public Clothe getClothe(int id);

  public ArrayList<Clothe> getListTop();

  public ArrayList<Clothe> getListFeet();

  public ArrayList<Clothe> getListClothes();

  public ArrayList<String> getAllColors();

  public ArrayList<String> getAllTypes();

  public int updateClothe(Clothe clothe, int clotheID);

  public Outfit getOutfit(int id);
}
