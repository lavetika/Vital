package lopez.laura.vital

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.exifinterface.media.ExifInterface
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_comida_diario.*
import kotlinx.android.synthetic.main.vista_comida.view.*
import java.io.File


class ComidaDiario : AppCompatActivity() {

    companion object{
        var alimentos = ArrayList<Alimento>()
        var imagenesAlimento = ArrayList<Bitmap>()
        var bundle = Bundle()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comida_diario)

        bundle = (intent.getBundleExtra("alimentos") as Bundle)
        alimentos = bundle.getSerializable("alimentos") as ArrayList<Alimento>
        loadLocalImages()

        var adapter: ComidaDiarioAdapter = ComidaDiarioAdapter(this, alimentos)
        gridviewComidaDiario.adapter = adapter


    }

    fun getAlimento(): ArrayList<Alimento>{
        return alimentos
    }

    fun loadLocalImages(){

        for(i in alimentos.indices){

            val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString() + "/${alimentos[i].nombre}" + ".jpg"

            val imgFile = File(path)
            val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)

            imagenesAlimento.add(myBitmap)


        }
    }


    private class ComidaDiarioAdapter : BaseAdapter {
        var comidas = ArrayList<Alimento>()
        var context: Context? = null

        constructor(context: Context, comidas: ArrayList<Alimento>){
            this.context = context
            this.comidas = alimentos
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var comida = comidas[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var view = inflator.inflate(R.layout.vista_comida, null)


            view.iv_imagen.setImageBitmap(imagenesAlimento[position])
            view.tv_titulo.text = comida.nombre

            return view
        }

        override fun getItem(position: Int): Any {
            return comidas[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return comidas.size
        }


    }

}