package android.study.imt_covid.data.dtbAndDAO

import android.study.imt_covid.data.dataClass.entity.VN_SUM_ID
import android.study.imt_covid.data.dataClass.entity.VnSummary
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnSummaryInfo
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
    fun getVnDataSummary(): LiveData<VnSummary>
}
