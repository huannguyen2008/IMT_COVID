package android.study.imt_covid.data.repository

import android.study.imt_covid.data.dataClass.entity.VnCity
import android.study.imt_covid.data.dataClass.entity.VnSummary
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnCityInfo
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnSummaryInfo
import androidx.lifecycle.LiveData

interface CovidRepository {
    suspend fun getVnSummary(VnSummary: VnSummary): LiveData<out UnitSpecifyVnSummaryInfo>
    suspend fun getVnCity(VnCity: List<VnCity>): LiveData<out List<VnCity>>

}