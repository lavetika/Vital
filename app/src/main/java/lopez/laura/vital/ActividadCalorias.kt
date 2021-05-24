package lopez.laura.vital

import android.R.attr
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class ActividadCalorias : AppCompatActivity() {

    private lateinit var cantidades: ArrayList<Int>
    private lateinit var semana: ArrayList<String>
    private lateinit var colores: ArrayList<Color>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_calorias)



    }
}