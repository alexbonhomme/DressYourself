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
import fr.redteam.dressyourself.core.clothes.Outfit;

@RunWith(RobolectricTestRunner.class)
public class ActivityOutfitMailTest {
  private ActivityOutfitMail activityOutfitMail;
  private Outfit outfit;

  @Before
  public void setUp() throws Exception {

    this.createOutfit();
    Intent intent = this.getIntent();
    this.activityOutfitMail =
        Robolectric.buildActivity(ActivityOutfitMail.class).withIntent(intent).create().visible()
            .get();
  }

  /*
   * Made a the clothe
   */
  public void createOutfit() throws FileNotFoundException {

    List<String> myWeather = new ArrayList<String>();
    myWeather.add("Cloudy");
    Clothe part1 = new Clothe("Vet1");
    part1.setBrand("brice");
    part1.setWeather(myWeather);
    part1.setColor("blue");
    part1.setBodies("top");
    part1.setType("sweat");

    Clothe part2 = new Clothe("Vet2");
    part2.setWeather(myWeather);
    part2.setType("jeans");
    part2.setBrand("brice");
    part2.setBodies("bottom");
    part2.setColor("Yellow");
    Outfit myOutfit = new Outfit();
    myOutfit.addClothe(part1);
    myOutfit.addClothe(part2);
    this.outfit = myOutfit;
  }

  public Intent getIntent() throws FileNotFoundException {
    Intent intent =
        new Intent(Robolectric.getShadowApplication().getApplicationContext(),
            ActivityOutfitMail.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("outfit", this.outfit);
    intent.putExtras(bundle);
    return intent;
  }


}
