package android.study.imt_covid.ui.fragments

import android.os.Bundle
import android.study.imt_covid.R
import android.study.imt_covid.data.dataClass.entity.CountrySummary
import android.study.imt_covid.data.dataClass.entity.VnCity
import android.study.imt_covid.ui.base.ScopedFragment
import android.study.imt_covid.ui.item.VnCityItem
import android.study.imt_covid.ui.item.WorldCountryItem
import android.study.imt_covid.ui.viewmodel.factory.HomeViewModelFactory
import android.study.imt_covid.viewmodel.HomeViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import java.text.DecimalFormat


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
        see_more.setOnClickListener(
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
                    note_vietnam.text = getString(R.string.note_vn)
                    cases_table_title.text = getString(R.string.cases_city_title)
                } else {
                    bindUIWorld()
                    city_or_country.text = getString(R.string.country_region)
                    note_vietnam.text = null
                    cases_table_title.text = getString(R.string.cases_country_title)
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
            total_cases_number.text = it.total.toString()
            active_cases_number.text = it.active.toString()
            recovered_cases_number.text = it.recover.toString()
            death_cases_number.text = it.totalDeath.toString()

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
        total_cases_number.text = ("${formatValue(total.toFloat())} + ${formatValue(newCases.toFloat())}↑")
    }

    private fun updateActive(active: Int) {
        active_cases_number.text = formatValue(active.toFloat()).toString()
    }

    private fun updateRecovered(recovered: Int) {
        recovered_cases_number.text = formatValue(recovered.toFloat()).toString()
    }

    private fun updateDeath(death: Int, newDeath: Int) {
        death_cases_number.text = ("${formatValue(death.toFloat())} + ${formatValue(newDeath.toFloat())}↑")
    }

    // parse vn city to recycle view
    private fun List<VnCity>.toVnCityItem(): List<VnCityItem> {
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
    private fun List<CountrySummary>.toCountryItem(): List<WorldCountryItem> {
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
    private fun formatValue(value: Float): String? {
        var value = value
        val arr = arrayOf("", "K", "M", "B", "T", "P", "E")
        var index = 0
        while (value / 1000 >= 1) {
            value /= 1000
            index++
        }
        val decimalFormat = DecimalFormat("#.##")
        return java.lang.String.format("%s %s", decimalFormat.format(value), arr[index])
    }
}
