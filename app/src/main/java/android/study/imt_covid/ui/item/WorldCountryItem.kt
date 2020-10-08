package android.study.imt_covid.ui.item

import android.study.imt_covid.R
import android.study.imt_covid.data.dataClass.entity.CountrySummary
import android.study.imt_covid.internal.formatNumber
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.world_country_item.*

class WorldCountryItem(
    val CountrySummary: CountrySummary
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            updateCountryTable()
        }
    }

    override fun getLayout() = R.layout.world_country_item
    private fun ViewHolder.updateCountryTable() {

        country_name.text = CountrySummary.countryRegion
        total_of_country.text = formatNumber(CountrySummary.total.toFloat()).toString()
        recovered_of_country.text = formatNumber(CountrySummary.recovered.toFloat()).toString()
        active_of_country.text = formatNumber(CountrySummary.active.toFloat()).toString()
        death_of_country.text = formatNumber(CountrySummary.death.toFloat()).toString()
    }
}