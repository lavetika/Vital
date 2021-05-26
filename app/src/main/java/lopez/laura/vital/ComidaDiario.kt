package lopez.laura.vital

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_comida_diario.*
import kotlinx.android.synthetic.main.vista_comida.view.*
import java.io.File
import kotlin.collections.ArrayList



class ComidaDiario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comida_diario)

        val storageRef = Firebase.storage.reference.child("images/475c392b-ef9c-4e6f-a602-c719c88d5787.jpg")

        var imagenes = ArrayList<ImageView>()

        val localFile = File.createTempFile("475c392b-ef9c-4e6f-a602-c719c88d5787", "jpg")
        storageRef.getFile(localFile).addOnSuccessListener {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            val bitmap = BitmapFactory.decodeFile(localFile.absolutePath)
            imagenPrueba.setImageBitmap(bitmap)
            imagenes.add(imagenPrueba)

            //gridviewComidaDiario.adapter = ArrayAdapter<ImageView>(this, R.layout.vista_comidas_diario, imagenes)

        }.addOnFailureListener {e ->
            e.printStackTrace()
        }


    }

    fun loadImagesFromFirebase(){}

    private class ComidaDiarioAdapter : BaseAdapter {
        var comidas = ArrayList<ImageView>()
        var context: Context? = null

        constructor(context: Context, comidas: ArrayList<ImageView>){
            this.context = context
            this.comidas = comidas
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var comida = comidas[position]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var view = inflator.inflate(R.layout.vista_comida, null)


            //view.iv_imagen.setImageBitmap()
            //view.tv_titulo.setText()

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