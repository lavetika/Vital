package lopez.laura.vital

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class EjerciciosGluteos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicios_gluteos)

        val imgSentadilla: ImageView = findViewById(R.id.imgSentadilla) as ImageView

        imgSentadilla.setOnClickListener {
            var intent: Intent = Intent(this, DetalleEjercicio::class.java)
            startActivity(intent)
        }
    }
}