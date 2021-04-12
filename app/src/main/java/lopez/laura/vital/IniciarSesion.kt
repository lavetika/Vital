package lopez.laura.vital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class IniciarSesion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_iniciar_sesion)

        var btnCrearCuenta: Button = findViewById(R.id.button_crear_cuenta)
        btnCrearCuenta.setOnClickListener {
            startActivity(Intent(this, FormularioRegistro::class.java))
        }
    }
}