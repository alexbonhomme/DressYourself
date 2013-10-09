package main.java.fr.redteam.dressyourself.activites;

import java.util.List;

import main.java.fr.redteam.dressyourself.R;
import main.java.fr.redteam.dressyourself.common.DBHelper;
import main.java.fr.redteam.dressyourself.core.clothes.Clothes;
import main.java.fr.redteam.dressyourself.core.clothes.Weather;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * An activity representing a single Clothe detail screen. This activity is only
 * used on handset devices. On tablet-size devices, item details are presented
 * side-by-side with a list of items in a {@link ClotheListActivity}.
 * <p>
 * This activity is mostly just a 'shell' activity containing nothing more than
 * a {@link ClotheDetailFragment}.
 */
public class ClotheDetailActivity extends FragmentActivity {

	private TextView body;
	private TextView label;
	private TextView color;
	private TextView type;
	private TextView weather;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clothe_detail);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Button btnModify = (Button) findViewById(R.id.modifyBtn);
		btnModify.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//TODO redirected to the 'filters page'
				Intent intent = new Intent(ClotheDetailActivity.this, ModifyClothe.class);
				startActivity(intent);	
			}
		});

		// savedInstanceState is non-null when there is fragment state
		// saved from previous configurations of this activity
		// (e.g. when rotating the screen from portrait to landscape).
		// In this case, the fragment will automatically be re-added
		// to its container so we don't need to manually add it.
		// For more information, see the Fragments API guide at:
		//
		// http://developer.android.com/guide/components/fragments.html
		//
		if (savedInstanceState == null) {
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(ClotheDetailFragment.ARG_ITEM_ID, getIntent()
					.getStringExtra(ClotheDetailFragment.ARG_ITEM_ID));
			ClotheDetailFragment fragment = new ClotheDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.clothe_detail_container, fragment).commit();
		}
		this.body = (TextView) findViewById(R.id.bodyTxt);
		this.label= (TextView) findViewById(R.id.LabelTxt);
		this.color = (TextView) findViewById(R.id.colorTxt);
		this.weather = (TextView) findViewById(R.id.weatherTxt);
		this.type = (TextView) findViewById(R.id.TypeTxt);
		DBHelper DB = new DBHelper();
		Clothes MyClothes = DB.getClothe(Integer.parseInt(ClotheDetailFragment.ARG_ITEM_ID));
		this.body.setText(MyClothes.getBody());
		this.label.setText(MyClothes.getLabel());
		this.color.setText(MyClothes.getColor());
		this.type.setText(MyClothes.getDescription());
		List<String> TheWeather = MyClothes.getWeatherList();
		String WeatherTxt="";
		for (String weatherLine : TheWeather) {
			WeatherTxt += weatherLine + " ";
		}
		this.weather.setText(WeatherTxt);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this, new Intent(this,
					ClotheListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
