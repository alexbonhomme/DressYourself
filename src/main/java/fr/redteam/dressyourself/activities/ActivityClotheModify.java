package fr.redteam.dressyourself.activities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
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
import fr.redteam.dressyourself.common.DBHelper;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.exceptions.DressyourselfRuntimeException;

/**
 * 
 * @author adrien This activity allows the user to modify the caracteristics of a clothe
 */

public class ActivityClotheModify extends Activity {

  /* constant */
  private final int SELECT_IMAGE = 1;

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

    /*
     * trick to hide the image scaling by setting the button background color with the value of the
     * activity background color
     */
    TypedArray array = this.getTheme().obtainStyledAttributes(new int[] {android.R.attr.colorBackground});
    this.image.setBackgroundColor(array.getColor(0, 0xFFFFFF));

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
        /* retrieves all fields and update the clothe then insert in BDD */
        updateClotheValues(clotheToEdit);
        /* update in DB */
        Toast toast;
        bdd.open();
        if (bdd.updateClothe(clotheToEdit) == 1) {
          toast = Toast.makeText(ActivityClotheModify.this, "Modifications have been saved !", Toast.LENGTH_LONG);
        } else {
          toast = Toast.makeText(ActivityClotheModify.this, "An error occured while saving modifications", Toast.LENGTH_LONG);
        }
        /* DEBUG display clothe informations */
        Log.d("Clothe After Modification ", clotheToEdit.getModel() + " " + clotheToEdit.getBrand() + " " + clotheToEdit.getColor() + " " + clotheToEdit.getType());
        /* print feedback message */
        toast.show();
        /* close DB */
        bdd.close();
        /* close Window */
        finish();
      }
    });

  }

  /** Init the spinners with the values stored in database */
  public void initSpinnersWithData() {
    /* retrieve informations from database */
    this.bdd.open();
    ArrayList<String> colors = this.bdd.getAllColors();
    ArrayList<String> types = this.bdd.getAllTypes();
    this.bdd.close();
    /* create colorSpinnerAdapter and initialising the corresponding spinner with */
    ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, colors);
    colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    this.colorSpinner.setAdapter(colorAdapter);
    /* get the clotheToEdit color and put it as default value */
    this.colorSpinner.setSelection(colorAdapter.getPosition(this.clotheToEdit.getColor()));

    /* create typeSpinnerAdapter and initialising the corresponding spinner with */
    ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
    typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    this.typeSpinner.setAdapter(typeAdapter);
    /* get the clotheToEdit color and put it as default value */
    this.typeSpinner.setSelection(typeAdapter.getPosition(this.clotheToEdit.getType()));
  }

  /** fill the editable fields with the caracteristics of the clothe in order to edit them */
  public void initFieldsWithValues(Clothe clotheToEdit) {
    this.image.setImageDrawable(Drawable.createFromStream(clotheToEdit.getImage(), clotheToEdit.getModel()));
    this.modelEditText.append(clotheToEdit.getModel() + "");
    this.brandEditText.append(clotheToEdit.getBrand() + "");
    this.initSpinnersWithData();
  }

  /** update clothe attributes with values of editable fields */
  public void updateClotheValues(Clothe clotheToEdit) {
    try {
      if (this.selectedImageUri != null) {
        clotheToEdit.setImage(getContentResolver().openInputStream(this.selectedImageUri));
      }
      clotheToEdit.setModel(modelEditText.getText().toString());
      clotheToEdit.setBrand(brandEditText.getText().toString());
      clotheToEdit.setColor(colorSpinner.getSelectedItem().toString());
      clotheToEdit.setType(typeSpinner.getSelectedItem().toString());
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      throw new DressyourselfRuntimeException(e);
    }

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.modify_clothe);

    /* get DBHelper */
    bdd = new DBHelper(ActivityClotheModify.this);

    /* retrieve the clothe object passed in the intent */
    Intent intent = getIntent();
    if (intent != null) {
      this.clotheToEdit = (Clothe) intent.getSerializableExtra("clothe");

      if (this.clotheToEdit == null) {
        throw new DressyourselfRuntimeException("ModifyClothe : Error while retrieving information from intent");
      }
    }

    /* just to test */
    try {
      if (intent.getStringExtra("image") != null) {
        this.clotheToEdit.setImage(new FileInputStream(intent.getStringExtra("image")));
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
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
    if (requestCode == SELECT_IMAGE) {
      if (resultCode == Activity.RESULT_OK) {
        this.selectedImageUri = data.getData();
        this.image.setImageURI(this.selectedImageUri);
        Log.d("path", this.selectedImageUri.getPath());
      }
    }
  }

}
