package fr.redteam.dressyourself.plugins.mail;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;

public class MailOutfitPlugin extends MailPlugin {

  private Outfit outfit;

  public MailOutfitPlugin(Outfit outfit, String subject, String textBody, String textDestinataire,
      Activity activity) {
    super(subject, textBody, textDestinataire, activity);
  }

  protected void Body() {

    List<Clothe> ListClothe = new ArrayList<Clothe>();

    ListClothe = outfit.getClothes();
    /**
     * get the key of outfit which was passed in parameter.
     */

    this.mailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
        "j'ai trouvé cette tenue et je pense qu'elle va te plaire.\n");
    mailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
        "Ma tenue contient les pieces suivantes.\n");
    /* Made the body of mail */
    for (Clothe vetement : ListClothe) {
      mailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "un(e) " + vetement.getType()
          + " de la marque " + vetement.getBrand() + " et de couleur " + vetement.getColor() + " "
          + vetement.getModel() + ".\n");
      // emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + vetement.getImageUrl()));

    }

  }
}
