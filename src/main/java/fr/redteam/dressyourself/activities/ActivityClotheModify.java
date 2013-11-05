package main.java.fr.redteam.dressyourself.activities;

import java.util.ArrayList;

import main.java.fr.redteam.dressyourself.R;
import main.java.fr.redteam.dressyourself.common.DBHelper;
import main.java.fr.redteam.dressyourself.core.clothes.Clothe;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * 
 * @author adrien This activity allows the user to modify the caracteristics of a clothe
 */

public class ActivityClotheModify extends Activity {


  /* the clothe to Edit */
  private Clothe clotheToEdit;
  /* the id in bdd of the clothe to edit (needed for Update the bdd after modifications) */
  private int clotheID;
  /* components */
  private ImageButton image;
  private EditText modelEditText;
  private EditText brandEditText;
  private Spinner colorSpinner;
  private Spinner typeSpinner;
  private Button saveButton;

  /* DB helper */
  private DBHelper bdd;

  /**
   * init the attributes with their corresponding item in layout
   */
  public void initComponents() {
    /* retrieving components from layout */
    this.image = (ImageButton) findViewById(R.id.imageButton);
    this.modelEditText = (EditText) findViewById(R.id.modelEdit);
    this.brandEditText = (EditText) findViewById(R.id.brandEdit);
    this.colorSpinner = (Spinner) findViewById(R.id.colorSpinner);
    this.typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
    this.saveButton = (Button) findViewById(R.id.save);

    // add listeners

    this.image.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        // A completer
      }
    });

    this.saveButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        // retrieves all fields and update the clothe then insert in BDD
        clotheToEdit.setModel(modelEditText.getText().toString());
        clotheToEdit.setBrand(brandEditText.getText().toString());
        clotheToEdit.setColor(colorSpinner.getSelectedItem().toString());
        clotheToEdit.setType(typeSpinner.getSelectedItem().toString());
        // !!!!!!!!!! Image à ajouter !!!!!!!!!!!

        // update in DB
        Toast toast;
        if (bdd.updateClothe(clotheToEdit, clotheID) == 1) {
          toast =
              Toast.makeText(getApplicationContext(), "Modifications have been saved !",
                  Toast.LENGTH_SHORT);
        } else {
          toast =
              Toast.makeText(getApplicationContext(),
                  "An error occured while saving modifications", Toast.LENGTH_SHORT);
        }
        // print message
        toast.show();
        // close DB
        bdd.close();
      }
    });

    // init all the dynamic fields (spinners and editable fields)
    this.initFieldsWithValues();
  }

  /**
   * Init the spinners with the values stored in database
   */
  public void initSpinnersWithData() {
    ArrayList<String> colors = this.bdd.getAllColors();
    ArrayList<String> types = this.bdd.getAllTypes();

    /* create colorSpinnerAdapter and initialising the corresponding spinner with */
    ArrayAdapter<String> colorAdapter =
        new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,
            colors);
    colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    this.colorSpinner.setAdapter(colorAdapter);
    // get the clotheToEdit color and put it as default value
    this.colorSpinner.setSelection(colorAdapter.getPosition(this.clotheToEdit.getColor()));

    /* create typeSpinnerAdapter and initialising the corresponding spinner with */
    ArrayAdapter<String> typeAdapter =
        new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,
            types);
    typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    this.typeSpinner.setAdapter(typeAdapter);
    // get the clotheToEdit color and put it as default value
    this.typeSpinner.setSelection(typeAdapter.getPosition(this.clotheToEdit.getType()));
  }

  /**
   * fill the editable fields with the caracteristics of the clothe in order to edit them
   */
  public void initFieldsWithValues() {
    // !!!!!!!!!! Image à ajouter !!!!!!!!!!!
    this.modelEditText.setText(this.clotheToEdit.getModel());
    this.brandEditText.setText(this.clotheToEdit.getBrand());
    this.initSpinnersWithData();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.modify_clothe);

    this.bdd = new DBHelper(this);
    this.bdd.open();

    // retrieve the clothe object and his Id in Database passed in the intent
    Intent intent = getIntent();
    if (intent != null) {
      this.clotheToEdit = (Clothe) intent.getSerializableExtra("clothe");
      this.clotheID = intent.getIntExtra("ID", 0);

      if (this.clotheToEdit == null || this.clotheID == 0) {
        throw new RuntimeException(new Exception("Error while retrieving information from intent"));
      }
    }
    // init the components of the page
    this.initComponents();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.modify_clothe, menu);
    return true;
  }

}
