package android.study.imt_covid.data.dtbAndDAO

import android.study.imt_covid.data.dataClass.entity.VN_GENDER_ID
import android.study.imt_covid.data.dataClass.entity.VnGender
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VnGenderDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert (VnGender: VnGender)
    @Query(value = "select * from gender where id= $VN_GENDER_ID")
    fun getVnGenderData(): LiveData<VnGender>
}