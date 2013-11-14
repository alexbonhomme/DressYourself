package fr.redteam.dressyourself.activities;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.widget.Button;
import fr.redteam.dressyourself.R;

@RunWith(RobolectricTestRunner.class)
public class MyActivityTest {

  ActivityMain mainActivity;

  @Before
  public void setUp() {
    // Default response for all http requests
    Robolectric.setDefaultHttpResponse(200, "OK");
  }

  @Test
  public void thisTestWorks() throws Exception {
    mainActivity = Robolectric.buildActivity(ActivityMain.class).create().visible().get();
    Button btAdd = (Button) mainActivity.findViewById(R.id.btAddClothing);

    assertEquals("Add new clothes", btAdd.getText());
  }
  
  @Test
  public void thisTestFail() throws Exception {
    mainActivity = Robolectric.buildActivity(ActivityMain.class).create().visible().get();
    Button btAdd = (Button) mainActivity.findViewById(R.id.btAddClothing);

    assertEquals("Add new lothes", btAdd.getText());
  }
}