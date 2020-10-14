package android.study.imt_covid.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import android.preference.PreferenceManager
import android.study.imt_covid.R
import android.study.imt_covid.ui.activities.MainActivity
import android.study.imt_covid.ui.viewmodel.MenuViewModel
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_menu.*
import java.util.*


class MenuFragment : Fragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    private lateinit var viewModel: MenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        about_us.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.aboutUsFragment, null)
        )

        change_lang.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                showChangeLanguageDialog()
            }
            false
        }
    }

    private fun showChangeLanguageDialog() {
        val bd: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(requireContext())
        bd.setTitle(R.string.choose_lang)
        bd.setSingleChoiceItems(R.array.change_lang, -1) { dialog, which ->
            if (which == 0) {
                PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putString(
                    "language",
                    "en"
                ).apply()
            } else if (which == 1) {
                PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putString(
                    "language",
                    "vi"
                ).apply()
            }
            restartApp()
            dialog.dismiss()
        }
        val md: android.app.AlertDialog? = bd.create()
        md?.show()
    }
    private fun restartApp() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        activity?.startActivity(intent)
        activity?.finishAffinity()
    }
}


