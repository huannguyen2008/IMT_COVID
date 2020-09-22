package android.study.imt_covid.data.network.networkSource

import android.study.imt_covid.data.dataClass.VnCity
import android.study.imt_covid.data.dataClass.VnCityResponse
import android.study.imt_covid.data.dataClass.VnSummary
import android.study.imt_covid.data.dataClass.VnSummaryResponse
import androidx.lifecycle.LiveData

interface DataSource {
    val downloadedVnSummary: LiveData<VnSummaryResponse>
    val downloadedVnCity: LiveData<VnCityResponse>

    suspend fun fetchVnSummary(
        VnSummary: VnSummary
    )
    suspend fun fetchVnCity(
        VnCity: List<VnCity>
    )
}