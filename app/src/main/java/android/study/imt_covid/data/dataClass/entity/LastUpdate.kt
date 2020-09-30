package android.study.imt_covid.data.dataClass.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

const val LAST_UPDATE_ID = 0

@Entity(tableName = "last_update")
data class LastUpdate(
    val lastUpdate: String
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = LAST_UPDATE_ID
}