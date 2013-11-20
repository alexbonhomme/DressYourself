package fr.redteam.dressyourself.plugins.weather;

import java.util.Locale;

/**
 * This class has a private constructor All the methods are static due to confidentiality of the
 * data
 * 
 * @author boens
 * 
 */
public class Weather {

  private static String weather;
  private static int temperature;

  private Weather() {

  }

  public static int getTemperature() {
    return temperature;
  }

  public static void setTemperature(int temperature) {
    Weather.temperature = temperature;
  }

  public static String getWeather() {
    return weather;
  }

  public static void setWeather(String weather) {
    Weather.weather = weather;
  }

  public static String process(String weather) {
    WeatherIdentifier.fillLists();
    return WeatherIdentifier.identifyGroup(weather.toLowerCase(Locale.US)).toString();
  }
}