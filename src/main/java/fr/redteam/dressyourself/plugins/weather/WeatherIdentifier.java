package fr.redteam.dressyourself.plugins.weather;

import java.util.Arrays;
import java.util.List;

public class WeatherIdentifier {

  public enum weatherGroup {
    HARDCORE, HOT, TEMPERATE, COLD, NOTFOUND
  }

  static List<String> listHardcore;
  static List<String> listHot;
  static List<String> listTemperate;
  static List<String> listCold;

  static void fillLists() {
    String[] arrayHardcore =
        {"tornado", "storm", "hurricane", "thunderstorms", "rain", "snow", "sleet", "showers",
            "flurries", "hail", "dust", "blustery", "thundershowers"};

    String[] arrayHot = {"sunny", "hot",};

    String[] arrayTemperate = {"cloudy", "clear", "fair"};

    String[] arrayCold = {"drizzle", "foggy", "haze", "cold", "windy", "smoky"};

    listHardcore = Arrays.asList(arrayHardcore);
    listHot = Arrays.asList(arrayHot);
    listTemperate = Arrays.asList(arrayTemperate);
    listCold = Arrays.asList(arrayCold);
  }

  static weatherGroup identifyGroup(String weather) {

    for (String s : listHardcore) {
      if (weather.contains(s)) {
        return weatherGroup.HARDCORE;
      }
    }

    for (String s : listHot) {
      if (weather.contains(s)) {
        return weatherGroup.HOT;
      }
    }

    for (String s : listTemperate) {
      if (weather.contains(s)) {
        return weatherGroup.TEMPERATE;
      }
    }

    for (String s : listCold) {
      if (weather.contains(s)) {
        return weatherGroup.COLD;
      }
    }

    return weatherGroup.NOTFOUND;
  }

}
