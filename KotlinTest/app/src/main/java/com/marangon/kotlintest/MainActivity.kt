package com.marangon.kotlintest

import android.app.Person
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var edtNombre : EditText
    lateinit var edtApellido : EditText
    lateinit var edtFecNac : EditText
    lateinit var edtRes : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtNombre = findViewById(R.id.edtNombre) as EditText
        edtApellido = findViewById(R.id.edtApellido) as EditText
        edtFecNac = findViewById(R.id.edtFecNac) as EditText
        edtRes = findViewById(R.id.edtRes) as EditText
    }

    var personas = ArrayList<Persona>()

    fun ingresar(v : View){

        val nombre = edtNombre.text.toString()
        val apellido = edtApellido.text.toString()
        val fecnac = edtFecNac.text.toString()

        personas.add(Persona(nombre,apellido,fecnac))
        edtRes.setText("Se ingresó el item")

    }

    fun listar(v : View){

        edtRes.setText("")

        for(persona in personas){
            edtRes.append("Nombre: " + persona.nombre + "\nApellido: " + persona.apellido + "\nFecha de nacimiento: " + persona.fecnac)
        }


    }



}

class Persona{

    var nombre: String
    var apellido: String
    var fecnac: String

    constructor(nombre: String, apellido:String, fecnac: String){
        this.nombre = nombre
        this.apellido = apellido
        this.fecnac = fecnac
    }

}