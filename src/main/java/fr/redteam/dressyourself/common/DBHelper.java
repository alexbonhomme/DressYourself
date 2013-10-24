package main.java.fr.redteam.dressyourself.common;

import java.util.ArrayList;

import main.java.fr.redteam.dressyourself.core.clothes.Clothe;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class DBHelper
{
  /**
   * <!-- begin-user-doc -->
   * <!--  end-user-doc  -->
   * @generated
   */
  private CreateSQLBase mbdd;
  private SQLiteDatabase bdd;
  
  public DBHelper(Context context){
    super();
    mbdd = new CreateSQLBase(context, "GYSBdd", null, 1);

  }
  
  public void open(){
    //on ouvre la BDD en écriture
	//Log.v("DBHelper", "ouverture bdd");
	
	//Log.v("DBHelper", mbdd.getDatabaseName()

    bdd= mbdd.getWritableDatabase();
  }
  
  public void close(){
    bdd.close();
  }

  public SQLiteDatabase getBDD(){
    return bdd;
  }
  
  public long insertColor(String couleur){
    ContentValues values = new ContentValues();
    values.put("colorName", couleur);
    return bdd.insert("COLOR", null, values);
  }
  
  public long insertWeather(String weather){
    ContentValues values = new ContentValues();
    values.put("weatherName", weather);
    return bdd.insert("WEATHER", null, values);
  }
  
  public long insertBodies(String bodies){
    ContentValues values = new ContentValues();
    values.put("bodiesName", bodies);
    return bdd.insert("BODIES", null, values);
  }
  
  public long insertType(String type, int id_bodies){
    ContentValues values = new ContentValues();
    values.put("typeName", type);
    values.put("ID_b", id_bodies);
    return bdd.insert("TYPE", null, values);
  }
  
  public long insertClothes(Clothe clothe){
    ContentValues values = new ContentValues();
    values.put("ID_t", getIDType(clothe.getModel()));
    values.put("ID_c", getIDColor(clothe.getColor()));
    long r = bdd.insert("CLOTHES", null, values);
    values= new ContentValues();
    for(int i =0 ;i< clothe.getWeather().size();i++){
      values.put("ID_c",r);
      values.put("ID_w",getIDWeather(clothe.getWeather().get(i)));
      bdd.insert("WEATHER_CLOTHES", null, values);
    }
    return r;
  }
  
  public long insertOutfit(String name , Clothe[] clothes){
    return 0;
  }
  
  public int getIDColor(String color){
    return 0;
  }
  
  public int getIDWeather(String weather){
    return 0;
  }
  
  public int getIDBodies(String bodies){
    return 0;
  }
  
  public int getIDType(String type){
    return 0;
  }
  
  public int getIDClothes(String clothes){
    return 0;
  }
  
  public int getIDOutfit(String outfit){
    return 0;
  }
  
  public String getColor(int id){
    return null;
  }
  
  public String getBodies (int id){
    return null;
  }
  
  public String getWeather(int id){
    return null;
  }
  
  public String getType (int id){
    return null;
  }
  
  public Clothe getClothe (int id){
    return null;
  }

  public ArrayList<Clothe> getListTop (){
    String query = "SELECT model FROM CLOTHES INNER JOIN type ON ID_t=ID_type INNER JOIN bodies ON ID_b=ID_bodies AND bodiesName='TOP'";
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<Clothe> listTop = new ArrayList<Clothe>();

    while (cursor.moveToNext()){
      Clothe top = new Clothe(cursor.getString(0));
      listTop.add(top);
    }

    return listTop;
  }
  
  public ArrayList<Clothe> getListBottom (){
    String query = "SELECT model FROM CLOTHES INNER JOIN type ON ID_t=ID_type INNER JOIN bodies ON ID_b=ID_bodies AND bodiesName='BOTTOM'";
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<Clothe> listBottom = new ArrayList<Clothe>();

    while (cursor.moveToNext()){
      Clothe bottom = new Clothe(cursor.getString(0));
      listBottom.add(bottom);
    }

    return listBottom;
  }
  
  public ArrayList<Clothe> getListFeet (){
    String query = "SELECT model FROM CLOTHES INNER JOIN type ON ID_t=ID_type INNER JOIN bodies ON ID_b=ID_bodies AND bodiesName='FEET'";
    Cursor cursor = bdd.rawQuery(query, null);
    ArrayList<Clothe> listFeet = new ArrayList<Clothe>();

    while (cursor.moveToNext()){
      Clothe feet = new Clothe(cursor.getString(0));
      listFeet.add(feet);
    }

    return listFeet;
  }

}	