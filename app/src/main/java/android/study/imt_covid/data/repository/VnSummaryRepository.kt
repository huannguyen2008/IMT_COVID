package android.study.imt_covid.data.repository

import android.study.imt_covid.data.dataClass.VnSummary
import android.study.imt_covid.data.unitlocalized.UnitSpecifyVnSummaryInfo
import androidx.lifecycle.LiveData

interface VnSummaryRepository {
    suspend fun getVnSummary(VnSummary: VnSummary): LiveData<out UnitSpecifyVnSummaryInfo>

}