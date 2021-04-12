package lopez.laura.vital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class FormularioRegistro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_registro)

        var btnCrear: Button = findViewById(R.id.btn_crear)
        btnCrear.setOnClickListener {
            startActivity(Intent(this, FormularioUsuario::class.java))
        }
    }
}