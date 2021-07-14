package com.untels.oyevecino

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import com.untels.oyevecino.databinding.ActivityMenuPrincipalBinding
import kotlinx.android.synthetic.main.activity_menu__principal.*

class Menu_Principal : AppCompatActivity() {
    private val PHONE_CALL_REQUEST_CODE =1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_menu__principal)
        val binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnAtras?.setOnClickListener {
            val auth = FirebaseAuth.getInstance()
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
            auth.signOut()
            finish()
        }//FIN DE BOTON INICIAR

        binding.btnVer?.setOnClickListener {
            val intent = Intent(this, Menu_Categorias::class.java)
            startActivity(intent)
        }//FIN DE BOTON VER

        binding.btnSettings?.setOnClickListener {
            val intent = Intent(this, Menu_Settings::class.java)
            startActivity(intent)
        }//FIN DE BOTON SETTINGS

        binding.btnPublicar?.setOnClickListener {
            val intent = Intent(this, ActivityPublicar::class.java)
            startActivity(intent)
        }//FIN DE BOTON PUBLICAR

        btnEmergencia.setOnClickListener {
            hacerllamada()
        }//FIN DE BOTON EMERGENCIA

    }


    private fun hacerllamada(){
        val numero="012873804"  //AQUI PONDREMOS EL NUMERO DE EMERGENCIA, QUE POR DEFECTO SERA LA POLICIA DE VES
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$numero")
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            startActivity(intent)
        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE),PHONE_CALL_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray)
    {
        if (requestCode == PHONE_CALL_REQUEST_CODE){
            if(grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                hacerllamada()
            }else{
                Toast.makeText(this,"Debes dar el permiso para hacer la llamada",Toast.LENGTH_LONG).show()
            }
        }
    }


}