package main.java.fr.redteam.dressyourself;

import main.java.fr.redteam.dressyourself.activites.ActivityClotheMail;
import main.java.fr.redteam.dressyourself.activites.ActivityOutfitMail;
import main.java.fr.redteam.dressyourself.core.api.APIShopSense;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ActivityDebug extends Activity {

  private Button debugAPI;
  private Button sendMailOutfit;
  private Button sendMailClothe;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_debug);

    debugAPI = (Button) findViewById(R.id.buttonDebugAPI);
    sendMailOutfit = (Button) findViewById(R.id.btnSendOutfitMail);
    sendMailClothe = (Button)  findViewById(R.id.btnEnvoieMailClothe);
    
    
    sendMailOutfit.setOnClickListener(new OnClickListener()
    {

		@Override
		public void onClick(View v) 
		{
			
			Intent intent = new Intent(ActivityDebug.this,	ActivityOutfitMail.class);
			intent.putExtra("idClothe", 1);
			startActivity(intent);
		}
	});
    
    sendMailClothe.setOnClickListener(new OnClickListener()
    {

		@Override
		public void onClick(View v) 
		{
			
			Intent intent = new Intent(ActivityDebug.this,	ActivityClotheMail.class);
			intent.putExtra("idClothe", 1);
			startActivity(intent);
		}
	});
    
    
    debugAPI.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        new APITesting().execute(new String());
      }
    });
    
    
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_debug, menu);
    return true;
  }

  /**
   * 
   * @author Alexandre Bonhomme
   * 
   */
  private class APITesting extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... param) {

      new APIShopSense();

      return null;
    }
  }

}
