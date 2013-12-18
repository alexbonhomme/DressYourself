package fr.redteam.dressyourself.plugins.mail;

import static org.junit.Assert.assertEquals;

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
/**
 * This class is made in order to test MailPlugin
 *
 */
@RunWith(RobolectricTestRunner.class)
public class MailPluginTest {

  private Activity activity;
  /**
   * Configure the environnement's test.
   */
  @Before
  public void setUp() throws Exception {
    Outfit outfit = new Outfit();
    Clothe clothe = new Clothe();
    outfit.addClothe(clothe);

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
  public void testAdressValid() throws Throwable {
    MailPlugin mailPlugin = new MailPlugin("", "toto@free.fr", this.activity);
    mailPlugin.createMail();
    assertEquals(true, mailPlugin.isValidMail());
  }

  /**
   * this test made a simple test if the test return false if the mail adress is false.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdressFalse() throws Throwable {
    MailPlugin mailPlugin = new MailPlugin("", "to@to@free.fr", this.activity);
    mailPlugin.createMail();
    assertEquals(false, mailPlugin.isValidMail());
  }

  /**
   * this test made a simple test if the test return true if all mail adress is valid.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdressesValid() throws Throwable {
    MailPlugin mailPlugin =
        new MailPlugin("", "toto@free.fr;jerm@live.com;foufou@gmail.com", this.activity);
    mailPlugin.createMail();
    assertEquals(true, mailPlugin.isValidMail());
  }

  /**
   * this test made a simple test if the test return false if the one of mail adress isn't valid.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdressesFalse() throws Throwable {
    MailPlugin mailPlugin =
        new MailPlugin("", "toto@free.fr;jerm@tu@tu.live.com;foufou@gmail.com", this.activity);
    mailPlugin.createMail();
    assertEquals(false, mailPlugin.isValidMail());
  }

}
