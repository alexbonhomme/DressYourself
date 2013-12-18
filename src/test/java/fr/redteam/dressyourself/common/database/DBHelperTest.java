package fr.redteam.dressyourself.common.database;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Context;
import fr.redteam.dressyourself.common.database.DBHelper;
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

  
  @Test
  public void testInsertColor() {
    long c = db.insertColor("Mauve");
    assertEquals("Mauve", db.getColor(c));
    
  }

  @Test
  public void testInsertWeather() {
    fail("Not yet implemented");
   }

  @Test
  public void testInsertBodies() {
    fail("Not yet implemented");
   }

  @Test
  public void testInsertType() {
    fail("Not yet implemented");
   }
  
  @Test
  public void testInsertBrand() {
	  fail("Not yet implemented");
  }

  @Test
  public void testInsertClothes() {
    Clothe clothe = new Clothe("toto");
    clothe.setBodies("haut");
    clothe.setBrand("xara");
    clothe.setColor("mauve");
    clothe.setImageRelativePath("path");
    clothe.setModel("model");
    clothe.setType("pull");
    long c = db.insertClothes(clothe);
	assertEquals(clothe, db.getClothe(c));
  }

  @Test
  public void testInsertOutfit() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIDColor() {
    long c = db.insertColor("Mauve");
    long l = db.getIDColor("Mauve");
    assertEquals(c,l);
  }

  @Test
  public void testGetIDWeather() {
    long c = db.insertWeather("hot");
    long l = db.getIDWeather("hot");
    assertEquals(c,l);
    }

  @Test
  public void testGetIDBodies() {
    long c = db.insertBodies("Top");
    
    long l = db.getIDBodies("Top");
    assertEquals(c,l);  }

  @Test
  public void testGetIDType() {
    long c = db.insertType("Jeans",1);
    
    long l = db.getIDType("Jeans");
    assertEquals(c,l);  }

  @Test
  public void testGetIDClothes() {
    Clothe clothe = new Clothe("toto");
    List<String> w = new ArrayList<String>();
    w.add("chaud");
    clothe.setBodies("haut");
    clothe.setBrand("xara");
    clothe.setColor("mauve");
    clothe.setImageRelativePath("path");

    long c = db.insertClothes(clothe);
    System.out.println();
    long l = db.getIDClothes("toto");

    assertEquals(c,l);
    }

  @Test
  public void testGetIDOutfit() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIDBrand() {
    long c = db.insertBrand("Mauve");
    
    long l = db.getIDBrand("Mauve");
    assertEquals(c,l);  
    }

  @Test
  public void testGetColor() {
    long l = db.insertColor("mauve");
    assertEquals (db.getColor(l),("mauve"));
  }

  @Test
  public void testGetBodies() {
    long l = db.insertBodies("mauve");
    assertEquals (db.getBodies(l),("mauve"));
    }

  @Test
  public void testGetWeather() {
    long l = db.insertWeather("mauve");
    assertEquals(db.getWeather(l),("mauve"));
    }

  @Test
  public void testGetType() {
    long l = db.insertType("mauve",0);
    assertEquals (db.getType(l),("mauve"));  }

  @Test
  public void testGetBrand() {
    long l = db.insertBrand("mauve");
    assertEquals (db.getBrand(l),("mauve"));
    }

  @Test
  public void testGetClothe() {
	  Clothe clothe = new Clothe("toto");
	  clothe.setBodies("TOP");
	  clothe.setBrand("xara");
	  clothe.setColor("mauve");
	  clothe.setImageRelativePath("path");
	  clothe.setType("pull");
	  long c = db.insertClothes(clothe);
	  Clothe result = db.getClothe(c);	
	  System.out.println(result.getBodies());
	  assertTrue(clothe.getBodies().equals(result.getBodies()));
  }

  @Test
  public void testGetListTop() {
	  Clothe clothe = new Clothe("toto");
	  clothe.setBodies("Bottom");
	  clothe.setBrand("xara");
	  clothe.setColor("mauve");
	  clothe.setImageRelativePath("path");
	  clothe.setType("pull");
	  long c = db.insertClothes(clothe);
	  List clothes = db.getListFeet();
  }

  @Test
  public void testGetListBottom() {
	  Clothe clothe = new Clothe("toto");
	  clothe.setBodies("shoes");
	  clothe.setBrand("xara");
	  clothe.setColor("mauve");
	  clothe.setImageRelativePath("path");
	  clothe.setType("pull");
	  long c = db.insertClothes(clothe);
	  List clothes = db.getListFeet();   }

  @Test
  public void testGetListFeet() {
	  Clothe clothe = new Clothe("toto");
	  clothe.setBodies("shoes");
	  clothe.setBrand("xara");
	  clothe.setColor("mauve");
	  clothe.setImageRelativePath("path");
	  clothe.setType("pull");
	  long c = db.insertClothes(clothe);
	  List clothes = db.getListFeet(); 
  }

  @Test
  public void testGetListClothes() {
	  Clothe clothe = new Clothe("toto");
	  clothe.setBodies("shoes");
	  clothe.setBrand("xara");
	  clothe.setColor("mauve");
	  clothe.setImageRelativePath("path");
	  clothe.setType("pull");
	  long c = db.insertClothes(clothe);
	  List clothes = db.getListClothes();   }

  @Test
  public void testGetAllColors() {
	  List t;
	  long c = db.insertColor("mauve");
	  t=db.getAllColors();
  }

  @Test
  public void testGetAllTypes() {
	  List t;
	  long c = db.insertType("Mauve",1);
	  t = db.getAllTypes();
	  }

  @Test
  public void testUpdateClothe() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetOutfit() {
    fail("Not yet implemented");
  }

}
