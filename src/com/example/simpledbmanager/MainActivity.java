package com.example.simpledbmanager;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	final String LOG_TAG = "myLogs";

	private EditText mText;
	private TableRow mData;

	private DBHelper mHelper;
	private SQLiteDatabase mDb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mText = (EditText) findViewById(R.id.edtText);
		mData = (TableRow) findViewById(R.id.edtResult);

		findViewById(R.id.btnText).setOnClickListener(this);

		mHelper = new DBHelper();
		mDb = mHelper.getWritableDatabase();

	}

	public void onDestroy() {
		mHelper.close();
	}

	@Override
	public void onClick(View v) {
		// получаем данные из полей ввода
		String sql = mText.getText().toString();
		
		Cursor cursor=null;
		   try{                                      
			   cursor=mDb.rawQuery(sql, null);       //mDb.rawQuery(sql, null);
		   }catch (Exception e) {
			Toast.makeText(this, e.getMessage(),Toast.LENGTH_SHORT).show();
		   }finally{
			   if(cursor != null){
				   cursor.close();
			   }
		}
		
		// подключаемся к БД
		// SQLiteDatabase mDb = mHelper.getWritableDatabase();
	}

	class DBHelper extends SQLiteOpenHelper {
		// конструктор суперкласса
		public DBHelper() {
			super(MainActivity.this, "myDatabase", null, 1);

		}

		@Override
		public void onCreate(SQLiteDatabase mDb) {
			Log.d(LOG_TAG, "--- Create mytable: ---");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

		}

	}
}