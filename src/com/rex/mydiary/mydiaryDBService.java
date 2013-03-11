/**
 * 
 */
package com.rex.mydiary;

import java.util.Date;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Administrator
 * 
 */
public class mydiaryDBService extends SQLiteOpenHelper {

	public final static int DATABASE_VERSION = 1;
	public final static String DATABASE_NAME = "mydiary2.db";

	public mydiaryDBService(Context context) {

		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		movedb(context,false);	
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite
	 * .SQLiteDatabase)
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		/*
		 * String sql = "CREATE TABLE [diary1] (" + "  [_id] AUTOINC, " +
		 * "  [theday] DATE, " + "  [title] TEXT, " + "  [content] TEXT, " +
		 * "  [font] TEXT, " + "  [color] TEXT, " + "  [demo] TEXT);";
		 * 
		 * db.execSQL(sql); Date thedate; String thetitle, thecontent; for(int
		 * i=0; i<20;i++) { thedate=randomDate("2012-01-01","2013-01-01");
		 * thetitle="����֪�����������·���"+thedate.toString();
		 * thecontent="����֪�����������ڼ���"+thedate.toString();
		 * db.execSQL("insert into diary1(theday,title,content) values(?,?,?)",
		 * new Object[]{thedate,thetitle,thecontent}); }
		 */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite
	 * .SQLiteDatabase, int, int)
	 */
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	//ִ��SQL���
	public Cursor query(String sql, String[] args)
	{
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor =db.rawQuery(sql, args);
		return cursor;
		
	}
	
	@SuppressLint("SimpleDateFormat")
	private static Date randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(beginDate);// ���쿪ʼ����
			Date end = format.parse(endDate);// �����������
			// getTime()��ʾ������ 1970 �� 1 �� 1 �� 00:00:00 GMT ������ Date �����ʾ�ĺ�������
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static long random(long begin, long end) {
		long rtn = begin + (long) (Math.random() * (end - begin));
		//������ص��ǿ�ʼʱ��ͽ���ʱ�䣬��ݹ���ñ������������ֵ
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}
	
	public static void movedb(Context context, boolean willCover){
	
		File path = context.getDatabasePath(DATABASE_NAME);
		
		if(!path.exists())
		{
			//����ļ������ڣ����ȴ����ļ�
			new File(path.toString().substring(0, path.toString().lastIndexOf("/"))).mkdirs();  
		}
		else if (!willCover)
		{
			//����������ǣ���ֱ�ӷ���
			return;
		}
		
		//�õ���ǰ/res/raw/�ļ������ݿ��������
		InputStream is=context.getResources().openRawResource(R.raw.mydiary2);
		FileOutputStream fos; 
		try {  
            fos = new FileOutputStream(path);  
  
            byte[] buffer = new byte[1024];  
  
            int count = 0;  
  
            while ((count = is.read(buffer)) > 0) {  
                fos.write(buffer, 0, count);  
            }  
            fos.close();  
            is.close();  
            //Log.e(TAG, "create new database");  
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
		
	}
	
   

}
