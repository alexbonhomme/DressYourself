package fr.redteam.dressyourself.core.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

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

    assertFalse(product == null);

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
    List<Clothe> listProducts = api.findClothesByModelName("jeans");

    assertEquals(92, listProducts.size());
  }

  @Test
  public void testFindClothesByType() {
    fail("Not implemented yet.");
  }

  @Test
  public void testFindAll() {
    fail("Not implemented yet.");
  }
}
