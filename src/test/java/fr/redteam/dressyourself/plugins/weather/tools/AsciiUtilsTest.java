package fr.redteam.dressyourself.plugins.weather.tools;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AsciiUtilsTest {

  @Test
  public void testconvertNonAscii() {
    assertEquals(null, AsciiUtils.convertNonAscii(null));
    assertEquals("this is a simple sample", AsciiUtils.convertNonAscii("this is a simple sample"));
    assertEquals("this is a sample with accents : e a u",
        AsciiUtils.convertNonAscii("this is a sample with accents : é à ù"));
  }

}
