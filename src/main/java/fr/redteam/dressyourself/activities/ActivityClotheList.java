package fr.redteam.dressyourself.activities;


import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import fr.redteam.dressyourself.common.DBHelper;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class ActivityClotheList extends ListActivity{

	private List<Clothe> clotheList;
	private boolean listIsEmpty = false;

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		String[] values;
		DBHelper db = new DBHelper(this);
		db.open();
		this.clotheList = db.getListClothes();
		db.close();

		if(this.clotheList.size() == 0){
			this.listIsEmpty = true; 
			values = new String[]{"You don't have any clothe"};
		}else{
			values = new String[this.clotheList.size()];
			int index = 0;

			for(Clothe clothe : this.clotheList){
				values[index] = clothe.getModel();
				index++;
			}
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values);
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
