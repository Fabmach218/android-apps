package com.marangon.appfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentLista extends Fragment {

    ArrayList<Presidente> lista;
    ListView lstPresidentes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lyt_fgt_lista, null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lstPresidentes = getView().findViewById(R.id.lstPresidentes);
        lista = new ArrayList<>();

        lista.add(new Presidente(1, "Fernando Belaúnde Terry", "Acción Popular", "1963 - 1968 / 1980 - 1985", R.drawable.fernando_belaunde));
        lista.add(new Presidente(2, "Alan García", "APRA", "1985 - 1990 / 2006 - 2011", R.drawable.alan_garcia));
        lista.add(new Presidente(3, "Valentín Paniagua", "Acción Popular", "2000 - 2001", R.drawable.valentin_paniagua));
        lista.add(new Presidente(4, "Ollanta Humala", "Gana Perú", "2011 - 2016", R.drawable.ollanta_humala));
        lista.add(new Presidente(5, "Martín Vizcarra", "Peruanos Por el Kambio", "2018 - 2020", R.drawable.martin_vizcarra));
        lista.add(new Presidente(6, "Francisco Sagasti", "Partido Morado", "2020 - 2021", R.drawable.francisco_sagasti));

        ArrayList<String> nombres = new ArrayList<>();

        for (Presidente objP:lista) {
            nombres.add(objP.getNom());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, nombres);
        lstPresidentes.setAdapter(adapter);
        lstPresidentes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                ((IPresidente)getActivity()).seleccionarPresidente(lista.get(i));
            }
        });

    }



}