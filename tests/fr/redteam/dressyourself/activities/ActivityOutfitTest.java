package fr.redteam.dressyourself.activities;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.redteam.dressyourself.R;

public class ActivityOutfitTest extends ActivityInstrumentationTestCase2<ActivityOutfit> {

  private ActivityOutfit mActivity;
  private Button buttonGenerate;
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
    buttonGenerate =
 (Button) mActivity.findViewById(R.id.button_generate);
    buttonRefreshTop =
 (Button) mActivity.findViewById(R.id.button_refresh_top);
    buttonRefreshBottom =
 (Button) mActivity.findViewById(R.id.button_refresh_bottom);
    buttonRefreshFeet =
 (Button) mActivity.findViewById(R.id.button_refresh_feet);
    imageViewTop =
 (ImageView) mActivity.findViewById(R.id.imageview_top);
    imageViewBottom =
 (ImageView) mActivity.findViewById(R.id.imageview_bottom);
    imageViewFeet =
 (ImageView) mActivity.findViewById(R.id.imageview_feet);
    textViewTop =
 (TextView) mActivity.findViewById(R.id.textview_top);
    textViewBottom =
 (TextView) mActivity.findViewById(R.id.textview_bottom);
    textViewFeet =
 (TextView) mActivity.findViewById(R.id.textview_feet);
  }

  public void testPreConditions() {
    assertTrue(buttonGenerate != null);
    assertTrue(buttonRefreshTop != null);
    assertTrue(buttonRefreshBottom != null);
    assertTrue(buttonRefreshFeet != null);
    assertTrue(imageViewTop != null);
    assertTrue(imageViewBottom != null);
    assertTrue(imageViewFeet != null);
    assertTrue(textViewTop != null);
    assertTrue(textViewBottom != null);
    assertTrue(textViewFeet != null);
  }

  @UiThreadTest
  public void testButtonRefreshTop() {
    String oldTextTop = (String) textViewTop.getText();
    this.buttonRefreshTop.performClick();
    assertFalse(textViewTop.getText().equals(oldTextTop));
  }

  @UiThreadTest
  public void testButtonRefreshBottom() {
    String oldTextBottom = (String) textViewBottom.getText();
    this.buttonRefreshBottom.performClick();
    assertFalse(textViewBottom.getText().equals(oldTextBottom));
  }

  @UiThreadTest
  public void testButtonRefreshFeet() {
    String oldTextFeet = (String) textViewFeet.getText();
    this.buttonRefreshFeet.performClick();
    assertFalse(textViewFeet.getText().equals(oldTextFeet));
  }
}
