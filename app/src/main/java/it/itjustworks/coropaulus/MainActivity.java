package it.itjustworks.coropaulus;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static final String PDF_URL = "it.itjustworks.CoroPaulus.PdfURL";

    private ListView listView;
    private List<Canto> canti;
    private CantiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.listView = (ListView) findViewById(R.id.listView);
        new CoroPaulusGetCanti().execute();
        this.listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Canto canto = this.canti.get(i);
        Intent intent = new Intent(this, DisplayPDFActivity.class);
        intent.putExtra(PDF_URL, canto.url());
        startActivity(intent);
    }

    private class CoroPaulusGetCanti extends AsyncTask<Void, Void, List<Canto>>{

        @Override
        protected List<Canto> doInBackground(Void... voids) {
            CoroPaulus coro = new CoroPaulus();
            try {
                List<Canto> canti = coro.prendiCanti();
                return canti;
            } catch (IOException e) {
                System.out.println("Exception: " + e.getMessage());
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Canto> cantos) {
            super.onPostExecute(cantos);
            canti = cantos;
            adapter = new CantiAdapter(MainActivity.this, canti);
            listView.setAdapter(adapter);
        }
    }
}
