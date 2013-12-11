package fr.redteam.dressyourself.activities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    assertEquals(clotheTest.getBrand(), textViewBrand.getText().toString());
  }

  @Test
  public void testTextViewBrandNotNull() {
    TextView textViewBrand = (TextView) activityClotheDetail.findViewById(R.id.brandTxt);
    assertTrue(null != textViewBrand.getText());
  }

  @Test
  public void testTextViewBrandNotEmpty() {
    TextView textViewBrand = (TextView) activityClotheDetail.findViewById(R.id.brandTxt);
    assertTrue(!textViewBrand.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewLabel
   */
  @Test
  public void testTextViewLabelValue() {
    TextView textViewModel = (TextView) activityClotheDetail.findViewById(R.id.modelTxt);
    assertEquals(clotheTest.getModel(), textViewModel.getText().toString());
  }

  @Test
  public void testTextViewLabelNotNull() {
    TextView textViewModel = (TextView) activityClotheDetail.findViewById(R.id.modelTxt);
    assertTrue(null != textViewModel.getText());
  }

  @Test
  public void testTextViewLabelNotEmpty() {
    TextView textViewModel = (TextView) activityClotheDetail.findViewById(R.id.modelTxt);
    assertTrue(!textViewModel.getText().toString().equals(""));
  }


  /*
   * Check the content of textViewLabel
   */
  @Test
  public void testTextViewColorValue() {
    TextView textViewColor = (TextView) activityClotheDetail.findViewById(R.id.colorTxt);
    assertEquals(clotheTest.getColor(), textViewColor.getText().toString());
  }

  @Test
  public void testTextViewColorNotNull() {
    TextView textViewColor = (TextView) activityClotheDetail.findViewById(R.id.colorTxt);
    assertTrue(null != textViewColor.getText());
  }

  @Test
  public void testTextViewColorNotEmpty() {
    TextView textViewColor = (TextView) activityClotheDetail.findViewById(R.id.colorTxt);
    assertTrue(!textViewColor.getText().toString().equals(""));
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
    assertEquals(WeatherTxt, textViewWeather.getText().toString());
  }

  @Test
  public void testTextViewWeatherNotNull() {
    TextView textViewWeather = (TextView) activityClotheDetail.findViewById(R.id.weatherTxt);
    assertTrue(null != textViewWeather.getText());
  }

  @Test
  public void testTextViewWeatherNotEmpty() {
    TextView textViewWeather = (TextView) activityClotheDetail.findViewById(R.id.weatherTxt);
    assertTrue(!textViewWeather.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewType
   */
  @Test
  public void TestTextViewTypeValue() {
    TextView textViewType = (TextView) activityClotheDetail.findViewById(R.id.typeTxt);
    assertEquals(clotheTest.getType(), textViewType.getText().toString());
  }

  @Test
  public void TestTextViewTypeNotNull() {
    TextView textViewType = (TextView) activityClotheDetail.findViewById(R.id.typeTxt);
    assertTrue(null != textViewType.getText());
  }

  @Test
  public void TestTextViewTypeNotEmpty() {
    TextView textViewType = (TextView) activityClotheDetail.findViewById(R.id.typeTxt);
    assertTrue(!textViewType.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewType
   */
  @Test
  public void TestTextViewBodyValue() {
    TextView textViewBody = (TextView) activityClotheDetail.findViewById(R.id.bodyTxt);
    assertEquals(clotheTest.getBodies(), textViewBody.getText().toString());
  }

  @Test
  public void TestTextViewBodyNotNull() {
    TextView textViewBody = (TextView) activityClotheDetail.findViewById(R.id.bodyTxt);
    assertTrue(null != textViewBody.getText());
  }

  @Test
  public void TestTextViewBodyNotEmpty() {
    TextView textViewBody = (TextView) activityClotheDetail.findViewById(R.id.bodyTxt);
    assertTrue(!textViewBody.getText().toString().equals(""));
  }
}
