package android.study.imt_covid.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.study.imt_covid.R
import android.study.imt_covid.fragments.HealthFragment
import android.study.imt_covid.fragments.HomeFragment
import android.study.imt_covid.fragments.MenuFragment
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFrag = HomeFragment()
        val healthFrag = HealthFragment()
        val menuFrag = MenuFragment()

        makeCurrentFrag(homeFrag)

        bot_nav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> makeCurrentFrag(homeFrag)
                R.id.ic_health -> makeCurrentFrag(healthFrag)
                R.id.ic_menu -> makeCurrentFrag(menuFrag)
            }
            true
        }
    }

    private fun makeCurrentFrag(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

}