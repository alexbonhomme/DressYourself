package fr.redteam.dressyourself.activities;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.app.SearchManager;
import android.content.Context;

/**
 * 
 * @author Alexandre Bonhomme
 * 
 */
@RunWith(RobolectricTestRunner.class)
public class ActivitySearchEngineTest {

  ActivitySearchEngine searchActivity;
  Context context;

  @Before
  public void setUp() {
    searchActivity = Robolectric.buildActivity(ActivitySearchEngine.class).create().visible().get();
    context = Robolectric.getShadowApplication().getApplicationContext();
  }

  @Test
  public void testSearchManager() throws Exception {
    SearchManager searchManager = (SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
    assertNotNull(searchManager);
  }
}