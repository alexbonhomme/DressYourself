package fr.redteam.dressyourself.plugins.mail;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

/**
 * this class was made to enable to share your clothe or outfit
 */
public class MailPlugin {
  private Intent mailIntent;
  private String subject;
  private String textDestinataire;
  private Activity activity;
  private boolean adresseValide;
  private static final int REQUEST_CODE_MAILINTENT = 1234;

  public MailPlugin(String subject, String textDestinataire, Activity activity) {
    this.mailIntent = new Intent(android.content.Intent.ACTION_SEND);
    this.subject = subject;
    this.textDestinataire = textDestinataire;
    this.activity = activity;
    this.adresseValide = true;
  }

  /**
   * Check if mailAdress is valid
   * 
   * @param email the mail to check
   */
  protected boolean isValidEmailAddress(String email) {
    // utilisation du pattern d'android pour vérifier l'adresse mail
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
  }

  /**
   * Make a list of receiver
   * 
   * @return list of receiver
   */
  protected String[] listDestinataire() {
    String txtDsc = this.textDestinataire;
    String[] dscList;
    int nbDst = 0;
    /*
     * Detect the number and the begin of mail adresse
     */
    List<Integer> positionSeparateur = new ArrayList<Integer>();
    for (int i = 0; i < txtDsc.length(); i++) {
      if (txtDsc.charAt(i) == ';') {
        nbDst++;
        positionSeparateur.add(i);
      }
    }
    /*
     * Make the list of receiver
     */
    if (nbDst > 0) {
      int depart = 0;
      dscList = new String[nbDst + 1];
      for (int i = 0; i < nbDst; i++) {
        dscList[i] = txtDsc.substring(depart, positionSeparateur.get(i));
        isValidEmailAddress(dscList[i]);
        depart = positionSeparateur.get(i) + 1;
      }
      dscList[nbDst] = txtDsc.substring(positionSeparateur.get(nbDst - 1) + 1);

      for (int i = 0; i < nbDst; i++) {
        if (this.adresseValide) {
          this.adresseValide = isValidEmailAddress(dscList[i]);
        }
      }
    } else {

      dscList = new String[1];
      dscList[0] = txtDsc;
      if (this.adresseValide) {
        this.adresseValide = isValidEmailAddress(dscList[0]);
      }
    }
    return dscList;
  }

  /**
   * function which made an mail intent in order to send it.
   */
  public void createMail() {
    /* Set the type of the mail */
    mailIntent.setType("image/png");

    /* add destinaire */
    mailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, this.listDestinataire());
    /* Add the subject for the mail */
    mailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, this.subject);
  }

  /**
   * create mail client chooser intent
   */
  public void launchMailAgent() {
    if (this.adresseValide) {
      this.activity.startActivityForResult(
          Intent.createChooser(mailIntent, "Choose an mail client"), REQUEST_CODE_MAILINTENT);
    } else {
      Toast.makeText(this.activity, "Check mail adress.", Toast.LENGTH_SHORT).show();
    }
  }

  /**
   * the mail adress is valid
   * 
   * @return mail Valid
   */
  public boolean isValidMail() {
    return this.adresseValide;
  }

  /**
   * get intent mail
   * 
   * @return mailintent
   */
  protected Intent getMailIntent() {
    return this.mailIntent;
  }
}
