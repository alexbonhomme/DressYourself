package fr.redteam.dressyourself.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;


/**
 * <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */

public class DBHelper implements IntDBHelper {
  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  private final CreateSQLBase mbdd;
  private SQLiteDatabase bdd;

  public DBHelper(Context context) {
    super();
    mbdd = new CreateSQLBase(context, "GYSBdd", null, 2);

  }
  // pour pouvoir creer une bdd avec un nom particulier , mettre null pour en memoire
  public DBHelper(Context context,String name) {
    super();
    mbdd = new CreateSQLBase(context, null, null, 1);

  }


  public void open() {
    // on ouvre la BDD en Ã©criture
    bdd = mbdd.getWritableDatabase();
  }

  public void close() {
    bdd.close();
  }

  public SQLiteDatabase getBDD() {
    return bdd;
  }

  @Override
  public long insertColor(String couleur) {
    ContentValues values = new ContentValues();
    values.put("colorName", couleur);
    return bdd.insert("COLOR", null, values);
  }

  @Override
  public long insertWeather(String weather) {
    ContentValues values = new ContentValues();
    values.put("weatherName", weather);
    return bdd.insertOrThrow("WEATHER", null, values);
  }

  @Override
  public long insertBodies(String bodies) {
    ContentValues values = new ContentValues();
    values.put("bodiesName", bodies);
    return bdd.insertOrThrow("BODIES", null, values);
  }

  public long insertType(String type, long l) {
    ContentValues values = new ContentValues();
    values.put("typeName", type);
    values.put("ID_b", l);
    return bdd.insertOrThrow("TYPE", null, values);
  }

  @Override
  public long insertClothes(Clothe clothe)  {
    
    
    try {
      ContentValues values = new ContentValues();
      values.put("model", clothe.getModel());
      values.put("ID_t", getIDType(clothe.getType()));
      values.put("ID_c", getIDColor(clothe.getColor()));
      values.put("ID_br", getIDBrand(clothe.getBrand()));
      byte[] bytes = null;
      clothe.getImage().read(bytes);
      values.put("image", bytes);
      long r = bdd.insertOrThrow("CLOTHES", null,values);
      return r;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    /*
     * values = new ContentValues(); for (int i = 0; i < clothe.getWeather().size(); i++) {
     * values.put("ID_c", r); values.put("ID_w", getIDWeather(clothe.getWeather().get(i)));
     * bdd.insert("WEATHER_CLOTHES", null, values); }
     */
    return 0;
  }

  @Override
  public long insertOutfit(String name, Clothe[] clothes) {
    return 0; // TODO
  }

  @Override
  public long insertBrand(String brand) {
    ContentValues values = new ContentValues();
    values.put("brandName", brand);
    return bdd.insertOrThrow("BRAND", null, values);
  }

  @Override
  public long getIDColor(String color) {
    String query = "SELECT ID_color FROM COLOR WHERE colorName = \"" + color + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    if (c.getCount()>0){
      c.moveToFirst();

      return c.getLong(0);
      }
      return -1;

  }

  @Override
  public long getIDWeather(String weather) {
    String query = "SELECT ID_weather FROM WEATHER WHERE weatherName = \"" + weather + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    if (c.getCount()>0){
    c.moveToFirst();

    return c.getLong(0);
    }
    return -1;
  }

  @Override
  public long getIDBodies(String bodies) {
    String query = "SELECT ID_bodies FROM BODIES WHERE bodiesName = \"" + bodies + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    if (c.getCount()>0){
      c.moveToFirst();

      return c.getLong(0);
      }
      return -1;
  }

  @Override
  public long getIDType(String type) {
    String query = "SELECT ID_type FROM TYPE WHERE typeName = \"" + type + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    if (c.getCount()>0){
      c.moveToFirst();

      return c.getLong(0);
      }
      return -1;
  }

  @Override
  public long getIDClothes(String clothes) {
    String query = "SELECT ID_clothes FROM CLOTHES WHERE model = \"" + clothes + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    if (c.getCount()>0){
      c.moveToFirst();

      return c.getLong(0);
      }
      return -1;
  }

  @Override
  public long getIDOutfit(String outfit) {
    String query = "SELECT ID_outfit FROM OUTFIT WHERE outfitName = \"" + outfit + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    if (c.getCount()>0){
      c.moveToFirst();

      return c.getLong(0);
      }
      return -1;
  }

  @Override
  public long getIDBrand(String brand) {
    String query = "SELECT ID_brand FROM BRAND WHERE brandName = \"" + brand + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    
    if (c.getCount()>0){
      c.moveToFirst();

      return c.getLong(0);
      }
      return -1;
  }

  @Override
  public String getColor(long id) {
    String query = "SELECT colorName FROM BRAND WHERE ID_brand = " + id;
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getString(0);
  }

  @Override
  public String getBodies(long l) {
    String query = "SELECT bodiesName FROM BODIES WHERE ID_bodies = " + l;
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getString(0);
  }

  @Override
  public String getWeather(long id) {
    String query = "SELECT weatherName FROM WEATHER WHERE ID_weather = " + id;
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getString(0);
  }

  @Override
  public String getType(long id) {
    String query = "SELECT typeName FROM TYPE WHERE ID_type = " + id;
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getString(0);
  }

  @Override
  public String getBrand(long id) {
    String query = "SELECT brandName FROM BRAND WHERE ID_brand = " + id;
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getString(0);

  }


  @Override
  public Clothe getClothe(long id) {
    String query =
        "SELECT CLOTHES.id, CLOTHES.model, CLOTHE.image TYPE.typeName, BODIES.bodiesName, BRAND.brandName, WEATHER.weatherName, COLOR.colorName FROM CLOTHES, TYPE, BODIES , BRAND ,WEATHER, WEATHER_CLOTHES,COLOR WHERE CLOTHES.ID_t = TYPE.ID_type AND CLOTHES.ID_br = BRAND.ID_brand AND CLOTHES.ID_c=COLOR.ID_color AND CLOTHES.ID_clothes = WEATHER_CLOTHES.ID_c AND WHEATHE.ID_weather = WEATHER_CLOTHES.ID_w AND TYPE.ID_b = BODIES.ID_bodies";
    Cursor cursor = bdd.rawQuery(query, null);
    Clothe clothe = new Clothe();
    cursor.moveToFirst();
    ArrayList<String> weather = new ArrayList<String>();
    clothe.setId(cursor.getInt(0));
    clothe.setModel(cursor.getString(1));
    clothe.setImage(new ByteArrayInputStream(cursor.getBlob(2)));
    clothe.setType(cursor.getString(3));
    clothe.setBodies(cursor.getString(4));
    clothe.setBrand(cursor.getString(5));
    weather.add(cursor.getString(6));
    clothe.setType(cursor.getString(7));
    while (cursor.moveToNext()) {
      weather.add(cursor.getString(6));
    }
    clothe.setWeather(weather);
    return clothe;
  }



  @Override
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

  @Override
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

  @Override
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

  @Override
  public ArrayList<String> getAllColors() {
    String query = "Select colorName FROM COLOR";
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<String> colors = new ArrayList<String>();
    while (cursor.moveToNext()) {
      colors.add(cursor.getString(0));
    }
    return colors;
  }

  @Override
  public ArrayList<String> getAllTypes() {
    String query = "Select typeName FROM TYPE";
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<String> types = new ArrayList<String>();
    while (cursor.moveToNext()) {
      types.add(cursor.getString(0));
    }
    return types;
  }

  @Override
  public long updateClothe(Clothe clothe) {
    ContentValues values = new ContentValues();
    values.put("model", clothe.getModel());
    values.put("ID_t", getIDType(clothe.getType()));
    values.put("ID_c", getIDColor(clothe.getColor()));
    // values.put("ID_br", getIDBrand(clothe.getBrand()));

    long r =
        bdd.update("CLOTHES", values, "ID_clothes = ?",
            new String[] {String.valueOf(clothe.getId())});
    return r;
  }

  @Override
  public Outfit getOutfit(long id) {
    return null;
  }
  @Override
  public long insertAllClothes(Clothe clothe) {
    long l =this.insertBodies(clothe.getBodies());
    this.insertBrand(clothe.getBrand());
    this.insertColor(clothe.getColor());
    this.insertType(clothe.getType(), l);
    for( int i = 0; i <clothe.getWeather().size(); i++)
    this.insertWeather(clothe.getWeather().get(i));
     l =insertClothes(clothe);
    return 0;
  }

}