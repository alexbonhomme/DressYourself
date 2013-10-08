package main.java.fr.redteam.dressyourself.activites;

import java.util.List;

import main.java.fr.redteam.dressyourself.R;
import main.java.fr.redteam.dressyourself.core.clothes.Clothes;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

/**
 * 
 * @author adrien
 * This activity allows the user to modify the caracteristics of a clothe
 */

public class ModifyClothe extends Activity {

	/* components */
	
	private Clothes clotheToEdit;
	private ImageButton image;
	private EditText label;
	private Spinner colorSpinner;
	private List<CheckBox> checkboxes;
	private EditText description;
	
	/**
	 * fill the editable fields with the caracteristics of the clothe in order to edit them
	 */
	private void initFieldsWithValues(){
		
	}
	
	/**
	 * @param clotheLabel the Label of the Clothe we need informations about
	 */
	private void retrieveClotheInformation(String clotheLabel){
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify_clothe);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modify_clothe, menu);
		return true;
	}

}
