package com.marangon.appexamenfinal.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.marangon.appexamenfinal.R;
import com.marangon.appexamenfinal.entity.Paciente;
import com.marangon.appexamenfinal.model.DAOPaciente;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtDNI, edtNom, edtApe, edtSintomas, edtAntecedentes;
    Button btnAccion;
    ListView lstPacientes;

    Paciente p;
    DAOPaciente daoPaciente;

    ArrayList<Paciente> listaPacientes;

    int idPaciente = 0;
    int dni;
    String ape, nom, sintomas, antecedentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        daoPaciente = new DAOPaciente(this);
        daoPaciente.openBD();
        listaPacientes = new ArrayList<>();
        asignarReferencias();
        listarPacientes();
    }

    public void asignarReferencias(){
        edtDNI = findViewById(R.id.edtDNI);
        edtNom = findViewById(R.id.edtNom);
        edtApe = findViewById(R.id.edtApe);
        edtSintomas = findViewById(R.id.edtSintomas);
        edtAntecedentes = findViewById(R.id.edtAntecedentes);
        btnAccion = findViewById(R.id.btnAccion);
        lstPacientes = findViewById(R.id.lstPacientes);
        registerForContextMenu(lstPacientes);

    }

    public void listarPacientes(){

        listaPacientes = daoPaciente.getPacientes();

        ArrayList<String> lista = new ArrayList<>();

        for(Paciente p:listaPacientes){
            lista.add(p.getId() + " " + p.getDni() + " " + p.getApe() + " " + p.getNom() + " " + p.getSintomas() + " " + p.getAntecedentes());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        lstPacientes.setAdapter(adapter);

    }

    public void capturarDatos(){

        dni = Integer.parseInt(edtDNI.getText() + "");
        ape = edtApe.getText() + "";
        nom = edtNom.getText() + "";
        sintomas = edtSintomas.getText() + "";
        antecedentes = edtAntecedentes.getText() + "";

        p = new Paciente(dni, ape, nom, sintomas, antecedentes);

    }

    public void accion(View v){

        capturarDatos();

        if(idPaciente == 0){
            if (daoPaciente.existeDNI(dni)){
                Toast.makeText(this, "DNI repetido, no se puede registrar!!!", Toast.LENGTH_SHORT).show();
            }else{
                daoPaciente.registrarPaciente(p);
                Toast.makeText(this, "Paciente registrado!!!", Toast.LENGTH_SHORT).show();
            }
        }else{
            p.setId(idPaciente);
            daoPaciente.modificarPaciente(p);
            Toast.makeText(this, "Paciente modificado exitosamente!!!", Toast.LENGTH_SHORT).show();
            idPaciente = 0;
            btnAccion.setText("REGISTRAR");
        }

        limpiar();
        listarPacientes();

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()){
            case R.id.modificar: setDatos(info.position); break;
            case R.id.eliminar: setId(info.position); daoPaciente.eliminarPaciente(idPaciente); idPaciente = 0; listarPacientes(); break;
        }

        return true;

    }

    public void setDatos(int pos){

        p = listaPacientes.get(pos);
        btnAccion.setText("MODIFICAR");
        idPaciente = p.getId();
        edtDNI.setText(p.getDni() + "");
        edtApe.setText(p.getApe());
        edtNom.setText(p.getNom());
        edtSintomas.setText(p.getSintomas());
        edtAntecedentes.setText(p.getAntecedentes());
    }

    public void setId(int pos){

        p = listaPacientes.get(pos);
        idPaciente = p.getId();
    }

    public void limpiar(){
        edtDNI.setText("");
        edtApe.setText("");
        edtNom.setText("");
        edtSintomas.setText("");
        edtAntecedentes.setText("");
    }
}