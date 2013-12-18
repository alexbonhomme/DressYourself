package fr.redteam.dressyourself.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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
import fr.redteam.dressyourself.common.database.DBHelper;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.exceptions.DressyourselfRuntimeException;

/**
 * 
 * @author adrien This activity allows the user to modify the caracteristics of a clothe
 */

public class ActivityClotheModify extends Activity {

  /* constant */
  private static final int SELECT_IMAGE = 1;

  /* the clothe to Edit */
  private Clothe clotheToEdit;

  /* Uri of the new image if image is modified */
  private Uri selectedImageUri;

  /* components */
  private ImageButton image;
  private EditText modelEditText;
  private EditText brandEditText;
  private Spinner colorSpinner;
  private Spinner typeSpinner;
  private Button saveButton;

  /* DB helper */
  private DBHelper bdd;

  /** init the attributes with their corresponding item in layout */
  public void initComponents() {
    /* retrieving components from layout */
    this.image = (ImageButton) findViewById(R.id.imageButton);
    this.modelEditText = (EditText) findViewById(R.id.modelEdit);
    this.brandEditText = (EditText) findViewById(R.id.brandEdit);
    this.colorSpinner = (Spinner) findViewById(R.id.colorSpinner);
    this.typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
    this.saveButton = (Button) findViewById(R.id.save);

    /* init all the dynamic fields (spinners and editable fields) */
    this.initFieldsWithValues(this.clotheToEdit);

    /* add listeners */
    this.image.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        /* creation of intent which allow the user to pick up an image in his device */
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_IMAGE);
      }
    });

    this.saveButton.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        /* retrieves all editable fields values and update the clothe then update in BDD */
        updateClotheValues(clotheToEdit);
        Toast toast;
        bdd.open();
        if (bdd.updateClothe(clotheToEdit) == (long)1) {
          toast = Toast.makeText(ActivityClotheModify.this, "Modifications have been saved !", Toast.LENGTH_LONG);
        } else {
          toast = Toast.makeText(ActivityClotheModify.this, "An error occured while saving modifications", Toast.LENGTH_LONG);
        }
        toast.show();
        bdd.close();
        finish();
      }
    });

  }

  /** Transform a null String in an empty String */
  public String nullStringToEmptyString(String s) {
    return (s == null ? s + " " : s);
  }

  /** Init the spinners with the values stored in database */
  public void initSpinnersWithData() {
    /* retrieve informations from database */
    this.bdd.open();
    List<String> colors = this.bdd.getAllColors();
    List<String> types = this.bdd.getAllTypes();
    this.bdd.close();

    /* create colorSpinnerAdapter and initialize the corresponding spinner with */
    ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colors);
    colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    this.colorSpinner.setAdapter(colorAdapter);

    /* get the clotheToEdit color and put it as default value */
    this.colorSpinner.setSelection(colorAdapter.getPosition(this.clotheToEdit.getColor()));

    /* create typeSpinnerAdapter and initialize the corresponding spinner with */
    ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
    typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    this.typeSpinner.setAdapter(typeAdapter);

    /* get the clotheToEdit color and put it as default value */
    this.typeSpinner.setSelection(typeAdapter.getPosition(this.clotheToEdit.getType()));
  }

  /** fill the editable fields with the caracteristics of the clothe in order to edit them */
  public void initFieldsWithValues(Clothe clotheToEdit) {
    // this.image.setImageURI(Uri.fromFile(new ClothesManager(new
    // AndroidFileManager(this)).loadClotheImage(nullStringToEmptyString(this.clotheToEdit.getImageRelativePath()))));
    this.modelEditText.append(nullStringToEmptyString(clotheToEdit.getModel()));
    this.brandEditText.append(nullStringToEmptyString(clotheToEdit.getBrand()));
    this.initSpinnersWithData();
  }

  /** update clothe attributes with values of editable fields */
  public void updateClotheValues(Clothe clotheToEdit) {
    if (this.selectedImageUri != null) {
      clotheToEdit.setImageRelativePath(selectedImageUri.getPath());
      Log.d("ImagePath", selectedImageUri.getPath());
    }
    clotheToEdit.setModel(modelEditText.getText().toString());
    clotheToEdit.setBrand(brandEditText.getText().toString());
    clotheToEdit.setColor(colorSpinner.getSelectedItem().toString());
    clotheToEdit.setType(typeSpinner.getSelectedItem().toString());

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.modify_clothe);

    bdd = new DBHelper(ActivityClotheModify.this);

    /* retrieve the clothe object passed in the intent */
    Intent intent = getIntent();
    if (intent != null) {
      this.clotheToEdit = (Clothe) intent.getSerializableExtra("clothe");

      if (this.clotheToEdit == null) {
        throw new DressyourselfRuntimeException("ModifyClothe : Error while retrieving information from intent");
      }
    }

    /* init the components of the page */
    this.initComponents();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    /* Inflate the menu; this adds items to the action bar if it is present. */
    getMenuInflater().inflate(R.menu.modify_clothe, menu);
    return true;
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
      /* hide background color of the imageButton */
      this.image.setBackgroundColor(android.R.attr.colorBackground);
      this.selectedImageUri = data.getData();
      this.image.setImageURI(this.selectedImageUri);
      Log.d("path", this.selectedImageUri.getPath());
    }
  }


}
