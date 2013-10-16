package main.java.fr.redteam.dressyourself.activites;

import java.util.ArrayList;

import main.java.fr.redteam.dressyourself.R;
import main.java.fr.redteam.dressyourself.common.DBHelper;
import main.java.fr.redteam.dressyourself.core.clothes.Clothe;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

/**
 * 
 * @author adrien This activity allows the user to modify the caracteristics of a clothe
 */

public class ActivityClotheModify extends Activity {

  /* components */

  private Clothe clotheToEdit;
  private ImageButton imageButtonImage;
  private Spinner Spinnercolor;
  private Spinner Spinnertype;
  private Spinner Spinnerweather;
  private EditText editTextDescription;

  /**
   * init the attributes with their corresponding item in layout
   */
  public void getComponentsFromLayout() {
    this.imageButtonImage = (ImageButton) findViewById(R.id.imageButton);
    this.Spinnercolor = (Spinner) findViewById(R.id.colorSpinner);
    this.Spinnertype = (Spinner) findViewById(R.id.typeSpinner);
    this.Spinnerweather = (Spinner) findViewById(R.id.weatherSpinner);
    this.editTextDescription = (EditText) findViewById(R.id.descritpionEdit);
  }

  /**
   * fill the editable fields with the caracteristics of the clothe in order to edit them
   */
  private void initFieldsWithValues() {

  }

  /**
   * @param clotheLabel the Label of the Clothe we need informations about
   */
  private ArrayList<String> retrieveClotheInformation(String clotheLabel) {
    ArrayList<String> listClotheInformations = new ArrayList<String>();
    DBHelper db = new DBHelper(this);


    return listClotheInformations;

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
