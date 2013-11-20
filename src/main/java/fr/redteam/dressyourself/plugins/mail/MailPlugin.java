package fr.redteam.dressyourself.plugins.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
  protected void isValidEmailAddress(String email) {
    Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
    // On declare un matcher, qui comparera le pattern avec la
    // string passee en argument
    Matcher m = p.matcher(email);
    if (!m.matches()) {
      this.adresseValide = false;
    }
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
      isValidEmailAddress(dscList[nbDst]);
    } else {
      dscList = new String[1];
      dscList[0] = txtDsc;
    }
    return dscList;
  }

  protected void body() {}

  /**
   * function which made an mail intent in order to send it.
   */
  public void creationMail() {

    /* Set the type of the mail */
    mailIntent.setType("image/png");

    /* add destinaire */
    mailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, this.listDestinataire());
    /* Add the subject for the mail */
    mailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, this.subject);
    this.body();
    if (this.adresseValide) {
      this.activity.startActivity(Intent.createChooser(mailIntent, "Choose an mail client"));
    } else {
      Toast.makeText(this.activity, "Check mail adress.", Toast.LENGTH_SHORT).show();
    }
  }

  /**
   * the mail si valide
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
