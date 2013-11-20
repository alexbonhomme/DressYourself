package fr.redteam.dressyourself.plugins.mail;

import android.app.Activity;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class MailClothePlugin extends MailPlugin {

  private Clothe clothe;

  public MailClothePlugin(Clothe clothe, String subject, String textBody, String textDestinataire,
      Activity activity) {
    super(subject, textBody, textDestinataire, activity);
    this.clothe = clothe;
  }

  protected void body() {
    String txtBody =
        this.textBody + "\n - a\\an " + clothe.getType() + " from the brand " + clothe.getBrand()
            + " and it's " + clothe.getColor() + " " + clothe.getModel() + ".\n";
    /* Add text */
    mailIntent.putExtra(android.content.Intent.EXTRA_TEXT, txtBody);
    // mailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + this.clothe.getImage()));
  }
}
