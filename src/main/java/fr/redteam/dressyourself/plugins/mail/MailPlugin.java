package fr.redteam.dressyourself.plugins.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class MailPlugin {

  protected Intent mailIntent;
  protected String subject;
  protected String textBody;
  protected String textDestinataire;
  protected Activity activity;
  protected boolean AdresseValide;

  public MailPlugin(String subject, String textBody, String textDestinataire, Activity activity) {
    this.mailIntent = new Intent(android.content.Intent.ACTION_SEND);
    this.subject = subject;
    this.textBody = textBody;
    this.textDestinataire = textDestinataire;
    this.activity = activity;
    this.AdresseValide = true;
  }

  protected void isValidEmailAddress(String email) {
    Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
    // On déclare un matcher, qui comparera le pattern avec la
    // string passée en argument
    Matcher m = p.matcher(email);
    if (!m.matches()) this.AdresseValide = false;
  }

  protected String[] ListDestinataire() {
    String txtDsc = this.textDestinataire;
    String[] DscList;
    int nbDst = 0;
    List<Integer> positionSeparateur = new ArrayList<Integer>();
    for (int i = 0; i < txtDsc.length(); i++) {
      if (txtDsc.charAt(i) == ';') {
        nbDst++;
        positionSeparateur.add(i);
      }
    }

    if (nbDst > 0) {
      int depart = 0;
      DscList = new String[nbDst + 1];
      for (int i = 0; i < nbDst; i++) {
        DscList[i] = txtDsc.substring(depart, positionSeparateur.get(i));
        isValidEmailAddress(DscList[i]);
        depart = positionSeparateur.get(i) + 1;
      }
      DscList[nbDst] = txtDsc.substring(positionSeparateur.get(nbDst - 1) + 1);
      isValidEmailAddress(DscList[nbDst]);
    } else {
      DscList = new String[1];
      DscList[0] = txtDsc;
    }
    return DscList;
  }

  protected void Body() {}

  /**
   * function which made an mail intent in order to send it.
   */
  public void creationMail() {

    /* Set the type of the mail */
    mailIntent.setType("image/png");

    /* add destinaire */
    mailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, this.ListDestinataire());
    /* Add the subject for the mail */
    mailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, this.subject);
    mailIntent.putExtra(android.content.Intent.EXTRA_TEXT, this.textBody + "\n");
    this.Body();
    if (this.AdresseValide) {
      System.out.println("lancement intent");
      this.activity.startActivity(Intent.createChooser(mailIntent, "Choose an mail client"));
    } else
      Toast.makeText(this.activity, "Check mail adress.", Toast.LENGTH_SHORT).show();
  }

  public boolean isValidMail() {
    return this.AdresseValide;
  }
}
