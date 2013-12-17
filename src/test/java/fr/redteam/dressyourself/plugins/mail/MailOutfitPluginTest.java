package fr.redteam.dressyourself.plugins.mail;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import fr.redteam.dressyourself.activities.ActivityOutfitMail;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;

@RunWith(RobolectricTestRunner.class)
public class MailOutfitPluginTest {

  private Activity activity;
  private Outfit outfit;

  @Before
  public void setUp() throws Exception {
    Outfit outfit = new Outfit();
    Clothe clothe = new Clothe();
    outfit.addClothe(clothe);
    this.outfit = outfit;

    Intent intent =
        new Intent(Robolectric.getShadowApplication().getApplicationContext(),
            ActivityOutfitMail.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("outfit", outfit);
    intent.putExtras(bundle);
    this.activity =
        Robolectric.buildActivity(ActivityOutfitMail.class).withIntent(intent).create().visible()
            .get();
  }


}
