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
import fr.redteam.dressyourself.plugins.mail.MailClothePlugin;

public class ActivityClotheMail extends Activity {

  private EditText textDestinataire;
  private EditText textContenu;
  private MailClothePlugin mail;
  private Clothe clothe;
  private static final int REQUEST_CODE_MAILINTENT = 1234;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_clothe_mail);
    Button buttonEnvoyer = (Button) findViewById(R.id.btnEnvoieMailClothe);
    this.textDestinataire = (EditText) findViewById(R.id.editDestinataireClothe);
    this.textContenu = (EditText) findViewById(R.id.editMailClothe);
    this.clothe = (Clothe) this.getIntent().getExtras().get("clothe");
    /**
     * define the click listener
     */

    buttonEnvoyer.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        ActivityClotheMail.this.creationMail();
      }
    });
  }

  /**
   * check if mailIntentActivity is finish and close this activity before redirect on filter page.
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == REQUEST_CODE_MAILINTENT) {
      Intent intent = new Intent(ActivityClotheMail.this, ActivityMain.class);
      if (ActivityClotheMail.this.mail.isValidMail()) {
        ActivityClotheMail.this.finish();
      }
      startActivity(intent);
    }
  }

  /**
   * function which made an mail intent in order to send it.
   */
  public void creationMail() {
    this.mail =
        new MailClothePlugin(this.clothe, "i want to share this Clothe", this.textContenu.getText()
            .toString(), this.textDestinataire.getText().toString(), ActivityClotheMail.this);
    mail.createMail();
  }
}
