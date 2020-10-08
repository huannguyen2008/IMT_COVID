package android.study.imt_covid.data.dataClass.entity

import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifySummaryInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

const val VN_SUM_ID = 0
@Entity(tableName = "vn_summary")
data class VnSummary(
    override val diff: Int,
    override val recover: Int,
    override val totalDeath: Int,
    override val active: Int,
    override val total: Int,
    override val newCases: Int,
    override val newDeath: Int
): UnitSpecifySummaryInfo {
    @PrimaryKey(autoGenerate = false)
    var id: Int = VN_SUM_ID
}

// convert json file <list<any>>
class VnSummaryTypeConverter : TypeAdapter<VnSummary>() {
    override fun read(reader: JsonReader?): VnSummary? {
        reader?.beginArray()
        val diff: Int? = reader?.nextInt()
        val recover: Int? = reader?.nextInt()
        val totalDeath: Int? = reader?.nextInt()
        val active: Int? = reader?.nextInt()
        val total: Int? = reader?.nextInt()
        val newCases: Int? = reader?.nextInt()
        val newDeath: Int? = reader?.nextInt()
        reader?.endArray()
        if (diff == null || recover == null || totalDeath == null ||
            active == null || total == null || newCases == null || newDeath == null
        ) {
            return null
        }
        return VnSummary(diff, recover, totalDeath, active, total, newCases, newDeath)
    }
    override fun write(out: JsonWriter?, value: VnSummary?) {

    }
}