package fr.redteam.dressyourself.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.common.DBHelper;
import fr.redteam.dressyourself.core.api.APIShopSense;
import fr.redteam.dressyourself.core.api.APIZara;
import fr.redteam.dressyourself.core.clothes.Clothe;


public class ActivityDebug extends Activity {

  private Button debugAPI;
  private Button addClothesToDataBase;
  private Button sendMailOutfit;
  private Button sendMailClothe;
  private Button modifyClothe;
  private Button ClothDetail;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_debug);

    debugAPI = (Button) findViewById(R.id.buttonDebugAPI);
    sendMailOutfit = (Button) findViewById(R.id.btnSendOutfitMail);
    sendMailClothe = (Button) findViewById(R.id.btnEnvoieMailClothe);
    this.modifyClothe = (Button) findViewById(R.id.buttonClotheModify);
    this.ClothDetail = (Button) findViewById(R.id.buttonDetailClothe);
    addClothesToDataBase = (Button) findViewById(R.id.buttonAddClotheClothesToDB);
    /*
     * sendMailOutfit.setOnClickListener(new OnClickListener() {
     * 
     * @Override public void onClick(View v) {
     * 
     * Intent intent = new Intent(ActivityDebug.this, ActivityOutfitMail.class);
     * intent.putExtra("id", 1); startActivity(intent); } });
     * 
     * 
     * sendMailClothe.setOnClickListener(new OnClickListener() {
     * 
     * @Override public void onClick(View v) { Intent intent = new Intent(ActivityDebug.this,
     * ActivityClotheMail.class); intent.putExtra("id", 1); startActivity(intent); } });
     */

    /* API */
    debugAPI.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        new APITesting().execute(new String());
      }
    });
    addClothesToDataBase.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        fillLocaleDataBaseWithFewClothes();
      }
    });



    this.modifyClothe.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO Auto-generated method stub
        Clothe clothe = new Clothe("Adrien's clothe");
        clothe.setBrand("Zara");
        clothe.setColor("RED");
        clothe.setType("pull");
        Intent intent = new Intent(ActivityDebug.this, ActivityClotheModify.class);
        intent.putExtra("clothe", clothe);
        startActivity(intent);
      }
    });

    this.ClothDetail.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        // TODO Auto-generated method stub
        List<String> weather = new ArrayList<String>();
        weather.add("Cloudy");
        weather.add("Rainy");
        Clothe clothe = new Clothe("Clothe test");
        clothe.setWeather(weather);
        clothe.setBrand("Zara");
        clothe.setColor("RED");
        clothe.setType("pull");
        clothe.setBodies("body");

        // try {
        // clothe.setImage(new FileInputStream(new File("../res/drawable/echarpe_peche.jpg")));
        // } catch (FileNotFoundException e) {
        // throw new RuntimeException(e);
        // }
        //
        Intent intent = new Intent(ActivityDebug.this, ActivityClotheDetail.class);
        intent.putExtra("clothe", clothe);
        startActivity(intent);
      }
    });

  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_debug, menu);
    return true;
  }

  /**
   * 
   * @author Alexandre Bonhomme
   * 
   */
  private class APITesting extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... param) {

      new APIShopSense();

      return null;
    }
  }

  /**
   * Méthode temporaire pour permettre l'avancement de Rémy et Antonia
   * 
   * @author Alexandre Bonhomme
   * 
   */
  private void fillLocaleDataBaseWithFewClothes() {
    DBHelper db = new DBHelper(this);
    APIZara api = new APIZara();

    db.open();
    long topId = db.insertBodies("Top");
    long bottomId = db.insertBodies("Bottom");
    long shoesId = db.insertBodies("Shoes");

    /* long brandId = */db.insertBrand("Zara");

    /**
     * Get 3 tops, 3 bottoms and 3 shoes (females)
     */
    for (int i = 0; i < 3; i++) {
      Clothe bottom = api.getClothe(500 + i);
      db.insertColor(bottom.getColor());
      db.insertType(bottom.getType(), (int) bottomId);
      db.insertClothes(bottom);

      Clothe top = api.getClothe(750 + i);
      db.insertColor(top.getColor());
      db.insertType(top.getType(), (int) topId);
      db.insertClothes(top);

      Clothe shoes = api.getClothe(960 + i);
      db.insertColor(shoes.getColor());
      db.insertType(shoes.getType(), (int) shoesId);
      db.insertClothes(shoes);
    }

    db.close();
  }
}
