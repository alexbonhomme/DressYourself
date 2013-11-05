package main.java.fr.redteam.dressyourself.activities;

import main.java.fr.redteam.dressyourself.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class ActivityClotheAdd extends Activity{
	
	private Spinner spinnerColor;
	private Spinner spinnerType;
	private Spinner spinnerWeather;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addclothes);
		
		spinnerColor = (Spinner) findViewById(R.id.spinnerColor);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapterColor = ArrayAdapter.createFromResource(this,
		                                                                          R.array.color_array,
		                                                                          android.R.layout.simple_spinner_item);
		
		// Specify the layout to use when the list of choices appears
		adapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Apply the adapter to the spinner
		spinnerColor.setAdapter(adapterColor);
		
		spinnerType = (Spinner) findViewById(R.id.spinnerType);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this,
		                                                                          R.array.type_array,
		                                                                          android.R.layout.simple_spinner_item);
		
		// Specify the layout to use when the list of choices appears
		adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Apply the adapter to the spinner
		spinnerType.setAdapter(adapterType);
		
		spinnerWeather = (Spinner) findViewById(R.id.spinnerWeather);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapterWeather = ArrayAdapter.createFromResource(this,
		                                                                            R.array.weather_array,
		                                                                            android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Apply the adapter to the spinner
		spinnerWeather.setAdapter(adapterWeather);
	}

}
