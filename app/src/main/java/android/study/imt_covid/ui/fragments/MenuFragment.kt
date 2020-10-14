package android.study.imt_covid.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.study.imt_covid.R
import android.study.imt_covid.ui.viewmodel.MenuViewModel
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat.recreate
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

    companion object {
        fun newInstance() = MenuFragment()
    }

    private lateinit var viewModel: MenuViewModel

//    private lateinit var sharepref: SharePref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuViewModel::class.java)
        about_us.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.aboutUsFragment, null)
        )
//        sharepref = SharePref(requireContext())
//        sharepref.getLang()
        setupSpinner()
    }

    private fun setupSpinner() {
        // spinner title
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.change_lang,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            change_lang_spinner.adapter = adapter
        }
        change_lang_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (change_lang_spinner.selectedItemPosition == 0) {
//                    sharepref.setLang("en")
                }
                if (change_lang_spinner.selectedItemPosition == 1) {
//                    sharepref.setLang("vi")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

}