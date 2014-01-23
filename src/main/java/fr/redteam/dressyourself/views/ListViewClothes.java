package fr.redteam.dressyourself.views;

import java.io.File;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.adapters.AdapterClothes;
import fr.redteam.dressyourself.common.filemanager.AndroidFileManager;
import fr.redteam.dressyourself.core.ClothesManager;
import fr.redteam.dressyourself.core.clothes.Clothe;

/**
 * View to display a {@link Clothes}. This is used by {@link AdapterClothes}.
 * 
 * @author Alexandre Bonhomme
 * 
 */
public class ListViewClothes extends LinearLayout {

  private Clothe product;

  private ImageView imageViewProduct;
  private TextView textViewProductBrand;
  private TextView textViewProductModel;

  public ListViewClothes(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    initComponent();
  }

  public ListViewClothes(Context context, AttributeSet attrs) {
    super(context, attrs);
    initComponent();
  }

  public ListViewClothes(Context context) {
    super(context);
    initComponent();
  }

  private void initComponent() {
    inflate(getContext(), R.layout.listview_clothes, this);

    imageViewProduct = (ImageView) findViewById(R.id.imageViewAdapter);
    textViewProductModel = (TextView) findViewById(R.id.textViewModelAdapter);
    textViewProductBrand = (TextView) findViewById(R.id.textViewBrandAdapter);
  }

  /**
   * Filled an element of the view with data from a {@link Clothe} object
   */
  public void bind(Clothe product) {
    this.product = product;

    textViewProductModel.setText(product.getModel());
    textViewProductBrand.setText(product.getBrand());

    // if no image attached to this product, we just put a default image
    if (product.getImageRelativePath() == null) {
      imageViewProduct.setImageResource(R.drawable.logo_2_2_1);
      return;
    }

    ClothesManager manager = new ClothesManager(new AndroidFileManager(getContext()));
    File imgFile = manager.loadClotheImage(product.getImageRelativePath());
    
    BitmapFactory.Options options = new BitmapFactory.Options();
    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);
    
    imageViewProduct.setImageBitmap(myBitmap);
  }

  public Clothe getProduct() {
    return product;
  }

}
