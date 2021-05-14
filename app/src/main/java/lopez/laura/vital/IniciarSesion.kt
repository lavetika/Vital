package lopez.laura.vital

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_iniciar_sesion.*


class IniciarSesion : AppCompatActivity() {

    private val LOGIN = 100
    private val callbackManager = CallbackManager.Factory.create()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        auth = Firebase.auth

        //Crear nueva cuenta
        button_crear_cuenta.setOnClickListener {
            var intent: Intent = Intent(this, FormularioRegistro::class.java)
            startActivity(intent)
        }
        //Iniciar sesion con correo
        button_iniciar_sesion.setOnClickListener {
            validarIngreso()
        }

        //Iniciar sesion Google
        button_iniciar_sesion_google.setOnClickListener {
            ingresarGoogle()
        }

        //Iniciar sesion Facebook
        button_iniciar_sesion_fb.setOnClickListener {
            ingresarFacebook()
        }
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(this)
    }

    //Despliega la pantalla de inicio al iniciar sesion
    private fun ingresarNuevaPantalla(){
        var intent: Intent =  Intent(this, Wrapper::class.java)
        startActivity(intent)
    }

    //Valida si los campos no estan vacios
    private fun validarIngreso(){

        var correo: String = et_correo_login.text.toString()
        var contrasena: String = et_password_login.text.toString()

        if (!correo.isNullOrBlank() && !contrasena.isNullOrBlank()){
            ingresar(correo, contrasena)
        } else {
            Toast.makeText(baseContext, "Debe llenar todos los campos",
                Toast.LENGTH_SHORT).show()
        }
    }

    //Ingresa sesion con correo y contrasena
    private fun ingresar(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser

                    ingresarNuevaPantalla()

                } else {
                    mostrarMensajeError()
                }
            }
    }

    //Ingresa con Google
    private fun ingresarGoogle(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignInClient.signOut()

        startActivityForResult(googleSignInClient.signInIntent, LOGIN)
    }

    //Ingresa con fb
    private fun ingresarFacebook(){
        LoginManager.getInstance().logInWithReadPermissions(this, listOf("email"))

        LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult>{

                    override fun onSuccess(result: LoginResult?) {
                        result?.let {
                            val token = it.accessToken

                            val credential = FacebookAuthProvider.getCredential(token.token)

                            FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                                if (it.isSuccessful){
                                    ingresarNuevaPantalla()

                                } else{
                                    mostrarMensajeError()
                                }
                            }
                        }
                    }

                    override fun onCancel() {
                    }

                    override fun onError(error: FacebookException?) {
                        mostrarMensajeError()
                    }})
    }

    //Si algo falla muestro mensaje de error
    private fun mostrarMensajeError(){
        Toast.makeText(this, "Error al inciciar sesion",
                Toast.LENGTH_SHORT).show()
    }

    //Captura el resultado de las operacion de inicio de sesion de terceros
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LOGIN && data != null){
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val account = task.getResult(ApiException::class.java)

            if (account != null){

                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful){
                        ingresarNuevaPantalla()
                    } else{
                        mostrarMensajeError()
                    }
                }
            }
        }
    }
}