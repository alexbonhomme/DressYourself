package fr.redteam.dressyourself.plugins.mail;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.widget.Toast;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;

public class MailOutfitPlugin extends MailPlugin {

  private Outfit outfit;
  private String textBody;

  public MailOutfitPlugin(Outfit outfit, String subject, String textBody, String textDestinataire,
      Activity activity) {
    super(subject, textDestinataire, activity);
    this.outfit = outfit;
    this.textBody = textBody;
  }

  protected void body() {

    List<Clothe> ListClothe = new ArrayList<Clothe>();

    ListClothe = outfit.getClothes();
    /**
     * get the key of outfit which was passed in parameter.
     */
    Toast.makeText(this.activity, " taille" + ListClothe.size(), Toast.LENGTH_SHORT).show();
    this.mailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
        "I found this outfit and I think it'll like.\n");
    mailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
        "this outfit contains the following parts.\n");
    /* Made the body of mail */
    for (Clothe vetement : ListClothe) {
      String txtBody =
          this.textBody + "\n - a\\an " + vetement.getType() + " from the brand "
              + vetement.getBrand() + " and it's " + vetement.getColor() + " "
              + vetement.getModel() + ".\n";
      /* Add text */
      mailIntent.putExtra(android.content.Intent.EXTRA_TEXT, txtBody);
    }

  }
}
