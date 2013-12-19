package fr.redteam.dressyourself.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.clothes.Outfit;
import fr.redteam.dressyourself.plugins.mail.MailOutfitPlugin;

/**
 * This activity is made in order to share an outfit by mail
 */
public class ActivityOutfitMail extends Activity {

  private EditText textDestinataire;
  private EditText textContenu;
  private MailOutfitPlugin mail;
  private Outfit outfit;
  private static final int REQUEST_CODE_MAILINTENT = 1234;
  /**
   * Define all object create on the open of activity
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    setContentView(R.layout.activity_outfit_mail);
    super.onCreate(savedInstanceState);

    Button buttonEnvoyer = (Button) findViewById(R.id.btnEnvoieMailOutfit);
    this.textDestinataire = (EditText) findViewById(R.id.editDestinataireOutfit);
    this.textContenu = (EditText) findViewById(R.id.editMailOutfit);
    this.outfit = (Outfit) this.getIntent().getExtras().get("outfit");

    /**
     * define the click listener
     */
    buttonEnvoyer.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        ActivityOutfitMail.this.creationMail();
      }
    });
  }

  /**
   * check if mailIntentActivity is finish and close this activity before redirect on filter page.
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE_MAILINTENT) {
      Intent intent = new Intent(ActivityOutfitMail.this, ActivityMain.class);
      if (ActivityOutfitMail.this.mail.isValidMail()) {
        ActivityOutfitMail.this.finish();
      }
      startActivity(intent);
    }
  }

  /**
   * function which made an mail intent in order to send it.
   */

  public void creationMail() {

    this.mail =
        new MailOutfitPlugin(outfit, "i want to share this Outfit", this.textContenu.getText()
            .toString(), this.textDestinataire.getText().toString(), this);
    this.mail.createMail();
  }
}
