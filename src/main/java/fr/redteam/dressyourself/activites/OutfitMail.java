package main.java.fr.redteam.dressyourself.activites;

import main.java.fr.redteam.dressyourself.R;
import main.java.fr.redteam.dressyourself.R.layout;
import main.java.fr.redteam.dressyourself.R.menu;
import main.java.fr.redteam.dressyourself.common.DBHelper;
import main.java.fr.redteam.dressyourself.core.clothes.Outfit;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
/**
 * This activity is made in order to share by mail
 *
 */
public class OutfitMail extends Activity {

	private Button buttonEnvoyer;
	private TextView textDestinataire;
	private TextView textContenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.buttonEnvoyer =  (Button) findViewById(R.id.btnEnvoieMail);
		this.textDestinataire = (TextView) findViewById(R.id.editDestinataire);
		this.textContenu = (TextView) findViewById(R.id.editMail);
		setContentView(R.layout.activity_outfit_mail);
		/**
		 * define the click listener
		 */
		this.buttonEnvoyer.setOnClickListener(new OnClickListener() {

			        @Override
			        public void onClick(View v) {
			          // TODO redirected to the 'filters page'
			        	OutfitMail.this.creationMail();
			          Intent intent = new Intent(OutfitMail.this, ActivityMain.class);
			          startActivity(intent);
			        }
			      });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.outfit_mail, menu);
		return true;
	}
	
	/**
	 * function which made an mail intent in order to send it.
	 */
	
	public void creationMail()
	{

	} 

}
