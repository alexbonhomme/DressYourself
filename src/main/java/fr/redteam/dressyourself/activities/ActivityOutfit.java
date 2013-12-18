package fr.redteam.dressyourself.activities;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.Bodypart;
import fr.redteam.dressyourself.core.algorithm.OutfitDecider;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.exceptions.DressyourselfRuntimeException;
import fr.redteam.dressyourself.plugins.weather.Weather;
import fr.redteam.dressyourself.plugins.weather.WeatherIdentifier;
import fr.redteam.dressyourself.plugins.weather.WeatherIdentifier.WeatherGroup;

public class ActivityOutfit extends Activity implements OnClickListener {

  private TextView textViewTop;
  private TextView textViewBottom;
  private TextView textViewFeet;
  private TextView textViewWeather;
  private Button buttonRefreshTop;
  private Button buttonRefreshBottom;
  private Button buttonRefreshFeet;
  private Button buttonGenerate;
  private ImageView imageTop;
  private ImageView imageBottom;
  private ImageView imageFeet;
  private ImageView imageWeather;
  private Clothe currentTop;
  private Clothe currentBottom;
  private Clothe currentFeet;
  private static boolean gotWeatherInfo;
  private OutfitDecider decider;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_outfit_layout);
    decider = new OutfitDecider(this);
    initComponent();
    if (Weather.getWeather() != null) {
      gotWeatherInfo = true;
      decider.setWeather(Weather.getWeather());
    }

    bindToLayout();
    setListener();
  }

  private void initComponent() {
    textViewTop = (TextView) findViewById(R.id.textview_top);
    textViewBottom = (TextView) findViewById(R.id.textview_bottom);
    textViewFeet = (TextView) findViewById(R.id.textview_feet);
    imageTop = (ImageView) findViewById(R.id.imageview_top);
    imageBottom = (ImageView) findViewById(R.id.imageview_bottom);
    imageFeet = (ImageView) findViewById(R.id.imageview_feet);
    buttonRefreshTop = (Button) findViewById(R.id.button_refresh_top);
    buttonRefreshBottom = (Button) findViewById(R.id.button_refresh_bottom);
    buttonRefreshFeet = (Button) findViewById(R.id.button_refresh_feet);
    imageWeather = (ImageView) findViewById(R.id.imageview_weather);
    buttonGenerate = (Button) findViewById(R.id.button_generate);
    textViewWeather = (TextView) findViewById(R.id.textview_weather);
  }

  private void bindToLayout() {
    Clothe clothe = new Clothe("Pull beige");
    textViewTop.setText(clothe.getModel());
    textViewBottom.setText("slim bleu fonce");
    textViewFeet.setText("Basket camel");
    textViewWeather.setText("" + Weather.getTemperature() + " °C");
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
      imageWeather.setImageDrawable(getResources().getDrawable(R.drawable.nothing));
      textViewWeather.setText("Couldn't get weather information ! ");
    }
  }

  private void setListener() {
    buttonRefreshTop.setOnClickListener(this);
    buttonRefreshBottom.setOnClickListener(this);
    buttonRefreshFeet.setOnClickListener(this);
    buttonGenerate.setOnClickListener(this);
  }

  private void refreshTop() {
    File fileImageTop;

    try {
      currentTop = decider.decide(Bodypart.TOP);
      textViewTop.setText(currentTop.getModel());
      // fileImageTop =
      // new ClothesManager(new AndroidFileManager(this)).loadClotheImage(currentTop
      // .getImageRelativePath());
      // Uri uri = Uri.fromFile(fileImageTop);
      // imageTop.setImageURI(uri);

      textViewTop.setText(textViewTop.getText());
    } catch (DressyourselfRuntimeException e) {
      // couldn't decide, we do nothing
    }

  }

  private void refreshBottom() {
    File fileImageBottom;

    try {
      currentBottom = decider.decide(Bodypart.BOTTOM);
      textViewBottom.setText(currentBottom.getModel());
      // fileImageBottom =
      // new ClothesManager(new AndroidFileManager(this)).loadClotheImage(currentBottom
      // .getImageRelativePath());
      // Uri uri = Uri.fromFile(fileImageBottom);
      // imageBottom.setImageURI(uri);
      textViewBottom.setText(textViewBottom.getText() + " ");
    } catch (DressyourselfRuntimeException e) {
      // couldn't decide, we do nothing
    }
  }

  private void refreshFeet() {
    File fileImageFeet;

    try {
      currentFeet = decider.decide(Bodypart.SHOES);
      textViewFeet.setText(currentFeet.getModel());
      // fileImageFeet =
      // new ClothesManager(new AndroidFileManager(this)).loadClotheImage(currentFeet
      // .getImageRelativePath());
      // Uri uri = Uri.fromFile(fileImageFeet);
      // imageFeet.setImageURI(uri);
      textViewFeet.setText(textViewFeet.getText() + " ");
    } catch (DressyourselfRuntimeException e) {
      // couldn't decide, we do nothing
    }
  }

  private void generate() {
    refreshTop();
    refreshBottom();
    refreshFeet();
  }


  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.button_refresh_top:
        refreshTop();
        break;
      case R.id.button_refresh_bottom:
        refreshBottom();
        break;
      case R.id.button_refresh_feet:
        refreshFeet();
        break;
      case R.id.button_generate:
        generate();
        break;
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
