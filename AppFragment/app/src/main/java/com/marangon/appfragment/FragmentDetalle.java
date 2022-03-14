package com.marangon.appfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDetalle extends Fragment {

    TextView txtNom, txtPartido, txtPeriodo;
    ImageView ivwFoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lyt_fgt_detalle, container);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        txtNom = getView().findViewById(R.id.txtNom);
        txtPartido = getView().findViewById(R.id.txtPartido);
        txtPeriodo = getView().findViewById(R.id.txtPeriodo);
        ivwFoto = getView().findViewById(R.id.ivwFoto);

    }

    public void mostrar(Presidente p){
        txtNom.setText(p.getNom());
        txtPartido.setText(p.getPartido());
        txtPeriodo.setText(p.getPeriodo());
        ivwFoto.setImageResource(p.getIdFoto());
    }


}