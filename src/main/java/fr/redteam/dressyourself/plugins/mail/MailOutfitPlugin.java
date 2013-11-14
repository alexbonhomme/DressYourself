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
    this.outfit = outfit;
  }

  protected void body() {

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
      String txtBody =
          this.textBody
              + "\n j'ai trouvé ce vetement et je pense qu'il va te plaire.\n C'est un(e)"
              + vetement.getType() + " de la marque " + vetement.getBrand() + " et de couleur "
              + vetement.getColor() + " " + vetement.getModel() + ".\n";
      /* Add text */
      mailIntent.putExtra(android.content.Intent.EXTRA_TEXT, txtBody);
      // mailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + vetement.getImage()));
    }

  }
}
