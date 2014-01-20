package fr.redteam.dressyourself.common.database;

import static org.junit.Assert.*;

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

  // ce commentaire est provisoire car ces classes

  /*
   * @Test public void testInsertColor() {zz long c = db.insertColor("Mauve"); assertEquals("Mauve",
   * db.getColor(c));
   * 
   * }
   * 
   * @Test public void testInsertWeather() { fail("Not yet implemented"); }
   * 
   * @Test public void testInsertBodies() { fail("Not yet implemented"); }
   * 
   * @Test public void testInsertType() { fail("Not yet implemented"); }
   * 
   * @Test public void testInsertBrand() { fail("Not yet implemented"); }
   */
  @Test
  public void testInsertClothes() throws NoSuchMethodException, IllegalArgumentException,
      IllegalAccessException, InvocationTargetException {
	 
    Clothe clothe = new Clothe("toto");
    clothe.setBodies("TOP");
    clothe.setBrand("xara");
    clothe.setColor("mauve");
    clothe.setImageRelativePath("path");
    clothe.setModel("model");
    clothe.setType("pull");
    clothe.setId( db.insertClothes(clothe));
    List<Clothe> compClothes  = new ArrayList<Clothe>();
    compClothes.add(clothe);
    clothe = new Clothe("tata");
    clothe.setBodies("Bot");
    clothe.setBrand("xara");
    clothe.setColor("mauve");
    clothe.setImageRelativePath("path");
    clothe.setModel("test");
    clothe.setType("jean");
    clothe.setId( db.insertClothes(clothe));
    compClothes.add(clothe);
    List<Clothe> clothes = db.getListClothes();
    
    assertEquals(compClothes.get(0).getId(), clothes.get(0).getId());
    assertEquals(compClothes.get(1).getId(), clothes.get(1).getId());
    
    assertEquals(compClothes.get(0).getModel(), clothes.get(0).getModel());
    assertEquals(compClothes.get(1).getModel(), clothes.get(1).getModel());

    assertEquals(compClothes.get(0).getBodies(), clothes.get(0).getBodies());
    assertEquals(compClothes.get(1).getBodies(), clothes.get(1).getBodies());

  }

 
  @Test
  public void testGetListTop() {
	  Clothe clothe = new Clothe("toto");
	    clothe.setBodies("Top");
	    clothe.setBrand("xara");
	    clothe.setColor("mauve");
	    clothe.setImageRelativePath("path");
	    clothe.setModel("model");
	    clothe.setType("pull");
	    clothe.setId( db.insertClothes(clothe));
	    List<Clothe> compClothes  = new ArrayList<Clothe>();
	    compClothes.add(clothe);
	    clothe = new Clothe("tata");
	    clothe.setBodies("Bot");
	    clothe.setBrand("xara");
	    clothe.setColor("mauve");
	    clothe.setImageRelativePath("path");
	    clothe.setModel("test");
	    clothe.setType("jean");
	    clothe.setId( db.insertClothes(clothe));
	    compClothes.add(clothe);
	    clothe = new Clothe("titi");
	    clothe.setBodies("Top");
	    clothe.setBrand("xara");
	    clothe.setColor("mauve");
	    clothe.setImageRelativePath("path");
	    clothe.setModel("test");
	    clothe.setType("jean");
	    clothe.setId( db.insertClothes(clothe));
	    compClothes.add(clothe);
	    List<Clothe> clothes = db.getListTop();
	    assertTrue(0 < clothes.size());
		for (int i = 0; i < clothes.size(); i ++){
		    assertEquals("Top", clothes.get(i).getBodies());
		}
  }

  @Test
  public void testGetListBottom() {
	  Clothe clothe = new Clothe("toto");
	    clothe.setBodies("Top");
	    clothe.setBrand("xara");
	    clothe.setColor("mauve");
	    clothe.setImageRelativePath("path");
	    clothe.setModel("model");
	    clothe.setType("pull");
	    clothe.setId( db.insertClothes(clothe));
	    List<Clothe> compClothes  = new ArrayList<Clothe>();
	    compClothes.add(clothe);
	    clothe = new Clothe("tata");
	    clothe.setBodies("Bottom");
	    clothe.setBrand("xara");
	    clothe.setColor("mauve");
	    clothe.setImageRelativePath("path");
	    clothe.setModel("test");
	    clothe.setType("jean");
	    clothe.setId( db.insertClothes(clothe));
	    compClothes.add(clothe);
	    clothe = new Clothe("titi");
	    clothe.setBodies("Top");
	    clothe.setBrand("xara");
	    clothe.setColor("mauve");
	    clothe.setImageRelativePath("path");
	    clothe.setModel("test");
	    clothe.setType("jean");
	    clothe.setId( db.insertClothes(clothe));
	    compClothes.add(clothe);
	    List<Clothe> clothes = db.getListBottom();
	    assertTrue(0 < clothes.size());
		for (int i = 0; i < clothes.size(); i ++){
		    assertEquals("Bottom", clothes.get(i).getBodies());
		}   }

  @Test
  public void testGetListFeet() {
	  Clothe clothe = new Clothe("toto");
	    clothe.setBodies("Top");
	    clothe.setBrand("xara");
	    clothe.setColor("mauve");
	    clothe.setImageRelativePath("path");
	    clothe.setModel("model");
	    clothe.setType("pull");
	    clothe.setId( db.insertClothes(clothe));
	    List<Clothe> compClothes  = new ArrayList<Clothe>();
	    compClothes.add(clothe);
	    clothe = new Clothe("tata");
	    clothe.setBodies("Bottom");
	    clothe.setBrand("xara");
	    clothe.setColor("mauve");
	    clothe.setImageRelativePath("path");
	    clothe.setModel("test");
	    clothe.setType("jean");
	    clothe.setId( db.insertClothes(clothe));
	    compClothes.add(clothe);
	    clothe = new Clothe("titi");
	    clothe.setBodies("Shoes");
	    clothe.setBrand("xara");
	    clothe.setColor("mauve");
	    clothe.setImageRelativePath("path");
	    clothe.setModel("test");
	    clothe.setType("pompe");
	    clothe.setId( db.insertClothes(clothe));
	    compClothes.add(clothe);
	    List<Clothe> clothes = db.getListFeet();
	    assertTrue(0 < clothes.size());
		for (int i = 0; i < clothes.size(); i ++){
		    assertEquals("Shoes", clothes.get(i).getBodies());
		}   }
  

  @Test
  public void testGetListClothes() {
	  Clothe clothe = new Clothe("toto");
	    clothe.setBodies("Top");
	    clothe.setBrand("xara");
	    clothe.setColor("mauve");
	    clothe.setImageRelativePath("path");
	    clothe.setModel("model");
	    clothe.setType("pull");
	    clothe.setId( db.insertClothes(clothe));
	    List<Clothe> compClothes  = new ArrayList<Clothe>();
	    compClothes.add(clothe);
	    clothe = new Clothe("tata");
	    clothe.setBodies("Bottom");
	    clothe.setBrand("xara");
	    clothe.setColor("mauve");
	    clothe.setImageRelativePath("path");
	    clothe.setModel("test");
	    clothe.setType("jean");
	    clothe.setId( db.insertClothes(clothe));
	    compClothes.add(clothe);
	    clothe = new Clothe("titi");
	    clothe.setBodies("Top");
	    clothe.setBrand("xara");
	    clothe.setColor("mauve");
	    clothe.setImageRelativePath("path");
	    clothe.setModel("test");
	    clothe.setType("t-shirt");
	    clothe.setId( db.insertClothes(clothe));
	    compClothes.add(clothe);
	    List<Clothe> clothes = db.getListClothes();
	    	for(int i = 0; i <clothes.size(); i ++){
	    		  assertEquals(compClothes.get(i).getId(), clothes.get(i).getId());	    		    
	    		  assertEquals(compClothes.get(i).getModel(), clothes.get(i).getModel());
	    		  assertEquals(compClothes.get(i).getBodies(), clothes.get(i).getBodies());
	    	}
	    }

  /*
   * @Test public void testGetAllColors() { List t; long c = db.insertColor("mauve");
   * t=db.getAllColors(); }
   * 
   * @Test public void testGetAllTypes() { List t; long c = db.insertType("Mauve",1); t =
   * db.getAllTypes(); }
   */
  @Test
  public void testUpdateClothe() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetOutfit() {
    fail("Not yet implemented");
  }

}
