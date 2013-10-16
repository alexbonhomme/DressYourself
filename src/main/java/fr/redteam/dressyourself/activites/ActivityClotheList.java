package main.java.fr.redteam.dressyourself.activites;

import main.java.fr.redteam.dressyourself.R;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * An activity representing a list of Clothes. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link ActivityClotheDetail} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link FragmentClotheList} and the item details (if present) is a
 * {@link FragmentClotheDetail}.
 * <p>
 * This activity also implements the required
 * {@link FragmentClotheList.Callbacks} interface to listen for item selections.
 */
public class ActivityClotheList extends FragmentActivity implements
		FragmentClotheList.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean bTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clothe_list);

		if (findViewById(R.id.clothe_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			bTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((FragmentClotheList) getSupportFragmentManager().findFragmentById(
					R.id.clothe_list)).setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}

	/**
	 * Callback method from {@link FragmentClotheList.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (bTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(FragmentClotheDetail.ARG_ITEM_ID, id);
			FragmentClotheDetail fragment = new FragmentClotheDetail();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.clothe_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, ActivityClotheDetail.class);
			detailIntent.putExtra(FragmentClotheDetail.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}
}
