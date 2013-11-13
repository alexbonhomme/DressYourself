package fr.redteam.dressyourself.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import fr.redteam.dressyourself.R;
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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.buttonEnvoyer = (Button) findViewById(R.id.btnEnvoieMail);
    this.textDestinataire = (EditText) findViewById(R.id.editDestinataire);
    this.textContenu = (EditText) findViewById(R.id.editMail);
    setContentView(R.layout.activity_outfit_mail);
    /**
     * define the click listener
     */
    this.buttonEnvoyer.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO redirected to the 'filters page'
        ActivityOutfitMail.this.creationMail();
        if (ActivityOutfitMail.this.mail.isValidMail()) {
          Intent intent = new Intent(ActivityOutfitMail.this, ActivityMain.class);
          startActivity(intent);
        }
      }
    });
  }

  /**
   * function which made an mail intent in order to send it.
   */

  public void creationMail() {
    this.mail =
        new MailOutfitPlugin(getIntent().getExtras().getInt("id"), "i want to share this Outfit",
            this.textContenu.getText().toString(), this.textDestinataire.getText().toString(), this);
    this.mail.creationMail();
  }
}
