package com.example.quizangola.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.myapplication.R;
import com.example.quizangola.dao.DatabaseHelper;
import com.example.quizangola.dao.Questaodao;
import com.example.quizangola.modelos.Questao;
import com.example.quizangola.util.ZNTUtil;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.ArrayList;

public class JogoActivity extends AppCompatActivity{
    DatabaseHelper databaseHelper;
    ArrayList<Questao> questaos;
    Questaodao questaoDAO;
    String categoria;
    TextView txPergunta, txRespostaA, txRespostaB, txRespostaC, txRespostaD, textViewTimer, textViewPerguntasRspondidas;
    ConstraintLayout layoutA, layoutB, layoutC, layoutD, layout;
    Questao questao;
    private CountDownTimer countDownTimer, piscar;
    int contadorSuspense = 0;//indicador do pisco quando uma opsao he clicada
    int tempoGastoNoJogo = 0;
    int quantPerguntasRespondida = 0;//indicador do quantidade de perguntas respondidas
    int timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ocultar a barra de notificação (barra de status)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_jogo);
        iniciarCronometros();
        inicializaDB();
        inicializaViews();
        Intent intent = getIntent();
        categoria = intent.getStringExtra(ZNTUtil.CATEGORIA);

        switch (categoria) {
            case "souangolano":
                buscaPerguntasDoSouAngolano();
                break;
            case "terminafrase":
                buscaPerguntasDoTerminaFrase();
                break;
            case "culturageral":
                buscaPerguntasDaCulturaGeral();
                break;
        }

        layoutA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNull();
                layout = layoutA;
                piscar.start();
                countDownTimer.cancel();
            }
        });
        layoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNull();
                layout = layoutB;
                piscar.start();
                countDownTimer.cancel();
            }
        });
        layoutC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNull();
                layout = layoutC;
                piscar.start();
                countDownTimer.cancel();
            }
        });
        layoutD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNull();
                layout = layoutD;
                piscar.start();
                countDownTimer.cancel();
            }
        });
    }
    private void proximaQuestao() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                quantPerguntasRespondida++;
                mostraQuestao(quantPerguntasRespondida);
            }
        }, 2000);
    }
    private void inicializaDB() {
        DatabaseHelper helper = new DatabaseHelper(JogoActivity.this);
        try{
            questaoDAO = new Questaodao(helper.getConnectionSource());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            helper.close();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    private void mostraQuestao(int index) {
        restartViews();//restoura as propriedades das Views
        //TODO baralhar as questoes e inserilas na ArrayList
        if (questaos.isEmpty()) {
            Log.d("Class Jogo", "questaodao Vazio!");
        } else {
            questao = questaos.get(index);
            textViewPerguntasRspondidas.setText(index+"/15");

            txPergunta.setText(questao.getPergunta());

            String[] respostas = {""+questao.getR1()
                    ,""+questao.getR2()
                    ,""+questao.getR3()
                    ,""+questao.getRv()};


            int[] array = ZNTUtil.gerarArrayDeNumeroAleatorio();

            txRespostaA.setText(respostas[array[3]]);
            txRespostaB.setText(respostas[array[0]]);
            txRespostaC.setText(respostas[array[2]]);
            txRespostaD.setText(respostas[array[1]]);

            countDownTimer.start();
        }
    }
    private void inicializaViews() {
        txPergunta = findViewById(R.id.idpergunta);
        txRespostaA = findViewById(R.id.resposta_1);
        txRespostaB = findViewById(R.id.resposta_2);
        txRespostaC = findViewById(R.id.resposta_3);
        txRespostaD = findViewById(R.id.resposta_4);
        textViewTimer = findViewById(R.id.textViewTimer);
        textViewPerguntasRspondidas = findViewById(R.id.textView2);
        layoutA = findViewById(R.id.layoutA);
        layoutB = findViewById(R.id.layoutB);
        layoutC = findViewById(R.id.layoutC);
        layoutD = findViewById(R.id.layoutD);
    }
    private void buscaPerguntasDoTerminaFrase() {
        buscaNa(ZNTUtil.TERMINA_FRASE);
    }
    private void buscaPerguntasDoSouAngolano() {
        buscaNa(ZNTUtil.SOU_ANGOLANO);
    }
    private void buscaPerguntasDaCulturaGeral() {
        buscaNa(ZNTUtil.CULTURA_GERAL);
    }
    private void buscaNa(String categoria){
        try {
            QueryBuilder<Questao, Integer> queryBuilder = questaoDAO.queryBuilder();
            queryBuilder.where().eq("categoria", categoria);
            questaos = (ArrayList<Questao>) queryBuilder.query();
            mostraQuestao(quantPerguntasRespondida);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void iniciarCronometros(){
        piscar = new CountDownTimer(5000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Atualizar o TextView a cada segundo
                if(contadorSuspense == 0){
                    layout.setBackgroundColor(Color.YELLOW);//TODO trocar o background para ter borda
                    contadorSuspense = 1;
                }
                else{
                    layout.setBackgroundColor(Color.WHITE);//TODO trocar o background para ter borda
                    contadorSuspense = 0;
                }
            }

            public void onFinish() {
                // Ação a ser executada quando a contagem regressiva terminar
                LinearLayout linearLayout = (LinearLayout) layout.getChildAt(1);
                TextView textView = (TextView) linearLayout.getChildAt(0);
                String respostaSelecionada = textView.getText().toString();
                if (respostaSelecionada.equals(questao.getRv())) {//   QUANDO A RESPOSTA SELECIONADA FOR VERDADEIRA
                    tempoGastoNoJogo += (timer-60);
                    layout.setBackgroundColor(Color.GREEN);//TODO trocar o background para ter borda
                    //inicializaViews();
                    Toast.makeText(JogoActivity.this, "Prestes a tracar as questoes", Toast.LENGTH_SHORT).show();
                    proximaQuestao();
                }
                else {//QUANDO A RESPOSTA SELECIONADO FOR FALSA
                        tempoGastoNoJogo += (timer-60);
                        layout.setBackgroundColor(Color.RED);//TODO trocar o background para ter borda
                        //TODO mostrar os resultados
                        ZNTUtil.showJanela(quantPerguntasRespondida,tempoGastoNoJogo,JogoActivity.this,R.layout.layout_dialog_resultado);
                        Toast.makeText(JogoActivity.this, "Acertaste "+quantPerguntasRespondida+" Pergunatas em "+tempoGastoNoJogo+" Segundos", Toast.LENGTH_SHORT).show();
                     }
            }
        };
        // Configurar o CountDownTimer para 10 segundos (10000 milissegundos)
        countDownTimer = new CountDownTimer(30000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Atualizar o TextView a cada segundo
                timer = (int) (millisUntilFinished / 1000);
                textViewTimer.setText(""+timer);
            }
            public void onFinish() {
                // Ação a ser executada quando a contagem regressiva terminar
                textViewTimer.setText("terminou");
                clickNull();
                tempoGastoNoJogo += (timer-60);
                //TODO mostrar os resultados
                ZNTUtil.showJanela(quantPerguntasRespondida,tempoGastoNoJogo,JogoActivity.this,R.layout.layout_dialog_resultado);
                Toast.makeText(JogoActivity.this, "Acertaste "+quantPerguntasRespondida+" Pergunatas em "+tempoGastoNoJogo+" Segundos", Toast.LENGTH_SHORT).show();
            }
        };
    }
    private void restartViews(){
        layoutA.setBackgroundColor(Color.TRANSPARENT);
        layoutB.setBackgroundColor(Color.TRANSPARENT);
        layoutC.setBackgroundColor(Color.TRANSPARENT);
        layoutD.setBackgroundColor(Color.TRANSPARENT);

        layoutA.setEnabled(true);
        layoutB.setEnabled(true);
        layoutC.setEnabled(true);
        layoutD.setEnabled(true);
    }
    private void clickNull(){
        layoutA.setEnabled(false);
        layoutB.setEnabled(false);
        layoutC.setEnabled(false);
        layoutD.setEnabled(false);
    }
}