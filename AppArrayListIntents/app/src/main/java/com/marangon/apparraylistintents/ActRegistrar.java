package com.marangon.apparraylistintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class ActRegistrar extends AppCompatActivity {

    EditText edtApe, edtNom, edtNumTelf, edtEmail;
    ArrayList<Cliente> listaClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lyt_registrar);
        asignarReferencias();
        recuperarDatos();
    }

    private void recuperarDatos() {
        listaClientes = (ArrayList<Cliente>) getIntent().getExtras().getSerializable("datos");
    }

    private void asignarReferencias() {
        edtApe = findViewById(R.id.edtApe);
        edtNom = findViewById(R.id.edtNom);
        edtNumTelf = findViewById(R.id.edtNumTelf);
        edtEmail = findViewById(R.id.edtEmail);
    }

    public void registrarCliente(View view){

        String ape = edtApe.getText() + "";
        String nom = edtNom.getText() + "";
        long numTelf = Long.parseLong(edtNumTelf.getText() + "");
        String email = edtEmail.getText() + "";

        Cliente objC = new Cliente(ape, nom, numTelf, email);
        listaClientes.add(objC);
        limpiar();

    }

    private void limpiar() {
        edtApe.setText("");
        edtNom.setText("");
        edtNumTelf.setText("");
        edtEmail.setText("");
    }

    public void irMenuP(View view){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datos", listaClientes);
        intent.putExtras(bundle);
        startActivity(intent);
    }

}