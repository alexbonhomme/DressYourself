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

public class ModifyClothe extends Activity {

  /* components */

  private Clothe clotheToEdit;
  private ImageButton image;
  private Spinner colorSpinner;
  private Spinner typeSpinner;
  private Spinner weatherSpinner;
  private EditText description;

  /**
   * init the attributes with their corresponding item in layout
   */
  public void getComponentsFromLayout() {
    this.image = (ImageButton) findViewById(R.id.imageButton);
    this.colorSpinner = (Spinner) findViewById(R.id.colorSpinner);
    this.typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
    this.weatherSpinner = (Spinner) findViewById(R.id.weatherSpinner);
    this.description = (EditText) findViewById(R.id.descritpionEdit);
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
    ArrayList<String> clotheInformations = new ArrayList<String>();
    DBHelper bd = new DBHelper(this);


    return clotheInformations;

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
