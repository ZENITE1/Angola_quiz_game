package com.example.quizangola.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.quizangola.modelos.Questao;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    private static final String DATABASE_NAME ="zntQuiz.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, com.example.quizangola.modelos.Questao.class);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao criar a tabela", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase p1, ConnectionSource p2, int i, int i1) {
        try {
            TableUtils.dropTable(p2, com.example.quizangola.modelos.Questao.class,true);

            onCreate(p1,p2);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar a tabela", e);}
    }

    @Override
    public void close() {
        super.close();
    }
}
