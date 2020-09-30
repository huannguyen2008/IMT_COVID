package android.study.imt_covid.ui.fragments

import android.study.imt_covid.R
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyCountrySummaryInfo
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.world_country_item.*

class WorldCountryItem(
    val CountrySummary: UnitSpecifyCountrySummaryInfo
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            updateCountryTable()
        }
    }

    override fun getLayout() = R.layout.world_country_item
    private fun ViewHolder.updateCountryTable() {
        country_name.text = CountrySummary.countryRegion
        total_of_country.text = CountrySummary.total.toString()
        recovered_of_country.text = CountrySummary.recovered.toString()
        active_of_country.text = CountrySummary.active.toString()
        death_of_country.text = CountrySummary.death.toString()
    }
}