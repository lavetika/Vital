package lopez.laura.vital.Fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_inicio.*
import kotlinx.android.synthetic.main.fragment_inicio.view.*
import lopez.laura.vital.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InicioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InicioFragment : Fragment() {
// T    ODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val SUCCESS_CODE = 5
    var calorias: Int = 0
    var alimentos = ArrayList<Alimento>()
    lateinit var tv_calorias: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)


        view.btn_favoritos.setOnClickListener {
            view.context.startActivity(Intent(view.context, FavoritosInicio::class.java))
        }

        view.btn_desayuno.setOnClickListener {
            val intent = Intent(view.context, ComidaDiario::class.java)

            val args = Bundle()
            args.putSerializable("alimentos", alimentos)
            intent.putExtra("alimentos", args)

            startActivity(intent)

        }

        view.agregar_comida.setOnClickListener {
            val intent: Intent = Intent(view.context, AgregarComida::class.java)
            startActivityForResult(intent, SUCCESS_CODE)
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK){
        if(requestCode == SUCCESS_CODE){
            var tv_calorias = view!!.findViewById<TextView>(R.id.totalCalorias)

            var alimento: Alimento = data!!.getSerializableExtra("alimento") as Alimento
            alimentos.add(alimento)
            calorias += alimento.calorias
            tv_calorias.text = calorias.toString()
        }
    }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InicioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InicioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}