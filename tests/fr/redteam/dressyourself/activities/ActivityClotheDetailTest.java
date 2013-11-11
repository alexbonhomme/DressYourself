package fr.redteam.dressyourself.activities;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class ActivityClotheDetailTest
    extends ActivityInstrumentationTestCase2<ActivityClotheDetail> {

  private ActivityClotheDetail activityClotheDetail;
  private TextView textViewBrand;
  private TextView textViewModel;
  private TextView textViewColor;
  private TextView textViewType;
  private TextView textViewWeather;
  private TextView textViewBody;
  private Clothe vetementTest;

  public ActivityClotheDetailTest(Class<ActivityClotheDetail> activityClass) {
    super(ActivityClotheDetail.class);
  }

  public ActivityClotheDetailTest() {
    super(ActivityClotheDetail.class);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();

    setActivityInitialTouchMode(false);

    this.createClothe();
    this.activityClotheDetail = getActivity();
    // Recup. du logo par exemple
    this.textViewBrand = (TextView) activityClotheDetail.findViewById(R.id.brandTxt);
    this.textViewModel = (TextView) activityClotheDetail.findViewById(R.id.modelTxt);
    this.textViewColor = (TextView) activityClotheDetail.findViewById(R.id.colorTxt);
    this.textViewWeather = (TextView) activityClotheDetail.findViewById(R.id.weatherTxt);
    this.textViewType = (TextView) activityClotheDetail.findViewById(R.id.typeTxt);
    this.textViewBody = (TextView) activityClotheDetail.findViewById(R.id.bodyTxt);
  }

  /**
   * Made a clothe test
   */
  public void createClothe() {
    List<String> weather = new ArrayList<String>();
    weather.add("Cloudy");
    weather.add("Rainy");
    this.vetementTest = new Clothe("Clothe test");
    this.vetementTest.setImage(new File("../res/drawable/echarpe_peche.jpg"));
    this.vetementTest.setWeather(weather);
    this.vetementTest.setBrand("Zara");
    this.vetementTest.setColor("RED");
    this.vetementTest.setType("pull");
    this.vetementTest.setBodies("body");

    Intent intent = new Intent();
    intent.putExtra("clothe", vetementTest);
    this.setActivityIntent(intent);
  }

  /*
   * Check the content of textViewBody
   */
  public void TestTextViewBrandValue() {
    assertEquals(textViewBrand.getText(), vetementTest.getBrand());
  }


  public void TestTextViewBrandNotNull() {
    assertFalse(this.textViewBrand.getText() == null);
  }

  public void TestTextViewBrandNotEmpty() {
    assertFalse(this.textViewBrand.getText().equals(""));
  }


  /*
   * Check the content of textViewLabel
   */
  public void TestTextViewLabelValue() {
    assertEquals(textViewModel.getText(), vetementTest.getModel());
  }

  public void TestTextViewLabelNotNull() {
    assertFalse(this.textViewModel.getText() == null);
  }

  public void TestTextViewLabelNotEmpty() {
    assertFalse(this.textViewModel.getText().equals(""));
  }


  /*
   * Check the content of textViewLabel
   */
  public void TestTextViewColorValue() {
    assertEquals(textViewColor.getText(), vetementTest.getColor());
  }

  public void TestTextViewColorNotNull() {
    assertFalse(this.textViewColor.getText() == null);
  }

  public void TestTextViewColorNotEmpty() {
    assertFalse(this.textViewColor.getText().equals(""));
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
    assertFalse(this.textViewWeather.getText() == null);
  }

  public void TestTextViewWeatherNotEmpty() {
    assertFalse(this.textViewWeather.getText().equals(""));
  }

  /*
   * Check the content of textViewType
   */
  public void TestTextViewTypeValue() {
    assertEquals(textViewType.getText(), vetementTest.getType());
  }

  public void TestTextViewTypeNotNull() {
    assertFalse(this.textViewType.getText() == null);
  }

  public void TestTextViewTypeNotEmpty() {
    assertFalse(this.textViewType.getText().equals(""));
  }

  /*
   * Check the content of textViewType
   */
  public void TestTextViewBodyValue() {
    assertEquals(textViewBody.getText(), vetementTest.getType());
  }

  public void TestTextViewBodyNotNull() {
    assertFalse(this.textViewBody.getText() == null);
  }

  public void TestTextViewBodyNotEmpty() {
    assertFalse(this.textViewBody.getText().equals(""));
  }
}
