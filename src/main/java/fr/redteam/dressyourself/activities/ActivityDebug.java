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
import fr.redteam.dressyourself.common.database.DBHelper;
import fr.redteam.dressyourself.common.filemanager.AndroidFileManager;
import fr.redteam.dressyourself.core.api.APIZara;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;


public class ActivityDebug extends Activity {

  private Button addClothesToDataBase;
  private Button sendMailOutfit;
  private Button sendMailClothe;
  private Button modifyClothe;
  private Button ClothDetail;
  private DBHelper db;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_debug);

    sendMailOutfit = (Button) findViewById(R.id.btnSendOutfitMail);
    sendMailClothe = (Button) findViewById(R.id.btnClothMail);
    this.modifyClothe = (Button) findViewById(R.id.buttonClotheModify);
    this.ClothDetail = (Button) findViewById(R.id.buttonDetailClothe);
    addClothesToDataBase = (Button) findViewById(R.id.buttonAddClotheClothesToDB);
    this.db = new DBHelper(this);

    sendMailOutfit.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        List<String> weather = new ArrayList<String>();
        weather.add("Cloudy");
        weather.add("Rainy");
        Clothe clothe = new Clothe("Clothe test");
        clothe.setWeather(weather);
        clothe.setBrand("Zara");
        clothe.setColor("RED");
        clothe.setType("pull");
        clothe.setBodies("body");

        Clothe clothe2 = new Clothe("Clothe2 test");
        clothe2.setWeather(weather);
        clothe2.setBrand("Zara");
        clothe2.setColor("RED");
        clothe2.setType("boot");
        clothe2.setBodies("body");
        Outfit outfit = new Outfit();
        outfit.addClothe(clothe);
        outfit.addClothe(clothe2);
        Intent intent = new Intent(ActivityDebug.this, ActivityOutfitMail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("outfit", outfit);
        intent.putExtras(bundle);
        startActivity(intent);
      }
    });


    sendMailClothe.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        List<String> weather = new ArrayList<String>();
        weather.add("Cloudy");
        weather.add("Rainy");
        Clothe clothe = new Clothe("Clothe test");
        clothe.setWeather(weather);
        clothe.setBrand("Zara");
        clothe.setColor("RED");
        clothe.setType("pull");
        clothe.setBodies("body");

        Intent intent = new Intent(ActivityDebug.this, ActivityClotheMail.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("clothe", clothe);
        intent.putExtras(bundle);
        startActivity(intent);
      }
    });


    /* API */
    addClothesToDataBase.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        new AsyncTask<String, Void, Void>() {

          @Override
          protected Void doInBackground(String... params) {
            fillLocaleDataBaseWithFewClothes();
            return null;
          }
        }.execute();
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
        // intent.putExtra("image",
        // "/mnt/shared/unreal/git/dressyourself/res/drawable/bottines.jpg");
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
   * Méthode temporaire pour permettre l'avancement de Rémy et Antonia
   * 
   * @author Alexandre Bonhomme
   * 
   */
  private void fillLocaleDataBaseWithFewClothes() {
    DBHelper db = new DBHelper(this);
    APIZara api = new APIZara(new AndroidFileManager(getApplicationContext()));

    db.open();
    long topId = db.insertBodies("Top");
    long bottomId = db.insertBodies("Bottom");
    long shoesId = db.insertBodies("Shoes");

    /* long brandId = */db.insertBrand("Zara");

    /**
     * Get 3 tops, 3 bottoms and 3 shoes (females)
     */

    for (int i = 0; i < 3; i++) {
      Clothe bottom = api.findClotheById(500 + i);
      bottom.setWeather(new ArrayList<String>());
      db.insertColor(bottom.getColor());
      db.insertType(bottom.getType(), (int) bottomId);
      db.insertClothes(bottom);

      Clothe top = api.findClotheById(750 + i);
      top.setWeather(new ArrayList<String>());
      db.insertColor(top.getColor());
      db.insertType(top.getType(), (int) topId);
      db.insertClothes(top);

      Clothe shoes = api.findClotheById(960 + i);
      shoes.setWeather(new ArrayList<String>());
      db.insertColor(shoes.getColor());
      db.insertType(shoes.getType(), (int) shoesId);
      db.insertClothes(shoes);
    }

    db.close();
  }
}
