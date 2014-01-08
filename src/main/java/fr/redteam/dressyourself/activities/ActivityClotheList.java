package fr.redteam.dressyourself.activities;


import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import fr.redteam.dressyourself.adapters.AdapterClothes;
import fr.redteam.dressyourself.common.database.DBHelper;
import fr.redteam.dressyourself.core.clothes.Clothe;

/**
 * 
 * @author Florian Recourt
 * 
 * This activity allows user to see a list of all clothes present in the database
 *
 */
public class ActivityClotheList extends ListActivity{

	private List<Clothe> clotheList;
	private boolean listIsEmpty = false;
	private DBHelper db;
	private AdapterClothes adapter;
	
	/**
	 * Set the List by using the DBHelper
	 */
	public void setClotheList(DBHelper db){
		this.db = db;
		this.db.open();
		this.clotheList = this.db.getListClothes();
		this.db.close();
	}
	
	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		this.db = new DBHelper(this);
		this.setClotheList(this.db);

		if(this.clotheList.size() == 0){
			this.listIsEmpty = true; 
			this.clotheList.add(new Clothe("There is no clothe"));
		}
		this.adapter = new AdapterClothes(this, this.clotheList);
		this.setListAdapter(adapter);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		this.setClotheList(this.db);
		this.adapter = new AdapterClothes(this, this.clotheList);
		setListAdapter(adapter);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		if(!this.listIsEmpty){
			Intent intent = new Intent(ActivityClotheList.this, ActivityClotheDetail.class);
			Bundle bundle = new Bundle();
			bundle.putSerializable("clothe", this.clotheList.get(position));
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}

	public List<Clothe> getList() {
		return this.clotheList;
	}
} 
