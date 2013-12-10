package fr.redteam.dressyourself.activities;


import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.redteam.dressyourself.adapters.AdapterClothes;
import fr.redteam.dressyourself.common.database.DBHelper;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class ActivityClotheList extends ListActivity{

	private List<Clothe> clotheList;
	private boolean listIsEmpty = false;
	private DBHelper db;
	private AdapterClothes adapter;
	
	private void setClotheList(){
		this.db.open();
		this.clotheList = this.db.getListClothes();
		this.db.close();
	}
	
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		this.db = new DBHelper(this);
		this.setClotheList();

		if(this.clotheList.size() == 0){
			this.listIsEmpty = true; 
			this.clotheList.add(new Clothe("There is no clothe"));
		}
		this.adapter = new AdapterClothes(this, this.clotheList);
		this.setListAdapter(adapter);
	}

	public void onResume(){
		super.onResume();
		this.setClotheList();
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
} 
