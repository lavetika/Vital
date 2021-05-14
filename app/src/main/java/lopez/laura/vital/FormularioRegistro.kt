package lopez.laura.vital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_formulario_registro.*

class FormularioRegistro : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_registro)

        auth = Firebase.auth

        var btnCrear: Button = findViewById(R.id.btn_crear)
        btnCrear.setOnClickListener {
            validarRegistro()
        }
    }

    private fun validarRegistro(){

        var correo: String = et_correo.text.toString()
        var contrasena1: String = et_password1.text.toString()
        var contrasena2: String = et_password2.text.toString()

        if (!correo.isNullOrBlank() && !contrasena1.isNullOrBlank() && !contrasena2.isNullOrBlank()){

            if(contrasena1 == contrasena2){
                crearUsuario(correo,  contrasena1)
            } else  {
                Toast.makeText(this, "Las contraseña no coinciden",
                    Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(this, "Debes llenar todos los campos",
                Toast.LENGTH_SHORT).show()
        }
    }

    private fun crearUsuario(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    Toast.makeText(baseContext, "${user.email} El usaurio se ha creado correctamente.",
                        Toast.LENGTH_SHORT).show()

                    var intent: Intent = Intent(this, FormularioUsuario::class.java)
                    startActivity(intent)

                } else {

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}