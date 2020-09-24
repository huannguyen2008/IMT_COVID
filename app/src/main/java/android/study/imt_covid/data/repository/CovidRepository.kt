package android.study.imt_covid.data.repository

import android.study.imt_covid.data.dataClass.entity.*
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnCityInfo
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnGenderInfo
import android.study.imt_covid.data.dataClass.unitlocalized.UnitSpecifyVnSummaryInfo
import androidx.lifecycle.LiveData

interface CovidRepository {
    suspend fun getVnSummary(VnSummary: VnSummary): LiveData<out VnSummary>
    suspend fun getVnCity(VnCity: List<VnCity>): LiveData<out List<VnCity>>
    suspend fun getVnNationality(VnNationality: List<VnNationality>): LiveData<out List<VnNationality>>
    suspend fun getVnGender(VnGender: VnGender): LiveData<out VnGender>
    suspend fun getVnAge(VnAge: List<VnAge>): LiveData<out List<VnAge>>

}