package com.marangon.applab5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtApe, edtNom, edtSBas;
    Spinner sprSeg, sprRes;
    RadioGroup rgpGen;
    RadioButton rbtM, rbtF, rbtO;
    ArrayList<Trabajador> listaTrab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asignarReferencias();
    }

    private void asignarReferencias() {
        edtApe = findViewById(R.id.edtApe);
        edtNom = findViewById(R.id.edtNom);
        edtSBas = findViewById(R.id.edtSBas);
        sprSeg = findViewById(R.id.sprSeg);
        sprRes = findViewById(R.id.sprRes);
        rgpGen = findViewById(R.id.rgpGen);
        rbtM = findViewById(R.id.rbtM);
        rbtF = findViewById(R.id.rbtF);
        rbtO = findViewById(R.id.rbtO);
        listaTrab = new ArrayList<>();
    }

    public double calcularSueldoNeto(double sBas, char seg){

        double sNeto = 0;
        double desc = 0;

        switch(seg){
            case 'A': desc = 0.125; break;
            case 'O': desc = 0.08;
        }

        sNeto = sBas - desc * sBas;
        sNeto = Math.rint(sNeto * 100) / 100;

        return sNeto;

    }


    public void registrar(View view){

        String ape = edtApe.getText() + "";
        String nom = edtNom.getText() + "";
        char seg = (sprSeg.getSelectedItem() + "").charAt(0);
        char gen = ' ';
        String msj;
        double sBas = Double.parseDouble(edtSBas.getText() + "");
        double sNeto;

        if(rbtM.isChecked()){
            gen = 'M';
        }else if (rbtF.isChecked()){
            gen = 'F';
        }else {
            gen = 'O';
        }

        if(sprSeg.getSelectedItemPosition() == 0){
            msj = "Debe seleccionar un seguro!!!";
        }else if(gen == ' '){
            msj = "Debe seleccionar un género!!!";
        }else if (sBas < 0){
            msj = "Escriba un sueldo válido!!!";
            edtSBas.setText("");
        } else{
            sNeto = calcularSueldoNeto(sBas, seg);
            Trabajador objT = new Trabajador(ape, nom, seg, gen, sBas, sNeto);
            listaTrab.add(objT);
            msj = "Trabajador registrado!!!";
            limpiar();
        }

        Toast.makeText(this, msj, Toast.LENGTH_SHORT).show();

    }

    public void limpiar() {
        edtApe.setText("");
        edtNom.setText("");
        edtSBas.setText("");
        sprSeg.setSelection(0);
        rgpGen.clearCheck();
    }

    public void mostrar(View view){
        
        ArrayList<String> listaRes = new ArrayList<>();
        listaRes.add("APELLIDO\tNOMBRE\tGÉNERO\tSEGURO\tSUELDO BÁSICO\tSUELDO NETO");

        for (Trabajador objT:listaTrab) {
            listaRes.add(objT.getApe() + "\t" + objT.getNom() + "\t" + objT.getGen() + "\t" + objT.getSeg() + "\t" + objT.getsBasico() + "\t" + objT.getsNeto());
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaRes);
        sprRes.setAdapter(adapter);

    }



}