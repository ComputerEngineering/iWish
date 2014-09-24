package iWish_Activity;
/**Michela*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import com.progect.iwish.R;


public class StatisticsActivity extends Activity {
	
	private ListView mListView;
	private ListView mListView_badge_won;
	private ListView mListView_badge_to_win;
	
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.statistics);
	    
		final ImageButton ButtonBody = (ImageButton)findViewById(R.id.bot_body);
		final ImageButton ButtonActivity = (ImageButton)findViewById(R.id.bot_activity);
		final ImageButton ButtonBadge = (ImageButton)findViewById(R.id.bot_badges);
		final ImageButton changeWeight = (ImageButton)findViewById(R.id.bot_change_weight);
	    
		final LinearLayout layout_list = (LinearLayout) findViewById(R.id.layout_list);
		final LinearLayout layout_badge = (LinearLayout) findViewById(R.id.layout_badge);
		final RelativeLayout layout_body = (RelativeLayout)findViewById(R.id.layout_body);
	
//click su BODY
		ButtonBody.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	        	ButtonBody.setImageResource(R.drawable.bot_body2); 
	        	ButtonActivity.setImageResource(R.drawable.bot_activity); 
	        	ButtonBadge.setImageResource(R.drawable.bot_badges); 
	        	
	        	layout_body.setVisibility(0);
	            layout_list.setVisibility(4);
	            layout_badge.setVisibility(4);
	        }    
	    }); 

//click su ACTIVITY
		ButtonActivity.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	        	ButtonBody.setImageResource(R.drawable.bot_body); 
	        	ButtonActivity.setImageResource(R.drawable.bot_activity2); 
	        	ButtonBadge.setImageResource(R.drawable.bot_badges); 

	        	layout_list.setVisibility(0);
	            layout_body.setVisibility(4);
	            layout_badge.setVisibility(4);
	        }	
            
	    }); 

//click su BADGE
		ButtonBadge.setOnClickListener(new OnClickListener() {

	        @Override
	        public void onClick(View v) {
	            // TODO Auto-generated method stub
	        	
	        	ButtonBody.setImageResource(R.drawable.bot_body); 
	        	ButtonActivity.setImageResource(R.drawable.bot_activity); 
	        	ButtonBadge.setImageResource(R.drawable.bot_badges2); 
	        	
	        	 layout_badge.setVisibility(0);
	             layout_list.setVisibility(4);
		         layout_body.setVisibility(4);
	        	
	          }
	        }); 
		
	
		ImageButton avanti = (ImageButton)findViewById(R.id.bott_omino);
		avanti.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				startActivity(new Intent(StatisticsActivity.this, PopUpCalendarioActivity.class ));
			}
		});
		
	
//click su CHANGE WEIGHT
		
		changeWeight.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Stub di metodo generato automaticamente
				startActivity(new Intent(StatisticsActivity.this, WeightChangeActivity.class ));
			}
		});
		

//LIST ACTIVITY		
		mListView = (ListView)findViewById(R.id.list);
    	 
 		List<Map> data = StatisticsMetod.RestituisciDati(0);
 		SimpleAdapter adapter = new SimpleAdapter(this, (List<? extends Map<String, ?>>) data, R.xml.statistics_item, new String[] { "icon", "value", "description" }, new int[] { R.id.icon, R.id.value, R.id.description });
 		 
 		mListView.setAdapter(adapter);
//fine list Activity
 		

//LIST BADGE		
 		
 		//premi vinti
		mListView_badge_won = (ListView)findViewById(R.id.list_badge_won);
	 
 		List<Map> data_badge_won = StatisticsMetod.RestituisciDati(1);
 		SimpleAdapter adapter_badge_won = new SimpleAdapter(this, (List<? extends Map<String, ?>>) data_badge_won, R.xml.statistics_item_badge2, new String[] { "premio1", "premio2", "premio3" }, new int[] { R.id.premio1, R.id.premio2, R.id.premio3 });
 		 
 		mListView_badge_won.setAdapter(adapter_badge_won);
 		 
		//premi da vincere
	
		mListView_badge_to_win = (ListView)findViewById(R.id.list_badge_to_win);
	 
		List<Map> data_badge_to_win = StatisticsMetod.RestituisciDati(2);
		SimpleAdapter adapter_badge_to_win = new SimpleAdapter(this, (List<? extends Map<String, ?>>) data_badge_to_win, R.xml.statistics_item_badge2, new String[] { "premio1", "premio2", "premio3" }, new int[] { R.id.premio1, R.id.premio2, R.id.premio3 });
		 
		mListView_badge_to_win.setAdapter(adapter_badge_to_win);
		 }
 		 
}
 		
//fine list Badge	
 		
 		

	
	
/*
		List<Map> GetSampleData2() {
		
	 List<Map> list_badge_won = new ArrayList<Map>();
	 
	Map map = new HashMap();
	 map.put("premio", R.drawable.stat_punti);
	 map.put("descrizione_premio", "22");
	list_badge_won.add(map);
	 map = new HashMap();
	 map.put("premio", R.drawable.stat_punti);
	 map.put("descrizione_premio", "22");
	list_badge_won.add(map);
	 map = new HashMap();
	 map.put("premio", R.drawable.stat_punti);
	 map.put("descrizione_premio", "22");
	list_badge_won.add(map);

	 
	return list_badge_won;
	
	 }
	
	
	
	
			mListView = (ListView)findViewById(R.id.list);
    	 
 		List<Map> data = GetSampleData();
 		 SimpleAdapter adapter = new SimpleAdapter(this, (List<? extends Map<String, ?>>) data, R.xml.statistics_item, new String[] { "icon", "value", "description" }, new int[] { R.id.icon, R.id.value, R.id.description });
 		 
 		mListView.setAdapter(adapter);
 		 }
	
	
	 		List<Map> GetSampleData() {
 			
 		 List<Map> list = new ArrayList<Map>();
 		 
 		Map map = new HashMap();
 		 map.put("icon", R.drawable.stat_punti);
 		 map.put("value", "22");
 		 map.put("description", "points won");
 		 list.add(map);
 		 map = new HashMap();
 		 map.put("icon", R.drawable.stat_vittorie);
 		 map.put("value", "5");
 		 map.put("description", "completed wish");
 		 list.add(map);
 		 map = new HashMap();
 		 map.put("icon", R.drawable.stat_sconfitte);
 		 map.put("value", "3");
 		 map.put("description", "missed wish");
 		 list.add(map);
 		 map = new HashMap();
 		 map.put("icon", R.drawable.stat_distanza);
 		 map.put("value", "13,5 km");
 		 map.put("description", "done");
 		 list.add(map);
 		 map = new HashMap();
 		 map.put("icon", R.drawable.stat_tempo);
 		 map.put("value", "10:27");
 		 map.put("description", "activity time");
 		 list.add(map);
 		 
 		return list;
 		 }
	
	
	
	
	*/
	
	