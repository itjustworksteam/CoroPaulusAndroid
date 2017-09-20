package it.itjustworks.coropaulus;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ricky on 9/20/17.
 */

public class CantiAdapter extends BaseAdapter {

    private Activity activity;
    private List<Canto> canti;
    private static LayoutInflater inflater=null;

    public CantiAdapter(Activity a, List<Canto> data){
        this.activity = a;
        this.canti = data;
        this.inflater = (LayoutInflater)this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if(canti == null || canti.size() == 0){
            return 1;
        }
        return canti.size();
    }

    @Override
    public Canto getItem(int i) {
        return canti.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View vi = view;
        if(view == null) {
            System.out.println("view == null");
            vi = this.inflater.inflate(R.layout.canto_layout, null);
        }
        TextView id = (TextView)vi.findViewById(R.id.cantoName);
        if(canti == null || canti.size() == 0){
            id.setText("Nessun Ordine Trovato");
        } else {
            Canto canto = canti.get(i);
            id.setText(canto.nome());
        }
        return vi;
    }
}
