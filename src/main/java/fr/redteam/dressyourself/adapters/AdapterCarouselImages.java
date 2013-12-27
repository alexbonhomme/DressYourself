package fr.redteam.dressyourself.adapters;

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

public class AdapterCarouselImages extends BaseAdapter {
  private final Context mContext;
  private final int[] data;

  public AdapterCarouselImages(Context c, int[] data) {
    mContext = c;
    this.data = data;
  }

  @Override
  public int getCount() {
    return data.length;
  }

  @Override
  public Object getItem(int position) {
    return null;
  }

  @Override
  public long getItemId(int position) {
    return data[position];
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
    holder.imageView.setImageResource(data[position]);

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
      Log.d("Clicked : ", Integer.toString(this.position));
      return false;
    }

  };

  private class ViewHolder {
    ImageView imageView;
  }
}
