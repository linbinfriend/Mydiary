package com.rex.mydiary;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Showcontent extends Activity {

	private TextView theday;
	private TextView title;
	private TextView content;
	private String thedaystring,titlestring,contentstring;
	private long currentid;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//设置视图
		setContentView(R.layout.showcontent);
		//设置标题
		setTitle(R.string.readdiary);
		
		theday=(TextView)findViewById(R.id.showtheday);
		title=(TextView)findViewById(R.id.showtitle);
		content =(TextView)findViewById(R.id.showcontent);
		
		Bundle bundle = getIntent().getExtras();
		
		if (bundle!=null)
		{
			thedaystring=bundle.getString("theday");
			titlestring = bundle.getString("title");
			contentstring = bundle.getString("content");
			currentid=bundle.getLong("id");
			
			theday.setText(thedaystring);
			title.setText(titlestring);
			content.setText(contentstring);
			
		}
		
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		
		getMenuInflater().inflate(R.menu.showcontentmenu, menu);
		
		return super.onCreateOptionsMenu(menu);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.editcontent:
			 Intent intent = new Intent(this,Editcontent.class);
			 intent.putExtra("id", currentid);
			 intent.putExtra("theday", thedaystring);
			 intent.putExtra("title", titlestring);
			 intent.putExtra("content", contentstring);
			 startActivity(intent);
			 break;
		case R.id.deletecontent:
			 //根据id删除当前记录，并返回。
			 String where = " _id = ? ";
			 String[] wherevalue = {Long.toString(currentid)};
			 mydiaryDBService dbService = MainActivity2.dbService;
			 SQLiteDatabase db= dbService.getWritableDatabase();
			 db.delete("diary1", where, wherevalue);
			 break;
		}
		
		return super.onOptionsItemSelected(item);
	}

}
