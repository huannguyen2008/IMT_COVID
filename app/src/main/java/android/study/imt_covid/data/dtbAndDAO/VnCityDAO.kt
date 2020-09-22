package android.study.imt_covid.data.dtbAndDAO

import android.study.imt_covid.data.dataClass.VN_CITY_ID
import android.study.imt_covid.data.dataClass.VN_SUM_ID
import android.study.imt_covid.data.dataClass.VnCity
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VnCityDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert (VnCity: List<VnCity>)
    @Query(value = "select * from vn_city where id= $VN_CITY_ID")
    fun getVnCity(): LiveData<VnCity>
}