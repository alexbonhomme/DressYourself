package fr.redteam.dressyourself.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import fr.redteam.dressyourself.R;

public class AdapterImage extends BaseAdapter {
  private final Context mContext;

  public AdapterImage(Context c) {
    mContext = c;
  }

  @Override
  public int getCount() {
    return mThumbIds.length;
  }

  @Override
  public Object getItem(int position) {
    return null;
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  // create a new ImageView for each item referenced by the Adapter
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ImageView imageView;
    if (convertView == null) { // if it's not recycled, initialize some attributes
      imageView = new ImageView(mContext);
      imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
      imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      imageView.setPadding(8, 8, 8, 8);
    } else {
      imageView = (ImageView) convertView;
    }

    imageView.setImageResource(mThumbIds[position]);
    return imageView;
  }

  // references to our images
  private final Integer[] mThumbIds = {R.drawable.add_clothes, R.drawable.list_clothes};
}