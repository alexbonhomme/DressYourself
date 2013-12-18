package fr.redteam.dressyourself.core.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import fr.redteam.dressyourself.common.filemanager.FileManager;
import fr.redteam.dressyourself.core.Bodypart;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.exceptions.DressyourselfIOException;
import fr.redteam.dressyourself.exceptions.DressyourselfRuntimeException;

public class APIZaraTest {

  private APIZara api;

  @Before
  public void setUp() {
    // Let's mock our FileManager interface
    FileManager mockedFileManager = mock(FileManager.class);

    // API using the mocked object
    api = new APIZara(mockedFileManager);
  }

  /*
   * APIAbstractHelper test cases
   */
  @Rule
  public ExpectedException exception = ExpectedException.none();

  @Test
  public void testGetContent() throws MalformedURLException {
    URL url = new URL("http://echo.jsontest.com/key/one?mime=5");

    String content = api.getContent(url);
    assertEquals("{\"key\": \"one\"}\n", content);

    /*
     * Error cases
     * 
     * Bad url
     */
    url = new URL("http://www.thisisafakeurlwebsite.me");

    exception.expect(DressyourselfRuntimeException.class);
    exception.expectMessage("IOException");
    api.getContent(url);
  }

  @Test
  public void testBuildClotheFromJSONObject() throws JSONException {
    JSONObject jsonClothe =
        new JSONObject(
            "{\"id\":876,"
                + "\"model\":\"ankle boots with interior wedge and elastic panels\","
                + "\"imageUrl\":\"http://static.zara.net/photos/2013/I/1/1/p/5155/201/003/2/w/400/5155201003_1_1_1.jpg?timestamp=1377247934735\","
                + "\"imagePath\":\"Zara/en/woman/shoes/113-ankle_boots_with_interior_wedge_and_elastic_panels.jpg\","
                + "\"brand\":\"Zara\"," + "\"color\":\"Ice\"," + "\"type\":\"Shoes\","
                + "\"bodies\":\"Shoes\"}");

    Clothe product = api.buildClotheFromJSONObject(jsonClothe);

    assertTrue(product != null);
    assertEquals(876, product.getId());
    assertEquals("ankle boots with interior wedge and elastic panels", product.getModel());
    assertEquals("Zara", product.getBrand());
    assertEquals("Ice", product.getColor());
    assertEquals("Shoes", product.getType());
    assertEquals("Shoes", product.getBodies());
    assertEquals("Zara/en/woman/shoes/113-ankle_boots_with_interior_wedge_and_elastic_panels.jpg",
        product.getImageRelativePath());
    
    /*
     * Error cases
     * 
     * Look at the `imageUrl` field
     */
    jsonClothe =
        new JSONObject(
            "{\"id\":876,"
                + "\"model\":\"ankle boots with interior wedge and elastic panels\","
                + "\"imageUrl\":\"http://thisisafakeurltotestanexception.ex\","
                + "\"imagePath\":\"Zara/en/woman/shoes/113-ankle_boots_with_interior_wedge_and_elastic_panels.jpg\","
                + "\"brand\":\"Zara\"," + "\"color\":\"Ice\"," + "\"type\":\"Shoes\","
                + "\"bodies\":\"Shoes\"}");

    exception.expect(DressyourselfIOException.class);
    api.buildClotheFromJSONObject(jsonClothe);
  }

  /*
   * APIAbstractHelper test cases
   */
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
