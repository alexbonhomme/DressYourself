package fr.redteam.dressyourself.core.api;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Ignore;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
public class APIAbstractHelperTest {

  /*
   * We have to ignore this test because the FUCKING PROXY doesn't want lets me get some stuff from
   * outside :-(
   */
  @Ignore
  public void testGetContent() throws MalformedURLException {
    APIAbstractHelper api = new APIAbstractHelper() {};

    URL url = new URL("http://www.blacko-product.ovh.org/");
    String content = api.getContent(url);

    assertEquals("Hello world !", content);
  }
}
