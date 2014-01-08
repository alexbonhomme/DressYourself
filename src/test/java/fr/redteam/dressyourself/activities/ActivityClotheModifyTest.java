package fr.redteam.dressyourself.activities;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowEnvironment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.exceptions.DressyourselfRuntimeException;

@RunWith(RobolectricTestRunner.class)
public class ActivityClotheModifyTest {

  private ActivityClotheModify myActivity;
  private Context context;

  private ImageButton image;
  private EditText modelEditText;
  private EditText brandEditText;
  private Spinner colorSpinner;
  private Spinner typeSpinner;
  private Clothe clotheToEdit;

  @Before
  public void setUp() {

    /* creating a clothe to Edit */
    this.clotheToEdit = new Clothe("my zara red pull");
    this.clotheToEdit.setBrand("Zara");
    this.clotheToEdit.setColor("RED");
    this.clotheToEdit.setType("pull");

    /* getting context */
    this.context = Robolectric.getShadowApplication().getApplicationContext();
    ShadowEnvironment.setExternalStorageState(Environment.MEDIA_MOUNTED);

    /* passing the clothe through intent */
    Intent intent = new Intent(this.context, ActivityClotheModify.class);
    intent.putExtra("clothe", this.clotheToEdit);

    /* retrieving the activity */
    this.myActivity = Robolectric.buildActivity(ActivityClotheModify.class).withIntent(intent).create().get();

    /* retrieving the editable components */
    this.image = (ImageButton) this.myActivity.findViewById(R.id.imageButton);
    this.modelEditText = (EditText) this.myActivity.findViewById(R.id.modelEdit);
    this.brandEditText = (EditText) this.myActivity.findViewById(R.id.brandEdit);
    this.colorSpinner = (Spinner) this.myActivity.findViewById(R.id.colorSpinner);
    this.typeSpinner = (Spinner) this.myActivity.findViewById(R.id.typeSpinner);

    /* retrieving informations from intent */
    this.clotheToEdit = (Clothe) intent.getSerializableExtra("clothe");

  }

  /* the following tests check the initialization of editable fields */

  /* check if the clothe's model value is properly loaded into model EditText */
  @Test
  public void testModelInitialValue() {
    assertEquals(this.clotheToEdit.getModel(), this.modelEditText.getText().toString());
  }

  /* check if the clothe's brand value is properly loaded into brand EditText */
  @Test
  public void testBrandInitialValue() {
    assertEquals(this.clotheToEdit.getBrand(), this.brandEditText.getText().toString());
  }

  /* check if the clothe's color value is properly loaded as first element into color Spinner */
  @Test
  public void testColorInitialValue() {
    assertEquals(this.clotheToEdit.getColor(), this.colorSpinner.getSelectedItem().toString());
  }

  /* check if the clothe's type value is properly loaded as first element into type Spinner */
  @Test
  public void testTypeInitialValue() {
    assertEquals(this.clotheToEdit.getType(), this.typeSpinner.getSelectedItem().toString());
  }

  /*
   * the following tests check the values of clothe attributes after modifications to ensure it has
   * been saved into the clothe object
   */

  /* check if the modifications on model have been saved */
  @Test
  public void testUpdateClotheValuesOnModel() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    Method updateClotheMethod = ActivityClotheModify.class.getDeclaredMethod("updateClothe", Clothe.class);
    updateClotheMethod.setAccessible(true);

    this.modelEditText.setText("modified model!");
    updateClotheMethod.invoke(this.myActivity, this.clotheToEdit);
    assertEquals(this.modelEditText.getText().toString(), this.clotheToEdit.getModel());
  }

  /* check if the modifications on brand have been saved */
  @Test
  public void testUpdateClotheValuesOnBrand() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    Method updateClotheMethod = ActivityClotheModify.class.getDeclaredMethod("updateClothe", Clothe.class);
    updateClotheMethod.setAccessible(true);

    this.brandEditText.setText("modified brand!");
    updateClotheMethod.invoke(this.myActivity, this.clotheToEdit);
    assertEquals(this.brandEditText.getText().toString(), this.clotheToEdit.getBrand());
  }

  /* check if the modifications on color have been saved */
  @Test
  public void testUpdateClotheValuesOnColor() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    Method updateClotheMethod = ActivityClotheModify.class.getDeclaredMethod("updateClothe", Clothe.class);
    updateClotheMethod.setAccessible(true);

    ArrayAdapter<String> colorAdapter = (ArrayAdapter<String>) this.colorSpinner.getAdapter();
    this.colorSpinner.setSelection(colorAdapter.getPosition("BLUE"));
    updateClotheMethod.invoke(this.myActivity, this.clotheToEdit);
    assertEquals(this.colorSpinner.getSelectedItem().toString(), this.clotheToEdit.getColor());
  }

  /* check if the modifications on type have been saved */
  @Test
  public void testUpdateClotheValuesOnType() throws NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    Method updateClotheMethod = ActivityClotheModify.class.getDeclaredMethod("updateClothe", Clothe.class);
    updateClotheMethod.setAccessible(true);

    ArrayAdapter<String> typeAdapter = (ArrayAdapter<String>) this.typeSpinner.getAdapter();
    this.typeSpinner.setSelection(typeAdapter.getPosition("T-shirt"));
    updateClotheMethod.invoke(this.myActivity, this.clotheToEdit);
    assertEquals(this.typeSpinner.getSelectedItem().toString(), this.clotheToEdit.getType());
  }

  @Test(expected = DressyourselfRuntimeException.class)
  public void testIfClotheToModifyIsNullThrowException() {
    this.clotheToEdit = null;
    Intent intent = new Intent(this.context, ActivityClotheModify.class);
    intent.putExtra("clothe", this.clotheToEdit);
    this.myActivity = Robolectric.buildActivity(ActivityClotheModify.class).withIntent(intent).create().get();

  }

  @Test
  public void updateImageTest() throws NoSuchMethodException, IllegalArgumentException,
      IllegalAccessException, InvocationTargetException {
    Class[] parametersType = {Uri.class, Clothe.class};
    Method updateImageMethod = ActivityClotheModify.class.getDeclaredMethod("updateImage", parametersType);
    updateImageMethod.setAccessible(true);
    
    Uri imageUri = Uri.fromFile(new File("res/drawable/bottines.jpg"));
    updateImageMethod.invoke(this.myActivity, imageUri, this.clotheToEdit);

    assertEquals(imageUri.getPath(), this.clotheToEdit.getImageRelativePath());
  }

  @Test
  public void testCopySelectedImage() throws NoSuchMethodException, IllegalArgumentException,
      IllegalAccessException, InvocationTargetException {
    Method copyselectedImage = ActivityClotheModify.class.getDeclaredMethod("copySelectedImage", File.class);
    copyselectedImage.setAccessible(true);
    
    File f = new File("res/drawable/bottines.jpg");
    copyselectedImage.invoke(this.myActivity, f);

    File copiedFile = new File("res/drawable/imported/bottines.jpg");
    assertTrue(copiedFile.exists());
  }

  @AfterClass
  public static void clean() {
    File f = new File("res/drawable/imported/bottines.jpg");
    f.delete();
  }

}
