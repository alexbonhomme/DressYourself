package fr.redteam.dressyourself.activities;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.redteam.dressyourself.R;

public class ActivityOutfitTest extends ActivityInstrumentationTestCase2<ActivityOutfit> {

  private ActivityOutfit mActivity;
  private Button buttonRefreshTop;
  private Button buttonRefreshBottom;
  private Button buttonRefreshFeet;
  private ImageView imageViewTop;
  private ImageView imageViewBottom;
  private ImageView imageViewFeet;
  private TextView textViewTop;
  private TextView textViewBottom;
  private TextView textViewFeet;

  public ActivityOutfitTest(Class<ActivityOutfit> activityClass) {
    super(activityClass);
  }

  public ActivityOutfitTest() {
    super(ActivityOutfit.class);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    setActivityInitialTouchMode(false);
    mActivity = getActivity();
  }

  @UiThreadTest
  public void testButtonRefreshTop() {
    buttonRefreshTop = (Button) mActivity.findViewById(R.id.button_refresh_top);
    imageViewTop = (ImageView) mActivity.findViewById(R.id.imageview_top);
    textViewTop = (TextView) mActivity.findViewById(R.id.textview_top);
    assertTrue(buttonRefreshTop != null);
    assertTrue(imageViewTop != null);
    assertTrue(textViewTop != null);
    String oldTextTop = (String) textViewTop.getText();
    this.buttonRefreshTop.performClick();
    assertFalse(textViewTop.getText().equals(oldTextTop));
  }

  @UiThreadTest
  public void testButtonRefreshBottom() {
    buttonRefreshBottom = (Button) mActivity.findViewById(R.id.button_refresh_bottom);
    imageViewBottom = (ImageView) mActivity.findViewById(R.id.imageview_bottom);
    textViewBottom = (TextView) mActivity.findViewById(R.id.textview_bottom);
    assertTrue(buttonRefreshBottom != null);
    assertTrue(imageViewBottom != null);
    assertTrue(textViewBottom != null);
    String oldTextBottom = (String) textViewBottom.getText();
    this.buttonRefreshBottom.performClick();
    assertFalse(textViewBottom.getText().equals(oldTextBottom));
  }

  @UiThreadTest
  public void testButtonRefreshFeet() {
    buttonRefreshFeet = (Button) mActivity.findViewById(R.id.button_refresh_feet);
    imageViewFeet = (ImageView) mActivity.findViewById(R.id.imageview_feet);
    textViewFeet = (TextView) mActivity.findViewById(R.id.textview_feet);
    assertTrue(buttonRefreshFeet != null);
    assertTrue(imageViewFeet != null);
    assertTrue(textViewFeet != null);
    String oldTextFeet = (String) textViewFeet.getText();
    this.buttonRefreshFeet.performClick();
    assertFalse(textViewFeet.getText().equals(oldTextFeet));
  }
}
