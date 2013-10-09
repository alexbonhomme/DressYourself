package main.java.fr.redteam.dressyourself.common;

import java.util.ArrayList;

import main.java.fr.redteam.dressyourself.core.clothes.Clothes;
import main.java.fr.redteam.dressyourself.core.clothes.Colors;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


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
		mbdd = new CreateSQLBase(context, "test", null, 1);

	}
	public DBHelper() {
		// TODO Stub du constructeur généré automatiquement
	}
	public void open(){
		//on ouvre la BDD en écriture
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
		values.put("nom_color", couleur);
		return bdd.insert("COLOR", null, values);
	}
	public long insertWeather(String weather){
		ContentValues values = new ContentValues();
		values.put("nom_weather", weather);
		return bdd.insert("WEATHER", null, values);
	}
	public long insertBodies(String bodies){
		ContentValues values = new ContentValues();
		values.put("nom_bodies", bodies);
		return bdd.insert("BODIES", null, values);
	}
	public long insertType(String type, int id_bodies){
		ContentValues values = new ContentValues();
		values.put("nom_type", type);
		values.put("ID_b", id_bodies);
		return bdd.insert("TYPE", null, values);
	}
	public long insertClothes(Clothes clothe){
		ContentValues values = new ContentValues();
		values.put("nom_clothe",clothe.getDescription());
		values.put("ID_t", getIDType(clothe.getLabel()));
		values.put("ID_c", getIDColor(clothe.getColor()));
		long r = bdd.insert("CLOTHES", null, values);
		values= new ContentValues();
		for(int i =0 ;i< clothe.getWeatherList().size();i++){
		values.put("ID_c",r);
		values.put("ID_w",getIDWeather(clothe.getWeatherList().get(i)));
		bdd.insert("WEATHER_CLOTHES", null, values);
		}
		return r;
	}
	public long insertOutfit(String nom , Clothes[] clothes){
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
	public Clothes getClothe (int id){
		return null;
	}
	
	public ArrayList<Clothes> getListTop (){
		//getIDBodies("TOP");
		//bdd.query(true, "CLOTHES", "nom_clothes", "ID_c", null, null, null, null, null, null);
		return null;
	}
	
}	
