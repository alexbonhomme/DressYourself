package fr.redteam.dressyourself.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.redteam.dressyourself.utils.AsciiUtils;

public class AsciiUtilsTest {

  @Test
  public void testconvertNonAscii() {
    assertEquals(null, AsciiUtils.convertNonAscii(null));
    assertEquals("this is a simple sample", AsciiUtils.convertNonAscii("this is a simple sample"));
    assertEquals("this is a sample with accents : e a u",
        AsciiUtils.convertNonAscii("this is a sample with accents : é à ù"));
  }

}
