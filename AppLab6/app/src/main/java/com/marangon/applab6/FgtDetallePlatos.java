package com.marangon.applab6;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FgtDetallePlatos extends Fragment {

    TextView txtNom, txtStock, txtPrecio, txtDesc;
    ImageView imgFoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lyt_fgt_detalle_platos, container);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        txtNom = getView().findViewById(R.id.txtNombre);
        txtStock = getView().findViewById(R.id.txtStock);
        txtPrecio = getView().findViewById(R.id.txtPrecio);
        txtDesc = getView().findViewById(R.id.txtDesc);
        imgFoto = getView().findViewById(R.id.imgFoto);

    }

    public void mostrar(Plato p){
        txtNom.setText(p.getNom());
        txtStock.setText(p.getStock() + "");
        txtPrecio.setText(p.getPrecio() + "");
        txtDesc.setText(p.getDesc());
        imgFoto.setImageResource(p.getIdFoto());
    }

}