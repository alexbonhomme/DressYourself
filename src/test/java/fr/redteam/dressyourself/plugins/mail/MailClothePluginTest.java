package fr.redteam.dressyourself.plugins.mail;


import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import fr.redteam.dressyourself.activities.ActivityClotheMail;
import fr.redteam.dressyourself.activities.ActivityOutfitMail;
import fr.redteam.dressyourself.core.clothes.Clothe;

@RunWith(RobolectricTestRunner.class)
public class MailClothePluginTest {

  private Activity activity;
  private Clothe clothe;

  @Before
  public void setUp() throws Exception {

    Clothe clothe = new Clothe();
    this.clothe = clothe;
    Intent intent =
        new Intent(Robolectric.getShadowApplication().getApplicationContext(),
            ActivityOutfitMail.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("clothe", clothe);
    intent.putExtras(bundle);
    this.activity =
        Robolectric.buildActivity(ActivityClotheMail.class).withIntent(intent).create().visible()
            .get();
  }


}
