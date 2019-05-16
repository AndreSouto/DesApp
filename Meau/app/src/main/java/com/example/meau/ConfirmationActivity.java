package com.example.meau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmationActivity extends AppCompatActivity {

    public static String TITLE_ACTION_BAR = "confirmatio_title_action_bar";
    public static String TITLE = "confirmation_title";
    public static String DESCRIPTION = "confirmation_description";
    public static String TITLE_BUTTON = "confimation_title_button";
    public static String ACTION = "confirmation_button";
    public static String ACTION_OK_REGISTER_PET = "action_ok_register_pet";

    String mTitle, mDescription, mTitleButton, mTitleActionBar, mConfimationClicked;
    TextView mTxtTitle, mTxtDescription;
    Button mBtButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Obtém os argumentos
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && !bundle.isEmpty()) {
            mTitle = bundle.getString(TITLE);
            mDescription = bundle.getString(DESCRIPTION);
            mTitleButton  = bundle.getString(TITLE_BUTTON);
            mTitleActionBar  = bundle.getString(TITLE_ACTION_BAR);
            mConfimationClicked = bundle.getString(ACTION);
        }
        else {
            finish();
        }

        // Suporter para Action Bar
        Toolbar mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(mTitleActionBar);
        setSupportActionBar(mToolbar);

        // Referências das Views
        mTxtTitle = findViewById(R.id.txt_confirmation_title);
        mTxtDescription = findViewById(R.id.txt_confirmation_description);
        mBtButton = findViewById(R.id.bt_confirmation_action);

        // Preenche a Activity
        mTxtTitle.setText(mTitle);
        mTxtDescription.setText(mDescription);
        mBtButton.setText(mTitleButton);
        mBtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onConfirmationClicked(mConfimationClicked);
            }
        });
    }

    private void onConfirmationClicked(String s) {
        if (s.equals(ACTION_OK_REGISTER_PET)) {
            finish();
        }
    }
}
