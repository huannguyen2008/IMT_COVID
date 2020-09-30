package android.study.imt_covid.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.study.imt_covid.R
import android.study.imt_covid.data.dataClass.entity.CountrySummary
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyCountrySummaryInfo
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnCityInfo
import android.study.imt_covid.ui.base.ScopedFragment
import android.study.imt_covid.viewmodel.HomeViewModel
import android.study.imt_covid.ui.viewmodel.factory.HomeViewModelFactory
import android.widget.AdapterView
import android.widget.ArrayAdapter
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
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter


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

        // move to chart fragment
        detail_button.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.chartFragment,
                null
            )
        )
        bindLastUpdate()
        setupSpinner()
    }

    private fun setupSpinner(){
        // spinner title
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.summary_title,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            summary_title_spinner.adapter = adapter
        }
        summary_title_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (summary_title_spinner.selectedItemPosition == 0) {
                    bindUIVn()
                    city_or_country.text = getString(R.string.city_provinces)
                } else {
                    bindUIWorld()
                    city_or_country.text = getString(R.string.country_region)
                    note_vietnam.text = null
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
    private fun bindUIVn() = launch {
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
            initRecycleViewCityVn(it.toVnCityItem())
        })

    }

    private fun bindUIWorld() = launch {
        val vnWorld = viewModel.worldSummary.await()
        vnWorld.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            updateTotal(it.total, it.newCases)
            updateActive(it.active)
            updateRecovered(it.recover)
            updateDeath(it.totalDeath, it.newDeath)
        })
        val countrySum = viewModel.countrySummary.await()
        countrySum.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            initRecycleViewCountry(it.toCountryItem())
        })
    }

    private fun bindLastUpdate() = launch {
        val lastUpdate = viewModel.lastUpdate.await()
        lastUpdate.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            val date = LocalDateTime.parse(it.lastUpdate)
                .format(DateTimeFormatter.ofPattern("EEE, dd MMM, yyyy, HH:mm:ss"))
            time_update.text = getString(R.string.last_update, date)
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

    // parse vn city to recycle view
    private fun List<UnitSpecifyVnCityInfo>.toVnCityItem(): List<VnCityItem> {
        return this.map {
            VnCityItem(it)
        }
    }

    private fun initRecycleViewCityVn(item: List<VnCityItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(item)
        }
        recycle_view.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            adapter = groupAdapter
        }
    }
    // parse world country to recycle view
    private fun List<UnitSpecifyCountrySummaryInfo>.toCountryItem(): List<WorldCountryItem> {
        return this.map {
            WorldCountryItem(it)
        }
    }

    private fun initRecycleViewCountry(item: List<WorldCountryItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(item)
        }
        recycle_view.apply {
            layoutManager = LinearLayoutManager(this@HomeFragment.context)
            adapter = groupAdapter
        }
    }
}
