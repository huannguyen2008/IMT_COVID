package android.study.imt_covid.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.study.imt_covid.R
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnCityInfo
import android.study.imt_covid.ui.base.ScopedFragment
import android.study.imt_covid.viewmodel.HomeViewModel
import android.study.imt_covid.ui.viewmodel.factory.HomeViewModelFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_home.*
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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModelFactory: HomeViewModelFactory by instance()
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)
        bindUI()
        detail_button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.chartFragment, null))

    }

    private fun bindUI() = launch {
        val vnSum = viewModel.vnSummary.await()
        vnSum.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            updateTotal(it.total, it.newCases)
            updateActive(it.active)
            updateRecovered(it.recover)
            updateDeath(it.totalDeath, it.newDeath)
        })
        val vnCity = viewModel.vnCity.await()
        vnCity.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            initRecycleView(it.toVnCityItem())
        })
        val vnWorld = viewModel.worldSummary.await()
        vnWorld.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            note_vietnam.text = it.toString()
        })
    }

    private fun updateTotal(total: Int, newCases: Int) {
        total_cases_number.text = ("$total + $newCases↑")
    }

    private fun updateActive(active: Int) {
        active_cases_number.text = active.toString()
    }

    private fun updateRecovered(recovered: Int) {
        recovered_cases_number.text = recovered.toString()
    }

    private fun updateDeath(death: Int, newDeath: Int) {
        death_cases_number.text = ("$death + $newDeath↑")
    }


    private fun List<UnitSpecifyVnCityInfo>.toVnCityItem(): List<VnCityItem> {
        return this.map {
            VnCityItem(it)
        }
    }

    private fun initRecycleView(item: List<VnCityItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(item)
        }
        recycleView.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            adapter = groupAdapter
        }
    }
}
