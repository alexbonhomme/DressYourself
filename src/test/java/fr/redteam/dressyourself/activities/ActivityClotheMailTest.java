package fr.redteam.dressyourself.activities;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Intent;
import android.os.Bundle;
import fr.redteam.dressyourself.core.clothes.Clothe;

@RunWith(RobolectricTestRunner.class)
public class ActivityClotheMailTest {
  private ActivityClotheMail activityClotheMail;
  private Clothe clothe;

  @Before
  public void setUp() throws Exception {

    this.clothe();
    Intent intent = this.createIntent();
    this.activityClotheMail =
        Robolectric.buildActivity(ActivityClotheMail.class).withIntent(intent).create().visible()
            .get();
  }

  /*
   * Made a the clothe
   */
  public void clothe() throws FileNotFoundException {

    List<String> weather = new ArrayList<String>();
    weather.add("Rainy");
    Clothe myClothe = new Clothe("the pull of your life");
    myClothe.setBodies("top");
    myClothe.setWeather(weather);
    myClothe.setColor("Black");
    myClothe.setBrand("the Brand");
    myClothe.setType("pull");
    this.clothe = myClothe;
  }

  public Intent createIntent() throws FileNotFoundException {
    Intent intent =
        new Intent(Robolectric.getShadowApplication().getApplicationContext(),
            ActivityClotheMail.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("clothe", this.clothe);
    intent.putExtras(bundle);
    return intent;
  }

}
