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
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.api.APIZara;
import fr.redteam.dressyourself.core.clothes.Clothe;

/**
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

    // Capture de la recherche
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

  private void handleIntent(Intent intent) {

    if (!Intent.ACTION_SEARCH.equals(intent.getAction())) {
      return;
    }

    // Vérification de la connectivité
    ConnectivityManager cm =
        (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    if (!isConnected) {
      Toast.makeText(context, "No Internet connexion", Toast.LENGTH_SHORT).show();
      return;
    }

    String query = intent.getStringExtra(SearchManager.QUERY);
    new WebApiTask().execute(query); // Lance la recherche sur l'API distante
  }

  /**
   * 
   * @author Alexandre Bonhomme
   * 
   */
  private class WebApiTask extends AsyncTask<String, Void, List<Clothe>> {
    @Override
    protected List<Clothe> doInBackground(String... queries) {
      System.err.println("Query: " + queries[0]);

      APIZara api = new APIZara();
      List<Clothe> listClothes = api.findClothesByModelName(queries[0]);

      return listClothes;
    }

    @Override
    protected void onPostExecute(List<Clothe> listClothes) {
      List<String> listProductModels = new ArrayList<String>();

      for (Clothe clothe : listClothes) {
        listProductModels.add(clothe.getModel());
      }

      // TODO : better adapter with picture
      ArrayAdapter<String> adapter =
          new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,
              listProductModels);
      setListAdapter(adapter);
    }
  }

}
