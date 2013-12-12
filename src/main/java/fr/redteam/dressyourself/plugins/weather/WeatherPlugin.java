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
import fr.redteam.dressyourself.activities.ActivityOutfit;
import fr.redteam.dressyourself.plugins.weather.yahooWeather.WeatherInfo;
import fr.redteam.dressyourself.plugins.weather.yahooWeather.YahooWeatherInfoListener;
import fr.redteam.dressyourself.plugins.weather.yahooWeather.YahooWeatherUtils;
import fr.redteam.dressyourself.utils.AsciiUtils;
import fr.redteam.dressyourself.utils.NetworkUtils;

public class WeatherPlugin implements YahooWeatherInfoListener {

  private String location;

  private Context appContext;

  public WeatherPlugin(String location, Context appContext) {
    this.location = location;
    this.appContext = appContext;
  }

  public void sendYahooQuery() {

    if (!NetworkUtils.isConnected(this.appContext)) {
      Toast.makeText(this.appContext, "Network connection is unavailable!!", Toast.LENGTH_SHORT)
          .show();
      return;
    }

    String convertedlocation = AsciiUtils.convertNonAscii(this.location);
    YahooWeatherUtils weatherUtils = new YahooWeatherUtils();
    weatherUtils.queryYahooWeather(this.appContext, convertedlocation, this);
  }

  // methode lancé quand yahoo repond
  @Override
  public void gotWeatherInfo(WeatherInfo weatherInfo) {

    String weather;
    if (weatherInfo != null) {
      Log.d("reponse", "reponse de yahoo!");
      weather = Weather.process(weatherInfo.getCurrentWeather());
      Weather.setWeather(weather);
      Weather.setTemperature(weatherInfo.getCurrentTempC());
      ActivityOutfit.setGotWeatherInfo(true);
    }
  }
}
