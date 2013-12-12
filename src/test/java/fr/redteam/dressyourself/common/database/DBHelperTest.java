package fr.redteam.dressyourself.common.database;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.model.InitializationError;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.app.Activity;
import android.content.Context;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;
import fr.redteam.dressyourself.R;
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
    
  }

  @Test
  public void testInsertWeather() {
    long c = db.insertWeather("Mauve");
   }

  @Test
  public void testInsertBodies() {
    long c = db.insertBodies("Mauve");
   }

  @Test
  public void testInsertType() {
    long c = db.insertType("Mauve",1);
   }

  @Test
  public void testInsertClothes() {
    Clothe clothe = new Clothe("toto");
    long c = db.insertClothes(clothe);
  }

  @Test
  public void testInsertOutfit() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIDColor() {
    long c = db.insertColor("Mauve");
   
    long l = db.getIDColor("Mauve");
    
    assertTrue(c == l);    ;
  }

  @Test
  public void testGetIDWeather() {
    long c = db.insertWeather("Mauve");
    
    long l = db.getIDWeather("Mauve");
    
    assertTrue(c == l) ;  }

  @Test
  public void testGetIDBodies() {
    long c = db.insertBodies("Mauve");
    
    long l = db.getIDBodies("Mauve");
    assertTrue( c == l) ;  }

  @Test
  public void testGetIDType() {
    long c = db.insertType("Mauve",1);
    
    long l = db.getIDType("Mauve");
    assertTrue( c == l );  }

  @Test
  public void testGetIDClothes() {
    Clothe clothe = new Clothe("toto");
    long c = db.insertClothes(clothe);
    long l = db.getIDClothes("toto");

    assertTrue (c == l);
    }

  @Test
  public void testGetIDOutfit() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIDBrand() {
    long c = db.insertBrand("Mauve");
    
    long l = db.getIDBrand("Mauve");
    assertTrue  (c == l) ;  
    }

  @Test
  public void testGetColor() {
    long l = db.insertColor("mauve");
    assertTrue (db.getColor(l).equals("mauve"));
  }

  @Test
  public void testGetBodies() {
    long l = db.insertBodies("mauve");
    assertTrue (db.getBodies(l).equals("mauve"));
    }

  @Test
  public void testGetWeather() {
    long l = db.insertWeather("mauve");
    assertTrue (db.getWeather(l).equals("mauve"));
    }

  @Test
  public void testGetType() {
    long l = db.insertType("mauve",0);
    assertTrue (db.getType(l).equals("mauve"));  }

  @Test
  public void testGetBrand() {
    long l = db.insertBrand("mauve");
    assertTrue (db.getBrand(l).equals("mauve"));
    }

  @Test
  public void testGetClothe() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetListTop() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetListBottom() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetListFeet() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetListClothes() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetAllColors() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetAllTypes() {
    fail("Not yet implemented");
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
