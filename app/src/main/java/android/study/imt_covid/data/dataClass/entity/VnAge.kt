package android.study.imt_covid.data.dataClass.entity

import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnAgeInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

@Entity(tableName = "age")
data class VnAge(
    @PrimaryKey(autoGenerate = false)
    override val patient: String,
    override val age: Int
): UnitSpecifyVnAgeInfo

// convert json file List<List<Any>>
class VnAgeTypeConverter : TypeAdapter<VnAge>() {
    override fun write(out: JsonWriter?, value: VnAge?) {

    }

    override fun read(reader: JsonReader?): VnAge? {
        reader?.beginArray()
        val patient: String? = reader?.nextString()
        val age: Int? = reader?.nextInt()

        reader?.endArray()
        if (patient == null || age == null
        ) {
            return null
        }
        return VnAge(patient, age)
    }
}