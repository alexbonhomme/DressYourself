package fr.redteam.dressyourself.plugins.weather;

import java.util.Arrays;
import java.util.List;

public class WeatherIdentifier {

  public enum weatherGroup {
    HARDCORE, HOT, TEMPERATE, COLD, NOTFOUND
  }

  private static List<String> listHardcore;
  private static List<String> listHot;
  private static List<String> listTemperate;
  private static List<String> listCold;

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

    weatherGroup result = weatherGroup.NOTFOUND;
    for (String s : listHardcore) {
      if (weather.contains(s)) {
        result = weatherGroup.HARDCORE;
        break;
      }
    }

    for (String s : listHot) {
      if (weather.contains(s)) {
        result = weatherGroup.HOT;
        break;
      }
    }

    for (String s : listTemperate) {
      if (weather.contains(s)) {
        result = weatherGroup.TEMPERATE;
        break;
      }
    }

    for (String s : listCold) {
      if (weather.contains(s)) {
        result = weatherGroup.COLD;
        break;
      }
    }

    return result;
  }

}
