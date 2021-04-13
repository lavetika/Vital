package lopez.laura.vital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_comida.*

class CatalogoReceta : AppCompatActivity() {
    var adapterRecetas: RecetaAdapter? = null

    var recetas = ArrayList<Receta>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receta)

        cargarRecetas()

        adapterRecetas = RecetaAdapter(this, recetas)
        gridviewComida.adapter = adapterRecetas
    }

    fun cargarRecetas(){



    }

}