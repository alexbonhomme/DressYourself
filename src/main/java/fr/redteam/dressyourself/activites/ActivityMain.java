package main.java.fr.redteam.dressyourself.activites;

import main.java.fr.redteam.dressyourself.ActivityDebug;
import main.java.fr.redteam.dressyourself.R;
import main.java.fr.redteam.dressyourself.common.DBHelper;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class ActivityMain extends Activity {

  private Button buttonAddClothing;
  private Button buttonListClothes;
  private Button buttonFilters;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    // creation base de donnée
    DBHelper db = new DBHelper(this);

    db.open();
    db.close();

    /* Open the page to add clothes */
    buttonAddClothing = (Button) findViewById(R.id.btAddClothing);
    buttonAddClothing.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ActivityMain.this, ActivityClotheAdd.class);
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
        Intent intent = new Intent(ActivityMain.this, ActivityFilter.class);
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

}
