package android.study.imt_covid.ui.item

import android.study.imt_covid.R
import android.study.imt_covid.data.dataClass.entity.CountrySummary
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.world_country_item.*
import java.text.DecimalFormat

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
        total_of_country.text = formatValue(CountrySummary.total.toFloat()).toString()
        recovered_of_country.text = formatValue(CountrySummary.recovered.toFloat()).toString()
        active_of_country.text = formatValue(CountrySummary.active.toFloat()).toString()
        death_of_country.text = formatValue(CountrySummary.death.toFloat()).toString()
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