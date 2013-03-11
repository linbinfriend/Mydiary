package com.rex.mydiary;

import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class Newcontent extends Activity {

	private static Date newday;
	private static String newtitle;
	private static String newcontent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newcontent);
		// Show the Up button in the action bar.
		setupActionBar();
		
		newday = Calendar.getInstance().getTime();
		TextView tv= (TextView) findViewById(R.id.newday);
		tv.setText(newday.toString());
		
		//SimpleDateFormat  sdf= new SimpleDateFormat();
		
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.newcontent, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.newsave:
			
			String string_title, string_content;
			EditText edittext_title= (EditText)findViewById(R.id.newtitle);
			EditText edittext_content=(EditText)findViewById(R.id.newcontent);
			string_title = edittext_title.getText().toString();
			string_content= edittext_content.getText().toString();
			
			if (!string_title.isEmpty() && !string_content.isEmpty()) {
				
				ContentValues contentvalues = new ContentValues();
				contentvalues.put("theday", newday.toString());
				contentvalues.put("title", string_title);
				contentvalues.put("content", string_content);
				//SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
				//		mydiaryDBService.DATABASE_NAME, null);
				mydiaryDBService dbService = MainActivity2.dbService;
				SQLiteDatabase db= dbService.getWritableDatabase();
				db.insert("diary1", null, contentvalues);
				db.close();
			}
			NavUtils.navigateUpFromSameTask(this);
			return true;
			
		case R.id.newundo:
			NavUtils.navigateUpFromSameTask(this);
			return true;
			}
		return super.onOptionsItemSelected(item);
	}

}
