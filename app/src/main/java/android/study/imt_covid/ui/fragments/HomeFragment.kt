package android.study.imt_covid.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.study.imt_covid.R
import android.study.imt_covid.activities.ChartActivity
import android.study.imt_covid.data.dataClass.entity.VnSummary
import android.study.imt_covid.ui.base.ScopedFragment
import android.study.imt_covid.viewmodel.HomeViewModel
import android.study.imt_covid.ui.viewmodel.HomeViewModelFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HomeFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        view.test_button.setOnClickListener {
            val intent = Intent(activity, ChartActivity::class.java)
            activity?.startActivity(intent)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory: HomeViewModelFactory by instance()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch {
        val vnSum = viewModel.vnSummary.await()

        vnSum.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            updateTotal(it.total)
            updateActive(it.active)
            updateRecovered(it.recover)
            updateDeath(it.totalDeath)
        })
        val vnCity = viewModel.vnCity.await()
        vnCity.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            test_text.text = it.toString()
        })
    }
    private fun updateTotal(total: Int){
        total_cases_number.text = total.toString()
    }
    private fun updateActive(active: Int){
        active_cases_number.text = active.toString()
    }
    private fun updateRecovered(recovered: Int){
        recovered_cases_number.text = recovered.toString()
    }
    private fun updateDeath(death: Int){
        death_cases_number.text = death.toString()
    }
}
