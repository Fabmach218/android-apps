package com.marangon.applab6;

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

public class FgtListaPlatos extends Fragment {

    ArrayList<Plato> listaPlatos;
    ListView lstPlatos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lyt_fgt_lista_platos, null);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        listaPlatos = (ArrayList<Plato>) getActivity().getIntent().getExtras().getSerializable("datos");
        lstPlatos = getView().findViewById(R.id.lstPlatos);

        ArrayList<String> nombres = new ArrayList<>();

        for (Plato objP:listaPlatos) {
            nombres.add(objP.getNom());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, nombres);
        lstPlatos.setAdapter(adapter);
        lstPlatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                ((IPlato)getActivity()).seleccionarPlato(listaPlatos.get(i));
            }
        });

    }

}