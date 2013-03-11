package com.rex.mydiary;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Editcontent extends Activity {

	private static EditText edittext_title;
	private static EditText edittext_content;
	private static TextView textview_theday;
	private static long currentid;
	private static String string_theday, string_title, string_content;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		// 设置视图
		setContentView(R.layout.editcontent);
		// 设置标题
		setTitle(R.string.editdiary);
		
		edittext_title=(EditText)findViewById(R.id.edittitle);
		edittext_content=(EditText)findViewById(R.id.editcontent);
		textview_theday=(TextView)findViewById(R.id.edittheday);
		
		Bundle  bundle = getIntent().getExtras();
		if (bundle!=null){
			
			currentid=bundle.getLong("id");
			string_theday= bundle.getString("theday");
			string_title=bundle.getString("title");
			string_content= bundle.getString("content");
			
			edittext_title.setText(string_title);
			textview_theday.setText(string_theday);
			edittext_content.setText(string_content);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.editcontentmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.editsave:
			
			string_title=edittext_title.getText().toString();
			string_content=edittext_content.getText().toString();
			
			if (!string_title.isEmpty() && !string_content.isEmpty()) {
				
				ContentValues contentvalues = new ContentValues();
				contentvalues.put("title", string_title);
				contentvalues.put("content", string_content);
				//SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(
				//		mydiaryDBService.DATABASE_NAME, null);
				mydiaryDBService dbService = MainActivity2.dbService;
				SQLiteDatabase db= dbService.getWritableDatabase();
				String where = " _id = ? ";
				String[] wherevalue = {Long.toString(currentid)};
				db.update("diary1", contentvalues, where, wherevalue);
				db.close();
			}
			 break;
		case R.id.editundo:
			 break;
		}
		
		return super.onOptionsItemSelected(item);
	}

}
