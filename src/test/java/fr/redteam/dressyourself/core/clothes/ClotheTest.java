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

}
