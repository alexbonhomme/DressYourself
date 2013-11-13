package fr.redteam.dressyourself.activities;

import java.io.FileNotFoundException;
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
   * 
   * @throws FileNotFoundException
   */
  public void createClothe() throws FileNotFoundException {
    List<String> weather = new ArrayList<String>();
    weather.add("Cloudy");
    weather.add("Rainy");
    System.out.println();
    this.vetementTest = new Clothe("Clothe test");
    // this.vetementTest.setImage(new FileInputStream(new
    // File("../res/drawable/echarpe_peche.jpg")));
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
  public void testTextViewBrandValue() {
    assertEquals(textViewBrand.getText().toString(), vetementTest.getBrand());
  }


  public void testTextViewBrandNotNull() {
    assertFalse(this.textViewBrand.getText() == null);
  }

  public void testTextViewBrandNotEmpty() {
    assertFalse(this.textViewBrand.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewLabel
   */
  public void testTextViewLabelValue() {
    assertEquals(textViewModel.getText().toString(), vetementTest.getModel());
  }

  public void testTextViewLabelNotNull() {
    assertFalse(this.textViewModel.getText() == null);
  }

  public void testTextViewLabelNotEmpty() {
    assertFalse(this.textViewModel.getText().toString().equals(""));
  }


  /*
   * Check the content of textViewLabel
   */
  public void testTextViewColorValue() {
    assertEquals(textViewColor.getText().toString(), vetementTest.getColor());
  }

  public void testTextViewColorNotNull() {
    assertFalse(this.textViewColor.getText() == null);
  }

  public void testTextViewColorNotEmpty() {
    assertFalse(this.textViewColor.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewWeather
   */
  public void testTextViewWeatherValue() {
    List<String> TheWeather = vetementTest.getWeather();
    String WeatherTxt = "";
    for (String weatherLine : TheWeather) {
      WeatherTxt += weatherLine + " ";
    }
    assertEquals(this.textViewWeather.getText().toString(), WeatherTxt);
  }

  public void testTextViewWeatherNotNull() {
    assertFalse(this.textViewWeather.getText() == null);
  }

  public void testTextViewWeatherNotEmpty() {
    assertFalse(this.textViewWeather.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewType
   */
  public void TestTextViewTypeValue() {
    assertEquals(textViewType.getText().toString(), vetementTest.getType());
  }

  public void TestTextViewTypeNotNull() {
    assertFalse(this.textViewType.getText() == null);
  }

  public void TestTextViewTypeNotEmpty() {
    assertFalse(this.textViewType.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewType
   */
  public void TestTextViewBodyValue() {
    assertEquals(textViewBody.getText().toString(), vetementTest.getType());
  }

  public void TestTextViewBodyNotNull() {
    assertFalse(this.textViewBody.getText() == null);
  }

  public void TestTextViewBodyNotEmpty() {
    assertFalse(this.textViewBody.getText().toString().equals(""));
  }
}
