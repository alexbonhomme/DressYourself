package fr.redteam.dressyourself.core.clothes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    assertEquals(null, myClothe.getImageRelativePath());
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
    assertEquals(null, myClothe.getImageRelativePath());
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
   * Test the constructor with a name and a path initialize the model
   * 
   * @throws Throwable
   */
  @Test
  public void testComplexeConstructorModel() throws Throwable {
    Clothe clothe = new Clothe("MonClothe", "./myPath");
    assertEquals("MonClothe", clothe.getModel());
  }

  /**
   * Test the constructor with a name and a path initialize the path as null
   */
  @Test
  public void testComplexeConstructorPath() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe", "./myPath");
    assertEquals("./myPath", myClothe.getImageRelativePath());
  }

  /**
   * Test the constructor with a name and a path initialize the weather with an empty list of string
   */
  @Test
  public void testComplexeConstructorWeather() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe", "./myPath");
    assertTrue(true == myClothe.getWeather().isEmpty());
  }

  /**
   * Test the constructor with a name and a path initialize the bodies as null
   */
  @Test
  public void testComplexeConstructorBodies() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe", "./myPath");;
    assertEquals(null, myClothe.getBodies());
  }

  /**
   * Test the constructor with a name and a path initialize the brand as null
   */
  @Test
  public void testComplexeConstructorBrand() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe", "./myPath");
    assertEquals(null, myClothe.getBrand());
  }

  /**
   * Test the constructor with a name and a path initialize the color as null
   */
  @Test
  public void testComplexeConstructorColor() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe", "./myPath");
    assertEquals(null, myClothe.getColor());
  }

  /**
   * Test the constructor with a name and a path initialize the type as null
   * 
   */
  @Test
  public void testComplexeConstructorType() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe", "./myPath");
    assertEquals(null, myClothe.getType());
  }

  /**
   * Test the constructor with a name and a path initialize the id is 0L
   */
  @Test
  public void testComplexeConstructorId() throws Throwable {
    Clothe myClothe = new Clothe("MonClothe", "./myPath");
    assertTrue(0L == myClothe.getId());
  }

}
