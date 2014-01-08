package fr.redteam.dressyourself.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.digitalaria.gama.carousel.Carousel;

import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.adapters.AdapterCarouselImages;
import fr.redteam.dressyourself.common.database.DBHelper;
import fr.redteam.dressyourself.core.Bodypart;
import fr.redteam.dressyourself.core.algorithm.OutfitDecider;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.plugins.weather.Weather;
import fr.redteam.dressyourself.plugins.weather.WeatherIdentifier;
import fr.redteam.dressyourself.plugins.weather.WeatherIdentifier.WeatherGroup;

public class ActivityOutfit extends Activity implements OnClickListener {

  private TextView textViewWeather;
  private ImageView imageWeather;
  private static boolean gotWeatherInfo;
  private OutfitDecider decider;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_outfit_layout);
    decider = new OutfitDecider(new DBHelper(this));
    initComponent();
    if (Weather.getWeather() != null) {
      gotWeatherInfo = true;
      decider.setWeather(Weather.getWeather());
    }
    
    initCarousel(R.id.carouselTop, Bodypart.TOP);
    initCarousel(R.id.carouselBottom, Bodypart.BOTTOM);
    initCarousel(R.id.carouselFeet, Bodypart.SHOES);

    bindToLayout();
  }

  private Carousel carousel;
  private AdapterCarouselImages adapter;

  /**
   * Initialize the carousel
   * 
   * @param name the carousel id
   * @param part the bodypart you want to fill it with
   */
  private void initCarousel(int name, Bodypart part) {
    // create the carousel object.
    carousel = (Carousel) findViewById(name);

    // configurations for the carousel.
    carousel.setType(Carousel.TYPE_COVERFLOW);
    carousel.setOverScrollBounceEnabled(true);
    carousel.setInfiniteScrollEnabled(false);
    carousel.setItemRearrangeEnabled(false);
    carousel.setUnselectedAlpha(0.33f);
    
    // set images for the carousel.
    adapter = new AdapterCarouselImages(this, decider.getBodypartClothes(part));
    carousel.setAdapter(adapter);
  }

  private void initComponent() {
    imageWeather = (ImageView) findViewById(R.id.imageview_weather);
    textViewWeather = (TextView) findViewById(R.id.textview_weather);
  }

  private void bindToLayout() {
    Clothe clothe = new Clothe("Pull beige");
    textViewWeather.setText("Now : " + Weather.getTemperature() + " °C ");
    WeatherIdentifier.fillLists();
    if (gotWeatherInfo) {
      switch (WeatherGroup.valueOf(Weather.getWeather())) {
        case HOT:
          imageWeather.setImageDrawable(getResources().getDrawable(R.drawable.sunny));
          break;
        case TEMPERATE:
          imageWeather.setImageDrawable(getResources().getDrawable(R.drawable.cloudy_sun));
          break;
        case HARDCORE:
          imageWeather.setImageDrawable(getResources().getDrawable(R.drawable.rain_snow));
          break;
        case COLD:
          imageWeather.setImageDrawable(getResources().getDrawable(R.drawable.cloud));
          break;
        case NOTFOUND:
        default:
          imageWeather.setImageDrawable(getResources().getDrawable(R.drawable.nothing));
          break;
      }
    } else {
      // imageWeather.setImageDrawable(getResources().getDrawable(R.drawable.nothing));
      textViewWeather.setText("Couldn't get weather information ! ");
    }
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      default:
        break;
      }
  }

  public static void setGotWeatherInfo(boolean gotWeather) {
    gotWeatherInfo = gotWeather;
  }

  public static boolean getGotWeatherInfo() {
    return gotWeatherInfo;
  }

}
