package main.java.fr.redteam.dressyourself.activites;

import java.util.ArrayList;

import main.java.fr.redteam.dressyourself.R;
import main.java.fr.redteam.dressyourself.common.DBHelper;
import main.java.fr.redteam.dressyourself.core.clothes.*;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityOutfit extends Activity{
	
	private TextView textViewTop;
	private TextView textViewBottom;
	private TextView textViewFeet;
	private DBHelper db = new DBHelper(this);
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.outfit_layout);
		textViewTop = (TextView) findViewById(R.id.top_text);
		textViewBottom = (TextView) findViewById(R.id.bottom_text);
		textViewFeet = (TextView) findViewById(R.id.feet_text);
		
		//connexion bdd
		db.open();
		
		//recup top
		ArrayList<Clothe> listTop = new ArrayList<Clothe>();
		listTop = db.getListTop();
		System.out.println(listTop);
		
		//recup bottom
		ArrayList<Clothe> listBottom = new ArrayList<Clothe>();
        listBottom = db.getListBottom();
        System.out.println(listBottom);
        
      //recup bottom
        ArrayList<Clothe> listFeet = new ArrayList<Clothe>();
        listFeet = db.getListFeet();
        System.out.println(listFeet);
        
		//vetements statique TODO: récupérer en bdd
		Clothe clothe = new Clothe("Pull beige");
		textViewTop.setText(clothe.getLabel());
		textViewBottom.setText("slim bleu fonce");
		textViewFeet.setText("Basket camel");
		
		db.close();
	
	}

}
