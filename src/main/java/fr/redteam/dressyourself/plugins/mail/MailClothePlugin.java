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
        this.textBody + "\n J'ai trouvé ce vetement et je pense qu'il va te plaire.\n C'est un(e)"
            + this.clothe.getType() + " de la marque " + this.clothe.getBrand() + " et de couleur "
            + this.clothe.getColor() + " " + this.clothe.getModel() + ".\n";
    /* Add text */
    mailIntent.putExtra(android.content.Intent.EXTRA_TEXT, txtBody);
    // mailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + this.clothe.getImage()));
  }
}
