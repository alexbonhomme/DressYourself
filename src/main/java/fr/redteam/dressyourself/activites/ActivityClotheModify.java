package main.java.fr.redteam.dressyourself.activites;

import main.java.fr.redteam.dressyourself.R;
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


  /* the clothe to Edit */
  private Clothe clotheToEdit;

  /* components */
  private ImageButton image;
  private EditText model;
  private Spinner brands;
  private Spinner colors;
  private Spinner types;

  /**
   * init the attributes with their corresponding item in layout
   */
  public void getComponentsFromLayout() {
    this.image = (ImageButton) findViewById(R.id.imageButton);
    this.model = (EditText) findViewById(R.id.modelEdit);
    this.brands = (Spinner) findViewById(R.id.brandSpinner);
    this.colors = (Spinner) findViewById(R.id.colorSpinner);
    this.types = (Spinner) findViewById(R.id.typeSpinner);
  }

  /**
   * Init the spinners with the values stored in database
   */
  public void initSpinnersWithData() {

  }

  /**
   * fill the editable fields with the caracteristics of the clothe in order to edit them
   */
  public void initFieldsWithValues(Clothe c) {
    // mettre l'image this.image.setI
    this.model.setText(this.clotheToEdit.getModel());
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
