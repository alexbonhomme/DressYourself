package main.java.fr.redteam.dressyourself.activites;

import main.java.fr.redteam.dressyourself.R;
import main.java.fr.redteam.dressyourself.common.DBHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btAddClothing;
	private Button btListClothes;
	private Button btFilters;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//creation base de donnée
		DBHelper t = new DBHelper(this);
		
		// XXX Exception au lancement de l'App
		// E/SQLiteLog(29954): (1) AUTOINCREMENT is only allowed on an INTEGER PRIMARY KEY
		
		//t.open();
		//t.close();

		/* Open the page to add clothes */
		btAddClothing = (Button) findViewById(R.id.btAddClothing);
		btAddClothing.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, AddClothesActivity.class);
				startActivity(intent);	
			}
		});

		btListClothes = (Button) findViewById(R.id.btListClothes);
		btListClothes.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, ClotheListActivity.class);
				startActivity(intent);	
			}
		});
		
		btFilters = (Button) findViewById(R.id.btFilters);
		btFilters.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//TODO redirected to the 'filters page'
				Intent intent = new Intent(MainActivity.this, OutfitActivity.class);
				startActivity(intent);	
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
