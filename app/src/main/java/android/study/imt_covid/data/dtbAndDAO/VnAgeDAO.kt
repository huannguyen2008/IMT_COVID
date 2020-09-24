package android.study.imt_covid.data.dtbAndDAO

import android.study.imt_covid.data.dataClass.entity.VnAge
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VnAgeDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert (VnAge: List<VnAge>)
    @Query(value = "select * from age")
    fun getVnAgeData(): LiveData<List<VnAge>>
}