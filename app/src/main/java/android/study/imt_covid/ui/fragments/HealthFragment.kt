package android.study.imt_covid.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.study.imt_covid.R
import android.study.imt_covid.ui.base.ScopedFragment
import android.study.imt_covid.ui.viewmodel.HealthViewModel
import android.study.imt_covid.ui.viewmodel.HealthViewModelFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_health.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HealthFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private lateinit var viewModel: HealthViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_health, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory: HealthViewModelFactory by instance()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HealthViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        val vnNation = viewModel.vnNation.await()
        vnNation.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            text_test.text = it.toString()
        })

        val vnGender = viewModel.vnGender.await()
        vnGender.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            text_test2.text = it.toString()
        })
        val vnAge = viewModel.vnAge.await()
        vnAge.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            text_test3.text = it.toString()
        })
    }
}