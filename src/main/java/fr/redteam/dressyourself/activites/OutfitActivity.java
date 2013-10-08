package main.java.fr.redteam.dressyourself.activites;

import main.java.fr.redteam.dressyourself.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OutfitActivity extends Activity{
	
	private TextView topText;
	private TextView bottomText;
	private TextView feetText;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.outfit_layout);
		topText = (TextView) findViewById(R.id.top_text);
		bottomText = (TextView) findViewById(R.id.bottom_text);
		feetText = (TextView) findViewById(R.id.feet_text);
		topText.setText("Pull beige");
		bottomText.setText("slim bleu foncé");
		feetText.setText("Basket camel");
	
	}

}
