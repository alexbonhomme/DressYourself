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
import android.widget.Button;
import android.widget.ImageView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.plugins.weather.WeatherPlugin;

public class ActivityMain extends Activity implements LocationListener {

  private Button buttonAddClothing;
  private Button buttonListClothes;
  private Button buttonFilters;
  private LocationManager locationManager;
  private String provider;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    // creation base de donnée et test
    /*
     * DBHelper db = new DBHelper(this);
     * 
     * @Override protected void onCreate(Bundle savedInstanceState) {
     * super.onCreate(savedInstanceState); setContentView(R.layout.activity_main); // creation base
     * de donnée DBHelper db = new DBHelper(this); >>>>>>> branch 'master' of
     * https://tbuisine@bitbucket.org/blckshrk/dressyourself.git
     * 
     * <<<<<<< HEAD db.open(); String t = Integer.toString(db.getIDColor("WHITE"));
     * 
     * Log.v("BDD", t); db.close();
     */

    // TODO recuperer coordonnees GPS puis recuperer le nom de la ville

    WeatherPlugin weather = new WeatherPlugin("lille", getApplicationContext());
    weather.sendYahooQuery();

    /* Open the page to add clothes */
    buttonAddClothing = (Button) findViewById(R.id.btAddClothing);
    buttonAddClothing.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ActivityMain.this, ActivitySearchEngine.class);
        startActivity(intent);
      }
    });

    buttonListClothes = (Button) findViewById(R.id.btListClothes);
    buttonListClothes.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ActivityMain.this, ActivityClotheList.class);
        startActivity(intent);
      }
    });

    buttonFilters = (Button) findViewById(R.id.btFilters);
    buttonFilters.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ActivityMain.this, ActivityOutfit.class);
        startActivity(intent);
      }
    });


    /* Hard tricks */
    /* Debbug zone */
    ImageView logoMain = (ImageView) findViewById(R.id.imgLogoMain);
    logoMain.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ActivityMain.this, ActivityDebug.class);
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
