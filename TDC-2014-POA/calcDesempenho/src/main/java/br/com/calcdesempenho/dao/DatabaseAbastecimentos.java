package br.com.calcdesempenho.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAbastecimentos extends SQLiteOpenHelper{

	private static final String DATABASE_NAME = "abastecimentos.db";
	private static final int DATABASE_VERSION = 1;
	
	public DatabaseAbastecimentos(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.createTableAbastecimento(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS abastecimentos");
		onCreate(db);
	}

	private void createTableAbastecimento(SQLiteDatabase db){
		StringBuilder sqlAbastecimento = new StringBuilder();
		sqlAbastecimento.append("CREATE TABLE abastecimento (");
		sqlAbastecimento.append("id INTEGER PRIMARY KEY AUTOINCREMENT,");
		sqlAbastecimento.append("kmAbastecimento INTEGER(11) NOT NULL,");
		sqlAbastecimento.append("quantidade REAL(10,2) NOT NULL,");
		sqlAbastecimento.append("valor REAL(10,2) NOT NULL,");
		sqlAbastecimento.append("data TEXT(10) NOT NULL)");
		db.execSQL(sqlAbastecimento.toString());
	}
}
