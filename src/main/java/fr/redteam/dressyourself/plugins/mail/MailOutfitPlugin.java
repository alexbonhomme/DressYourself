package fr.redteam.dressyourself.plugins.mail;

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
/**
 * 
 * @param outfit
 * @param subject
 * @param textBody
 * @param receiver
 * @param activity
 */
  public MailOutfitPlugin(Outfit outfit, String subject, String textBody, String receiver,
      Activity activity) {
    super(subject, receiver, activity);
    this.outfit = outfit;
    this.textBody = textBody;
  }

  /**
   * redefine the method Create Mail
   */
  public void createMail() {
    super.createMail();
    this.body();
    this.launchMailAgent();
  }

  /**
   * Write the body of mail
   */
  protected void body() {

    List<Clothe> listClothe = outfit.getClothes();
    /*
     * get the key of outfit which was passed in parameter.
     */
    this.textBody +=
        "I found this outfit and I think it'll like.\nthis outfit contains the following parts.\n";
    /* Made the body of mail */
    for (Clothe vetement : listClothe) {
      this.textBody +=
          "- a\\an " + vetement.getType() + " from the brand " + vetement.getBrand() + " and it's "
              + vetement.getColor() + " " + vetement.getModel() + ".\n";
    }
    /* Add text */
    this.getMailIntent().putExtra(android.content.Intent.EXTRA_TEXT, this.textBody);
  }
}
