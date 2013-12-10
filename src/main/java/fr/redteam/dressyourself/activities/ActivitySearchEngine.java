package fr.redteam.dressyourself.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.adapters.AdapterClothes;
import fr.redteam.dressyourself.common.database.DBHelper;
import fr.redteam.dressyourself.common.filemanager.AndroidFileManager;
import fr.redteam.dressyourself.core.api.APIZara;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.views.ListViewClothes;

/**
 * This class is in charge of the download and the adding of Clothes from the API to the local
 * database.
 * 
 * @author Alexandre Bonhomme
 * 
 */
public class ActivitySearchEngine extends ListActivity {

  private Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    context = getApplicationContext();

    // Capture of the research
    handleIntent(getIntent());
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_search_engine, menu);

    // Associate searchable configuration with the SearchView
    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    searchView.setIconified(false);

    return true;
  }

  @Override
  protected void onNewIntent(Intent intent) {
    handleIntent(intent);
  }

  @Override
  protected void onListItemClick(ListView l, View v, int position, long id) {
    ListViewClothes productView = (ListViewClothes) v;
    Clothe product = productView.getProduct();
    
    DBHelper db = new DBHelper(context);
    db.open();
    db.insertClothes(product);
    db.close();
    
    Toast.makeText(context, product.getModel() + " was added to your closet !", Toast.LENGTH_SHORT)
        .show();
  }

  private void handleIntent(Intent intent) {
    if (!Intent.ACTION_SEARCH.equals(intent.getAction())) {
      return;
    }

    // Verification of connectivity
    ConnectivityManager cm =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    if (!isConnected) {
      Toast.makeText(context, "No Internet connexion", Toast.LENGTH_SHORT).show();
      return;
    }

    String query = intent.getStringExtra(SearchManager.QUERY);

    // Run the research with the remote API
    new WebApiTask().execute(query);
  }

  /**
   * This class has the responsibility of getting Clothes from the API
   */
  private class WebApiTask extends AsyncTask<String, Void, List<Clothe>> {
    @Override
    protected List<Clothe> doInBackground(String... queries) {
      APIZara api = new APIZara(new AndroidFileManager(context));
      List<Clothe> listClothes = api.findClothesByModel(queries[0]);

      return listClothes;
    }

    @Override
    protected void onPostExecute(List<Clothe> listClothes) {
      List<String> listProductModels = new ArrayList<String>();

      for (Clothe clothe : listClothes) {
        listProductModels.add(clothe.getModel());
      }

      AdapterClothes adapter = new AdapterClothes(context, listClothes);
      setListAdapter(adapter);
    }
  }

}
