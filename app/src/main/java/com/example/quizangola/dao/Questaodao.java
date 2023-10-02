package com.example.quizangola.dao;

import com.example.quizangola.modelos.Questao;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.Spliterator;

public class Questaodao extends BaseDaoImpl<Questao, Integer> {

    @Override
    public Spliterator<com.example.quizangola.modelos.Questao> spliterator() {
        return null;
    }

    public Questaodao(ConnectionSource cs) throws SQLException {
        super(com.example.quizangola.modelos.Questao.class);
        setConnectionSource(cs);
        initialize();

    }

}
