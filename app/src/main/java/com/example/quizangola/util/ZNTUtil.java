package com.example.quizangola.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.quizangola.activitys.JogoActivity;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ZNTUtil {
    public static String CATEGORIA = "categoria";
    public static String SOU_ANGOLANO = "souangolano";
    public static String TERMINA_FRASE = "terminafrase";
    public static String CULTURA_GERAL = "culturageral";

    public static int[] gerarArrayDeNumeroAleatorio() {
        int[][] arrayDeArray = {
                 {2,1,0,3}
                ,{2,0,3,1}
                ,{2,0,1,3}
                ,{1,3,2,0}
                ,{1,3,0,2}
                ,{1,2,3,0}
                ,{1,2,0,3}
                ,{1,0,2,3}
                ,{0,3,1,2}
                ,{0,2,1,3}
                ,{0,1,3,2}
                ,{0,1,2,3}
        };
        int i = new Random().nextInt(11);

        return arrayDeArray[i];
    }
    public static void showJanela(Context context,int layout){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Inflar o layout personalizado
        LayoutInflater inflater = LayoutInflater.from(context);
        View customView = inflater.inflate(layout, null);
        builder.setView(customView);

//        // Recupere os elementos do layout personalizado
//        TextView textViewTitle = customView.findViewById(R.id.textViewTitle);
//        EditText editTextMessage = customView.findViewById(R.id.editTextMessage);
//        Button buttonOK = customView.findViewById(R.id.buttonOK);
//
//        // Personalize os elementos conforme necessário
//        textViewTitle.setText("Título Personalizado");
//        editTextMessage.setHint("Digite algo aqui");
//
//        // Configure a ação do botão OK
//        buttonOK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Faça algo quando o botão OK for clicado
//                // Por exemplo, feche o AlertDialog
//                alertDialog.dismiss();
//            }
//        });

        // Crie e exiba o AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public static void showJanela(int quantPerguntasRespondida, int tempoGastoNoJogo, Context context, int layout) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Inflar o layout personalizado
        LayoutInflater inflater = LayoutInflater.from(context);
        View customView = inflater.inflate(layout, null);
        builder.setView(customView);

//        // Recupere os elementos do layout personalizado
//        TextView textViewTitle = customView.findViewById(R.id.textViewTitle);
//        EditText editTextMessage = customView.findViewById(R.id.editTextMessage);
//        Button buttonOK = customView.findViewById(R.id.buttonOK);
//
//        // Personalize os elementos conforme necessário
//        textViewTitle.setText("Título Personalizado");
//        editTextMessage.setHint("Digite algo aqui");
//
//        // Configure a ação do botão OK
//        buttonOK.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Faça algo quando o botão OK for clicado
//                // Por exemplo, feche o AlertDialog
//                alertDialog.dismiss();
//            }
//        });

        // Crie e exiba o AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}