package main.java.fr.redteam.dressyourself.activites;

import main.java.fr.redteam.dressyourself.R;
import main.java.fr.redteam.dressyourself.common.DBHelper;
import main.java.fr.redteam.dressyourself.core.clothes.*;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class OutfitActivity extends Activity{
	
	private TextView topText;
	private TextView bottomText;
	private TextView feetText;
	private DBHelper db = new DBHelper(this);
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.outfit_layout);
		topText = (TextView) findViewById(R.id.top_text);
		bottomText = (TextView) findViewById(R.id.bottom_text);
		feetText = (TextView) findViewById(R.id.feet_text);
		
		//connexion bdd
		db.open();
		
		//recup top
		//db.getListTop();
		Clothe clothe = new Clothe("Pull beige");
		
		topText.setText(clothe.getLabel());
		bottomText.setText("slim bleu fonce");
		feetText.setText("Basket camel");
		
		db.close();
	
	}

}
