package fr.redteam.dressyourself.plugins.mail;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;

/**
 * class spécialized to mail an outfit
 */
public class MailOutfitPlugin extends MailPlugin {

  private Outfit outfit;
  private String textBody;

  public MailOutfitPlugin(Outfit outfit, String subject, String textBody, String textDestinataire,
      Activity activity) {
    super(subject, textDestinataire, activity);
    this.outfit = outfit;
    this.textBody = textBody;
  }

  /**
   * Write the body of mail
   */
  protected void body() {

    List<Clothe> ListClothe = new ArrayList<Clothe>();

    ListClothe = outfit.getClothes();
    /*
     * get the key of outfit which was passed in parameter.
     */
    this.getMailIntent().putExtra(android.content.Intent.EXTRA_TEXT,
        "I found this outfit and I think it'll like.\n");
    this.getMailIntent().putExtra(android.content.Intent.EXTRA_TEXT,
        "this outfit contains the following parts.\n");
    /* Made the body of mail */
    for (Clothe vetement : ListClothe) {
      String txtBody =
          this.textBody + "\n - a\\an " + vetement.getType() + " from the brand "
              + vetement.getBrand() + " and it's " + vetement.getColor() + " "
              + vetement.getModel() + ".\n";
      /* Add text */
      this.getMailIntent().putExtra(android.content.Intent.EXTRA_TEXT, txtBody);
    }

  }
}
