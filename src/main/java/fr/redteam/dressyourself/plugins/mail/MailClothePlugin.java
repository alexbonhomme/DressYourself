package fr.redteam.dressyourself.plugins.mail;

import android.app.Activity;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class MailClothePlugin extends MailPlugin {

  private Clothe clothe;
  private String textBody;

  public MailClothePlugin(Clothe clothe, String subject, String textBody, String textDestinataire,
      Activity activity) {
    super(subject, textDestinataire, activity);
    this.clothe = clothe;
    this.textBody = textBody;
  }

  protected void body() {
    String txtBody =
        this.textBody + "\n - a\\an " + clothe.getType() + " from the brand " + clothe.getBrand()
            + " and it's " + clothe.getColor() + " " + clothe.getModel() + ".\n";
    /* Add text */
    mailIntent.putExtra(android.content.Intent.EXTRA_TEXT, txtBody);
  }
}
