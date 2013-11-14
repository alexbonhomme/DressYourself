package fr.redteam.dressyourself.common;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.content.Context;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class DBHelperTest extends AndroidTestCase  {
  DBHelper db;
  
  Context context;
 
  
  @Override
  @Before
  public void setUp() throws Exception {
    context = new MockContext();
    db = new DBHelper(context, null);
    db.open();
  }
  

  @Override
  @After
  public void tearDown() throws Exception {
    db.close();
  }

  
  @Test
  public void testInsertColor() {
    long c = db.insertColor("Mauve");
   assert c!=-1;
    
  }

  @Test
  public void testInsertWeather() {
    long c = db.insertWeather("Mauve");
   assert c!=-1;  }

  @Test
  public void testInsertBodies() {
    long c = db.insertBodies("Mauve");
   assert c!=-1;  }

  @Test
  public void testInsertType() {
    long c = db.insertType("Mauve",1);
   assert c!=-1;  }

  @Test
  public void testInsertClothes() {
    Clothe clothe = new Clothe("toto");
    clothe.setImage(context.getResources().openRawResource(
        R.raw.embossed_leather_high_heel_ankle_boot));
    long c = db.insertClothes(clothe);
    assert c !=-1;
  }

  @Test
  public void testInsertOutfit() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIDColor() {
    long c = db.insertColor("Mauve");
   
    long l = db.getIDColor("mauve");
    assert   c == l ;
  }

  @Test
  public void testGetIDWeather() {
    long c = db.insertWeather("Mauve");
    
    long l = db.getIDWeather("mauve");
    assert  c == l ;  }

  @Test
  public void testGetIDBodies() {
    long c = db.insertBodies("Mauve");
    
    long l = db.getIDBodies("mauve");
    assert   c == l ;  }

  @Test
  public void testGetIDType() {
    long c = db.insertType("Mauve",1);
    
    long l = db.getIDType("mauve");
    assert   c == l ;  }

  @Test
  public void testGetIDClothes() {
    Clothe clothe = new Clothe("toto");
    long c = db.insertClothes(clothe);
    long l = db.getIDClothes("toto");

    assert c !=0;
    }

  @Test
  public void testGetIDOutfit() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIDBrand() {
    long c = db.insertBrand("Mauve");
    
    long l = db.getIDBrand("mauve");
    assert   c == l ;  
    }

  @Test
  public void testGetColor() {
    long l = db.insertColor("mauve");
    assert db.getColor(l).equals("mauve");
  }

  @Test
  public void testGetBodies() {
    long l = db.insertBodies("mauve");
    assert db.getBodies(l).equals("mauve");
    }

  @Test
  public void testGetWeather() {
    long l = db.insertWeather("mauve");
    assert db.getWeather(l).equals("mauve");
    }

  @Test
  public void testGetType() {
    long l = db.insertType("mauve",0);
    assert db.getType(l).equals("mauve");  }

  @Test
  public void testGetBrand() {
    long l = db.insertBrand("mauve");
    assert db.getBrand(l).equals("mauve");
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
