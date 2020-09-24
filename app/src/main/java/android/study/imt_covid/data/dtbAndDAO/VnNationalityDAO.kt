package android.study.imt_covid.data.dtbAndDAO

import android.study.imt_covid.data.dataClass.entity.VnNationality
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VnNationalityDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert (VnNationality: List<VnNationality>)
    @Query(value = "select * from nationality")
    fun getVnNationalityData(): LiveData<List<VnNationality>>
}