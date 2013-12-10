package fr.redteam.dressyourself.common;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.junit.Test;

import fr.redteam.dressyourself.utils.StreamTools;

public class StreamToolsTest {

  @Test
  public void testConvertStreamToString() throws UnsupportedEncodingException {
    String initialString = "abcd124//_\n\r#\nx)è";
    InputStream is = new ByteArrayInputStream(initialString.getBytes("UTF-8"));
    
    String resultString = StreamTools.convertStreamToString(is);
    assertEquals(initialString, resultString);
  }
}
