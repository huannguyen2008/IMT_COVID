package android.study.imt_covid.data.dataClass.entity

import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyCountrySummaryInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
@Entity(tableName = "country_summary")
data class CountrySummary(
    @PrimaryKey(autoGenerate = false)
    override val countryRegion: String,
    override val total: Int,
    override val death: Int,
    override val active: Int,
    override val recovered: Int,
    override val newCases: Int,
    override val newDeath: Int
): UnitSpecifyCountrySummaryInfo

// convert json file List<List<Any>>
class CountrySummaryTypeConverter : TypeAdapter<CountrySummary>() {
    override fun write(out: JsonWriter?, value: CountrySummary?) {

    }

    override fun read(reader: JsonReader?): CountrySummary? {
        reader?.beginArray()
        val countryRegion: String? = reader?.nextString()
        val total: Int? = reader?.nextInt()
        val death: Int? = reader?.nextInt()
        val active: Int? = reader?.nextInt()
        val recovered: Int? = reader?.nextInt()
        val newCases: Int? = reader?.nextInt()
        val newDeath: Int? = reader?.nextInt()
        reader?.endArray()
        if (countryRegion == null || total == null || death == null ||
            active == null || recovered == null || newCases == null || newDeath == null
        ) {
            return null
        }
        return CountrySummary(countryRegion, total, death, active, recovered, newCases, newDeath)
    }
}