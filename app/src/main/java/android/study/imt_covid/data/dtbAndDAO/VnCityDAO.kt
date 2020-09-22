package android.study.imt_covid.data.dtbAndDAO

import android.study.imt_covid.data.dataClass.entity.VnCity
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VnCityDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert (VnCity: List<VnCity>)
    @Query(value = "select * from vn_city")
    fun getVnDataCity(): LiveData<List<VnCity>>
}