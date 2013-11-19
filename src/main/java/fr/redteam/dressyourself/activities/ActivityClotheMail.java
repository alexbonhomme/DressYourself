package fr.redteam.dressyourself.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.plugins.mail.MailClothePlugin;

public class ActivityClotheMail extends Activity {

  private Button buttonEnvoyer;
  private EditText textDestinataire;
  private EditText textContenu;
  private MailClothePlugin mail;
  private Clothe clothe;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_clothe_mail);
    this.buttonEnvoyer = (Button) findViewById(R.id.btnEnvoieMailClothe);
    this.textDestinataire = (EditText) findViewById(R.id.editDestinataireClothe);
    this.textContenu = (EditText) findViewById(R.id.editMailClothe);
    this.clothe = (Clothe) this.getIntent().getExtras().get("clothe");

    /**
     * define the click listener
     */

    this.buttonEnvoyer.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO redirected to the 'filters page'
        ActivityClotheMail.this.creationMail();
        if (ActivityClotheMail.this.mail.isValidMail()) {
          ActivityClotheMail.this.finish();
        }
      }
    });
  }

  /**
   * function which made an mail intent in order to send it.
   */


  public void creationMail() {
    this.mail =
        new MailClothePlugin(clothe, "i want to share this Clothe", this.textContenu.getText()
            .toString(), this.textDestinataire.getText().toString(), ActivityClotheMail.this);
    mail.creationMail();
  }
}
