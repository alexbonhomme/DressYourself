package main.java.fr.redteam.dressyourself.activities;

import java.util.ArrayList;
import java.util.List;

import main.java.fr.redteam.dressyourself.R;
import main.java.fr.redteam.dressyourself.common.DBHelper;
import main.java.fr.redteam.dressyourself.core.clothes.Clothe;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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

		    public void onClick(View v)
		    {
		    	// TODO redirected to the 'filters page'
		    	ActivityClotheMail.this.creationMail();
		    	Intent intent = new Intent(ActivityClotheMail.this, ActivityMain.class);
		    	startActivity(intent);
		    }
			});
	    }

	 public String[] ListDestinataire() 
	 {
			String txtDsc = this.textDestinataire.getText().toString();
			String[] DscList;
			int nbDst = 0;
			List<Integer> positionSeparateur = new ArrayList<Integer>();
			for (int i = 0; i < txtDsc.length(); i++) 
			{
			    if (txtDsc.charAt(i) == ';') 
			    {
			    	nbDst++;
			    	positionSeparateur.add(i);
			    }
			}

			if (nbDst > 0) 
			{
			    int depart = 0;
			    DscList = new String[nbDst + 1];
			    for (int i = 0; i < nbDst; i++)
			    {
			    	DscList[i] = txtDsc.substring(depart, positionSeparateur.get(i));
			    	depart = positionSeparateur.get(i) + 1;
			    }
			    DscList[nbDst] = txtDsc.substring(positionSeparateur.get(nbDst - 1) + 1);
			}
			else 
			{
			    DscList = new String[1];
			    DscList[0] = txtDsc;
			}
			return DscList;
	}

    /**
     * function which made an mail intent in order to send it.
     */

	 public void creationMail() 
    {
		Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
		Clothe vetement;
		/**
		 * get the key of outfit which was passed in parameter.
		 */
		Bundle b = getIntent().getExtras();
		int cleTenue = b.getInt("idClothe");

		vetement = new DBHelper(this).getClothe(cleTenue);

		/*Set the type of the mail*/
		emailIntent.setType("image/png");
		/* add destinaire*/
		emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,this.ListDestinataire());
		/*Add the subject for the mail*/
		emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Partage d'une tenue");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,this.textContenu.getText().toString()+"\n");
		/*Add text*/
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,"j'ai trouvé cette tenue et je pense qu'elle va te plaire.\n");
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,"Ma tenue contient les pieces suivantes.\n");
		/* Made the body of mail */
		emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,"un(e) "+vetement.getType()+
		" de la marque "+vetement.getBrand()+" et de couleur "+vetement.getColor()+" "+vetement.getModel()+".\n");
		emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" +vetement.getImageUrl()));		
			
		startActivity(Intent.createChooser(emailIntent,"Choisissez un client mail"));
		
	 }

}
