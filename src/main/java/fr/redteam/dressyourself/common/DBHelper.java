package main.java.fr.redteam.dressyourself.common;

import java.util.ArrayList;

import main.java.fr.redteam.dressyourself.core.clothes.Clothe;
import main.java.fr.redteam.dressyourself.core.clothes.Outfit;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class DBHelper {
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private CreateSQLBase mbdd;
  private SQLiteDatabase bdd;

  public DBHelper(Context context) {
    super();
    mbdd = new CreateSQLBase(context, "GYSBdd", null, 1);

  }


  public void open() {
    // on ouvre la BDD en écriture
    bdd = mbdd.getWritableDatabase();
  }

  public void close() {
    bdd.close();
  }

  public SQLiteDatabase getBDD() {
    return bdd;
  }

  public long insertColor(String couleur) {
    ContentValues values = new ContentValues();
    values.put("colorName", couleur);
    return bdd.insert("COLOR", null, values);
  }

  public long insertWeather(String weather) {
    ContentValues values = new ContentValues();
    values.put("weatherName", weather);
    return bdd.insert("WEATHER", null, values);
  }

  public long insertBodies(String bodies) {
    ContentValues values = new ContentValues();
    values.put("bodiesName", bodies);
    return bdd.insert("BODIES", null, values);
  }

  public long insertType(String type, int id_bodies) {
    ContentValues values = new ContentValues();
    values.put("typeName", type);
    values.put("ID_b", id_bodies);
    return bdd.insert("TYPE", null, values);
  }

  public long insertClothes(Clothe clothe) {
    ContentValues values = new ContentValues();
    values.put("model", clothe.getModel());
    values.put("ID_t", getIDType(clothe.getType()));
    values.put("ID_c", getIDColor(clothe.getColor()));
    values.put("ID_br", getIDBrand(clothe.getBrand()));

    long r = bdd.insert("CLOTHES", null, values);
    values = new ContentValues();
    for (int i = 0; i < clothe.getWeather().size(); i++) {
      values.put("ID_c", r);
      values.put("ID_w", getIDWeather(clothe.getWeather().get(i)));
      bdd.insert("WEATHER_CLOTHES", null, values);
    }
    return r;
  }

  public long insertOutfit(String name, Clothe[] clothes) {
    return 0;
  }

  public int getIDColor(String color) {
    String query = "SELECT ID_color FROM COLOR WHERE colorName = \"" + color + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getInt(0);

  }

  public int getIDWeather(String weather) {
    String query = "SELECT ID_weather FROM WEATHER WHERE weatherName = \"" + weather + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getInt(0);
  }

  public int getIDBodies(String bodies) {
    String query = "SELECT ID_bodies FROM BODIES WHERE bodiesName = \"" + bodies + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getInt(0);
  }

  public int getIDType(String type) {
    String query = "SELECT ID_type FROM TYPE WHERE typeName = \"" + type + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getInt(0);
  }

  public int getIDClothes(String clothes) {
    String query = "SELECT ID_clothes FROM CLOTHES WHERE clothesName = \"" + clothes + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getInt(0);
  }

  public int getIDOutfit(String outfit) {
    String query = "SELECT ID_outfit FROM OUTFIT WHERE outfitName = \"" + outfit + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getInt(0);
  }

  public int getIDBrand(String brand) {
    String query = "SELECT ID_brand FROM BRAND WHERE brandName = \"" + brand + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getInt(0);
  }

  public String getColor(int id) {
    String query = "SELECT colorName FROM BRAND WHERE ID_brand = " + id;
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getString(0);
  }

  public String getBodies(int id) {
    String query = "SELECT bodiesName FROM BODIES WHERE ID_bodies = " + id;
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getString(0);
  }

  public String getWeather(int id) {
    String query = "SELECT weatherName FROM WEATHER WHERE ID_weather = " + id;
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getString(0);
  }

  public String getType(int id) {
    String query = "SELECT typeName FROM TYPE WHERE ID_type = " + id;
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getString(0);
  }

  public String getBrand(int id) {
    String query = "SELECT brandName FROM BRAND WHERE ID_brand = " + id;
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getString(0);
  }


  public Clothe getClothe(int id) {
    return null;
  }

  public ArrayList<Clothe> getListTop() {
    String query =
        "SELECT model FROM CLOTHES INNER JOIN type ON ID_t=ID_type INNER JOIN bodies ON ID_b=ID_bodies AND bodiesName='TOP'";
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<Clothe> listTop = new ArrayList<Clothe>();

    while (cursor.moveToNext()) {
      Clothe top = new Clothe(cursor.getString(0));
      listTop.add(top);
    }

    return listTop;
  }

  public ArrayList<Clothe> getListBottom() {
    String query =
        "SELECT model FROM CLOTHES INNER JOIN type ON ID_t=ID_type INNER JOIN bodies ON ID_b=ID_bodies AND bodiesName='BOTTOM'";
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<Clothe> listBottom = new ArrayList<Clothe>();

    while (cursor.moveToNext()) {
      Clothe bottom = new Clothe(cursor.getString(0));
      listBottom.add(bottom);
    }

    return listBottom;
  }

  public ArrayList<Clothe> getListFeet() {
    String query =
        "SELECT model FROM CLOTHES INNER JOIN type ON ID_t=ID_type INNER JOIN bodies ON ID_b=ID_bodies AND bodiesName='FEET'";
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<Clothe> listFeet = new ArrayList<Clothe>();

    while (cursor.moveToNext()) {
      Clothe feet = new Clothe(cursor.getString(0));
      listFeet.add(feet);
    }

    return listFeet;
  }

  public ArrayList<Clothe> getListClothes() {
    String query =
        "SELECT clothesName FROM clothes INNER JOIN type ON ID_t=ID_type INNER JOIN bodies ON ID_b=ID_bodies";
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<Clothe> listClothes = new ArrayList<Clothe>();

    while (cursor.moveToNext()) {
      Clothe clothe = new Clothe(cursor.getString(0));
      listClothes.add(clothe);
    }

    return listClothes;
  }

  public ArrayList<String> getAllColors() {
    String query = "Select colorName FROM COLOR";
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<String> colors = new ArrayList<String>();
    while (cursor.moveToNext()) {
      colors.add(cursor.getString(0));
    }
    return colors;
  }

  public ArrayList<String> getAllTypes() {
    String query = "Select typeName FROM TYPE";
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<String> types = new ArrayList<String>();
    while (cursor.moveToNext()) {
      types.add(cursor.getString(0));
    }
    return types;
  }

  public int updateClothe(Clothe clothe, int clotheID) {
    ContentValues values = new ContentValues();
    values.put("model", clothe.getModel());
    values.put("ID_t", getIDType(clothe.getType()));
    values.put("ID_c", getIDColor(clothe.getColor()));
    values.put("ID_br", getIDBrand(clothe.getBrand()));

    int r =
        bdd.update("CLOTHES", values, "ID_clothes = ?", new String[] {String.valueOf(clotheID)});
    return r;
  }
  
  public Outfit getOutfit(int id){
	  return null;
  }

}
