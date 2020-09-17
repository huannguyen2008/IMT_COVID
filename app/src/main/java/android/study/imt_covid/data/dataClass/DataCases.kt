package android.study.imt_covid.data.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

const val CASE_INFO_ID = 0
@Entity(tableName = "case_info")
data class CaseInfo(val date: String, val total: Int, val active: Int)
{
    @PrimaryKey(autoGenerate = false)
    var id: Int = CASE_INFO_ID
}

// convert json file <list<list<any>>>
class CaseInfoTypeConverter: TypeAdapter<CaseInfo>() {
    override fun write(out: JsonWriter?, value: CaseInfo?) {

    }

    override fun read(reader: JsonReader?): CaseInfo? {
        reader?.beginArray()
        val date = reader?.nextString()
        val total = reader?.nextInt()
        val active = reader?.nextInt()
        reader?.endArray()
        if (date == null || total == null || active == null) {
            return null
        }
        return CaseInfo(date, total, active)
    }
}