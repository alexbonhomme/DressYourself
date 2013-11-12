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
  public void setUp() throws Exception {
    api = new APIZara();
  }

  @Test
  public void testGetClothesByType() {
    List<Clothe> list = api.getClothesByType("jean");

    assertEquals(88, list.size());
  }

  @Test
  public void testGetClothe() {
    Clothe product = api.getClothe(97);

    assertEquals("JACQUARD CARDIGAN WITH FUR TRIM", product.getModel());
    assertEquals("Zara", product.getBrand());
    assertEquals("Navy blue", product.getColor());
    assertEquals("Knitwears", product.getType());
    assertEquals("Top", product.getBodies());

    assertFalse(product.getImage() == null);
  }

  @Test
  public void testSearchAll() {
    fail("Not implemented yet.");
  }

}
