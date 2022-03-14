package com.marangon.applab7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FgtFoto extends Fragment {

    ImageView imgFoto;
    TextView txtNombre;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.lyt_fgt_foto, container);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        imgFoto = getView().findViewById(R.id.imgFoto2);
        txtNombre = getView().findViewById(R.id.txtNombre2);
        registerForContextMenu(imgFoto);

    }

    public void mostrar(Prenda p){
        imgFoto.setImageResource(p.getIdFoto());
        txtNombre.setText(p.getNom());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.menu_context,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.detalles:

                Intent intent = new Intent(getActivity(), ActDetalle.class);
                startActivity(intent);

                return true;

            case R.id.act_stock:



                return true;

            default: return super.onOptionsItemSelected(item);
        }

    }



}