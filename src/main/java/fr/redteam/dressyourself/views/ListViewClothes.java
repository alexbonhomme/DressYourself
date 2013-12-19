package fr.redteam.dressyourself.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.core.clothes.Clothe;

/**
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

	/**
	 * Initialisation des composants
	 */
	private void initComponent() {
		inflate(getContext(), R.layout.listview_clothes, this);

		imageViewProduct = (ImageView) findViewById(R.id.imageViewAdapter);
		textViewProductModel = (TextView) findViewById(R.id.textViewModelAdapter);
		textViewProductBrand = (TextView) findViewById(R.id.textViewBrandAdapter);
	}

	/**
	 * Cette méthode "remplit" les élements de la vue avec les données issues d'un objet Clothe
	 * 
	 * @param product
	 */
	public void bind(Clothe product) {
		this.product = product;
		//TODO mettre image dynamique
//		if (!(product.getImageRelativePath() == null)) {
//			File imgFile = ClothesManager.loadClotheImage(getContext(), product.getImageRelativePath());
//			if (imgFile.exists()) {
//				Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//				imageViewProduct.setImageBitmap(myBitmap);
//			}
//		}
		imageViewProduct.setImageResource(R.raw.medium_wash_jeans);
		textViewProductModel.setText(product.getModel());
		textViewProductBrand.setText(product.getBrand());
	}

	/**
	 * Retourn l'id du vêtement associé a cette élément
	 * 
	 * @return
	 */
	public Clothe getProduct() {
		return product;
	}

}
