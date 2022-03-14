package com.marangon.applab7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FgtLista extends Fragment {

    ArrayList<Prenda> listaPrendas;
    ListView lstPrendas;

    public int generarAleatorio(int min, int max){
        return (int)(Math.random() * (max - min + 1) + min);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lyt_fgt_lista, null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        listaPrendas = new ArrayList<>();

        listaPrendas.add(new Prenda("Camisa 1", R.drawable.camisa_1, "", 59.9, generarAleatorio(10, 100)));
        listaPrendas.add(new Prenda("Camisa 2", R.drawable.camisa_2, "", 59.9, generarAleatorio(10, 100)));
        listaPrendas.add(new Prenda("Jean 1", R.drawable.jean_1, "", 99.9, generarAleatorio(10, 100)));
        listaPrendas.add(new Prenda("Jean 2", R.drawable.jean_2, "", 99.9, generarAleatorio(10, 100)));
        listaPrendas.add(new Prenda("Polo 1", R.drawable.polo_1, "", 19.9, generarAleatorio(10, 100)));
        listaPrendas.add(new Prenda("Polo 2", R.drawable.polo_2, "", 19.9, generarAleatorio(10, 100)));
        listaPrendas.add(new Prenda("Ropa de baño 1", R.drawable.ropa_bano_1, "", 89.9, generarAleatorio(10, 100)));
        listaPrendas.add(new Prenda("Ropa de baño 2", R.drawable.ropa_bano_2, "", 89.9, generarAleatorio(10, 100)));
        listaPrendas.add(new Prenda("Short 1", R.drawable.short_1, "", 69.9, generarAleatorio(10, 100)));
        listaPrendas.add(new Prenda("Short 2", R.drawable.short_2, "", 69.9, generarAleatorio(10, 100)));

        lstPrendas = getView().findViewById(R.id.lstPrendas);

        ArrayList<String> nombres = new ArrayList<>();

        for (Prenda objP:listaPrendas) {
            nombres.add(objP.getNom());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, nombres);
        lstPrendas.setAdapter(adapter);
        lstPrendas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                ((IPrenda)getActivity()).seleccionarPrenda(listaPrendas.get(i));
            }
        });

    }


}