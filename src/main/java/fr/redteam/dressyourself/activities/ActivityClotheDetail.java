package fr.redteam.dressyourself.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class ActivityClotheDetail extends Activity {

  private ImageView imagePhoto;
  private Clothe myClothe;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_clothe_detail);

    // Show the Up button in the action bar.
    // add onclick action for the button in order to put the object cloth and call the modification
    // page.
    Button btnModify = (Button) findViewById(R.id.modifyBtn);
    btnModify.setOnClickListener(new OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent intent = new Intent(ActivityClotheDetail.this, ActivityClotheModify.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("clothe", myClothe);
        intent.putExtras(bundle);
        startActivity(intent);
        ActivityClotheDetail.this.finish();
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
    Intent myIntent = getIntent();
    if (myIntent != null) {
      this.myClothe = (Clothe) myIntent.getSerializableExtra("clothe");
    }
  }

  public void initImageView() {
    this.imagePhoto = (ImageView) findViewById(R.id.photo);

    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inSampleSize = 2;
    Bitmap bm = BitmapFactory.decodeStream(myClothe.getImage(), null, options);
    this.imagePhoto.setImageBitmap(bm);
  }

  /*
   * Init all textviews
   */
  public void initTextView() {

    /*
     * make reference on the activity object
     */

    TextView textViewBrand = (TextView) findViewById(R.id.brandTxt);
    TextView textViewLabel = (TextView) findViewById(R.id.modelTxt);
    TextView textViewColor = (TextView) findViewById(R.id.colorTxt);
    TextView textViewWeather = (TextView) findViewById(R.id.weatherTxt);
    TextView textViewType = (TextView) findViewById(R.id.typeTxt);
    TextView textViewBody = (TextView) findViewById(R.id.bodyTxt);
    /*
     * Put all information in the text field
     */

    textViewBrand.setText(this.myClothe.getBrand());
    textViewLabel.setText(this.myClothe.getModel());
    textViewColor.setText(this.myClothe.getColor());
    textViewType.setText(this.myClothe.getType());
    textViewBody.setText(this.myClothe.getBodies());
    textViewWeather.setText(this.getStringWeather());
  }

  /*
   * Method which made the text of wather
   */
  public String getStringWeather() {
    /*
     * List all weather information and contact with ';'
     */
    List<String> theWeather = this.myClothe.getWeather();
    String weatherTxt = "";
    for (String weatherLine : theWeather) {
      weatherTxt += weatherLine + " ";
    }
    return weatherTxt;
  }

}
