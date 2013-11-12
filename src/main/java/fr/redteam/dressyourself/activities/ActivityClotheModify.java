package fr.redteam.dressyourself.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.common.DBHelper;
import fr.redteam.dressyourself.core.clothes.Clothe;

/**
 * 
 * @author adrien This activity allows the user to modify the caracteristics of a clothe
 */

public class ActivityClotheModify extends Activity {


  /* the clothe to Edit */
  private Clothe clotheToEdit;

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
        updateClotheValues(clotheToEdit);
        // update in DB
        Toast toast;
        if (bdd.updateClothe(clotheToEdit) == 1) {
          toast =
              Toast.makeText(ActivityClotheModify.this, "Modifications have been saved !",
                  Toast.LENGTH_LONG);
        } else {
          toast =
              Toast.makeText(ActivityClotheModify.this,
                  "An error occured while saving modifications", Toast.LENGTH_LONG);
        }
        // DEBUG display clothe informations
        Log.d("Clothe After Modification ", clotheToEdit.getModel() + " " + clotheToEdit.getBrand()
            + " " + clotheToEdit.getColor() + " " + clotheToEdit.getType());
        // print feedback message
        toast.show();
        // close DB
        bdd.close();
        // close Window
        finish();
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
        new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
            colors);
    colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    this.colorSpinner.setAdapter(colorAdapter);
    // get the clotheToEdit color and put it as default value
    this.colorSpinner.setSelection(colorAdapter.getPosition(this.clotheToEdit.getColor()));

    /* create typeSpinnerAdapter and initialising the corresponding spinner with */
    ArrayAdapter<String> typeAdapter =
        new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
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
    this.modelEditText.append(this.clotheToEdit.getModel());
    this.brandEditText.append(this.clotheToEdit.getBrand());
    this.initSpinnersWithData();
  }

  /* update clothe attributes with values of editable fields */
  public void updateClotheValues(Clothe clotheToEdit) {
    clotheToEdit.setModel(modelEditText.getText().toString());
    clotheToEdit.setBrand(brandEditText.getText().toString());
    clotheToEdit.setColor(colorSpinner.getSelectedItem().toString());
    clotheToEdit.setType(typeSpinner.getSelectedItem().toString());
    // !!!!!!!!!! Image à ajouter !!!!!!!!!!!
  }
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.modify_clothe);

    // retrieve the clothe object passed in the intent
    Intent intent = getIntent();
    if (intent != null) {
      this.clotheToEdit = (Clothe) intent.getSerializableExtra("clothe");

      if (this.clotheToEdit == null) {
        throw new RuntimeException(new Exception("Error while retrieving information from intent"));
      }
    }
    // open BDD
    bdd = new DBHelper(ActivityClotheModify.this);
    bdd.open();
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
