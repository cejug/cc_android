package com.example.cc_android.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private SQLiteDatabase db;
	
	static final String DB_DATABASE_NAME = "cc_android.db";
	static final String DB_SCRIPT_CRIACAO =
			"create table funcionario (_id integer primary key autoincrement, "
			+ "nome text not null, salario decimal(10,2) not null);";
	
	public DatabaseHelper(Context context) {
		super(context, DB_DATABASE_NAME, null, 1);
		//createTable = sqlCriacaoBanco;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(DB_SCRIPT_CRIACAO);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS funcionarios");
		onCreate(db);
	}
	
	public DatabaseHelper open() {
		db = this.getReadableDatabase();
		return this;
	}

	public SQLiteDatabase getDb() {
		return db;
	}

	public void setDb(SQLiteDatabase db) {
		this.db = db;
	}
	
}
