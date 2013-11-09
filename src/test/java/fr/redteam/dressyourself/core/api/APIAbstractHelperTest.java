package fr.redteam.dressyourself.core.api;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
public class APIAbstractHelperTest {

  @Test
  public void testGetContent() throws MalformedURLException {
    APIAbstractHelper api = new APIAbstractHelper() {};

    URL url = new URL("http://www.blacko-product.ovh.org/");
    String content = api.getContent(url);

    assertEquals("Hello world !", content);
  }
}
