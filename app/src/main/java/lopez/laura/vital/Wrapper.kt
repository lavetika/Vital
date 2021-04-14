package lopez.laura.vital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_wrapper.*
import lopez.laura.vital.Fragments.*

class Wrapper : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wrapper)

        val inicioFragment = InicioFragment()
        val alimentoFragment = AlimentoFragment()
        val recetaFragment = RecetaFragment()
        val ejerciciosFragment = EjercicioFragment()
        val perfilFragment = PerfilFragment()

        makeCurrentFragment(inicioFragment)

        bottom_navegation.itemIconTintList = null
        bottom_navegation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_inicio -> makeCurrentFragment(inicioFragment)
                R.id.ic_alimento -> makeCurrentFragment(alimentoFragment)
                R.id.ic_receta -> makeCurrentFragment(recetaFragment)
                R.id.ic_ejercicio -> makeCurrentFragment(ejerciciosFragment)
                R.id.ic_perfil -> makeCurrentFragment(perfilFragment)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
}