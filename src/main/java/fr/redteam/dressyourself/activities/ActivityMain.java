package fr.redteam.dressyourself.activities;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.plugins.weather.WeatherPlugin;

public class ActivityMain extends Activity implements LocationListener {

  private ImageView buttonFilters;
  private LocationManager locationManager;
  private String provider;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    // TODO recuperer coordonnees GPS puis recuperer le nom de la ville

    WeatherPlugin weather = new WeatherPlugin("lille", getApplicationContext());
    weather.sendYahooQuery();

    buttonFilters = (ImageView) findViewById(R.id.btoutfit);
    buttonFilters.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ActivityMain.this, ActivityOutfit.class);
        startActivity(intent);
      }
    });


    /* Hard tricks */
    /* Debug zone */
    ImageView logoMain = (ImageView) findViewById(R.id.imgLogoMain);
    logoMain.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ActivityMain.this, ActivityDebug.class);
        startActivity(intent);
      }
    });
    
    /* Grid view */
    /* To optimize */
    ImageButton buttonAdd = (ImageButton) findViewById(R.id.button_add);
    ImageButton buttonList = (ImageButton) findViewById(R.id.button_list);

    buttonAdd.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ActivityMain.this, ActivitySearchEngine.class);
        startActivity(intent);
      }
    });
    
    buttonList.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ActivityMain.this, ActivityClotheList.class);
        startActivity(intent);
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public void onLocationChanged(Location location) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onProviderDisabled(String provider) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onProviderEnabled(String provider) {
    // TODO Auto-generated method stub

  }

  @Override
  public void onStatusChanged(String provider, int status, Bundle extras) {
    // TODO Auto-generated method stub

  }
}
