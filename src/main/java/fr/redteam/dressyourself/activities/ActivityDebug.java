package fr.redteam.dressyourself.activities;

import java.io.FileNotFoundException;
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
import android.widget.Toast;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.common.DBHelper;
import fr.redteam.dressyourself.core.api.APIShopSense;
import fr.redteam.dressyourself.core.api.APIZara;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;


public class ActivityDebug extends Activity {

  private Button debugAPI;
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

    debugAPI = (Button) findViewById(R.id.buttonDebugAPI);
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
        clothe.setWeather(weather);
        clothe.setBrand("Zara");
        clothe.setColor("RED");
        clothe.setType("boot");
        clothe.setBodies("body");
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
    debugAPI.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        new APITesting().execute(new String());
      }
    });
    addClothesToDataBase.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        try {
          fillLocalDatabaseHard();
        } catch (FileNotFoundException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
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
   * XXX: Ne fonctionne pas a cause du connecteur
   * 
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

  /**
   * 
   * @throws FileNotFoundException
   */
  private void fillLocalDatabaseHard() throws FileNotFoundException {

    DBHelper db = new DBHelper(this);

    db.open();
    long topId = db.insertBodies("Top");
    long bottomId = db.insertBodies("Bottom");
    long shoesId = db.insertBodies("Shoes");

    /* long brandId = */db.insertBrand("Zara");

    /* Tops */
    Clothe top = new Clothe("two-tone wrap around jacket");
    top.setBodies("Top");
    top.setBrand("Zara");
    top.setColor("Ecru / Black");
    top.setType("Knitwears");
    top.setImage(getResources().openRawResource(R.raw.twotone_wrap_around_jacket));

    db.insertColor(top.getColor());
    db.insertType(top.getType(), (int) topId);
    db.insertClothes(top);

    top = new Clothe("high neck sweater with strass");
    top.setBodies("Top");
    top.setBrand("Zara");
    top.setColor("Grey");
    top.setType("Knitwears");
    top.setImage(getResources().openRawResource(R.raw.high_neck_sweater_with_strass));

    db.insertColor(top.getColor());
    // db.insertType(top.getType(), (int) topId);
    db.insertClothes(top);

    top = new Clothe("sweater with loose turtle neck");
    top.setBodies("Top");
    top.setBrand("Zara");
    top.setColor("Ecru / Black");
    top.setType("Knitwears");
    top.setImage(getResources().openRawResource(R.raw.sweater_with_loose_turtle_neck));

    db.insertColor(top.getColor());
    // db.insertType(top.getType(), (int) topId);
    db.insertClothes(top);

    /* Bottoms */
    Clothe bottom = new Clothe("skinny cropped jeans");
    bottom.setBodies("Top");
    bottom.setBrand("Zara");
    bottom.setColor("Blue");
    bottom.setType("Jean");
    bottom.setImage(getResources().openRawResource(R.raw.skinny_cropped_jeans));

    db.insertColor(bottom.getColor());
    db.insertType(bottom.getType(), (int) bottomId);
    db.insertClothes(bottom);

    bottom = new Clothe("medium wash jeans");
    bottom.setBodies("Top");
    bottom.setBrand("Zara");
    bottom.setColor("Indigo");
    bottom.setType("Jean");
    bottom.setImage(getResources().openRawResource(R.raw.medium_wash_jeans));

    db.insertColor(bottom.getColor());
    // db.insertType(bottom.getType(), (int) topId);
    db.insertClothes(bottom);

    bottom = new Clothe("skinny jeans");
    bottom.setBodies("Top");
    bottom.setBrand("Zara");
    bottom.setColor("Black");
    bottom.setType("Jean");
    bottom.setImage(getResources().openRawResource(R.raw.skinny_jeans));

    db.insertColor(bottom.getColor());
    // db.insertType(bottom.getType(), (int) topId);
    db.insertClothes(bottom);

    /* Shoes */
    Clothe shoes = new Clothe("embossed leather high heel ankle boot");
    shoes.setBodies("Top");
    shoes.setBrand("Zara");
    shoes.setColor("Black");
    shoes.setType("Shoes");
    shoes.setImage(getResources().openRawResource(R.raw.embossed_leather_high_heel_ankle_boot));

    db.insertColor(shoes.getColor());
    db.insertType(shoes.getType(), (int) shoesId);
    db.insertClothes(shoes);

    shoes = new Clothe("high heel leather ankle boot with zips");
    shoes.setBodies("Top");
    shoes.setBrand("Zara");
    shoes.setColor("Black");
    shoes.setType("Shoes");
    shoes.setImage(getResources().openRawResource(R.raw.high_heel_leather_ankle_boot_with_zips));

    db.insertColor(shoes.getColor());
    // db.insertType(shoes.getType(), (int) topId);
    db.insertClothes(shoes);

    shoes = new Clothe("leather ankle boot with zip");
    shoes.setBodies("Top");
    shoes.setBrand("Zara");
    shoes.setColor("Black");
    shoes.setType("Shoes");
    shoes.setImage(getResources().openRawResource(R.raw.leather_ankle_boot_with_zip));

    db.insertColor(shoes.getColor());
    // db.insertType(shoes.getType(), (int) topId);
    db.insertClothes(shoes);

    Toast.makeText(this, "Products added to local database !", Toast.LENGTH_SHORT).show();

    db.close();
  }
}
