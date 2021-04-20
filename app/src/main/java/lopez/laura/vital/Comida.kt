package lopez.laura.vital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_comida.*

class Comida : AppCompatActivity() {
    var adapterComida: RecetaAdapter? = null

    var comidas = ArrayList<Receta>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comida)

        cargarComidas()

        adapterComida = RecetaAdapter(this, comidas)

        gridviewComida.adapter = adapterComida
    }

    fun cargarComidas(){
        comidas.add(Receta(getString(R.string.pollo_brocoli), R.drawable.pollobrocoli, getString(R.string.ingredientes_pollo_brocoli), getString(R.string.procedimiento_pollo_brocoli)))
        comidas.add(Receta(getString(R.string.caldo_papa), R.drawable.caldopapa, getString(R.string.ingredientes_pollo_brocoli), getString(R.string.procedimiento_pollo_brocoli)))
        comidas.add(Receta(getString(R.string.pasta_alfredo), R.drawable.pastaalfredo, getString(R.string.ingredientes_pollo_brocoli), getString(R.string.procedimiento_pollo_brocoli)))
        comidas.add(Receta(getString(R.string.lentejas), R.drawable.lentejas, getString(R.string.ingredientes_pollo_brocoli), getString(R.string.procedimiento_pollo_brocoli)))
        comidas.add(Receta(getString(R.string.albondigas), R.drawable.albondigas, getString(R.string.ingredientes_pollo_brocoli), getString(R.string.procedimiento_pollo_brocoli)))
        comidas.add(Receta(getString(R.string.enchiladas), R.drawable.enchiladas, getString(R.string.ingredientes_pollo_brocoli), getString(R.string.procedimiento_pollo_brocoli)))
        comidas.add(Receta(getString(R.string.chile_relleno), R.drawable.chilerelleno, getString(R.string.ingredientes_pollo_brocoli), getString(R.string.procedimiento_pollo_brocoli)))
        comidas.add(Receta(getString(R.string.crema_champiniones), R.drawable.cremachampiniones, getString(R.string.ingredientes_pollo_brocoli), getString(R.string.procedimiento_pollo_brocoli)))
    }
}