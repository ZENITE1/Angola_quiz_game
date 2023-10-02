package com.example.quizangola.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.quizangola.util.ZNTUtil;

import java.time.Instant;

public class CategoriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Ocultar a barra de notificação (barra de status)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_categorias);

        findViewById(R.id.camposouangola).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriaActivity.this, JogoActivity.class);
                intent.putExtra(ZNTUtil.CATEGORIA,ZNTUtil.SOU_ANGOLANO);
                startActivity(intent);
            }
        });
    }
}
