package android.study.imt_covid.data.dataClass.entity

import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifySummaryInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

const val WORLD_SUM_ID = 0

@Entity(tableName = "world_summary")
data class WorldSummary(
    override val diff: Int,
    override val recover: Int,
    override val totalDeath: Int,
    override val active: Int,
    override val total: Int,
    override val newCases: Int,
    override val newDeath: Int
): UnitSpecifySummaryInfo {
    @PrimaryKey(autoGenerate = false)
    var id: Int = WORLD_SUM_ID
}

// convert json file <list<any>>
class WorldSummaryTypeConverter : TypeAdapter<WorldSummary>() {
    override fun write(out: JsonWriter?, value: WorldSummary?) {

    }

    override fun read(reader: JsonReader?): WorldSummary? {
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
        return WorldSummary(diff, recover, totalDeath, active, total, newCases, newDeath)
    }
}