package android.study.imt_covid.data

import android.study.imt_covid.data.dataClass.CASE_INFO_ID
import android.study.imt_covid.data.dataClass.CaseInfo
import android.study.imt_covid.data.unitlocalized.UnitSpecificCasesInfo
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CasesInfoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(caseInfo: List<CaseInfo>)
    @Query(value = "select * from case_info where id= $CASE_INFO_ID")
    fun getCasesInfo(): LiveData<UnitSpecificCasesInfo>
}