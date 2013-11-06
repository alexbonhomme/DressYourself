package fr.redteam.dressyourself.activities;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.activities.ActivityOutfit;

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
 (Button) mActivity.findViewById(R.id.generate_button);
    buttonRefreshTop =
 (Button) mActivity.findViewById(R.id.top_refresh_button);
    buttonRefreshBottom =
 (Button) mActivity.findViewById(R.id.bottom_refresh_button);
    buttonRefreshFeet =
 (Button) mActivity.findViewById(R.id.feet_refresh_button);
    imageViewTop =
 (ImageView) mActivity.findViewById(R.id.top_image);
    imageViewBottom =
 (ImageView) mActivity.findViewById(R.id.bottom_image);
    imageViewFeet =
 (ImageView) mActivity.findViewById(R.id.feet_image);
    textViewTop =
 (TextView) mActivity.findViewById(R.id.top_text);
    textViewBottom =
 (TextView) mActivity.findViewById(R.id.bottom_text);
    textViewFeet =
 (TextView) mActivity.findViewById(R.id.feet_text);
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
    assertTrue(textViewTop.getText() != oldTextTop);
  }

  @UiThreadTest
  public void testButtonRefreshBottom() {
    String oldTextBottom = (String) textViewBottom.getText();
    this.buttonRefreshBottom.performClick();
    assertTrue(textViewBottom.getText() != oldTextBottom);
  }

  @UiThreadTest
  public void testButtonRefreshFeet() {
    String oldTextFeet = (String) textViewFeet.getText();
    this.buttonRefreshFeet.performClick();
    assertTrue(textViewFeet.getText() != oldTextFeet);
  }
}
