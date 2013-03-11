package com.rex.mydiary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity2 extends ListActivity {

	public static mydiaryDBService dbService=null;
	private static Cursor cursor =null;
	public static SimpleCursorAdapter adapter=null ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		
		dbService = new mydiaryDBService(this);
		cursor = dbService.query("select * from diary1", null);
		
		
	    adapter = new SimpleCursorAdapter(this, R.layout.listmydiary,
				cursor, new String[]{"theday","title","content"}, new int[]{R.id.theday,
				R.id.thetitle,R.id.thecontent},RESULT_OK );
		
        setListAdapter(adapter);
        
		
		//add and delete by linbin on 20130309
		/*SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.listmydiary,
                new String[]{"theday","thetitle","thecontent"},
                new int[]{R.id.theday,R.id.thetitle,R.id.thecontent});
        setListAdapter(adapter);*/
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.main, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	

	
	//add by linbin on 20130309
	private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
 
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("theday", "2013年3月1日");
        map.put("thetitle", "去洪湖公园玩");
        map.put("thecontent", "带了可可去，玩得真高兴");
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("theday", "2013年3月2日");
        map.put("thetitle", "去洪湖公园玩");
        map.put("thecontent", "带了可可去，玩得真高兴,带了可可去，玩得真高兴,带了可可去，玩得真高兴" +
        		"带了可可去，玩得真高兴带了可可去，玩得真高兴带了可可去，玩得真高兴" +
        		"带了可可去，玩得真高兴带了可可去，玩得真高兴");
        list.add(map);
 
        map = new HashMap<String, Object>();
        map.put("theday", "2013年3月3日");
        map.put("thetitle", "去洪湖公园玩");
        map.put("thecontent", "带了可可去，玩得真高兴");
        list.add(map);
         
        return list;
    }

	/* (non-Javadoc)
	 * @see android.app.ListActivity#onDestroy()
	 */
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		cursor.close();
		dbService.close();
	}

	/* (non-Javadoc)
	 * @see android.app.ListActivity#onListItemClick(android.widget.ListView, android.view.View, int, long)
	 */
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		
		Cursor c = cursor ;
		c.moveToPosition(position);
		Intent intent = new Intent(this,Showcontent.class);
		//Log.d("you select id:", getString((int) id));
		intent.putExtra("id", id);
		intent.putExtra("theday", c.getString(c.getColumnIndexOrThrow("theday")));
		intent.putExtra("title", c.getString(c.getColumnIndexOrThrow("title")));
		intent.putExtra("content", c.getString(c.getColumnIndexOrThrow("content")));
		startActivity(intent);
		//Log.d("you select id:", getString((int) id));
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.newtimerecord:
			 Intent intent = new Intent(this,Newcontent.class);
			 startActivity(intent);
			 break;
		case R.id.search:
			 break;
		case R.id.quit:
			 break;
		}
		
		return super.onOptionsItemSelected(item);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		
		adapter.notifyDataSetChanged();
		
		super.onResume();
	}
	

}
