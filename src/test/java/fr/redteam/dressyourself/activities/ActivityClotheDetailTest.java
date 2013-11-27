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

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.clothes.Clothe;

@RunWith(RobolectricTestRunner.class)
public class ActivityClotheDetailTest {
  private ActivityClotheDetail activityClotheDetail;
  private TextView textViewBrand;
  private TextView textViewModel;
  private TextView textViewColor;
  private TextView textViewType;
  private TextView textViewWeather;
  private TextView textViewBody;
  private Clothe clotheTest;

  @Before
  protected void setUp() throws Exception {

    /* getting context */
    Context context = Robolectric.getShadowApplication().getApplicationContext();


    Intent intent = this.createClothe();
    this.activityClotheDetail =
        Robolectric.buildActivity(ActivityClotheDetail.class).withIntent(intent).create().get();
    // Recup. du logo par exemple
    this.textViewBrand = (TextView) activityClotheDetail.findViewById(R.id.brandTxt);
    this.textViewModel = (TextView) activityClotheDetail.findViewById(R.id.modelTxt);
    this.textViewColor = (TextView) activityClotheDetail.findViewById(R.id.colorTxt);
    this.textViewWeather = (TextView) activityClotheDetail.findViewById(R.id.weatherTxt);
    this.textViewType = (TextView) activityClotheDetail.findViewById(R.id.typeTxt);
    this.textViewBody = (TextView) activityClotheDetail.findViewById(R.id.bodyTxt);
    this.clotheTest = (Clothe) intent.getSerializableExtra("clothe");
  }

  /**
   * Made a clothe test
   * 
   * @throws FileNotFoundException
   */
  public Intent createClothe() throws FileNotFoundException {
    List<String> weather = new ArrayList<String>();
    weather.add("Cloudy");
    weather.add("Rainy");
    System.out.println();
    this.clotheTest = new Clothe("Clothe test");
    // this.vetementTest.setImage(new FileInputStream(new
    // File("../res/drawable/echarpe_peche.jpg")));
    this.clotheTest.setWeather(weather);
    this.clotheTest.setBrand("Zara");
    this.clotheTest.setColor("RED");
    this.clotheTest.setType("pull");
    this.clotheTest.setBodies("body");

    Intent intent = new Intent();
    intent.putExtra("clothe", clotheTest);
    return intent;
  }

  /*
   * Check the content of textViewBody
   */
  @Test
  public void testTextViewBrandValue() {
    assertEquals(textViewBrand.getText().toString(), clotheTest.getBrand());
  }

  @Test
  public void testTextViewBrandNotNull() {
    assertFalse(this.textViewBrand.getText() == null);
  }

  @Test
  public void testTextViewBrandNotEmpty() {
    assertFalse(this.textViewBrand.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewLabel
   */
  @Test
  public void testTextViewLabelValue() {
    assertEquals(textViewModel.getText().toString(), clotheTest.getModel());
  }

  @Test
  public void testTextViewLabelNotNull() {
    assertFalse(this.textViewModel.getText() == null);
  }

  @Test
  public void testTextViewLabelNotEmpty() {
    assertFalse(this.textViewModel.getText().toString().equals(""));
  }


  /*
   * Check the content of textViewLabel
   */
  @Test
  public void testTextViewColorValue() {
    assertEquals(textViewColor.getText().toString(), clotheTest.getColor());
  }

  @Test
  public void testTextViewColorNotNull() {
    assertFalse(this.textViewColor.getText() == null);
  }

  @Test
  public void testTextViewColorNotEmpty() {
    assertFalse(this.textViewColor.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewWeather
   */
  @Test
  public void testTextViewWeatherValue() {
    List<String> TheWeather = clotheTest.getWeather();
    String WeatherTxt = "";
    for (String weatherLine : TheWeather) {
      WeatherTxt += weatherLine + " ";
    }
    assertEquals(this.textViewWeather.getText().toString(), WeatherTxt);
  }

  @Test
  public void testTextViewWeatherNotNull() {
    assertFalse(this.textViewWeather.getText() == null);
  }

  @Test
  public void testTextViewWeatherNotEmpty() {
    assertFalse(this.textViewWeather.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewType
   */
  @Test
  public void TestTextViewTypeValue() {
    assertEquals(textViewType.getText().toString(), clotheTest.getType());
  }

  @Test
  public void TestTextViewTypeNotNull() {
    assertFalse(this.textViewType.getText() == null);
  }

  @Test
  public void TestTextViewTypeNotEmpty() {
    assertFalse(this.textViewType.getText().toString().equals(""));
  }

  /*
   * Check the content of textViewType
   */
  @Test
  public void TestTextViewBodyValue() {
    assertEquals(textViewBody.getText().toString(), clotheTest.getType());
  }

  @Test
  public void TestTextViewBodyNotNull() {
    assertFalse(this.textViewBody.getText() == null);
  }

  @Test
  public void TestTextViewBodyNotEmpty() {
    assertFalse(this.textViewBody.getText().toString().equals(""));
  }
}
