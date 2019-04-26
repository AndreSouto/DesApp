package com.example.meau;

import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class CadastroPessoal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pessoal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);

        TextView tv = findViewById(R.id.Topintitle);
        tv.setText("Cadastrar");

        TextView tx = (TextView)findViewById(R.id.warning);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Courgette-Regular.ttf");
        tx.setTypeface(custom_font);
    }
}
