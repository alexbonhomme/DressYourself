package fr.redteam.dressyourself.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import fr.redteam.dressyourself.core.clothes.Clothe;
import fr.redteam.dressyourself.views.ListViewClothes;

public class AdapterClothes extends BaseAdapter {

  private final Context context;
  private List<Clothe> listClothes = new ArrayList<Clothe>();
  
  /**
   * @param context
   */
  public AdapterClothes(Context context, List<Clothe> listClothes) {
    this.context = context;
    this.listClothes = listClothes;
  }

  @Override
  public int getCount() {
    return listClothes.size();
  }

  @Override
  public Object getItem(int position) {
    return listClothes.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ListViewClothes view = (ListViewClothes) convertView;

    if (view == null) {
      view = new ListViewClothes(context);
    }

    // On rempli la vue
    view.bind(listClothes.get(position));

    return view;
  }
}
