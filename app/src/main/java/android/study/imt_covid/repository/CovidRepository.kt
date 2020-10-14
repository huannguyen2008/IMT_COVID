package android.study.imt_covid.repository

import android.study.imt_covid.data.dataClass.entity.*
import androidx.lifecycle.LiveData

interface CovidRepository {
    suspend fun getVnSummary(VnSummary: VnSummary): LiveData<out VnSummary>
    suspend fun getVnCity(VnCity: List<VnCity>): LiveData<out List<VnCity>>
    suspend fun getVnNationality(VnNationality: List<VnNationality>): LiveData<out List<VnNationality>>
    suspend fun getVnGender(VnGender: VnGender): LiveData<out VnGender>
    suspend fun getVnAge(VnAge: List<VnAge>): LiveData<out List<VnAge>>
    suspend fun getWorldSummary(WorldSummary: WorldSummary): LiveData<out WorldSummary>
    suspend fun getLastUpdate(LastUpdate: LastUpdate): LiveData<out LastUpdate>
    suspend fun getCountrySummary(CountrySummary: List<CountrySummary>): LiveData<out List<CountrySummary>>
    suspend fun initData()
}