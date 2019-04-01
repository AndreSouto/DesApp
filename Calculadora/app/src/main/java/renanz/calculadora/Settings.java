package renanz.calculadora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class Settings extends AppCompatActivity {

    Button ret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ret = (Button)findViewById(R.id.ret);
        /*CheckBox bpr = (CheckBox) findViewById(R.id.bpr);
        CheckBox lpr = (CheckBox)findViewById(R.id.lpr);

        bpr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer k = 1;
                Intent i = new Intent(Settings.this, calc.class);
                i.putExtra("key",k);
                startActivity(i);
                finish();
            }
        });

        lpr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Integer k = 0;
                Intent i = new Intent(Settings.this, calc.class);
                i.putExtra("key",k);
                startActivity(i);
                finish();
            }
        });*/

        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
