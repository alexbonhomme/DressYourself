package fr.redteam.dressyourself.common;

import static org.junit.Assert.*;

import org.hamcrest.core.IsInstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.InstanceOf;
import org.objenesis.instantiator.basic.NewInstanceInstantiator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBHelperTest {
  Context context;
  DBHelper db;
  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    context = Mockito.mock(Context.class);
    db = new DBHelper(context, null);
    db.open();
  }
  

  @After
  public void tearDown() throws Exception {
    db.close();
  }

  @Test
  public void testGetBDD() {
    SQLiteDatabase bdd  = db.getBDD();
    assertTrue(bdd instanceof SQLiteDatabase);
  }

  @Test
  public void testInsertColor() {
    db.insertColor("Mauve");
    
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
  public void testInsertClothes() {
    fail("Not yet implemented");
  }

  @Test
  public void testInsertOutfit() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIDColor() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIDWeather() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIDBodies() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIDType() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIDClothes() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIDOutfit() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetIDBrand() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetColor() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetBodies() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetWeather() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetType() {
    fail("Not yet implemented");
  }

  @Test
  public void testGetBrand() {
    fail("Not yet implemented");
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
