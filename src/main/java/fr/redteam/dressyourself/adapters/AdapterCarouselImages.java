package fr.redteam.dressyourself.adapters;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.digitalaria.gama.carousel.Carousel;

import fr.redteam.dressyourself.R;
import fr.redteam.dressyourself.common.filemanager.AndroidFileManager;
import fr.redteam.dressyourself.core.ClothesManager;
import fr.redteam.dressyourself.core.clothes.Clothe;

public class AdapterCarouselImages extends BaseAdapter {
  private final Context mContext;
  private final List<Clothe> data;

  public AdapterCarouselImages(Context c, List<Clothe> data) {
    mContext = c;
    this.data = data;
  }

  @Override
  public int getCount() {
    return data.size();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = convertView;
    if (view == null) {
      view = LayoutInflater.from(mContext).inflate(R.layout.carousel_item, parent, false);
      view.setLayoutParams(new Carousel.LayoutParams(parent.getWidth() / 2, parent.getHeight()));
      ViewHolder holder = new ViewHolder();
      holder.imageView = (ImageView) view.findViewById(R.id.itemImage);

      view.setTag(holder);
    }

    ViewHolder holder = (ViewHolder) view.getTag();
    holder.imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

    // Set image
    AndroidFileManager fileManager = new AndroidFileManager(mContext);
    ClothesManager manager = new ClothesManager(fileManager);
    holder.imageView.setImageBitmap(manager.getClotheBitmapImage(data.get(position)));

    // Get some information about the clothe selected
    holder.imageView.setOnTouchListener(new itemListener(position) {});

    return view;
  }

  // My clothe listener
  public class itemListener implements OnTouchListener {

    int position;

    public itemListener(int position) {
      this.position = position;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
      Log.d("Clicked : ", data.get(position).getModel());
      return false;
    }

  };

  private class ViewHolder {
    ImageView imageView;
  }

  @Override
  public Object getItem(int position) {
    return null;
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }
}
