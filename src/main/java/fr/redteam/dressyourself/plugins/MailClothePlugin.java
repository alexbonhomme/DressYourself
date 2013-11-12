package fr.redteam.dressyourself.plugins;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import fr.redteam.dressyourself.common.DBHelper;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class MailClothePlugin extends MailPlugin {

  public MailClothePlugin(int id, String subject, String textBody, String textDestinataire,
      Activity activity) {
    super(id, subject, textBody, textDestinataire, activity);
  }

  protected void Body() {

    Clothe vetement = new DBHelper(this.activity).getClothe(this.id);
    /* Add text */
    mailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
        "j'ai trouvé ce vetement et je pense qu'il va te plaire.\n");
    /* Made the body of mail */
    mailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "un(e) " + vetement.getType()
        + " de la marque " + vetement.getBrand() + " et de couleur " + vetement.getColor() + " "
        + vetement.getModel() + ".\n");
    mailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + vetement.getImage()));
  }

}
