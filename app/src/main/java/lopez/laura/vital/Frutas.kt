package lopez.laura.vital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_wrapper.*

class Frutas : AppCompatActivity() {

    val wp: Wrapper = Wrapper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frutas)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            val inflater = menuInflater
            inflater.inflate(R.menu.nav_menu, menu)
            return true
            //return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return true
        /*return when (item.itemId) {
            R.id.btn_frutas -> {
                // Action goes here
                bottom_navegation.display
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        */
    }

}