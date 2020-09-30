package android.study.imt_covid.data.network.networkSource

import android.study.imt_covid.data.dataClass.entity.*
import android.study.imt_covid.data.dataClass.response.*
import androidx.lifecycle.LiveData

interface DataSource {
    val downloadedVnSummary: LiveData<VnSummaryResponse>
    val downloadedWorldSummary: LiveData<WorldSummaryResponse>
    val downloadedVnCity: LiveData<VnCityResponse>
    val downloadedVnNationality: LiveData<VnNationalityResponse>
    val downloadedVnGender: LiveData<VnGenderResponse>
    val downloadedVnAge: LiveData<VnAgeResponse>
    val downloadedLastUpdate: LiveData<LastUpdateResponse>
    val downloadedCountrySummary: LiveData<CountrySummaryResponse>

    suspend fun fetchVnSummary(
        VnSummary: VnSummary
    )
    suspend fun fetchWorldSummary(
        WorldSummary: WorldSummary
    )
    suspend fun fetchVnCity(
        VnCity: List<VnCity>
    )

    suspend fun fetchVnNationality(
        VnNationality: List<VnNationality>
    )

    suspend fun fetchVnGender(
        VnGender: VnGender
    )
    suspend fun fetchVnAge(
        VnAge: List<VnAge>
    )
    suspend fun fetchLastUpdate(
        LastUpdate: LastUpdate
    )
    suspend fun fetchCountrySummary(
        CountrySummary: List<CountrySummary>
    )
}