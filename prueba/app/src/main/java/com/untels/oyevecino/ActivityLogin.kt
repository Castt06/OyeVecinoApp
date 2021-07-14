package com.untels.oyevecino
import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import com.google.firebase.auth.FirebaseAuth
import com.untels.oyevecino.databinding.ActivityLoginBinding

class ActivityLogin : AppCompatActivity() {
    private  var loadingDialog :Dialog? = null
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
        //FIN DE BOTON INICIAR
        //binding.btnIniciar?.setOnClickListener {
        //   val intent = Intent(this, Menu_Principal::class.java)
        //    startActivity(intent)
        //}
        //FIN DE BOTON REGISTRAR
        binding.btnRegistro?.setOnClickListener {
            val intent = Intent(this, menu_registro::class.java)
            startActivity(intent)
        }

        //BOTON FACEBOOK
        binding.btnFacebook?.setOnClickListener {
            val intent = Intent(this, ActivityWeb::class.java)
            startActivity(intent)
        }
        //BOTON INSTAGRAM
        binding.btnInstagram?.setOnClickListener {
            val intent = Intent(this, ActivityWebInstagram::class.java)
            startActivity(intent)
        }
        //BOTON TWITTER
        binding.btnTwitter?.setOnClickListener {
            val intent = Intent(this, ActivityWebTwitter::class.java)
            startActivity(intent)
        }
        //BOTON OLVIDO LA CONTRASEÑA
        binding.btnOlvidoContra?.setOnClickListener {
            val intent = Intent(this, OlvidoContra::class.java)
            startActivity(intent)
        }
    }
    fun setup(){
        binding.btnIniciar.setOnClickListener {
            validarCorreo()
            validarContrasenia()

            var email = binding.edtCorreo.text.toString()
            var password = binding.edtContrasenia.text.toString()
            val mPref: SharedPreferences? = null
            if( email.isNotEmpty() && password.isNotEmpty() &&
                binding.edtContrasenia.text.length >=6){
                showLoading()
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email.toLowerCase(),
                    password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val user = mPref!!.getString("users", "")
                        //accediendo al email del usuario registrado
                        if (user == "clients") {
                            val intent = Intent(this, Menu_Principal::class.java).apply{
                            }
                            startActivity(intent)
                        } else {
                            val intent = Intent(this, Menu_Admin::class.java).apply{
                            }
                            startActivity(intent)
                        }
                    }else{
                        showAlert()
                    }
                    hideLoading()
                }
            }
        }
    }
    //mostrar ensaje carga
    private fun hideLoading(){
        loadingDialog?.let { if(it.isShowing)it.cancel() }
    }
    private fun showLoading(){
        hideLoading()
        loadingDialog=CommonUtils.ShowDialogoCarga(context=this)
    }
    fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("El usuario no existe o contraseña invalida")
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    fun validarCorreo(){
        val email = binding.edtCorreo.text.toString()
        if(email.isEmpty()){
            binding.edtCorreo.error = "El campo no debe estar vacio"
        }else if(!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()){
            binding.edtCorreo.error = "Ingrese un correo valido, debe tener un @"
        }else{
            binding.edtCorreo.error = null
        }
    }
    fun validarContrasenia(){
        val contrasenia = binding.edtContrasenia.text.toString()
        if(contrasenia.isEmpty() || contrasenia.length<6 ){
            binding.edtContrasenia.error = "Debe ingresar su contraseña mayor o igual a 6 caracteres"
        }else {
            binding.edtContrasenia.error= null
        }
    }

    //En correccion
    fun inicioAutomatico(){
        super.onStart()
        if(FirebaseAuth.getInstance().currentUser != null){
            startActivity(Intent(this,Menu_Principal::class.java))
            finish()
        }

    }
}