package android.study.imt_covid.data.dtbAndDAO

import android.study.imt_covid.data.dataClass.entity.WORLD_SUM_ID
import android.study.imt_covid.data.dataClass.entity.WorldSummary
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WorldSummaryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert (WorldSummary: WorldSummary)
    @Query(value = "select * from world_summary where id= $WORLD_SUM_ID")
    fun getWorldSummaryData(): LiveData<WorldSummary>
}