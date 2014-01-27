package fr.redteam.dressyourself.common.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.exceptions.DressyourselfDatabaseException;

/**
 * 
 *
 */
public class DBHelper implements IntDBHelper {

  private final CreateSQLBase mbdd;
  private SQLiteDatabase bdd;

  public DBHelper(Context context) {
    mbdd = new CreateSQLBase(context, "GYSBdd", null, 2);
  }

  // pour pouvoir creer une bdd avec un nom particulier , mettre null pour en
  // memoire
  public DBHelper(Context context, String name) {
    mbdd = new CreateSQLBase(context, name, null, 1);
  }

  @Override
  public void open() {
    // on ouvre la BDD en écriture
    bdd = mbdd.getWritableDatabase();
  }

  @Override
  public void close() {
    bdd.close();
  }

  public SQLiteDatabase getBDD() {
    return bdd;
  }

  private long insertColor(String couleur) {
    try {
      ContentValues values = new ContentValues();
      values.put("colorName", couleur);
      return bdd.insertWithOnConflict("COLOR", null, values, SQLiteDatabase.CONFLICT_IGNORE);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  private long insertWeather(String weather) {
    try {
      ContentValues values = new ContentValues();
      values.put("weatherName", weather);
      return bdd.insertWithOnConflict("WEATHER", null, values, SQLiteDatabase.CONFLICT_IGNORE);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  private long insertBodies(String bodies) {
    try {
      ContentValues values = new ContentValues();
      values.put("bodiesName", bodies);
      return bdd.insertWithOnConflict("BODIES", null, values, SQLiteDatabase.CONFLICT_IGNORE);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  private long insertType(String type, long idBodies) {
    try {
      ContentValues values = new ContentValues();
      values.put("typeName", type);
      values.put("ID_b", idBodies);
      return bdd.insertWithOnConflict("TYPE", null, values, SQLiteDatabase.CONFLICT_IGNORE);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }

  }

  private long insertJustClothes(Clothe clothe) {
    try {
      ContentValues values = new ContentValues();
      values.put("model", clothe.getModel());

      if (clothe.getType() != null) {
        values.put("ID_t", getIDType(clothe.getType()));
      } else {
		values.put("ID_t", 0);
      }

      if (clothe.getColor() != null) {
        values.put("ID_c", getIDColor(clothe.getColor()));
      } else {
        values.put("ID_c", 0);
      }

      if (clothe.getBrand() != null) {
        values.put("ID_br", getIDBrand(clothe.getBrand()));
      } else {
        values.put("ID_br", 0);
      }
      if (clothe.getImageRelativePath() != null) {
        values.put("image", clothe.getImageRelativePath());
      } else {
        values.put("image", 0);
      }

      long r = bdd.insertWithOnConflict("CLOTHES", null, values, SQLiteDatabase.CONFLICT_IGNORE);

      return r;
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  private long insertBrand(String brand) {
    try {
      ContentValues values = new ContentValues();
      values.put("brandName", brand);
      return bdd.insertWithOnConflict("BRAND", null, values, SQLiteDatabase.CONFLICT_IGNORE);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  private long getIDColor(String color) {
    String query = "SELECT ID_color FROM COLOR WHERE colorName = \"" + color + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getLong(c.getColumnIndexOrThrow("ID_color"));
  }

  private long getIDBodies(String bodies) {
    String query = "SELECT ID_bodies FROM BODIES WHERE bodiesName = \"" + bodies + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getLong(c.getColumnIndexOrThrow("ID_bodies"));
  }

  private long getIDType(String type) {
    String query = "SELECT ID_type FROM TYPE WHERE typeName = \"" + type + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getLong(c.getColumnIndexOrThrow("ID_type"));
  }

  private long getIDBrand(String brand) {
    String query = "SELECT ID_brand FROM BRAND WHERE brandName = \"" + brand + "\"";
    Log.v("BDD", query);
    Cursor c = bdd.rawQuery(query, null);
    c.moveToFirst();

    return c.getLong(c.getColumnIndexOrThrow("ID_brand"));
  }

  @Override
  public List<Clothe> getListTop() {

    String query =
        "SELECT CLOTHES.ID_clothes AS id, CLOTHES.model,TYPE.typeName, BODIES.bodiesName, BRAND.brandName,COLOR.colorName,WEATHER.weatherName,CLOTHES.image "
            + "FROM BRAND,COLOR,TYPE,BODIES, CLOTHES " + "LEFT JOIN WEATHER_CLOTHES ON CLOTHES.ID_clothes = WEATHER_CLOTHES.ID_c "
            + "LEFT JOIN WEATHER  ON WEATHER.ID_weather = WEATHER_CLOTHES.ID_w  " + "WHERE CLOTHES.ID_br= BRAND.ID_brand " + "AND CLOTHES.ID_c = COLOR.ID_color " + "AND CLOTHES.ID_t = TYPE.ID_type "
            + "AND TYPE.ID_b = BODIES.ID_bodies AND bodiesName='Top'";

    /*
     * placement des champs dans le curseur 0:ID_clothes 1: model 2: typeName 3:bodiesName 4:
     * brandName 5: colorName 6: weatherName 7:image
     */
    try {
      Cursor cursor = bdd.rawQuery(query, null);
      ArrayList<Clothe> listClothes = new ArrayList<Clothe>();
      Clothe clothe;
      int id = -1;
      clothe = new Clothe();
      ArrayList<String> weather = new ArrayList<String>();

      // add a Clothe with multiple Weather
      while (cursor.moveToNext()) {
        Log.v("BDD", Integer.toString(cursor.getInt(0)));
        if (cursor.getInt(0) != id) {
          if (id != -1) {
            listClothes.add(clothe);
          }
          clothe = new Clothe();
          weather = new ArrayList<String>();
          id = cursor.getInt(0);
          clothe.setId(cursor.getInt(0));
          clothe.setModel(cursor.getString(1));
          clothe.setType(cursor.getString(2));
          clothe.setBodies(cursor.getString(3));
          clothe.setBrand(cursor.getString(4));
          clothe.setColor(cursor.getString(5));
          clothe.setImageRelativePath(cursor.getString(7));
          weather.add(cursor.getString(6));
          clothe.setWeather(weather);
        } else {
          weather.add(cursor.getString(6));
        }
      }
      listClothes.add(clothe);

      return listClothes;
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  @Override
  public List<Clothe> getListBottom() {

    String query =
        "SELECT CLOTHES.ID_clothes AS id, CLOTHES.model,TYPE.typeName, BODIES.bodiesName, BRAND.brandName,COLOR.colorName,WEATHER.weatherName,CLOTHES.image "
            + "FROM BRAND,COLOR,TYPE,BODIES, CLOTHES " + "LEFT JOIN WEATHER_CLOTHES ON CLOTHES.ID_clothes = WEATHER_CLOTHES.ID_c "
            + "LEFT JOIN WEATHER  ON WEATHER.ID_weather = WEATHER_CLOTHES.ID_w  " + "WHERE CLOTHES.ID_br= BRAND.ID_brand " + "AND CLOTHES.ID_c = COLOR.ID_color " + "AND CLOTHES.ID_t = TYPE.ID_type "
            + "AND TYPE.ID_b = BODIES.ID_bodies " + "AND bodiesName='Bottom'";

    /*
     * placement des champs dans le curseur 0:ID_clothes 1: model 2: typeName 3:bodiesName 4:
     * brandName 5: colorName 6: weatherName 7:image
     */

    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<Clothe> listClothes = new ArrayList<Clothe>();
    Clothe clothe;
    int id = -1;
    clothe = new Clothe();
    ArrayList<String> weather = new ArrayList<String>();

    // add a Clothe with multiple Weather

    while (cursor.moveToNext()) {
      Log.v("BDD", Integer.toString(cursor.getInt(0)));

      if (cursor.getInt(0) != id) {
        if (id != -1) {
          listClothes.add(clothe);
        }
        clothe = new Clothe();
        weather = new ArrayList<String>();
        id = cursor.getInt(0);
        clothe.setId(cursor.getInt(0));
        clothe.setModel(cursor.getString(1));
        clothe.setType(cursor.getString(2));
        clothe.setBodies(cursor.getString(3));
        clothe.setBrand(cursor.getString(4));
        clothe.setColor(cursor.getString(5));
        clothe.setImageRelativePath(cursor.getString(7));
        weather.add(cursor.getString(6));
        clothe.setWeather(weather);
      } else {
        weather.add(cursor.getString(6));
      }

    }
    listClothes.add(clothe);

    return listClothes;
  }

  @Override
  public List<Clothe> getListFeet() {

    String query =
        "SELECT CLOTHES.ID_clothes AS id, CLOTHES.model,TYPE.typeName, BODIES.bodiesName, BRAND.brandName,COLOR.colorName,WEATHER.weatherName,CLOTHES.image "
            + "FROM BRAND,COLOR,TYPE,BODIES, CLOTHES " + "LEFT JOIN WEATHER_CLOTHES ON CLOTHES.ID_clothes = WEATHER_CLOTHES.ID_c "
            + "LEFT JOIN WEATHER  ON WEATHER.ID_weather = WEATHER_CLOTHES.ID_w  " + "WHERE CLOTHES.ID_br= BRAND.ID_brand " + "AND CLOTHES.ID_c = COLOR.ID_color " + "AND CLOTHES.ID_t = TYPE.ID_type "
            + "AND TYPE.ID_b = BODIES.ID_bodies " + "AND bodiesName='Shoes'";

    /*
     * placement des champs dans le curseur 0:ID_clothes 1: model 2: typeName 3:bodiesName 4:
     * brandName 5: colorName 6: weatherName 7:image
     */
    try {
      Cursor cursor = bdd.rawQuery(query, null);
      ArrayList<Clothe> listClothes = new ArrayList<Clothe>();
      Clothe clothe;
      int id = -1;
      clothe = new Clothe();
      ArrayList<String> weather = new ArrayList<String>();
      // add a Clothe with multiple Weather

      while (cursor.moveToNext()) {
        Log.v("BDD", Integer.toString(cursor.getInt(0)));
        if (cursor.getInt(0) != id) {
          if (id != -1) {
            listClothes.add(clothe);
          }
          clothe = new Clothe();
          weather = new ArrayList<String>();
          id = cursor.getInt(0);
          clothe.setId(cursor.getInt(0));
          clothe.setModel(cursor.getString(1));
          clothe.setType(cursor.getString(2));
          clothe.setBodies(cursor.getString(3));
          clothe.setBrand(cursor.getString(4));
          clothe.setColor(cursor.getString(5));
          clothe.setImageRelativePath(cursor.getString(7));
          weather.add(cursor.getString(6));
          clothe.setWeather(weather);

        } else {
          weather.add(cursor.getString(6));
        }
      }
      listClothes.add(clothe);


      return listClothes;
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  @Override
  public List<Clothe> getListClothes() {
    try {
      String query =
          "SELECT CLOTHES.ID_clothes AS id, CLOTHES.model,TYPE.typeName, BODIES.bodiesName, BRAND.brandName,COLOR.colorName,WEATHER.weatherName,CLOTHES.image "
              + "FROM BRAND,COLOR,TYPE,BODIES, CLOTHES " + "LEFT JOIN WEATHER_CLOTHES ON CLOTHES.ID_clothes = WEATHER_CLOTHES.ID_c "
              + "LEFT JOIN WEATHER  ON WEATHER.ID_weather = WEATHER_CLOTHES.ID_w  " + "WHERE CLOTHES.ID_br= BRAND.ID_brand " + "AND CLOTHES.ID_c = COLOR.ID_color "
              + "AND CLOTHES.ID_t = TYPE.ID_type " + "AND TYPE.ID_b = BODIES.ID_bodies";

      /*
       * placement des champs dans le curseur 0:ID_clothes 1: model 2: typeName 3:bodiesName 4:
       * brandName 5: colorName 6: weatherName 7:image
       */

      Cursor cursor = bdd.rawQuery(query, null);
      ArrayList<Clothe> listClothes = new ArrayList<Clothe>();
      Clothe clothe;
      int id = -1;
      clothe = new Clothe();
      ArrayList<String> weather = new ArrayList<String>();

      // add a Clothe with multiple Weather
      while (cursor.moveToNext()) {
        Log.v("BDD", Integer.toString(cursor.getInt(0)));
        if (cursor.getInt(0) != id) {
          if (id != -1) {
            listClothes.add(clothe);
          }
          clothe = new Clothe();
          weather = new ArrayList<String>();
          id = cursor.getInt(0);
          clothe.setId(cursor.getInt(0));
          clothe.setModel(cursor.getString(1));
          clothe.setType(cursor.getString(2));
          clothe.setBodies(cursor.getString(3));
          clothe.setBrand(cursor.getString(4));
          clothe.setColor(cursor.getString(5));
          clothe.setImageRelativePath(cursor.getString(7));
          weather.add(cursor.getString(6));
          clothe.setWeather(weather);
          Log.v("BDD", clothe.getBodies());

        } else {
          weather.add(cursor.getString(6));
        }
      }
      listClothes.add(clothe);

      return listClothes;

    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  @Override
  public List<String> getAllColors() {
    String query = "Select colorName FROM COLOR";
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<String> colors = new ArrayList<String>();
    while (cursor.moveToNext()) {
      colors.add(cursor.getString(0));
    }
    return colors;
  }

  @Override
  public List<String> getAllTypes() {
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

    if (clothe.getType() != null) {
      values.put("ID_t", getIDType(clothe.getType()));
    } else {
      values.put("ID_t", 0);
    }

    if (clothe.getColor() != null) {
      values.put("ID_c", getIDColor(clothe.getColor()));
    } else {
      values.put("ID_c", 0);
    }

    if (clothe.getBrand() != null) {
      values.put("ID_br", getIDBrand(clothe.getBrand()));
    } else {
      values.put("ID_br", 0);
    }

    if (clothe.getImageRelativePath() != null) {
      values.put("image", clothe.getImageRelativePath());
    } else {
      values.put("image", 0);
    }

    return bdd.update("CLOTHES", values, "ID_clothes = ?",
        new String[] {String.valueOf(clothe.getId())});
  }

  @Override
  public long insertClothes(Clothe clothe) {
    try {
      insertBodies(clothe.getBodies());
      insertBrand(clothe.getBrand());
      insertColor(clothe.getColor());

      long l = getIDBodies(clothe.getBodies());
      insertType(clothe.getType(), l);

      for (int i = 0; i < clothe.getWeather().size(); i++) {
        this.insertWeather(clothe.getWeather().get(i));
      }

      return insertJustClothes(clothe);
    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

  @Override
  public void removeClothes(String clothes) {
    try {
      String query = "DELETE FROM CLOTHES" + "WHERE CLOTHES.model = " + clothes;
      bdd.rawQuery(query, null);

    } catch (RuntimeException e) {
      throw new DressyourselfDatabaseException("RuntimeException", e);
    }
  }

}
