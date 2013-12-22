package fr.redteam.dressyourself.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.core.clothes.Outfit;
import fr.redteam.dressyourself.plugins.mail.MailClothePlugin;
import fr.redteam.dressyourself.plugins.mail.MailOutfitPlugin;
import fr.redteam.dressyourself.plugins.mail.MailPlugin;

/**
 * This activity is made in order to share a clothe by mail
 */
public class ActivityMail extends Activity {

  private EditText textDestinataire;
  private EditText textContenu;
  private MailPlugin mail;
  private static final int REQUEST_CODE_MAILINTENT = 1234;

  /**
   * Define all object create on the open of activity
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mail);
    Button buttonEnvoyer = (Button) findViewById(R.id.btnEnvoieMailClothe);
    this.textDestinataire = (EditText) findViewById(R.id.editDestinataireClothe);
    this.textContenu = (EditText) findViewById(R.id.editMailClothe);
    /**
     * define the click listener
     */

    buttonEnvoyer.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        ActivityMail.this.creationMail();
      }
    });
  }

  /**
   * check if mailIntentActivity is finish and close this activity before redirect on filter page.
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE_MAILINTENT) {
      Intent intent = new Intent(ActivityMail.this, ActivityMain.class);
      if (ActivityMail.this.mail.isValidMail()) {
        ActivityMail.this.finish();
      }
      startActivity(intent);
    }
  }

  /**
   * function which made an mail intent in order to send it.
   */
  public void creationMail() {
    Clothe clothe = (Clothe) this.getIntent().getExtras().get("clothe");
    if (clothe != null) {
      MailClothePlugin mailClothe =
          new MailClothePlugin(clothe, "i want to share this Clothe", this.textContenu.getText()
              .toString(), this.textDestinataire.getText().toString(), ActivityMail.this);
      this.mail = mailClothe;
      mailClothe.createMail();
    } else {

      Outfit outfit = (Outfit) this.getIntent().getExtras().get("outfit");
      MailOutfitPlugin mailOutfit =
          new MailOutfitPlugin(outfit, "i want to share this Outfit", this.textContenu.getText()
              .toString(), this.textDestinataire.getText().toString(), this);
      this.mail = mailOutfit;
      mailOutfit.createMail();
    }

  }
}
