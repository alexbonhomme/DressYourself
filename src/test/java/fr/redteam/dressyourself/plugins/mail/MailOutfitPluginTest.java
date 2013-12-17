package fr.redteam.dressyourself.plugins.mail;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
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

  /**
   * this test made a simple test if the test return true if the mail adress is valid.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdresseValide() throws Throwable {
    MailOutfitPlugin mailOutfitPlugin =
        new MailOutfitPlugin(this.outfit, "", "", "toto@free.fr", this.activity);
    mailOutfitPlugin.createMail();
    assertTrue(true == mailOutfitPlugin.isValidMail());
  }

  /**
   * this test made a simple test if the test return false if the mail adress is false.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdresseFalse() throws Throwable {
    MailOutfitPlugin mailOutfitPlugin =
        new MailOutfitPlugin(this.outfit, "", "", "to@to@free.fr", this.activity);
    mailOutfitPlugin.createMail();
    assertTrue(false == mailOutfitPlugin.isValidMail());
  }

  /**
   * this test made a simple test if the test return true if all mail adress is valid.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdressesValide() throws Throwable {
    MailOutfitPlugin mailOutfitPlugin =
        new MailOutfitPlugin(this.outfit, "", "", "toto@free.fr;jerm@live.com;foufou@gmail.com",
            this.activity);
    mailOutfitPlugin.createMail();
    assertTrue(true == mailOutfitPlugin.isValidMail());
  }

  /**
   * this test made a simple test if the test return false if the one of mail adress isn't valid.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdressesFalse() throws Throwable {
    MailOutfitPlugin mailOutfitPlugin0 =
        new MailOutfitPlugin(this.outfit, "", "",
            "toto@free.fr;jerm@tu@tu.live.com;foufou@gmail.com", this.activity);
    mailOutfitPlugin0.createMail();
    assertTrue(false == mailOutfitPlugin0.isValidMail());
  }

}
