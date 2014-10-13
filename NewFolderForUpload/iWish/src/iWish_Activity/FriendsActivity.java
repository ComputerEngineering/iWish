package iWish_Activity;

import iWish_Friends.Friends;
import iWish_database.FriendsDao;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.progect.iwish.R;

/** Raffaella*/

public class FriendsActivity extends ListActivity{
	private ImageButton bt_search;
	private ImageButton bt_cancel;
	private ImageButton bt_sortByName;
	private ImageButton bt_sortByLatest;
	private ImageButton bt_sortByPoints;
	private FriendsDao datasource_friends;
	List<Friends> mFriends = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friends);
		
		datasource_friends = new FriendsDao(this);
		datasource_friends.open();
		
		mFriends = datasource_friends.getAllFriends();
		
		
		// use the SimpleCursorAdapter to show the elements in a ListView
	   ArrayAdapter<Friends> adapter = new ArrayAdapter<Friends>(this,android.R.layout.simple_list_item_1);
	   setListAdapter(adapter);
	}
	


	// Will be called via the onClick attribute
	 public void onClick(View view){
		 ArrayAdapter<Friends> adapter = (ArrayAdapter<Friends>)getListAdapter();
		 Friends friends = null;
		 //TODO da finire il click della lista
		 adapter.notifyDataSetChanged();
	 }

	@Override
	protected void onResume() {
		datasource_friends.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource_friends.close();
		super.onPause();
	}
	 
	
}
