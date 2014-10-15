package iWish_Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.progect.iwish.R;

public class StatisticsMetod {
	public static List<Map> RestituisciDati(int x){
		if(x==0){
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
		if(x==1){
			List<Map> list_badge_won = new ArrayList<Map>();
			Map map = new HashMap();
			map.put("premio1", R.drawable.badge_welcome4);
			map.put("premio2", R.drawable.badge_faster2);
			map.put("premio3", R.drawable.badge_finish3);
			list_badge_won.add(map);
			map = new HashMap();
			map.put("premio1", R.drawable.badge_welcome4);
			map.put("premio2", R.drawable.badge_faster2);
			map.put("premio3", R.drawable.badge_finish3);
			list_badge_won.add(map);
			return list_badge_won;
		}
		List<Map> list_badge_to_win = new ArrayList<Map>();
		Map map = new HashMap();
		map.put("premio1", R.drawable.badge_welcome_off2);
		map.put("premio2", R.drawable.badge_faster_off2);
		map.put("premio3", R.drawable.badge_finish_off2);
		list_badge_to_win.add(map);
		map = new HashMap();
		map.put("premio1", R.drawable.badge_welcome_off2);
		map.put("premio2", R.drawable.badge_faster_off2);
		map.put("premio3", R.drawable.badge_finish_off2);
		list_badge_to_win.add(map);
		return list_badge_to_win;
	}
}
/*

		List<Map> list_badge_won = new ArrayList<Map>();

 		Map map = new HashMap();
 		 map.put("premio", R.drawable.badge_welcome);
 		 map.put("descrizione_premio", "welcome");
 		list_badge_won.add(map);
 		 map = new HashMap();
 		 map.put("premio", R.drawable.badge_faster);
 		 map.put("descrizione_premio", "faster");
 		list_badge_won.add(map);
 		 map = new HashMap();
 		 map.put("premio", R.drawable.badge_finish);
 		 map.put("descrizione_premio", "finish");
 		list_badge_won.add(map);


 		return list_badge_won;



 */
