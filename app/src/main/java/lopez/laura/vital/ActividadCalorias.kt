package lopez.laura.vital

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.PieChart

class ActividadCalorias : AppCompatActivity() {
    private lateinit var pchart: PieChart
    private lateinit var bchart: BarChart
    private lateinit var cantidades: ArrayList<Int>
    private lateinit var semana: ArrayList<String>
    private lateinit var colores: ArrayList<Color>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_calorias)
        bchart = findViewById(R.id.barchar)
        pchart = findViewById(R.id.piechart)


    }
    private fun getChart(c: Chart, descripcion: String, textColor: Int, background: Int, animacion: Int): Chart{

    }
}