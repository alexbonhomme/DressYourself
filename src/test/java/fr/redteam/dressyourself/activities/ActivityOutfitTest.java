package fr.redteam.dressyourself.activities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.redteam.dressyourself.R;

@RunWith(RobolectricTestRunner.class)
public class ActivityOutfitTest {

  ActivityOutfit activityOutfit;

  @Before
  public void setUp() {
    activityOutfit = Robolectric.buildActivity(ActivityOutfit.class).create().visible().get();
  }

  @Test
  public void testButtonRefreshTop() throws Exception {
    Button buttonRefreshTop = (Button) activityOutfit.findViewById(R.id.button_refresh_top);
    ImageView imageViewTop = (ImageView) activityOutfit.findViewById(R.id.imageview_top);
    TextView textViewTop = (TextView) activityOutfit.findViewById(R.id.textview_top);
    assertTrue(buttonRefreshTop != null);
    assertTrue(imageViewTop != null);
    assertTrue(textViewTop != null);
    String oldTextTop = (String) textViewTop.getText();
    buttonRefreshTop.performClick();
    assertFalse(textViewTop.getText().equals(oldTextTop));
  }

  @Test
  public void testButtonRefreshBottom() {
    Button buttonRefreshBottom = (Button) activityOutfit.findViewById(R.id.button_refresh_bottom);
    ImageView imageViewBottom = (ImageView) activityOutfit.findViewById(R.id.imageview_bottom);
    TextView textViewBottom = (TextView) activityOutfit.findViewById(R.id.textview_bottom);
    assertTrue(buttonRefreshBottom != null);
    assertTrue(imageViewBottom != null);
    assertTrue(textViewBottom != null);
    String oldTextBottom = (String) textViewBottom.getText();
    buttonRefreshBottom.performClick();
    assertFalse(textViewBottom.getText().equals(oldTextBottom));
  }

  @Test
  public void testButtonRefreshFeet() {
    Button buttonRefreshFeet = (Button) activityOutfit.findViewById(R.id.button_refresh_feet);
    ImageView imageViewFeet = (ImageView) activityOutfit.findViewById(R.id.imageview_feet);
    TextView textViewFeet = (TextView) activityOutfit.findViewById(R.id.textview_feet);
    assertTrue(buttonRefreshFeet != null);
    assertTrue(imageViewFeet != null);
    assertTrue(textViewFeet != null);
    String oldTextFeet = (String) textViewFeet.getText();
    buttonRefreshFeet.performClick();
    assertFalse(textViewFeet.getText().equals(oldTextFeet));
  }
}