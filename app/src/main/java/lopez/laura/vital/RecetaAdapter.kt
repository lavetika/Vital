package lopez.laura.vital

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.vista_comida.view.*

class RecetaAdapter : BaseAdapter {
    var recetas = ArrayList<Receta>()
    var context: Context? = null

    constructor(context: Context, recetas: ArrayList<Receta>){
        this.context = context
        this.recetas = recetas
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var receta = recetas[position]
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflator.inflate(R.layout.vista_comida, null)

        view.iv_imagen.setImageResource(receta.imagen)
        view.tv_titulo.setText(receta.nombre)
        view.iv_imagen.setOnClickListener {
            var intent = Intent(context, DetalleComida::class.java)
            intent.putExtra("nombre", receta.nombre)
            intent.putExtra("imagen", receta.imagen)
            intent.putExtra("ingredientes", receta.ingredientes)
            intent.putExtra("procedimiento", receta.procedimiento)
            intent.putExtra("position", position)
            context!!.startActivity(intent)
        }
        return view
    }

    override fun getItem(position: Int): Any {
        return recetas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return recetas.size
    }


}