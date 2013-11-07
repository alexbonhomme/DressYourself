/*
 * Copyright (C) 2011 The Android Open Source Project Copyright (C) 2012 Zhenghong Wang
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package fr.redteam.dressyourself.plugins.weather;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import fr.redteam.dressyourself.activities.ActivityMain;
import fr.redteam.dressyourself.plugins.weather.tools.AsciiUtils;
import fr.redteam.dressyourself.plugins.weather.tools.NetworkUtils;
import fr.redteam.dressyourself.plugins.weather.yahooWeather4a.WeatherInfo;
import fr.redteam.dressyourself.plugins.weather.yahooWeather4a.YahooWeatherInfoListener;
import fr.redteam.dressyourself.plugins.weather.yahooWeather4a.YahooWeatherUtils;

public class WeatherPlugin implements YahooWeatherInfoListener {

  public WeatherPlugin(String location, Context appContext, ActivityMain activityMain) {


    if (!NetworkUtils.isConnected(appContext)) {
      Toast.makeText(appContext, "Network connection is unavailable!!", Toast.LENGTH_SHORT).show();
      return;
    }

    String convertedlocation = AsciiUtils.convertNonAscii(location);
    YahooWeatherUtils weatherUtils = new YahooWeatherUtils();
    weatherUtils.queryYahooWeather(appContext, convertedlocation, this);
  }

  // methode lancé quand yahoo repond
  @Override
  public void gotWeatherInfo(WeatherInfo weatherInfo) {
    if (weatherInfo != null) {
      Log.d("reponse", "reponse de yahoo!");
      Weather.setWeather(weatherInfo.getCurrentWeather());
      Weather.setTemperature(weatherInfo.getCurrentTempC());
    }
  }
}
