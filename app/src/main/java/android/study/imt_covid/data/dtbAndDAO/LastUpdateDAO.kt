package android.study.imt_covid.data.dtbAndDAO

import android.study.imt_covid.data.dataClass.entity.LastUpdate
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LastUpdateDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert (LastUpdate: LastUpdate)
    @Query(value = "select * from last_update")
    fun getLastUpdateData(): LiveData<LastUpdate>
}