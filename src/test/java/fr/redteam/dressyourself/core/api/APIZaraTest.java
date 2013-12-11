package fr.redteam.dressyourself.core.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.redteam.dressyourself.common.filemanager.FileManager;
import fr.redteam.dressyourself.core.Bodypart;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class APIZaraTest {

  private APIZara api;

  @Before
  public void setUp() {
    // Let's mock our FileManager interface
    FileManager mockedFileManager = mock(FileManager.class);

    // API using the mocked object
    api = new APIZara(mockedFileManager);
  }

  @Test
  public void testfindClotheById() {
    Clothe product = api.findClotheById(97);

    assertTrue(product != null);

    assertEquals(97, product.getId());
    assertEquals("structured sweater", product.getModel());
    assertEquals("Zara", product.getBrand());
    assertEquals("Yellow", product.getColor());
    assertEquals("Knitwears", product.getType());
    assertEquals("Top", product.getBodies());
    assertEquals("Zara/en/man/knitwear/10-structured_sweater.jpg", product.getImageRelativePath());
  }

  @Test
  public void testFindClothesByModel() {
    List<Clothe> listProducts = api.findClothesByModel("jeans");

    assertEquals(93, listProducts.size());
  }

  @Test
  public void testFindClothesByType() {
    List<Clothe> listProducts = api.findClothesByType("sweat-shirt");

    assertEquals(29, listProducts.size());
  }

  @Test
  public void testFindClothesByBodyPart() {
    List<Clothe> listProducts = api.findClothesByBodyPart(Bodypart.BOTTOM);

    assertEquals(185, listProducts.size());
  }

  @Test
  public void testFindClothesByBrand() {
    List<Clothe> listProducts = api.findClothesByBrand("zara");

    assertEquals(1017, listProducts.size());
  }

  @Test
  public void testFindClothesByColor() {
    List<Clothe> listProducts = api.findClothesByColor("red");

    assertEquals(16, listProducts.size());
  }

  @Test
  public void testFindAll() {
    List<Clothe> listProducts = api.findAll("zip");

    assertEquals(27, listProducts.size());
  }
}
