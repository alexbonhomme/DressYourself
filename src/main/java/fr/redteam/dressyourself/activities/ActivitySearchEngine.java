package fr.redteam.dressyourself.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SearchView;
import android.widget.Toast;
import fr.redteam.dressyourself.R;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
public class ActivitySearchEngine extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

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

    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
      String query = intent.getStringExtra(SearchManager.QUERY);

      // On lance la recherche
      doSearch(query);
    }
  }

  /**
   * 
   * @param query
   */
  private void doSearch(String query) {
    // TODO Implement
    Toast.makeText(this, "Query: " + query, Toast.LENGTH_SHORT).show();
  }

}
