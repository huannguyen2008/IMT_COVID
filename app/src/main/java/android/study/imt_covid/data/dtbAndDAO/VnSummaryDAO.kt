package android.study.imt_covid.data.dtbAndDAO

import android.study.imt_covid.data.dataClass.VN_SUM_ID
import android.study.imt_covid.data.dataClass.VnSummary
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface VnSummaryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert (VnSummary: VnSummary)
    @Query(value = "select * from vn_summary where id= $VN_SUM_ID")
    fun getVnSummary(): LiveData<VnSummary>
}
