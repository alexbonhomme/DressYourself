package fr.redteam.dressyourself.core.clothes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;


public class ClotheTest {

  /**
   * Test the empty constructor set the clothe's name as null
   */
  @Test
  public void testEmptyConstructorModel() throws Throwable {
    Clothe clothe = new Clothe();
    assertEquals(null, clothe.getModel());
  }

  /**
   * Test the empty constructor initialize the weather with an empty list of string
   */
  @Test
  public void testEmptyConstructorWeather() throws Throwable {
    Clothe myClothe = new Clothe();
    assertTrue(true == myClothe.getWeather().isEmpty());
  }

  /**
   * Test the empty constructor initialize the path as null
   */
  @Test
  public void testEmptyConstructorPath() throws Throwable {
    Clothe myClothe = new Clothe();
    assertEquals("", myClothe.getImageRelativePath());
  }

  /**
   * Test the empty constructor initialize the bodies as null
   */
  @Test
  public void testEmptyConstructorBodies() throws Throwable {
    Clothe myClothe = new Clothe();
    assertEquals(null, myClothe.getBodies());
  }

  /**
   * Test the constructor with initialize the brand as null
   */
  @Test
  public void testEmptyConstructorBrand() throws Throwable {
    Clothe myClothe = new Clothe();
    assertEquals(null, myClothe.getBrand());
  }

  /**
   * Test the empty constructor initialize the color as null
   */
  @Test
  public void testEmptyConstructorColor() throws Throwable {
    Clothe myClothe = new Clothe();
    assertEquals(null, myClothe.getColor());
  }

  /**
   * Test the empty constructor initialize the type as null
   */
  @Test
  public void testEmptyConstructorType() throws Throwable {
    Clothe myClothe = new Clothe();
    assertEquals(null, myClothe.getType());
  }

  /**
   * Test the empty constructor initialize the id is 0L
   */
  @Test
  public void testEmptyConstructorId() throws Throwable {
    Clothe myClothe = new Clothe();
    assertTrue(0L == myClothe.getId());
  }

  /**
   * Test the constructor set the clothe's name
   */
  @Test
  public void testSimpleConstructorModel() throws Throwable {
    Clothe clothe = new Clothe("MonClothe");
    assertEquals("MonClothe", clothe.getModel());
  }

  /**
   * Test the constructor with a name initialize the weather with an empty list of string
   */
  @Test
  public void testSimpleConstructorWeather() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe");
    assertTrue(true == myClothe.getWeather().isEmpty());
  }

  /**
   * Test the constructor with a name initialize the path as null
   */
  @Test
  public void testSimpleConstructorPath() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe");
    assertEquals("", myClothe.getImageRelativePath());
  }

  /**
   * Test the constructor with a name initialize the bodies as null
   */
  @Test
  public void testSimpleConstructorBodies() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe");
    assertEquals(null, myClothe.getBodies());
  }

  /**
   * Test the constructor with a name initialize the brand as null
   */
  @Test
  public void testSimpleConstructorBrand() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe");
    assertEquals(null, myClothe.getBrand());
  }

  /**
   * Test the constructor with a name initialize the color as null
   */
  @Test
  public void testSimpleConstructorColor() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe");
    assertEquals(null, myClothe.getColor());
  }

  /**
   * Test the constructor with a name initialize the type as null
   */
  @Test
  public void testSimpleConstructorType() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe");
    assertEquals(null, myClothe.getType());
  }

  /**
   * Test the constructor with a name initialize the id is 0L
   */
  @Test
  public void testSimpleConstructorId() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe");
    assertTrue(0L == myClothe.getId());
  }

  /**
   * test the setter of name clothe
   * 
   * @throws Throwable
   */
  @Test
  public void testNameSetter() throws Throwable {
    Clothe clothe = new Clothe("MonClothe");
    clothe.setModel("toto");
    assertEquals("toto", clothe.getModel());
  }

  /**
   * test the setter of clothe's color
   * 
   * @throws Throwable
   */
  @Test
  public void testColorSetter() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe");
    myClothe.setColor("yellow");
    assertEquals("yellow", myClothe.getColor());
  }

  /**
   * test the setter of clothe's brand
   * 
   * @throws Throwable
   */
  @Test
  public void testBrandSetter() throws Throwable {
    Clothe myClothe = new Clothe();
    myClothe.setBrand("MyBrand");
    assertEquals("MyBrand", myClothe.getBrand());
  }

  /**
   * Test the setter of clothe's bodies
   * 
   * @throws Throwable
   */
  @Test
  public void testBodiesSetter() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe");
    myClothe.setBodies("MyBodies");
    assertEquals("MyBodies", myClothe.getBodies());
  }


  /**
   * Test the setter of clothe's weather
   * 
   * @throws Throwable
   */

  @Test
  public void testWeatherSetter() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe");
    ArrayList<String> weather = new ArrayList<String>();
    weather.add("rainning");
    myClothe.setWeather(weather);
    assertTrue(1 == myClothe.getWeather().size());
  }

  /**
   * Test the setter of clothe's type
   * 
   * @throws Throwable
   */
  @Test
  public void testTypeSetter() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe");
    myClothe.setType("Type");
    assertEquals("Type", myClothe.getType());
  }

  /**
   * Test the setter of clothe's type
   * 
   * @throws Throwable
   */
  @Test
  public void testPathSetter() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe");
    myClothe.setImageRelativePath("./Mypath");
    assertEquals("./Mypath", myClothe.getImageRelativePath());
  }

}
