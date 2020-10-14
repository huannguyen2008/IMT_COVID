package android.study.imt_covid.ui.activities

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.preference.PreferenceManager
import android.study.imt_covid.R
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_menu.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var mCurrentLocale: Locale

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLocale()
        setContentView(R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bot_nav.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        setLocale()
    }

    override fun onRestart() {
        super.onRestart()
        val locale: Locale = getLocale(this)
        if (locale != mCurrentLocale) {
            mCurrentLocale = locale
            recreate()
        }
    }

    private fun getLocale(context: Context?): Locale {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        var lang = sharedPreferences.getString("language", "en")
        when (lang) {
            "English" -> lang = "en"
            "Vietnam" -> lang = "vi"
        }
        return Locale(lang!!)
    }
    private fun setLocale() {
        val resources: Resources = resources
        val configuration: Configuration = resources.configuration
        var lang = PreferenceManager.getDefaultSharedPreferences(this).getString("language", "en")
        val locale = Locale(lang!!)
        if (configuration.locale != locale) {
            configuration.setLocale(locale)
            resources.updateConfiguration(configuration, null)
        }
    }
}


