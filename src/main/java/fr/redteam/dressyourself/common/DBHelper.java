package main.java.fr.redteam.dressyourself.common;

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

}

