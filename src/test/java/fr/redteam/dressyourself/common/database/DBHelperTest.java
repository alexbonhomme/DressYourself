package fr.redteam.dressyourself.common.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Context;
import fr.redteam.dressyourself.core.clothes.Clothe;

@RunWith(RobolectricTestRunner.class)
public class DBHelperTest  {

  DBHelper db;
  Context context;

  @Before
  public void setUp() throws Exception {
    context = Robolectric.getShadowApplication().getApplicationContext();
    db = new DBHelper(context, null);
    db.open();
  }

  @After
  public void tearDown() throws Exception {
    db.close();
  }

  /**
   * Generate a {@link Clothe} object from the given features.
   * 
   * @param model
   * @param bodies
   * @param brand
   * @param color
   * @param path
   * @param type
   * 
   * @return {@link Clothe}
   */
  private Clothe createClothe(String model, String bodies, String brand, String color,
      String path, String type) {
    Clothe clothe = new Clothe(model);
    clothe.setBodies(bodies);
    clothe.setBrand(brand);
    clothe.setColor(color);
    clothe.setImageRelativePath(path);
    clothe.setType(type);

    return clothe;
  }

  @Test
  public void testInsertClothes() throws NoSuchMethodException, IllegalArgumentException,
      IllegalAccessException, InvocationTargetException {

    List<Clothe> expectedClothes  = new ArrayList<Clothe>();

    Clothe clothe = createClothe("toto", "Top", "Zara", "mauve", "path", "pull");
    clothe.setId(db.insertClothes(clothe));
    expectedClothes.add(clothe);

    clothe = createClothe("tata", "Bottom", "Zara", "mauve", "path", "jean");
    clothe.setId(db.insertClothes(clothe));
    expectedClothes.add(clothe);

    // Testing
    List<Clothe> clothes = db.getListClothes();

    assertEquals(2, clothes.size());

    assertEquals(expectedClothes.get(0).getId(), clothes.get(0).getId());
    assertEquals(expectedClothes.get(1).getId(), clothes.get(1).getId());

    assertEquals(expectedClothes.get(0).getModel(), clothes.get(0).getModel());
    assertEquals(expectedClothes.get(1).getModel(), clothes.get(1).getModel());

    assertEquals(expectedClothes.get(0).getBodies(), clothes.get(0).getBodies());
    assertEquals(expectedClothes.get(1).getBodies(), clothes.get(1).getBodies());

  }


  @Test
  public void testGetListTop() {
    Clothe clothe = createClothe("toto", "Top", "Zara", "mauve", "path", "pull");
    db.insertClothes(clothe);

    clothe = createClothe("tata", "Bottom", "Zara", "mauve", "path", "jean");
    db.insertClothes(clothe);

    clothe = createClothe("titi", "Top", "Zara", "red", "path", "pull");
    db.insertClothes(clothe);

    // Testing
    List<Clothe> clothes = db.getListTop();
    assertTrue(clothes.size() > 0);

    for (Clothe topClothe : clothes) {
      assertEquals("Top", topClothe.getBodies());
    }
  }

  @Test
  public void testGetListBottom() {
    Clothe clothe = createClothe("toto", "Top", "Zara", "mauve", "path", "pull");
    db.insertClothes(clothe);

    clothe = createClothe("tata", "Bottom", "Zara", "mauve", "path", "jean");
    db.insertClothes(clothe);

    clothe = createClothe("titi", "Bottom", "Zara", "red", "path", "jean");
    db.insertClothes(clothe);

    // Testing
    List<Clothe> clothes = db.getListBottom();
    assertTrue(clothes.size() > 0);

    for (Clothe bottomClothe : clothes) {
      assertEquals("Bottom", bottomClothe.getBodies());
    }
  }

  @Test
  public void testGetListFeet() {
    Clothe clothe = createClothe("toto", "Shoes", "Zara", "black", "path", "boots");
    db.insertClothes(clothe);

    clothe = createClothe("tata", "Bottom", "Zara", "mauve", "path", "jean");
    db.insertClothes(clothe);

    clothe = createClothe("titi", "Top", "Zara", "red", "path", "pull");
    db.insertClothes(clothe);

    // Testing
    List<Clothe> clothes = db.getListFeet();
    assertTrue(clothes.size() > 0);

    for (Clothe topClothe : clothes) {
      assertEquals("Shoes", topClothe.getBodies());
    }
  }


  @Test
  public void testGetListClothes() {
    List<Clothe> expectedClothes = new ArrayList<Clothe>();

    Clothe clothe = createClothe("toto", "Top", "Zara", "mauve", "path", "pull");
    clothe.setId(db.insertClothes(clothe));
    expectedClothes.add(clothe);

    clothe = createClothe("tata", "Bottom", "Zara", "mauve", "path", "jean");
    clothe.setId(db.insertClothes(clothe));
    expectedClothes.add(clothe);

    clothe = createClothe("titi", "Top", "Zara", "mauve", "path", "t-shirt");
    clothe.setId(db.insertClothes(clothe));
    expectedClothes.add(clothe);

    // Testing
    List<Clothe> clothes = db.getListClothes();
    for (int i = 0; i < clothes.size(); i++) {
      assertEquals(expectedClothes.get(i).getId(), clothes.get(i).getId());
      assertEquals(expectedClothes.get(i).getModel(), clothes.get(i).getModel());
      assertEquals(expectedClothes.get(i).getBodies(), clothes.get(i).getBodies());
    }
  }

  @Test
  public void testUpdateClothe() {
    List<Clothe> expectedClothes = new ArrayList<Clothe>();

    Clothe clothe = createClothe("toto", "Top", "Zara", "red", "path", "pull");
    clothe.setId(db.insertClothes(clothe));
    expectedClothes.add(clothe);

    clothe = createClothe("tata", "Bottom", "Zara", "mauve", "path", "jean");
    clothe.setId(db.insertClothes(clothe));
    expectedClothes.add(clothe);

    // Updating
    clothe.setColor("red");
    db.updateClothe(clothe);

    // Testing
    List<Clothe> clothes = db.getListClothes();
    assertEquals(2, clothes.size());

    assertEquals(expectedClothes.get(0).getModel(), clothes.get(0).getModel());
    assertEquals(clothe.getColor(), clothes.get(1).getColor());

  }

}
