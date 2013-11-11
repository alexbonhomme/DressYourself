package fr.redteam.dressyourself.activities;

import java.util.List;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.common.DBHelper;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class ActivityClotheDetailTest
    extends ActivityInstrumentationTestCase2<ActivityClotheDetail> {

  private ActivityClotheDetail activityClotheDetail;
  private TextView textViewBrand;
  private TextView textViewModel;
  private TextView textViewColor;
  private TextView textViewType;
  private TextView textViewWeather;
  private Clothe vetementTest;

  public ActivityClotheDetailTest(Class<ActivityClotheDetail> activityClass) {
    super(ActivityClotheDetail.class);
    this.vetementTest = new DBHelper(this.activityClotheDetail).getClothe(1);
  }

  public ActivityClotheDetailTest() {
    super(ActivityClotheDetail.class);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    setActivityInitialTouchMode(false);
    activityClotheDetail = getActivity();
    // Recup. du logo par exemple
    this.textViewBrand = (TextView) activityClotheDetail.findViewById(R.id.bodyTxt);
    this.textViewModel = (TextView) activityClotheDetail.findViewById(R.id.LabelTxt);
    this.textViewColor = (TextView) activityClotheDetail.findViewById(R.id.colorTxt);
    this.textViewWeather = (TextView) activityClotheDetail.findViewById(R.id.weatherTxt);
    this.textViewType = (TextView) activityClotheDetail.findViewById(R.id.TypeTxt);
  }

  /*
   * Check the content of textViewBody
   */
  public void TestTextViewBodyValue() {
    assertEquals(textViewBrand.getText(), vetementTest.getBrand());
  }


  public void TestTextViewBodyNotNull() {
    assertFalse(this.textViewBrand.getText() == null || this.textViewBrand.getText().equals(""));
  }


  /*
   * Check the content of textViewLabel
   */
  public void TestTextViewLabelValue() {
    assertEquals(textViewModel.getText(), vetementTest.getModel());
  }

  public void TestTextViewLabelNotNull() {
    assertFalse(this.textViewModel.getText() == null || this.textViewModel.getText().equals(""));
  }

  /*
   * Check the content of textViewLabel
   */
  public void TestTextViewColorValue() {
    assertEquals(textViewColor.getText(), vetementTest.getColor());
  }

  public void TestTextViewColorNotNull() {
    assertFalse(this.textViewColor.getText() == null || this.textViewColor.getText().equals(""));
  }

  /*
   * Check the content of textViewWeather
   */
  public void TestTextViewWeatherValue() {
    List<String> TheWeather = vetementTest.getWeather();
    String WeatherTxt = "";
    for (String weatherLine : TheWeather) {
      WeatherTxt += weatherLine + " ";
    }
    assertEquals(this.textViewWeather.getText(), WeatherTxt);
  }

  public void TestTextViewWeatherNotNull() {
    assertFalse(this.textViewWeather.getText() == null || this.textViewWeather.getText().equals(""));
  }

  /*
   * Check the content of textViewType
   */
  public void TestTextViewTypeValue() {
    assertEquals(textViewType.getText(), vetementTest.getType());
  }

  public void TestTextViewTypeNotNull() {
    assertFalse(this.textViewType.getText() == null || this.textViewType.getText().equals(""));
  }


}
