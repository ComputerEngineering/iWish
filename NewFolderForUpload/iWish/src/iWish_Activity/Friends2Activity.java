package iWish_Activity;

import iWish_Friends.Friends;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import sottostante utile per effettuare comparazione e ordinamenti //
import java.util.Comparator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.progect.iwish.R;

/**Antonio*/

public class Friends2Activity extends Activity {

	/*	Friends amico1 = new Friends("1","antonio","rossi",43);
	Friends amico2 = new Friends("2","mario","bianchi",46);
	Friends amico3 = new Friends("3","giovanni","verdi",49);
	Friends amico4 = new Friends("4","andrea","gialli",89);*/
	private ListView mListView;

	//List<Friends>lista_amici = new List<Frin>

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.friends2);

		final TextView ButtonByName = (TextView)findViewById(R.id.byname);
		final TextView ButtonByLatest = (TextView)findViewById(R.id.bylatest);
		final TextView ButtonByPoints = (TextView)findViewById(R.id.bypoints);

		ButtonByName.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ButtonByName.setTextColor(getResources().getColor(R.color.verde_scuro));
				ButtonByLatest.setTextColor(getResources().getColor(R.color.placeholder));
				ButtonByPoints.setTextColor(getResources().getColor(R.color.placeholder));
			}    
		}); 
		ButtonByLatest.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ButtonByName.setTextColor(getResources().getColor(R.color.placeholder));
				ButtonByLatest.setTextColor(getResources().getColor(R.color.verde_scuro));
				ButtonByPoints.setTextColor(getResources().getColor(R.color.placeholder));
			}    
		}); 
		ButtonByPoints.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ButtonByName.setTextColor(getResources().getColor(R.color.placeholder));
				ButtonByLatest.setTextColor(getResources().getColor(R.color.placeholder));
				ButtonByPoints.setTextColor(getResources().getColor(R.color.verde_scuro));
			}
		}); 	
		ImageButton avanti = (ImageButton)findViewById(R.id.bott_omino);
		avanti.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(Friends2Activity.this,ProfileActivity.class ));
			}
		});
		//LIST ACTIVITY		
		mListView = (ListView)findViewById(R.id.listfriends2); 
		List<Map> data = GetSampleData();
		SimpleAdapter adapter = new SimpleAdapter(this, (List<? extends Map<String, ?>>) 
				data, R.xml.friends2item, new String[] { "foto", "nome", "punti" }, new int[] { 
			R.id.foto, R.id.nome, R.id.punti });
		mListView.setAdapter(adapter);
	}
	List<Map> GetSampleData() {
		List<Map> list = new ArrayList<Map>();
		Map map = new HashMap();
		map.put("foto", R.drawable.hysen);
		map.put("nome", "Hysen Drogu");
		map.put("punti", "34");
		list.add(map);
		map = new HashMap();
		map.put("foto", R.drawable.hysen);
		map.put("nome", "andrea bellizzi");
		map.put("punti", "54");
		list.add(map);
		map = new HashMap();
		map.put("foto", R.drawable.hysen);
		map.put("nome", "paola centamore");
		map.put("punti", "95");
		list.add(map);
		map = new HashMap();
		map.put("foto", R.drawable.hysen);
		map.put("nome", "giulia leone");
		map.put("punti", "100");
		list.add(map);
		map = new HashMap();
		map.put("foto", R.drawable.hysen);
		map.put("nome", "silvia bertossi");
		map.put("punti", "69");
		list.add(map);
		map = new HashMap();
		map.put("foto", R.drawable.hysen);
		map.put("nome", "mario rossi");
		map.put("punti", "69");
		list.add(map);
		map = new HashMap();
		map.put("foto", R.drawable.hysen);
		map.put("nome", "marco bianchi");
		map.put("punti", "69");
		list.add(map);
		map = new HashMap();
		map.put("foto", R.drawable.hysen);
		map.put("nome", "luigi rossi");
		map.put("punti", "69");
		list.add(map); 
		return list;
	}
}

