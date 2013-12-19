package fr.redteam.dressyourself.activities;

import java.io.File;
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
import fr.redteam.dressyourself.common.filemanager.AndroidFileManager;
import fr.redteam.dressyourself.core.ClothesManager;
import fr.redteam.dressyourself.core.clothes.Clothe;
/**
 * this Activity able to complete the activity action
 *
 */
public class ActivityClotheDetail extends Activity {

  private ImageView imagePhoto;
  private Clothe myClothe;
  /**
   * Define all object create on the open of activity
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_clothe_detail);

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

  /**
   * init cloth object
   */
  public void initMyClothe() {
    Intent myIntent = getIntent();
    if (myIntent != null) {
      this.myClothe = (Clothe) myIntent.getSerializableExtra("clothe");
    }
  }
  /**
   * Insert the clothe picture on activity
   */
  public void initImageView() {
    this.imagePhoto = (ImageView) findViewById(R.id.photo);
    if (!(myClothe.getImageRelativePath() == null)) {
      File imgFile =
          new ClothesManager(new AndroidFileManager(this)).loadClotheImage(myClothe
              .getImageRelativePath());
      if (imgFile.exists()) {
        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        this.imagePhoto.setImageBitmap(myBitmap);
      }
    }
  }

  /**
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

  /**
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
