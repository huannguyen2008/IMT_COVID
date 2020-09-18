package android.study.imt_covid.data.repository

import android.study.imt_covid.data.dataClass.CaseInfo
import android.study.imt_covid.data.unitlocalized.UnitSpecificCasesInfo
import androidx.lifecycle.LiveData

interface CasesRepository {
    suspend fun getTotalAndActive(cases: List<CaseInfo>): LiveData<out UnitSpecificCasesInfo>
}