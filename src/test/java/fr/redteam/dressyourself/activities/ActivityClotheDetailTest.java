package fr.redteam.dressyourself.activities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.clothes.Clothe;

@RunWith(RobolectricTestRunner.class)
public class ActivityClotheDetailTest {
  private ActivityClotheDetail activityClotheDetail;
  private Clothe clotheTest;

  @Before
  public void setUp() throws Exception {

    this.createClothe();
    Intent intent = this.createIntent();
    this.activityClotheDetail =
        Robolectric.buildActivity(ActivityClotheDetail.class).withIntent(intent).create().visible()
            .get();
  }

  /*
   * Made a intent of class
   * 
   * @throws FileNotFoundException
   */
  public Intent createIntent() throws FileNotFoundException {
    Intent intent =
        new Intent(Robolectric.getShadowApplication().getApplicationContext(),
            ActivityClotheDetail.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("clothe", this.clotheTest);
    intent.putExtras(bundle);
    return intent;
  }

  /*
   * Made a the clothe
   */
  public void createClothe() throws FileNotFoundException {

    List<String> weather = new ArrayList<String>();
    weather.add("Cloudy");
    weather.add("Rainy");
    Clothe myClotheTest = new Clothe("Clothe test");
    myClotheTest.setWeather(weather);
    myClotheTest.setBrand("Zara");
    myClotheTest.setColor("RED");
    myClotheTest.setType("pull");
    myClotheTest.setBodies("body");
    this.clotheTest = myClotheTest;
  }

  /*
   * Check the content of textViewBody
   */
  @Test
  public void testTextViewBrandValue() {
    TextView textViewBrand = (TextView) activityClotheDetail.findViewById(R.id.brandTxt);
    assertEquals(textViewBrand.getText().toString(), clotheTest.getBrand());
  }

  @Test
  public void testTextViewBrandNotNull() {
    TextView textViewBrand = (TextView) activityClotheDetail.findViewById(R.id.brandTxt);
    assertFalse(textViewBrand.getText() == null);
  }

  @Test
  public void testTextViewBrandNotEmpty() {
    TextView textViewBrand = (TextView) activityClotheDetail.findViewById(R.id.brandTxt);
    assertFalse(textViewBrand.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewLabel
   */
  @Test
  public void testTextViewLabelValue() {
    TextView textViewModel = (TextView) activityClotheDetail.findViewById(R.id.modelTxt);
    assertEquals(textViewModel.getText().toString(), clotheTest.getModel());
  }

  @Test
  public void testTextViewLabelNotNull() {
    TextView textViewModel = (TextView) activityClotheDetail.findViewById(R.id.modelTxt);
    assertFalse(textViewModel.getText() == null);
  }

  @Test
  public void testTextViewLabelNotEmpty() {
    TextView textViewModel = (TextView) activityClotheDetail.findViewById(R.id.modelTxt);
    assertFalse(textViewModel.getText().toString().equals(""));
  }


  /*
   * Check the content of textViewLabel
   */
  @Test
  public void testTextViewColorValue() {
    TextView textViewColor = (TextView) activityClotheDetail.findViewById(R.id.colorTxt);
    assertEquals(textViewColor.getText().toString(), clotheTest.getColor());
  }

  @Test
  public void testTextViewColorNotNull() {
    TextView textViewColor = (TextView) activityClotheDetail.findViewById(R.id.colorTxt);
    assertFalse(textViewColor.getText() == null);
  }

  @Test
  public void testTextViewColorNotEmpty() {
    TextView textViewColor = (TextView) activityClotheDetail.findViewById(R.id.colorTxt);
    assertFalse(textViewColor.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewWeather
   */
  @Test
  public void testTextViewWeatherValue() {
    TextView textViewWeather = (TextView) activityClotheDetail.findViewById(R.id.weatherTxt);
    List<String> TheWeather = clotheTest.getWeather();
    String WeatherTxt = "";
    for (String weatherLine : TheWeather) {
      WeatherTxt += weatherLine + " ";
    }
    assertEquals(textViewWeather.getText().toString(), WeatherTxt);
  }

  @Test
  public void testTextViewWeatherNotNull() {
    TextView textViewWeather = (TextView) activityClotheDetail.findViewById(R.id.weatherTxt);
    assertFalse(textViewWeather.getText() == null);
  }

  @Test
  public void testTextViewWeatherNotEmpty() {
    TextView textViewWeather = (TextView) activityClotheDetail.findViewById(R.id.weatherTxt);
    assertFalse(textViewWeather.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewType
   */
  @Test
  public void TestTextViewTypeValue() {
    TextView textViewType = (TextView) activityClotheDetail.findViewById(R.id.typeTxt);
    assertEquals(textViewType.getText().toString(), clotheTest.getType());
  }

  @Test
  public void TestTextViewTypeNotNull() {
    TextView textViewType = (TextView) activityClotheDetail.findViewById(R.id.typeTxt);
    assertFalse(textViewType.getText() == null);
  }

  @Test
  public void TestTextViewTypeNotEmpty() {
    TextView textViewType = (TextView) activityClotheDetail.findViewById(R.id.typeTxt);
    assertFalse(textViewType.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewType
   */
  @Test
  public void TestTextViewBodyValue() {
    TextView textViewBody = (TextView) activityClotheDetail.findViewById(R.id.bodyTxt);
    assertEquals(textViewBody.getText().toString(), clotheTest.getBodies());
  }

  @Test
  public void TestTextViewBodyNotNull() {
    TextView textViewBody = (TextView) activityClotheDetail.findViewById(R.id.bodyTxt);
    assertFalse(textViewBody.getText() == null);
  }

  @Test
  public void TestTextViewBodyNotEmpty() {
    TextView textViewBody = (TextView) activityClotheDetail.findViewById(R.id.bodyTxt);
    assertFalse(textViewBody.getText().toString().equals(""));
  }
}
