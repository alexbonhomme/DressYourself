package test.java.fr.redteam.dressyourself.core.api;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import main.java.fr.redteam.dressyourself.core.api.APIAbstractHelper;

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

    URL url = new URL("http://ip.jsontest.com/");
    String content = api.getContent(url);

    assertEquals("{\"ip\": \"2.5.185.37\"}", content);
  }
}
