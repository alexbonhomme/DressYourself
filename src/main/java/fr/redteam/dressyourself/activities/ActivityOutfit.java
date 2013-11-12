package fr.redteam.dressyourself.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.common.DBHelper;
import fr.redteam.dressyourself.core.algorithm.OutfitDecider;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.plugins.weather.Weather;

public class ActivityOutfit extends Activity {

  private TextView textViewTop;
  private TextView textViewBottom;
  private TextView textViewFeet;
  private Button buttonRefreshTop;
  private Button buttonRefreshBottom;
  private Button buttonRefreshFeet;
  private final DBHelper db = new DBHelper(this);
  private ArrayList<Clothe> listTop = new ArrayList<Clothe>();
  private ArrayList<Clothe> listBottom = new ArrayList<Clothe>();
  private ArrayList<Clothe> listFeet = new ArrayList<Clothe>();
  private Clothe currentTop;
  private Clothe currentBottom;
  private Clothe currentFeet;
  private OutfitDecider decider = new OutfitDecider(false);

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.outfit_layout);
    textViewTop = (TextView) findViewById(R.id.top_text);
    textViewBottom = (TextView) findViewById(R.id.bottom_text);
    textViewFeet = (TextView) findViewById(R.id.feet_text);
    
    if (Weather.getWeather() != null) {
      Log.d("weather", Weather.getWeather());
    }

    // connexion bdd
    db.open();

    // recup top
    listTop = db.getListTop();

    // recup bottom
    listBottom = db.getListBottom();

    // recup feet
    listFeet = db.getListFeet();

    // vetements statique TODO: récupérer en bdd
    Clothe clothe = new Clothe("Pull beige");
    textViewTop.setText(clothe.getModel());
    textViewBottom.setText("slim bleu fonce");
    textViewFeet.setText("Basket camel");

    buttonRefreshTop = (Button) findViewById(R.id.top_refresh_button);
    buttonRefreshTop.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        refreshTop();
      }
    });

    buttonRefreshBottom = (Button) findViewById(R.id.bottom_refresh_button);
    buttonRefreshBottom.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        refreshBottom();
      }
    });

    buttonRefreshFeet = (Button) findViewById(R.id.feet_refresh_button);
    buttonRefreshFeet.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        refreshFeet();
      }
    });
    db.close();
  }

  private void refreshTop() {
    // TODO recuperation en base
    if (listTop.size() > 1) {
      currentTop = decider.DecideTop(listTop);
    }
    textViewTop.setText(textViewTop.getText() + " ");
  }

  private void refreshBottom() {
    // TODO recuperation en base
    if (listBottom.size() > 1) {
      currentBottom = decider.DecideTop(listBottom);
    }
    textViewBottom.setText(textViewBottom.getText() + " ");
  }

  private void refreshFeet() {
    // TODO recuperation en base
    if (listFeet.size() > 1) {
      currentFeet = decider.DecideTop(listFeet);
    }
    textViewFeet.setText(textViewFeet.getText() + " ");
  }
}
