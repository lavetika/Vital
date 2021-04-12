package lopez.laura.vital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class PantallaPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_principal)

        var icono: ImageView = findViewById(R.id.icono_inicio)
        icono.setOnClickListener {
            startActivity(Intent(this, IniciarSesion::class.java))
        }
    }
}