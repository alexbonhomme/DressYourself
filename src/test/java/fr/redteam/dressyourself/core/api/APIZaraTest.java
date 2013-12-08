package fr.redteam.dressyourself.core.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.redteam.dressyourself.core.Bodypart;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class APIZaraTest {

  private APIZara api;

  @Before
  public void setUp() {
    api = new APIZara();
  }

  @Test
  public void testfindClotheById() {
    Clothe product = api.findClotheById(97);

    assertTrue(product != null);

    assertEquals(97, product.getId());
    assertEquals("crossover cape jacket", product.getModel());
    assertEquals("Zara", product.getBrand());
    assertEquals("Black", product.getColor());
    assertEquals("Sweat-shirt", product.getType());
    assertEquals("Top", product.getBodies());

    // TODO
    // assertFalse(product.getImage() == null);
  }

  @Test
  public void testFindClothesByModel() {
    List<Clothe> listProducts = api.findClothesByModel("jeans");

    assertEquals(92, listProducts.size());
  }

  @Test
  public void testFindClothesByType() {
    List<Clothe> listProducts = api.findClothesByType("sweat-shirt");

    assertEquals(32, listProducts.size());
  }

  @Test
  public void testFindClothesByBodyPart() {
    List<Clothe> listProducts = api.findClothesByBodyPart(Bodypart.BOTTOM);

    assertEquals(181, listProducts.size());
  }

  @Test
  public void testFindClothesByBrand() {
    List<Clothe> listProducts = api.findClothesByBrand("zara");

    assertEquals(1008, listProducts.size());
  }

  @Test
  public void testFindClothesByColor() {
    List<Clothe> listProducts = api.findClothesByColor("red");

    assertEquals(17, listProducts.size());
  }

  @Test
  public void testFindAll() {
    List<Clothe> listProducts = api.findAll("zip");

    assertEquals(27, listProducts.size());
  }
}
