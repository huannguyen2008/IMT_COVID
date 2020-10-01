package android.study.imt_covid.ui.item

import android.study.imt_covid.R
import android.study.imt_covid.data.dataClass.entity.VnCity
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.vn_city_item.*

class VnCityItem(
    val VnCity: VnCity
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            updateVnCityTable()
        }
    }

    override fun getLayout() = R.layout.vn_city_item
    private fun ViewHolder.updateVnCityTable(){
        city_name.text = VnCity.city
        total_of_city.text = VnCity.totalCity.toString()
        recovered_of_city.text = VnCity.recoveredCity.toString()
        active_of_city.text = VnCity.activeCity.toString()
        death_of_city.text = VnCity.deathCity.toString()
    }
}