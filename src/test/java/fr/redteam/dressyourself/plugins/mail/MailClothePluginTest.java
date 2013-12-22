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
import fr.redteam.dressyourself.activities.ActivityMail;
import fr.redteam.dressyourself.core.clothes.Clothe;

/**
 * This class is made in order to test MailClothePlugin
 */
@RunWith(RobolectricTestRunner.class)
public class MailClothePluginTest {

  private Activity activity;
  private Clothe clothe;

  /**
   * Configure the environnement's test.
   */
  @Before
  public void setUp() throws Exception {

    Clothe clothe = new Clothe();
    this.clothe = clothe;
    Intent intent =
        new Intent(Robolectric.getShadowApplication().getApplicationContext(),
            ActivityMail.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("clothe", clothe);
    intent.putExtras(bundle);
    this.activity =
        Robolectric.buildActivity(ActivityMail.class).withIntent(intent).create().visible()
            .get();
  }

  /**
   * this test made a simple test if the test return true if the mail adress is valid.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdressValid() throws Throwable {
    MailClothePlugin mailClothePlugin =
        new MailClothePlugin(this.clothe, "", "", "toto@free.fr", this.activity);
    mailClothePlugin.createMail();
    assertEquals(true, mailClothePlugin.isValidMail());
  }

  /**
   * this test made a simple test if the test return false if the mail adress is false.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdressFalse() throws Throwable {
    MailClothePlugin mailClothePlugin =
        new MailClothePlugin(this.clothe, "", "", "to@to@tutu.tox.free.fr", this.activity);
    mailClothePlugin.createMail();
    assertEquals(false, mailClothePlugin.isValidMail());
  }

  /**
   * this test made a simple test if the test return true if all mail adress is valid.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdressesValid() throws Throwable {
    MailClothePlugin mailClothePlugin =
        new MailClothePlugin(this.clothe, "", "", "toto@free.fr;jerm@live.com;foufou@gmail.com",
            this.activity);
    mailClothePlugin.createMail();
    assertEquals(true, mailClothePlugin.isValidMail());
  }

  /**
   * this test made a simple test if the test return false if the one of mail adress isn't valid.
   * 
   * @throws Throwable
   */
  @Test
  public void testAdressesFalse() throws Throwable {
    MailClothePlugin mailClothePlugin =
        new MailClothePlugin(this.clothe, "", "",
            "toto@free.fr;to@to@tutu.tox.free.fr;jerm@live.com;foufou@gmail.com", this.activity);
    mailClothePlugin.createMail();
    assertEquals(false, mailClothePlugin.isValidMail());
  }
}
