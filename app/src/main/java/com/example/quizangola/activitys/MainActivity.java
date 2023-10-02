package com.example.quizangola.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.quizangola.dao.DatabaseHelper;
import com.example.quizangola.dao.Questaodao;
import com.example.quizangola.modelos.Questao;
import com.example.quizangola.util.ZNTUtil;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ArrayList<Questao> questaos;
    Questaodao questaoDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ocultar a barra de notificação (barra de status)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        inicializarBD();
        Toast.makeText(this, "dados inseridos|!", Toast.LENGTH_SHORT).show();
//Batao play
        findViewById(R.id.imageView8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CategoriaActivity.class));
            }
        });
//Batao musica
        findViewById(R.id.imageView7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//Batao sair
        findViewById(R.id.imageView4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, CategoriaActivity.class));
                ZNTUtil.showJanela(MainActivity.this,R.layout.layout_dialog_sair);
            }
        });

        //Batao info
        findViewById(R.id.imageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, CategoriaActivity.class));
                ZNTUtil.showJanela(MainActivity.this,R.layout.layout_info);
            }
        });
    }

    private void inicializarBD(){

        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
        try{
            questaoDAO = new Questaodao(helper.getConnectionSource());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            helper.close();
        }

        /*try {
            databaseHelper = new DatabaseHelper(this);
            questaodao = new Questaodao(databaseHelper.getConnectionSource());
            //questaodao.create(new Questao("4","34","12","18","Quantas províncias Angola possui atualmente?", ZNTUtil.SOU_ANGOLANO));
            //questaodao.create(new Questao("Londres","Cabinda","Washington","Luanda","Qual é a capital de Angola?", ZNTUtil.SOU_ANGOLANO));
        } catch (SQLException e) {
            e.printStackTrace();
            Toast.makeText(this, "Err: "+e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            databaseHelper.close();
        }*/
    }

    private void inserirDados(){
//        try {
//            questaoDAO.create(new Questao("Francês"
//                    ,"Espanhol"
//                    ," Inglês"
//                    ," Português"
//                    ,"Qual é a capita do Brazil?"
//                    , ZNTUtil.CULTURA_GERAL));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}