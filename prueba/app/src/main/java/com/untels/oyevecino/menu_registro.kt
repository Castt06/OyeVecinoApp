package com.untels.oyevecino

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.util.PatternsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database
import com.untels.oyevecino.R
import com.untels.oyevecino.databinding.ActivityMenuRegistroBinding
import java.util.regex.Pattern

class menu_registro : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private lateinit var binding: ActivityMenuRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuRegistroBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnRegistrar.setOnClickListener {
            validarCorreo();
            validarUsuario();
            validarNombres()
            validarApellidos()
            validarContrasenia()
            validarConfirmarContrasenia()
        }
        setup()
    }

    fun setup() {
        title = "Autentication"
        database = Firebase.database.reference
        FirebaseAuth.getInstance()
        binding.btnRegistrar.setOnClickListener {
            val usuario = binding.edtUsuario.text.toString()
            val correo = binding.etCorreo.text.toString()
            val contrasenia = binding.edtContrasenia.text.toString()
            val nombre = binding.etNombre.text.toString()
            val apaterno = binding.etApellidoPaterno.text.toString()
            val amaterno = binding.etApellidoMaterno.text.toString()

            if (binding.etCorreo.text.isNotEmpty() && binding.edtContrasenia.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.etCorreo.text.toString(),
                        binding.edtContrasenia.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful) {
                        var Uid: String? = FirebaseAuth.getInstance().getUid()
                        val user = User(correo,usuario,contrasenia,nombre,apaterno,amaterno)
                        //accediendo al email del usuario registrado
                        database.child("users").child("clients").child(Uid.toString()).setValue(user)

                        /*     .addOnCanceledListener {
                         if(it.isSuccessful){
                             showHome(it.result?.user?.email?:"")
                         }else {
                             showAlert()
                         }
                     }*/
                        showHome(it.result?.user?.email ?: "")
                    } else {
                        showAlert()
                    }
                }
            }
        }

    }

    fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun showHome(email: String) {
        val intent = Intent(this, Menu_Principal::class.java).apply {
            putExtra("email", email)
        }
        startActivity(intent)
    }

    fun validarCorreo(): Boolean {
        val email = binding.etCorreo.text.toString()
        return if (email.isEmpty()) {
            binding.etCorreo.error = "El campo no debe estar vacio"
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etCorreo.error = "Ingrese un correo valido"
            false
        } else {
            binding.etCorreo.error = null
            true
        }
    }

    fun validarUsuario(): Boolean {
        val usuario = binding.edtUsuario.text.toString()
        val valNomb = Pattern.compile("^[0-9a-zA-Z]+\$")
        return if (usuario.isEmpty()) {
            binding.etCorreo.error = "El campo no debe estar vacio"
            false
        } else if (!valNomb.matcher(usuario).matches()) {
            binding.edtUsuario.error = "Debe ingresar letras o numeros sin espacio"
            false
        } else {
            binding.edtUsuario.error = null
            true
        }
    }

    fun validarNombres(): Boolean {
        val nombres = binding.etNombre.text.toString()
        val valNomb = Pattern.compile("^[a-zA-Z]+ ?([a-zA-Z]+ ?){1,2}\$")
        return if (nombres.isEmpty()) {
            binding.etNombre.error = "El campo no debe estar vacio"
            false
        } else if (!valNomb.matcher(nombres).matches()) {
            binding.etNombre.error = "Debe ingresar solo letras"
            false
        } else {
            binding.etNombre.error = null
            true
        }
    }

    fun validarApellidos(): Boolean {
        val apellidos = binding.etApellidoPaterno.text.toString()
        val valNomb = Pattern.compile("^[a-zA-Z]+ ?([a-zA-Z]+ ?){1,2}\$")
        return if (apellidos.isEmpty()) {
            binding.etApellidoPaterno.error = "El campo no debe estar vacio"
            false
        } else if (!valNomb.matcher(apellidos).matches()) {
            binding.etApellidoPaterno.error = "Debe ingresar solo letras"
            false
        } else {
            binding.etApellidoPaterno.error = null
            true
        }
    }

    fun validarContrasenia(): Boolean {
        val contrasenia = binding.edtContrasenia.text.toString()
        val valNomb = Pattern.compile("^[0-9a-zA-Z]+\$")
        return if (contrasenia.isEmpty()) {
            binding.edtContrasenia.error = "El campo no debe estar vacio"
            false
        } else if (!valNomb.matcher(contrasenia).matches()) {
            binding.edtContrasenia.error = "La contraseña solo puede contar con numerosy letras"
            false
        } else {
            binding.edtContrasenia.error = null
            true
        }
    }

    fun validarConfirmarContrasenia(): Boolean {
        val confirmarContrasenia = binding.edtContrasenia1.text.toString()
        val valNomb = Pattern.compile("^[0-9a-zA-Z]+\$")
        return if (confirmarContrasenia.isEmpty()) {
            binding.edtContrasenia1.error = "El campo no debe estar vacio"
            false
        } else if (!valNomb.matcher(confirmarContrasenia).matches()) {
            binding.edtContrasenia1.error = "La contraseña solo puede contar con numeros, letras minuscula o mayusculas"
            false
        } else if (binding.edtContrasenia.text.toString() != binding.edtContrasenia1.text.toString()) {
            binding.edtContrasenia1.error = "Debe coincidir con su contraseña"
            false
        } else {
            binding.edtContrasenia1.error = null
            true
        }
    }
}