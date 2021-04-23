package lopez.laura.vital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_principal.*

class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val bundle = intent.extras

        if(bundle != null){
            val nombre = bundle.getString("name")
            val correo = bundle.getString("email")

            txtNombreUser.setText(nombre)
            txtCorreo.setText(correo)
        }

        btnContinuar.setOnClickListener {

            val intent= Intent(this, Wrapper::class.java)
        }
    }
}