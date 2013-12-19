package fr.redteam.dressyourself.plugins.weather.yahooWeather;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.w3c.dom.Document;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
@RunWith(RobolectricTestRunner.class)
public class WOEIDUtilsTest {

  /**
   * Test case example of a private method using 'reflection' mechanisms
   */
  @Test
  public void testConvertStringToDocument() throws NoSuchMethodException, IllegalArgumentException,
      IllegalAccessException, InvocationTargetException {
    // Classical instance
    WOEIDUtils woeidInstance = WOEIDUtils.getInstance();
    
    // Get a reference on the target method
    Method privateMethod =
        WOEIDUtils.class.getDeclaredMethod("convertStringToDocument", String.class);

    // The most IMPORTANT thing to do ! Remove the modifiers
    privateMethod.setAccessible(true);
    
    // LITTLE BIT DIRTY ;)
    String strDoc =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?><query xmlns:yahoo=\"http://www.yahooapis.com/v1/base.rng\" yahoo:count=\"1\" yahoo:created=\"2013-12-18T17:45:04Z\" yahoo:lang=\"fr-FR\"><results><place xmlns=\"http://where.yahooapis.com/v1/schema.rng\" xml:lang=\"en-US\" yahoo:uri=\"http://where.yahooapis.com/v1/place/608105\"><woeid>608105</woeid><placeTypeName code=\"7\">Town</placeTypeName><name>Lille</name><country code=\"FR\" type=\"Country\" woeid=\"23424819\">France</country><admin1 code=\"\" type=\"Region\" woeid=\"7153324\">Nord-Pas-de-Calais</admin1><admin2 code=\"FR-59\" type=\"Department\" woeid=\"12597175\">Nord</admin2><admin3 code=\"\" type=\"Local Administrative Area\" woeid=\"12661407\">Lille</admin3><locality1 type=\"Town\" woeid=\"608105\">Lille</locality1><locality2/><postal/><centroid><latitude>50.628262</latitude><longitude>3.060150</longitude></centroid><boundingBox><southWest><latitude>50.600780</latitude><longitude>3.016790</longitude></southWest><northEast><latitude>50.657028</latitude><longitude>3.103990</longitude></northEast></boundingBox><areaRank>3</areaRank><popRank>10</popRank><timezone type=\"Time Zone\" woeid=\"28350911\">Europe/Paris</timezone></place></results></query>";

    // Run the method convertStringToDocument() with a fake doc as argument
    Document doc = (Document) privateMethod.invoke(woeidInstance, strDoc);

    // Classical assertion
    assertTrue(doc != null);
  }

}
