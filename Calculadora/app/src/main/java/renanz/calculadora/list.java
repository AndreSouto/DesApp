package renanz.calculadora;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class list extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    private ListView mlistView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mlistView = (ListView)findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);
        
        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: mostrando dados na tela");
        //pega o dado e junta em uma lista
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        //pega o valor da coluna 1 do banco de dados e adiciona no ArrayList
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        //cria uma lista adaptavel e a instancia
        //ListAdapter adpt = new ArrayAdapter<>(this, R.layout.simple_list_item_1, listData);
        //mlistView.setAdapter(adpt);
    }
}
