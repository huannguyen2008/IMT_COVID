package android.study.imt_covid.data.dataClass.entity

import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnCityInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter


@Entity(tableName = "vn_city")
data class VnCity(
    @PrimaryKey(autoGenerate = false)
    override val city: String,
    override val totalCity: Int,
    override val activeCity: Int,
    override val recoveredCity: Int,
    override val deathCity: Int,
    override val diffCity: Int
) : UnitSpecifyVnCityInfo{

}

// convert json file List<List<Any>>
class VnCityTypeConverter : TypeAdapter<VnCity>() {
    override fun write(out: JsonWriter?, value: VnCity?) {

    }

    override fun read(reader: JsonReader?): VnCity? {
        reader?.beginArray()
        val city: String? = reader?.nextString()
        val totalCity: Int? = reader?.nextInt()
        val activeCity: Int? = reader?.nextInt()
        val recoveredCity: Int? = reader?.nextInt()
        val deathCity: Int? = reader?.nextInt()
        val diffCity: Int? = reader?.nextInt()
        reader?.endArray()
        if (city == null || totalCity == null || activeCity == null ||
            recoveredCity == null || deathCity == null || diffCity == null
        ) {
            return null
        }
        return VnCity(city, totalCity, activeCity, recoveredCity, deathCity, diffCity)
    }
}