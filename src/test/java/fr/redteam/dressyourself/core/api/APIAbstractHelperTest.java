package fr.redteam.dressyourself.core.api;

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

    URL url = new URL("http://echo.jsontest.com/key/value");
    String content = api.getContent(url);

    // assertEquals("{\"key\": \"value\"}", content);
  }
}
