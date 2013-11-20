package fr.redteam.dressyourself.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.clothes.Outfit;
import fr.redteam.dressyourself.plugins.mail.MailOutfitPlugin;

/**
 * This activity is made in order to share by mail
 * 
 */
public class ActivityOutfitMail extends Activity {

  private Button buttonEnvoyer;
  private EditText textDestinataire;
  private EditText textContenu;
  private MailOutfitPlugin mail;
  private Outfit outfit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_outfit_mail);
    super.onCreate(savedInstanceState);

    this.buttonEnvoyer = (Button) findViewById(R.id.btnEnvoieMailOutfit);
    this.textDestinataire = (EditText) findViewById(R.id.editDestinataireOutfit);
    this.textContenu = (EditText) findViewById(R.id.editMailOutfit);
    this.outfit = (Outfit) this.getIntent().getExtras().get("outfit");

    /**
     * define the click listener
     */
    this.buttonEnvoyer.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO redirected to the 'filters page'
        ActivityOutfitMail.this.creationMail();
        if (ActivityOutfitMail.this.mail.isValidMail()) {
          ActivityOutfitMail.this.finish();
        }
      }
    });
  }

  /**
   * function which made an mail intent in order to send it.
   */

  public void creationMail() {

    this.mail =
        new MailOutfitPlugin(outfit, "i want to share this Outfit", this.textContenu.getText()
            .toString(), this.textDestinataire.getText().toString(), this);
    this.mail.creationMail();
  }
}
