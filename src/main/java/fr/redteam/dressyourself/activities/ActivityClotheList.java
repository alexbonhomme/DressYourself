package fr.redteam.dressyourself.activities;


import java.util.ArrayList;

import fr.redteam.dressyourself.common.DBHelper;
import fr.redteam.dressyourself.core.clothes.Clothe;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
//import fr.redteam.dressyourself.R;
import android.widget.Toast;

public class ActivityClotheList extends ListActivity {

	private ArrayList<Clothe> clotheList;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		DBHelper db = new DBHelper(this);
		db.open();
		this.clotheList = db.getListClothes();
		String[] values = new String[this.clotheList.size()];
		int index = 0;
		db.close();

		for(Clothe clothe : this.clotheList){
			values[index] = clothe.getModel();
			index++;
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values);
		setListAdapter(adapter);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(ActivityClotheList.this, ActivityClotheDetail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("clothe", this.clotheList.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
	}
} 
