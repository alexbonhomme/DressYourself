package main.java.fr.redteam.dressyourself.common;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateSQLBase extends SQLiteOpenHelper {
  private static final String CREATE_TABLE_IFE = "CREATE TABLE IF NOT EXISTS";
  private static final String PRIMARY_INTEGER_KEY = " INTEGER PRIMARY KEY AUTOINCREMENT";
  private static final String TABLE_COLOR = CREATE_TABLE_IFE + " COLOR" + "(" + "ID_color"
      + PRIMARY_INTEGER_KEY + "," + "colorName TEXT)";
  
  private static final String TABLE_BODIES = CREATE_TABLE_IFE + " BODIES" + "(" + "ID_bodies"
      + PRIMARY_INTEGER_KEY + "," + "bodiesName TEXT" + ")";
  
  private static final String TABLE_TYPE = CREATE_TABLE_IFE + " TYPE" + "(" + "ID_type"
      + PRIMARY_INTEGER_KEY + "," + "typeName TEXT" + "," + "ID_b INTEGER" + ","
      + "FOREIGN KEY (ID_b) REFERENCES BODIES(ID_bodies))";

  private static final String TABLE_WEATHER = CREATE_TABLE_IFE + " WEATHER" + "(" + "ID_weather"
      + PRIMARY_INTEGER_KEY + "," + "weatherName TEXT)";
  
  private static final String TABLE_CLOTHES = CREATE_TABLE_IFE + " CLOTHES" + "(" + "ID_clothes"
      + PRIMARY_INTEGER_KEY + "," + "clothesName TEXT" + "," + "image BLOB" + "," + "ID_c INTEGER"
      + "," + "ID_t INTEGER" + "," + "FOREIGN KEY (ID_c) REFERENCES COLOR (ID_color)" + ","
      + "FOREIGN KEY (ID_t) REFERENCES TYPE (ID_type)" + ")";
  
  private static final String TABLE_WEATHER_CLOTHES = CREATE_TABLE_IFE + " WEATHER_CLOTHES " + "( "
      + "ID_c INTEGER" + "," + "ID_w INTEGER" + "," + "PRIMARY KEY (ID_c,ID_w)" + ","
      + "FOREIGN KEY (ID_c)REFERENCES CLOTHES (ID_clothes)" + ","
      + "FOREIGN KEY (ID_w)REFERENCES WEATHER (ID_weather))";

  private static final String TABLE_OUTFIT = CREATE_TABLE_IFE + " OUTFIT" + "(" + "ID_outfit"
      + PRIMARY_INTEGER_KEY + "," + "outfitName TEXT)";

  private static final String TABLE_OUTFIT_CLOTHES = CREATE_TABLE_IFE + " OUTFIT_CLOTHES"
      + "( ID_c INTEGER" + "," + "ID_o INTEGER" + "," + "PRIMARY KEY (ID_c,ID_o)" + ","
      + "FOREIGN KEY (ID_c)REFERENCES CLOTHES (ID_clothes)" + ","
      + "FOREIGN KEY (ID_o)REFERENCES OUTFIT (ID_outfit))";

  private static final String CREATE_BDD = TABLE_COLOR + "," + TABLE_WEATHER + "," + TABLE_TYPE
      + "," + TABLE_CLOTHES + "," + TABLE_OUTFIT + "," + TABLE_OUTFIT_CLOTHES + ","
      + TABLE_WEATHER_CLOTHES + ";";


  public CreateSQLBase(Context context, String name, CursorFactory factory, int version) {
    super(context, name, factory, version);
    // TODO Auto-generated constructor stub
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    // TODO Auto-generated method stub
    // on créé la table à partir de la requête écrite dans la variable CREATE_BDD
    db.execSQL(TABLE_COLOR);
    db.execSQL(TABLE_WEATHER);
    db.execSQL(TABLE_TYPE);
    db.execSQL(TABLE_CLOTHES);
    db.execSQL(TABLE_OUTFIT);
    db.execSQL(TABLE_BODIES);
    db.execSQL(TABLE_OUTFIT_CLOTHES);
    db.execSQL(TABLE_WEATHER_CLOTHES);

    insertColor(db, "WHITE");
    insertColor(db, "BLACK");
    insertColor(db, "BLUE");
    insertColor(db, "YELLOW");
    insertColor(db, "GREEN");
    insertColor(db, "RED");
    insertColor(db, "PURPLE");
    insertColor(db, "PINK");
    insertBodies(db, "TOP");
    insertBodies(db, "HEAD");
    insertBodies(db, "BOTTOM");
    insertBodies(db, "SHOES");
    insertWeather(db, "SUNNY");
    insertWeather(db, "CLOUNY");
    insertWeather(db, "RAINY");
    insertWeather(db, "SNOWNY");

  }

  public long insertColor(SQLiteDatabase db, String couleur) {
    ContentValues values = new ContentValues();
    values.put("colorName", couleur);
    
    return db.insert("COLOR", null, values);
  }

  public long insertWeather(SQLiteDatabase db, String weather) {
    ContentValues values = new ContentValues();
    values.put("weatherName", weather);
    
    return db.insert("WEATHER", null, values);
  }

  public long insertBodies(SQLiteDatabase db, String bodies) {
    ContentValues values = new ContentValues();
    values.put("bodiesName", bodies);
    
    return db.insert("BODIES", null, values);
  }

  public long insertType(SQLiteDatabase db, String type, int id_bodies) {
    ContentValues values = new ContentValues();
    values.put("typeName", type);
    values.put("ID_b", id_bodies);
    
    return db.insert("Type", null, values);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // TODO Auto-generated method stub

  }

}
