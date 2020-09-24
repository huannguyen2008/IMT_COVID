package android.study.imt_covid.data.dataClass.entity

import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnGenderInfo
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnSummaryInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
const val VN_GENDER_ID = 0

@Entity(tableName = "gender")
data class VnGender(
    override val male: Int,
    override val female: Int,

): UnitSpecifyVnGenderInfo {
    @PrimaryKey(autoGenerate = false)
    var id: Int = VN_GENDER_ID
}

// convert json file <list<any>>
class VnGenderTypeConverter : TypeAdapter<VnGender>() {
    override fun write(out: JsonWriter?, value: VnGender?) {

    }

    override fun read(reader: JsonReader?): VnGender? {
        reader?.beginArray()
        val male: Int? = reader?.nextInt()
        val female: Int? = reader?.nextInt()

        reader?.endArray()
        if (male == null || female == null
        ) {
            return null
        }
        return VnGender(male, female)
    }
}