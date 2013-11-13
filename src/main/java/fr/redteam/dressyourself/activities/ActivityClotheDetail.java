package fr.redteam.dressyourself.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class ActivityClotheDetail extends Activity {

  private TextView textViewBrand;
  private TextView textViewLabel;
  private TextView textViewColor;
  private TextView textViewType;
  private TextView textViewWeather;
  private TextView textViewBody;
  private ImageView ImagePhoto;
  private Button btnModify;
  private Clothe myClothe;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_clothe_detail);

    // Show the Up button in the action bar.
    // getActionBar().setDisplayHomeAsUpEnabled(true);

    // add onclick action for the button in order to put the object cloth and call the modification
    // page.
    this.btnModify = (Button) findViewById(R.id.modifyBtn);
    btnModify.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ActivityClotheDetail.this, ActivityClotheModify.class);
        intent.putExtra("clothe", myClothe);
        startActivity(intent);
      }
    });

    this.initMyClothe();
    this.initTextView();
    this.initImageView();
  }

  /*
   * init cloth object
   */
  public void initMyClothe() {
    Intent MyIntent = getIntent();
    if (MyIntent != null) this.myClothe = (Clothe) MyIntent.getSerializableExtra("clothe");
  }

  public void initImageView() {
    this.ImagePhoto = (ImageView) findViewById(R.id.photo);

    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inSampleSize = 2;
    Bitmap bm = BitmapFactory.decodeStream(myClothe.getImage(), null, options);
    this.ImagePhoto.setImageBitmap(bm);
  }

  /*
   * Init all textviews
   */
  public void initTextView() {

    /*
     * make reference on the activity object
     */

    this.textViewBrand = (TextView) findViewById(R.id.brandTxt);
    this.textViewLabel = (TextView) findViewById(R.id.modelTxt);
    this.textViewColor = (TextView) findViewById(R.id.colorTxt);
    this.textViewWeather = (TextView) findViewById(R.id.weatherTxt);
    this.textViewType = (TextView) findViewById(R.id.typeTxt);
    this.textViewBody = (TextView) findViewById(R.id.bodyTxt);
    /*
     * Put all information in the text field
     */

    this.textViewBrand.setText(this.myClothe.getBrand());
    this.textViewLabel.setText(this.myClothe.getModel());
    this.textViewColor.setText(this.myClothe.getColor());
    this.textViewType.setText(this.myClothe.getType());
    this.textViewBody.setText(this.myClothe.getBodies());
    this.textViewWeather.setText(this.getStringWeather());
  }

  /*
   * Method which made the text of wather
   */
  public String getStringWeather() {
    /*
     * List all weather information and contact with ';'
     */
    List<String> TheWeather = this.myClothe.getWeather();
    String WeatherTxt = "";
    for (String weatherLine : TheWeather) {
      WeatherTxt += weatherLine + " ";
    }
    return WeatherTxt;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        NavUtils.navigateUpTo(this, new Intent(this, ActivityClotheList.class));
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
