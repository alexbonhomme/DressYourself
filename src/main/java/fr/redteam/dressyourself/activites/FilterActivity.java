package main.java.fr.redteam.dressyourself.activites;
import main.java.fr.redteam.dressyourself.R;
import main.java.fr.redteam.dressyourself.R.layout;
import main.java.fr.redteam.dressyourself.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FilterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.filter, menu);
		
		Button btOutfit = (Button) findViewById(R.id.validationFiltre);
		btOutfit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//TODO redirected to the 'filters page'
				Intent intent = new Intent(FilterActivity.this, OutfitActivity.class);
				startActivity(intent);	
			}
		});
		
		return true;
	}
}
