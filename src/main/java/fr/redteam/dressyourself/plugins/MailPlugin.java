package fr.redteam.dressyourself.plugins;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;

public class MailPlugin {

  protected Intent mailIntent;
  protected int id;
  protected String subject;
  protected String textBody;
  protected String textDestinataire;
  protected Activity activity;

  public MailPlugin(int id, String subject, String textBody, String textDestinataire,
      Activity activity) {
    this.id = id;
    this.mailIntent = new Intent(android.content.Intent.ACTION_SEND);
    this.subject = subject;
    this.textBody = textBody;
    this.textDestinataire = textDestinataire;
    this.activity = activity;
  }

  public String[] ListDestinataire() {
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
        depart = positionSeparateur.get(i) + 1;
      }
      DscList[nbDst] = txtDsc.substring(positionSeparateur.get(nbDst - 1) + 1);
    } else {
      DscList = new String[1];
      DscList[0] = txtDsc;
    }
    return DscList;
  }


  public void Body() {}

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
    this.activity.startActivity(Intent.createChooser(mailIntent, "Choose an mail client"));
  }

}
