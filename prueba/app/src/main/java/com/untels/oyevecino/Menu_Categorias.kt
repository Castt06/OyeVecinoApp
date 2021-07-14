package com.untels.oyevecino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.untels.oyevecino.databinding.ActivityMenuCategoriasBinding

class Menu_Categorias : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMenuCategoriasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnVolber?.setOnClickListener {
            val intent = Intent(this, Menu_Principal::class.java)
            startActivity(intent)
        }//FIN DE BOTON VOLVER

        binding.btnOP1?.setOnClickListener {
            val intent = Intent(this, ActivityHospitales::class.java)
            startActivity(intent)
        }//FIN DE BOTON HOSPITALES

        binding.btnOP2?.setOnClickListener {
            val intent = Intent(this, Menu_Principal::class.java)
            startActivity(intent)
        }//FIN DE BOTON TRANSITO

        binding.btnOP3?.setOnClickListener {
            val intent = Intent(this, Menu_Principal::class.java)
            startActivity(intent)
        }//FIN DE BOTON ESTABLECIMIENTO

        binding.btnOP4?.setOnClickListener {
            val intent = Intent(this, Menu_Principal::class.java)
            startActivity(intent)
        }//FIN DE BOTON AREAS PUBLICAS

        binding.btnOP5?.setOnClickListener {
            val intent = Intent(this, Menu_Principal::class.java)
            startActivity(intent)
        }//FIN DE BOTON LIMPIEZA PUBLICA

        binding.btnOP6?.setOnClickListener {
            val intent = Intent(this, Menu_Principal::class.java)
            startActivity(intent)
        }//FIN DE BOTON RECOJO MUNICIPAL

        binding.btnOP7?.setOnClickListener {
            val intent = Intent(this, Menu_Principal::class.java)
            startActivity(intent)
        }//FIN DE BOTON SEGURIDAD
    }
}