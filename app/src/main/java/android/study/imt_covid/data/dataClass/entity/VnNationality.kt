package android.study.imt_covid.data.dataClass.entity

import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnNationalityInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter


@Entity(tableName = "nationality")
data class VnNationality(
    @PrimaryKey(autoGenerate = false)
    override val nationality: String,
    override val numberCases: Int
): UnitSpecifyVnNationalityInfo

// convert json file List<List<Any>>
class VnNationalityTypeConverter : TypeAdapter<VnNationality>() {
    override fun write(out: JsonWriter?, value: VnNationality?) {

    }

    override fun read(reader: JsonReader?): VnNationality? {
        reader?.beginArray()
        val nationality: String? = reader?.nextString()
        val numberCases: Int? = reader?.nextInt()

        reader?.endArray()
        if (nationality == null || numberCases == null
        ) {
            return null
        }
        return VnNationality(nationality, numberCases)
    }
}