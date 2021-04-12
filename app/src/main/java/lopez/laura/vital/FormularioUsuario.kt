package lopez.laura.vital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FormularioUsuario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_usuario)

        var btnCalcularPeso: Button = findViewById(R.id.btn_calcular_peso)
        btnCalcularPeso.setOnClickListener {
            startActivity(Intent(this, Inicio::class.java))
        }
    }
}