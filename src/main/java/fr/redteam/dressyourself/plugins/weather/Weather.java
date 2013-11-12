package fr.redteam.dressyourself.plugins.weather;



public class Weather {
  private static String weather;
  private static int temperature;

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
    return WeatherIdentifier.identifyGroup(weather.toLowerCase()).toString();
  }
}