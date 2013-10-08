package main.java.fr.redteam.dressyourself.common;

import android.content.ContentValues;
import android.content.Context;
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
	
}

