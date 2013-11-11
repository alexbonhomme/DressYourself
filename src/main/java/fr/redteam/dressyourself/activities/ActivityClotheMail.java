package fr.redteam.dressyourself.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.plugins.MailClothePlugin;

public class ActivityClotheMail extends Activity {

  private Button buttonEnvoyer;
  private EditText textDestinataire;
  private EditText textContenu;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    this.buttonEnvoyer = (Button) findViewById(R.id.btnEnvoieMailClothe);
    this.textDestinataire = (EditText) findViewById(R.id.editDestinataireClothe);
    this.textContenu = (EditText) findViewById(R.id.editMailClothe);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_clothe_mail);
    /**
     * define the click listener
     */
    this.buttonEnvoyer.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO redirected to the 'filters page'
        ActivityClotheMail.this.creationMail();
        Intent intent = new Intent(ActivityClotheMail.this, ActivityMain.class);
        startActivity(intent);
      }
    });
  }

  /**
   * function which made an mail intent in order to send it.
   */

  public void creationMail() {
    MailClothePlugin mail =
        new MailClothePlugin(getIntent().getExtras().getInt("idClothe"),
            "i want to share this Outfit", this.textContenu.getText().toString(),
            this.textDestinataire.getText().toString(), this);
    mail.creationMail();
  }

}
