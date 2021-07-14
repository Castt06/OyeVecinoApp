package com.untels.oyevecino

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

import com.untels.oyevecino.databinding.ActivityMenuSettingsBinding

class Menu_Settings : AppCompatActivity() {
    private  var loadingDialog : Dialog? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityMenuSettingsBinding
            override fun onCreate(savedInstanceState: Bundle?) {
                auth = Firebase.auth
                database = Firebase.database.reference
                binding = ActivityMenuSettingsBinding.inflate(layoutInflater)
                super.onCreate(savedInstanceState)
                setContentView(binding.root)
                title = "Inicio"

                datosUsuario()
              //  binding.btnCerrarSesion.setOnClickListener {
               //     FirebaseAuth.getInstance().signOut()
                 //   startActivity(Intent(this, ActivityLogin::class.java))
                  //  finish()
                //}
    }
    fun datosUsuario(){
        val user = auth.currentUser?.uid
        showLoading()
        database.child("users").child("clients").child(user!!).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if(dataSnapshot.exists()) {
                    //usamos dataSnapshot, buscamos al hijo nombres y obtenemos su valor con getValue
                    val correo: String = dataSnapshot.child("correo").getValue().toString()
                    val usuario: String = dataSnapshot.child("usuario").getValue().toString()
                    val nombre: String = dataSnapshot.child("nombre").getValue().toString()
                    val apellidoPaterno: String = dataSnapshot.child("apaterno").getValue().toString()
                    val apellidoMaterno: String = dataSnapshot.child("amaterno").getValue().toString()
                    println(correo)
                    println(usuario)
                    println(apellidoMaterno)
                    println(apellidoPaterno)
                    binding.tvCorreo.setText("Correo: \n $correo")
                    binding.tvUsuario.setText("@$usuario")
                    binding.tvNombre.setText("Nombre completo: \n $nombre $apellidoPaterno $apellidoMaterno")
                    hideLoading()
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }
    private fun hideLoading(){
        loadingDialog?.let { if(it.isShowing)it.cancel() }
    }
    private fun showLoading(){
        hideLoading()
        loadingDialog=DialogoCarga.ShowDialogoCarga(context=this)
    }
}
