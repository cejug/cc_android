package com.example.cc_android.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.cc_android.pojo.Funcionario;

public class FuncionarioDB {
	
	static final String DATABASE_TABLE = "funcionario";
	static final String KEY_ROWID = "_id";
	static final String KEY_NOME = "nome";
	static final String KEY_SALARIO = "salario";
	
	final Context context;
	
	DatabaseHelper DBHelper;
	SQLiteDatabase db;
	
	public FuncionarioDB(Context context) {
		this.context = context;		
		DBHelper = new DatabaseHelper(context);
	}
	/**
	 * open database.
	 * @return FuncionarioDB this;
	 * @throws SQLException e
	 */
	public FuncionarioDB open() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}	
	
	/**
	 * to close;
	 */
	public void close() {
		DBHelper.close();
	}
	
	/**
	 * 	
	 * @param funcionario
	 * @return
	 */
	public long insert(Funcionario funcionario) {
		
		ContentValues values = new ContentValues();
		values.put(KEY_ROWID,funcionario.getId());
		values.put(KEY_NOME, funcionario.getNome());
		values.put(KEY_SALARIO, funcionario.getSalario());
		
		return db.insert(DATABASE_TABLE, null, values);
	}
	
	public boolean delete(Funcionario funcionario) {
		return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + funcionario.getId(), null) > 0;
		
	}
	
	//---retrieves all the contacts---
	public Cursor getAll() {
		return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NOME, KEY_SALARIO}, null, null, null, null, null);
	}
	
	// 
	public Cursor getById(long id) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NOME, KEY_SALARIO}, KEY_ROWID + "=" + id, null,
			null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	
	//---updates a contact---
	public boolean update(long rowId, String nome, double salario) {
		ContentValues args = new ContentValues();
		args.put(KEY_NOME, nome);
		args.put(KEY_SALARIO, salario);
		return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
	}
	
	
	
}	
