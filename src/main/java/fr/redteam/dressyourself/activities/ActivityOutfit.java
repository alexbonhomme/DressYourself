package fr.redteam.dressyourself.activities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.common.DBHelper;
import fr.redteam.dressyourself.core.algorithm.OutfitDecider;
import fr.redteam.dressyourself.core.clothes.Clothe;
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
  private final DBHelper db = new DBHelper(this);
  private List<Clothe> listTop = new ArrayList<Clothe>();
  private List<Clothe> listBottom = new ArrayList<Clothe>();
  private List<Clothe> listFeet = new ArrayList<Clothe>();
  private Clothe currentTop;
  private Clothe currentBottom;
  private Clothe currentFeet;
  private OutfitDecider decider = new OutfitDecider(false);

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_outfit_layout); 
    initComponent();
    if (Weather.getWeather() != null) {
      Log.d("weather", Weather.getWeather());
    }
    // connexion bdd
    db.open();
    loadData();
    bindToLayout();
    setListener();
    db.close();
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
  
  private void loadData() {
	  // recup top
	    listTop = db.getListTop();

	    // recup bottom
	    listBottom = db.getListBottom();

	    // recup feet
	    listFeet = db.getListFeet(); 
  }
  
  private void bindToLayout() {
    // vetements statique TODO: récupérer en bdd
	 Clothe clothe = new Clothe("Pull beige");
	 textViewTop.setText(clothe.getModel());
	 textViewBottom.setText("slim bleu fonce");
	 textViewFeet.setText("Basket camel");
    textViewWeather.setText("" + Weather.getTemperature() + " °C");
	 WeatherIdentifier.fillLists();
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
        case NOTFOUND:
        default:
          imageWeather.setImageDrawable(getResources().getDrawable(R.drawable.nothing));
          break;
      }
    } else {
      imageWeather.setImageDrawable(getResources().getDrawable(R.drawable.nothing));
      textViewWeather.setText("");
    }
  }
   
  private void setListener() {
	  buttonRefreshTop.setOnClickListener(this); 
	  buttonRefreshBottom.setOnClickListener(this);
	  buttonRefreshFeet.setOnClickListener(this);
    buttonGenerate.setOnClickListener(this);
  }

  private void refreshTop() {
    if (listTop.size() > 1) {
      currentTop = decider.decideTop(listTop);
      textViewTop.setText(currentTop.getModel());
      imageTop.setImageDrawable(Drawable.createFromStream(currentTop.getImage(),
          currentTop.getModel()));
    }
    textViewTop.setText(textViewTop.getText() + " ");

  }

  private void refreshBottom() {
    if (listBottom.size() > 1) {
      currentBottom = decider.decideBottom(listBottom);
      textViewBottom.setText(currentBottom.getModel());
      imageBottom.setImageDrawable(Drawable.createFromStream(currentBottom.getImage(),
              currentBottom.getModel()));
    }
    textViewBottom.setText(textViewBottom.getText() + " ");
  }

  private void refreshFeet() {
    if (listFeet.size() > 1) {
      currentFeet = decider.decideFeet(listFeet);
      textViewFeet.setText(currentFeet.getModel());
      imageFeet.setImageDrawable(Drawable.createFromStream(currentFeet.getImage(),
              currentFeet.getModel()));
    }
    textViewFeet.setText(textViewFeet.getText() + " ");
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

}
