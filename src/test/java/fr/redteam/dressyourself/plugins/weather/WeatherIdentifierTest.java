package fr.redteam.dressyourself.plugins.weather;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * weather returning by yahoo: http://developer.yahoo.com/weather/#codes
 * 
 * @author boens
 * 
 */
public class WeatherIdentifierTest {

  @Before
  public void setUp() throws Exception {
    WeatherIdentifier.fillLists();
  }

  @Test
  public void testIdentifyGroupHardcore() {
    assertEquals(WeatherIdentifier.WeatherGroup.HARDCORE,
        WeatherIdentifier.identifyGroup("tornado"));
    assertEquals(WeatherIdentifier.WeatherGroup.HARDCORE,
        WeatherIdentifier.identifyGroup("freezing rain"));
    assertEquals(WeatherIdentifier.WeatherGroup.HARDCORE,
        WeatherIdentifier.identifyGroup("mixed rain and snow"));
  }

  @Test
  public void testIdentifyGroupHot() {
    assertEquals(WeatherIdentifier.WeatherGroup.HOT, WeatherIdentifier.identifyGroup("sunny"));
    assertEquals(WeatherIdentifier.WeatherGroup.HOT, WeatherIdentifier.identifyGroup("hot"));
  }

  @Test
  public void testIdentifyGroupTemperate() {
    assertEquals(WeatherIdentifier.WeatherGroup.TEMPERATE,
        WeatherIdentifier.identifyGroup("partly cloudy"));
    assertEquals(WeatherIdentifier.WeatherGroup.TEMPERATE,
        WeatherIdentifier.identifyGroup("clear (night)"));
    assertEquals(WeatherIdentifier.WeatherGroup.TEMPERATE,
        WeatherIdentifier.identifyGroup("fair (day)"));
  }

  @Test
  public void testIdentifyGroupCold() {
    assertEquals(WeatherIdentifier.WeatherGroup.COLD,
        WeatherIdentifier.identifyGroup("freezing drizzle"));
    assertEquals(WeatherIdentifier.WeatherGroup.COLD, WeatherIdentifier.identifyGroup("cold"));
    assertEquals(WeatherIdentifier.WeatherGroup.COLD, WeatherIdentifier.identifyGroup("windy"));
  }

}
