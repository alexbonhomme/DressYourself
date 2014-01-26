package fr.redteam.dressyourself.activities;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import fr.redteam.dressyourself.common.filemanager.AndroidFileManager;
import fr.redteam.dressyourself.core.ClothesManager;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.exceptions.DressyourselfRuntimeException;

/**
 * 
 * @author adrien This activity allows the user to modify the caracteristics of a clothe
 */

public class ActivityClotheModify extends Activity {

  /* constant */
  private static final int SELECT_IMAGE = 1;
  private static final String COPIED_IMAGE_LOCATION = "res/drawable/imported";

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

  /** init the attributes with their corresponding item in layout */
  private void initComponents() {
    /* retrieving components from layout */
    this.image = (ImageButton) findViewById(R.id.imageButton);
    this.modelEditText = (EditText) findViewById(R.id.modelEdit);
    this.brandEditText = (EditText) findViewById(R.id.brandEdit);
    this.colorSpinner = (Spinner) findViewById(R.id.colorSpinner);
    this.typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
    this.saveButton = (Button) findViewById(R.id.save);

  }

  /** Set listeners on buttons */
  private void setListeners() {
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
        updateClothe(clotheToEdit);
        Toast toast;
        bdd.open();
        if (bdd.updateClothe(clotheToEdit) == 1) {
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

  /** load all the data into the page */
  private void loadData(Clothe clothe) {
    this.modelEditText.append(nullStringToEmptyString(clothe.getModel()));
    this.brandEditText.append(nullStringToEmptyString(clothe.getBrand()));
    ClothesManager manager = new ClothesManager(new AndroidFileManager(this.getApplicationContext()));
    this.image.setImageBitmap(manager.getClotheBitmapImage(this.clotheToEdit));

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
    this.colorSpinner.setSelection(colorAdapter.getPosition(clothe.getColor()));

    /* create typeSpinnerAdapter and initialize the corresponding spinner with */
    ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
    typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    this.typeSpinner.setAdapter(typeAdapter);

    /* get the clotheToEdit color and put it as default value */
    this.typeSpinner.setSelection(typeAdapter.getPosition(clothe.getType()));
  }

  /** update the clothe's attributes with values of editable fields */
  private void updateClothe(Clothe clothe) {
    clothe.setModel(modelEditText.getText().toString());
    clothe.setBrand(brandEditText.getText().toString());
    clothe.setColor(colorSpinner.getSelectedItem().toString());
    clothe.setType(typeSpinner.getSelectedItem().toString());

  }

  /**
   * update the imageButton image with the selected image and update the clothe's image value with
   * the new path
   */
  private void updateImage(Uri image, Clothe clothe) {
    clothe.setImageRelativePath(image.getPath());
    this.image.setImageURI(image);
  }

  /** Copy one file to another location */
  private void copySelectedImage(File source) {

    File destinationDir = new File(COPIED_IMAGE_LOCATION);
    try {
      FileUtils.copyFileToDirectory(source, destinationDir);
    } catch (IOException e) {
      e.printStackTrace();
    }
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

      if (this.clotheToEdit != null) {
        this.initComponents();
        this.setListeners();
        this.loadData(this.clotheToEdit);
      } else {
        throw new DressyourselfRuntimeException("ModifyClothe : Error while retrieving information from intent");
      }
    }
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == SELECT_IMAGE && resultCode == Activity.RESULT_OK) {
      /* hack to hide scaling */
      // this.image.setBackgroundColor(android.R.attr.colorBackground);
      this.updateImage(data.getData(), this.clotheToEdit);
      this.copySelectedImage(new File(data.getData().getPath()));
    }
  }

  /** Transform a null String in an empty String */
  public String nullStringToEmptyString(String s) {
    return (s == null ? s + "" : s);
  }

}
